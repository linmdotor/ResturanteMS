package presentacion.ventanas.factura;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import negocio.factura.TFacturaPlato;
import negocio.plato.TPlato;
import negocio.plato.TPlatoBebida;
import negocio.plato.TPlatoComida;
import negocio.plato.TipoPlato;
import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;
import presentacion.ventanas.MiModeloTabla;

@SuppressWarnings("serial")
public class VentanaFacturaPlato extends JFrame{

	private static VentanaFacturaPlato ventana; //instancia singleton
	
	private JTextField textFieldID_Factura;
	private JTextField textFieldTotal;

	private MiModeloTabla modeloplatos;
	private MiModeloTabla modelofactura;
	private JTable tbPlatos;
	private JTable tbFactura;
	private Vector fila;

	// Mutadores y Accedentes

	public JTable getTbPlatos() {
		return tbPlatos;
	}

	public void setTbPlatos(JTable tbPlatos) {
		this.tbPlatos = tbPlatos;
	}
	
	public JTable getTbFactura() {
		return tbFactura;
	}

	public void setTbFactura(JTable tbFactura) {
		this.tbFactura = tbFactura;
	}

	//GetInstance
	public static VentanaFacturaPlato obtenerInstancia() {

		if (ventana == null)
			ventana = new VentanaFacturaPlato();

		return ventana;
	}
		
	// Constructor
	public VentanaFacturaPlato() {

		setTitle("Nueva Factura");
		setResizable(false);
		setVisible(false);
		setSize(1100, 360);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBounds(874, 5, 215, 350);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);
		
		JLabel lblId = new JLabel("ID Factura:");
		lblId.setBounds(25, 46, 60, 16);
		panelFormulario.add(lblId);
		
		textFieldID_Factura = new JTextField();
		textFieldID_Factura.setBounds(90, 44, 40, 20);
		textFieldID_Factura.setEditable(false);
		panelFormulario.add(textFieldID_Factura);
		textFieldID_Factura.setColumns(10);
		
		JButton btnAadirPlato= new JButton("Anadir Plato");
		btnAadirPlato.setBounds(9, 110, 192, 26);
		panelFormulario.add(btnAadirPlato);
		btnAadirPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (getTbPlatos().getSelectedRow() != -1 )
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ANADIR_PLATO_A_FACTURA, obtenerPlato(getTbPlatos(), getTbPlatos().getSelectedRow()));
			}
		});
		
		JButton btnQuitarPlato= new JButton("Quitar Plato");
		btnQuitarPlato.setBounds(9, 140, 192, 26);
		panelFormulario.add(btnQuitarPlato);
		btnQuitarPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (getTbFactura().getSelectedRow() != -1 )
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.QUITAR_PLATO_DE_FACTURA, obtenerPlato(getTbFactura(), getTbFactura().getSelectedRow()));

			}
		});
		
		JButton btnBorrarFormulario = new JButton("Limpiar Factura");
		btnBorrarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiarFormulario(true);
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERFACTURAPLATOS, null);

			}
		});
		
		btnBorrarFormulario.setFont(new Font("Dialog", Font.BOLD, 9));
		btnBorrarFormulario.setBounds(83, 231, 118, 22);
		panelFormulario.add(btnBorrarFormulario);
		
		JButton btnAadirFactura= new JButton("Finalizar Factura");
		btnAadirFactura.setBounds(9, 276, 192, 26);
		panelFormulario.add(btnAadirFactura);
		btnAadirFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(mensajeConfirmacionSiNo("�Realmente desea finalizar esta factura?", "Confirmar finalizar Factura"))
				{
					ArrayList<TFacturaPlato> lista = obtenerListaFacturaPlato();
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ANADIR_FACTURA_PLATO, lista);
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.IMPRIMIR_FACTURA, lista);
				}
			}
		});
		
		JPanel panelLista = new JPanel();
		panelLista.setBounds(10, 5, 864, 320);
		getContentPane().add(panelLista);
		panelLista.setLayout(null);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setBounds(600, 282, 75, 16);
		panelLista.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setText("0");
		textFieldTotal.setBounds(650, 280, 116, 20);
		textFieldTotal.setEditable(false);
		panelLista.add(textFieldTotal);
		textFieldTotal.setColumns(10);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(0, 12, 500, 291);
		panelLista.add(scrollPane1);

		tbPlatos = new JTable();
		scrollPane1.setViewportView(tbPlatos);
		tbPlatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(505, 12, 360, 261);
		panelLista.add(scrollPane2);
		
		tbFactura = new JTable();
		scrollPane2.setViewportView(tbFactura);
		tbFactura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		inicializarTablafactura();
		
	}

	// Metodos
	
	ArrayList<TFacturaPlato> obtenerListaFacturaPlato()
	{
		ArrayList<TFacturaPlato> lista = new ArrayList();
		int n_filas = modelofactura.getRowCount();
		
		for(int i=0; i<n_filas; i++)
		{
			TFacturaPlato t_aux = new TFacturaPlato();
			t_aux.setID_Factura(Integer.parseInt(textFieldID_Factura.getText()));
			t_aux.setID_Plato((int)tbFactura.getValueAt(i, 0));
			t_aux.setPrecio((float)tbFactura.getValueAt(i, 2));
			t_aux.setCantidad((int)tbFactura.getValueAt(i, 3));
			lista.add(t_aux);
		}		
		return lista;
	}

	private void inicializarTablafactura() {
		
		modelofactura = new MiModeloTabla();

		modelofactura.addColumn("ID");
		modelofactura.addColumn("NOMBRE");
		modelofactura.addColumn("PRECIO");
		modelofactura.addColumn("CANTIDAD");
		modelofactura.addColumn("TOTAL");
		
		tbFactura.setModel(modelofactura);
	}

	public void actualizar(Object object) {

		if (object == null)
			rellenarTabla(new ArrayList<TPlato>());
		else			
			rellenarTabla((ArrayList<TPlato>) object);

		setVisible(true);
		repaint();

	}

	public void rellenarTabla(ArrayList<TPlato> lista) {

		
		modeloplatos = new MiModeloTabla();

		modeloplatos.addColumn("ID");
		modeloplatos.addColumn("NOMBRE");
		modeloplatos.addColumn("PRECIO");
		modeloplatos.addColumn("STOCK");
		modeloplatos.addColumn("TIPO PLATO");
		modeloplatos.addColumn("TIPO BEBIDA");

		for (int i = 0; i < lista.size(); i++) {

			fila = new Vector();
		    TPlato t = lista.get(i);
		    
			fila.add(t.getID());
			fila.add(t.getNombre());
			fila.add(t.getPrecio());
			fila.add(t.getStock());
			
			if(t instanceof TPlatoComida)
			{
				TPlatoComida c = (TPlatoComida) t;
				fila.add(c.getTipo());
				fila.add("---");
			}
			if(t instanceof TPlatoBebida)
			{
				TPlatoBebida b = (TPlatoBebida) t;
				fila.add("---");
				if (b.isAlcoholica())	
					fila.add(TipoPlato.EnumTipoBebida.Alcoholica.toString());
				else
					fila.add(TipoPlato.EnumTipoBebida.No_Alcoholica.toString());
			}
			
			modeloplatos.addRow(fila);
		}

		tbPlatos.setModel(modeloplatos);
		
	}

	public void anadirPlato(Object objeto) {
		
		int stock_actual = (int)tbPlatos.getValueAt(getTbPlatos().getSelectedRow(), 3);
		if(stock_actual > 0)	
		{
			TPlato tPlato = (TPlato)objeto;	
			
			int cantidad = 0;
			float total = 0;
			
			JTable tbfactura = getTbFactura();
			
			int filas = modelofactura.getRowCount();			
			int fila_act = 0;
			boolean plato_encontrado = false;
			while(fila_act < filas && !plato_encontrado) //recorre toda la tabla factura fila a fila	
			{   	
				
				if((int)tbfactura.getValueAt(fila_act, 0) == tPlato.getID())
				{
					cantidad = (int)tbfactura.getValueAt(fila_act, 3);
					total = (float)tbfactura.getValueAt(fila_act, 4);
					modelofactura.removeRow(fila_act);				
					plato_encontrado = true;
					fila_act--;
				}
				fila_act++;
			}

			fila = new Vector();
		    fila.add(tPlato.getID());
			fila.add(tPlato.getNombre());
			fila.add(redondear(tPlato.getPrecio()));
			fila.add(cantidad+1);
			fila.add(redondear(total+tPlato.getPrecio()));
						
			modelofactura.insertRow(fila_act, fila);
			
			textFieldTotal.setText(String.valueOf(redondear(Float.valueOf(textFieldTotal.getText()) + tPlato.getPrecio())));	
			
			tbPlatos.setValueAt(stock_actual-1, getTbPlatos().getSelectedRow(), 3);
		}
		
	}

	public void quitarPlato(Object objeto) {

		TPlato tPlato = (TPlato)objeto;
		
		int cantidad_factura = tPlato.getStock() - 1;
		float total_factura = Float.valueOf(textFieldTotal.getText()) - tPlato.getPrecio();		
		int indice_tabla_factura = tbFactura.getSelectedRow();
		
		tbFactura.setValueAt(cantidad_factura, indice_tabla_factura, 3);
		tbFactura.setValueAt(redondear((float)tbFactura.getValueAt(indice_tabla_factura, 4) - tPlato.getPrecio()), indice_tabla_factura, 4);
		textFieldTotal.setText(String.valueOf(redondear(total_factura)));

		if(cantidad_factura <= 0)
			modelofactura.removeRow(indice_tabla_factura);	

		int filas = modeloplatos.getRowCount();		
		int fila_act = 0;
		boolean plato_encontrado = false;		
		while(fila_act < filas && !plato_encontrado) //recorre toda la tabla factura fila a fila	
		{   			
			if((int)tbPlatos.getValueAt(fila_act, 0) == tPlato.getID())
				tbPlatos.setValueAt((int)tbPlatos.getValueAt(fila_act, 3) + 1, fila_act, 3);
			fila_act++;
		}
	
	}
	
	public TPlato obtenerPlato(JTable tabla, int fila) {
		TPlato t = new TPlato();
		
		t.setID((int)tabla.getValueAt(fila, 0));
		t.setNombre(tabla.getValueAt(fila, 1).toString());
		t.setPrecio((float)tabla.getValueAt(fila, 2));
		t.setStock((int)tabla.getValueAt(fila, 3));
		
		return t;
	}
	
	public void limpiarFormulario(boolean mostrarVentana)
	{
		inicializarTablafactura();
		textFieldTotal.setText("0");
		
		setVisible(mostrarVentana);
	}
	
	private boolean mensajeConfirmacionSiNo(String msj, String cabecera) {	
		return (JOptionPane.showConfirmDialog(this, msj, cabecera, JOptionPane.YES_NO_OPTION) == 0);

	}

	public float redondear(float numero){		
		return (float)Math.rint(numero*100)/100;		
	}

	public void actualizarID(String id) {
		textFieldID_Factura.setText(id);
	}

}
