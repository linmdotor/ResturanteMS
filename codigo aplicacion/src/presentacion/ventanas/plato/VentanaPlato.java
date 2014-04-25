/**
 * 
 * Vista del Almacen de Platos
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.ventanas.plato;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.ComprobadorEnteros;
import negocio.ComprobadorFloat;
import negocio.plato.TPlato;
import negocio.plato.TPlatoBebida;
import negocio.plato.TPlatoComida;
import negocio.plato.TipoPlato;
import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;
import presentacion.ventanas.MiModeloTabla;

@SuppressWarnings("serial")
public class VentanaPlato extends JFrame {

	private static VentanaPlato ventana; //instancia singleton
	
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JComboBox<String> comboBoxTipo;
	private JComboBox<String> comboBoxSubTipo;

	private MiModeloTabla modelo;
	private JTable tbAlmacen;
	private Vector fila;

	// Mutadores y Accedentes

	public JTable getTbAlmacen() {
		return tbAlmacen;
	}

	public void setTbAlmacen(JTable tbAlmacen) {
		this.tbAlmacen = tbAlmacen;
	}

	//GetInstance
	public static VentanaPlato obtenerInstancia() {

		if (ventana == null)
			ventana = new VentanaPlato();

		return ventana;
	}
		
	// Constructor
	public VentanaPlato() {


		setTitle("Gestión de Platos");
		setResizable(false);
		setVisible(false);
		setSize(798, 390);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBounds(574, 5, 215, 350);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);

		JButton btnAnadirPlato = new JButton("Añadir Plato");
		btnAnadirPlato.setBounds(9, 276, 192, 26);
		panelFormulario.add(btnAnadirPlato);
		btnAnadirPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ANADIR_PLATO, obtenerPlato());
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERPLATOS, null);

			}
		});

		JButton btnModificarPlato = new JButton("Modificar Plato");
		btnModificarPlato.setBounds(9, 310, 192, 26);
		panelFormulario.add(btnModificarPlato);
		btnModificarPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.MODIFICAR_PLATO, obtenerPlato());
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERPLATOS, null);

			}
		});

		JLabel lblAnadirNuevoPlato = new JLabel("Añadir o Modificar Plato:");
		lblAnadirNuevoPlato.setFont(new Font("Dialog", Font.BOLD, 13));
		lblAnadirNuevoPlato.setBounds(12, 12, 192, 16);
		panelFormulario.add(lblAnadirNuevoPlato);
		lblAnadirNuevoPlato.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(64, 46, 14, 16);
		panelFormulario.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(83, 44, 118, 20);
		textFieldID.setEditable(false);
		panelFormulario.add(textFieldID);
		textFieldID.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 77, 48, 16);
		panelFormulario.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 75, 118, 20);
		panelFormulario.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(38, 167, 40, 16);
		panelFormulario.add(lblPrecio);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(83, 165, 118, 20);
		panelFormulario.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(51, 107, 27, 16);
		panelFormulario.add(lblTipo);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(83, 103, 118, 25);
		panelFormulario.add(comboBoxTipo);
		comboBoxTipo.setModel(new DefaultComboBoxModel(TipoPlato.EnumTipoPlato.values()));
		
		JLabel lblSubTipo = new JLabel("SubTipo:");
		lblSubTipo.setBounds(30, 138, 55, 16);
		panelFormulario.add(lblSubTipo);
		
		comboBoxSubTipo = new JComboBox();
		comboBoxSubTipo.setBounds(83, 134, 118, 25);
		panelFormulario.add(comboBoxSubTipo);
		comboBoxSubTipo.setModel(new DefaultComboBoxModel(TipoPlato.EnumTipoComida.values()));
		
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //carga los subitpos en función del tipo elegido
				if(comboBoxTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoPlato.Comida.toString()) == 0)
				{
					comboBoxSubTipo.setModel(new DefaultComboBoxModel(TipoPlato.EnumTipoComida.values()));
				}
				else if(comboBoxTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoPlato.Bebida.toString()) == 0)
				{
					comboBoxSubTipo.setModel(new DefaultComboBoxModel(TipoPlato.EnumTipoBebida.values()));
				}
				else
				{
					comboBoxSubTipo.setModel(new DefaultComboBoxModel());
				}
			}
		});
		
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(42, 200, 36, 16);
		panelFormulario.add(lblStock);

		textFieldStock = new JTextField();
		textFieldStock.setBounds(83, 198, 118, 20);
		panelFormulario.add(textFieldStock);
		textFieldStock.setColumns(10);

		JButton btnBorrarFormulario = new JButton("Borrar Formulario");
		btnBorrarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiarFormulario();

			}
		});

		btnBorrarFormulario.setFont(new Font("Dialog", Font.BOLD, 9));
		btnBorrarFormulario.setBounds(83, 231, 118, 22);
		panelFormulario.add(btnBorrarFormulario);

		JPanel panelLista = new JPanel();
		panelLista.setBounds(10, 5, 564, 353);
		getContentPane().add(panelLista);
		panelLista.setLayout(null);

		JButton btnEliminarPlato = new JButton("Eliminar Plato Seleccionado");
		btnEliminarPlato.setBounds(0, 310, 564, 26);
		panelLista.add(btnEliminarPlato);
		btnEliminarPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(mensajeConfirmacionSiNo("¿Realmente desea eliminar el plato?", "Confirmar eliminar Plato"))
				{
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ELIMINAR_PLATO, getTbAlmacen().getSelectedRow() );
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERPLATOS, null);

				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 564, 291);
		panelLista.add(scrollPane);

		tbAlmacen = new JTable();
		scrollPane.setViewportView(tbAlmacen);
		tbAlmacen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAlmacen.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
	
				if (getTbAlmacen().getSelectedRow() != -1) //hay alguna fila seleccionada
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.MODIFICAR_FORMULARIO_PLATO, getTbAlmacen().getSelectedRow() );	
				
			}
		});

	}

	// Metodos

	public void actualizar(Object object) {

		if (object == null)
			rellenarTabla(new ArrayList<TPlato>());
		else			
			rellenarTabla((ArrayList<TPlato>) object);

		setVisible(true);
		repaint();

	}

	public void rellenarTabla(ArrayList<TPlato> lista) {

		
		modelo = new MiModeloTabla();

		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("PRECIO");
		modelo.addColumn("STOCK");
		modelo.addColumn("TIPO PLATO");
		modelo.addColumn("TIPO BEBIDA");

		for (int i = 0; i < lista.size(); i++) {

			fila = new Vector();
		    TPlato t = lista.get(i);
			fila.add(t.getID());
			fila.add(t.getNombre());
			fila.add(redondear(t.getPrecio()));
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
			

			modelo.addRow(fila);
		}

		tbAlmacen.setModel(modelo);

	}

	public TPlato obtenerPlato() {

		int ID = -1;
		float precio = -1;
		int stock = -1;

		ComprobadorEnteros comprobadorEntero = new ComprobadorEnteros();

		if (comprobadorEntero.isNumeric(textFieldID.getText())) {
			ID = Integer.parseInt(textFieldID.getText());
		}

		if (comprobadorEntero.isNumeric(textFieldStock.getText())) {
			stock = Integer.parseInt(textFieldStock.getText());
		}

		ComprobadorFloat comprobadorFloat = new ComprobadorFloat();

		if (comprobadorFloat.isNumeric(textFieldPrecio.getText())) {
			precio = Float.parseFloat(textFieldPrecio.getText());
		}

		
		//Crea un tipo de transfer u otro en función del tipo que sea el comboBox, pero lo encapsula en TPlato
		TPlato t;
		
		if(comboBoxTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoPlato.Comida.toString()) == 0)
		{	
			TPlatoComida t_aux = new TPlatoComida();
			t_aux.setID(ID);
			t_aux.setNombre(textFieldNombre.getText());
			t_aux.setPrecio((float)precio);
			t_aux.setStock(stock);
			t_aux.setTipo(comboBoxSubTipo.getSelectedItem().toString());		
			t = t_aux;
		}
		else //if (comboBoxTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoPlato.Bebida.toString()) != 0)
		{
			TPlatoBebida t_aux = new TPlatoBebida();
			t_aux.setID(ID);
			t_aux.setNombre(textFieldNombre.getText());
			t_aux.setPrecio((float)precio);
			t_aux.setStock(stock);
			
			if(comboBoxSubTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoBebida.Alcoholica.toString()) == 0)
				t_aux.setAlcoholica(true);
			else //if(comboSubBoxTipo.getSelectedItem().toString().compareTo(TipoPlato.EnumTipoBebida.No_Alcoholica.toString()) != 0)
				t_aux.setAlcoholica(false);

			t = t_aux;
		}

		return t;
	}

	public void modificarFormulario(Object objeto) {

		TPlato tPlato = (TPlato) objeto;
		
		textFieldID.setText(Integer.toString(tPlato.getID()));
		textFieldNombre.setText(tPlato.getNombre());
		textFieldPrecio.setText(Float.toString(redondear(tPlato.getPrecio())));
		textFieldStock.setText(Integer.toString(tPlato.getStock()));
		
		if(tPlato instanceof TPlatoComida)
		{
			TPlatoComida tPlato_aux = (TPlatoComida) tPlato;
			comboBoxTipo.setSelectedItem(TipoPlato.EnumTipoPlato.Comida);
			if(tPlato_aux.getTipo().compareTo(TipoPlato.EnumTipoComida.Entrante.toString()) == 0)
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoComida.Entrante);
			else if(tPlato_aux.getTipo().compareTo(TipoPlato.EnumTipoComida.Primero.toString()) == 0)
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoComida.Primero);
			else if(tPlato_aux.getTipo().compareTo(TipoPlato.EnumTipoComida.Segundo.toString()) == 0)
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoComida.Segundo);
			else if(tPlato_aux.getTipo().compareTo(TipoPlato.EnumTipoComida.Postre.toString()) == 0)
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoComida.Postre);
		}
		else //if(t instanceof TPlatoBebida)
		{
			TPlatoBebida tPlato_aux = (TPlatoBebida) tPlato;
			comboBoxTipo.setSelectedItem(TipoPlato.EnumTipoPlato.Bebida);
			if(tPlato_aux.isAlcoholica())
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoBebida.Alcoholica);
			else if(! tPlato_aux.isAlcoholica())
				comboBoxSubTipo.setSelectedItem(TipoPlato.EnumTipoBebida.No_Alcoholica);
		}

	}
	
	public void limpiarFormulario()
	{
		textFieldID.setText("");
		textFieldNombre.setText("");
		textFieldPrecio.setText("");
		textFieldStock.setText("");
		comboBoxTipo.setSelectedIndex(0);
	}
	
	private boolean mensajeConfirmacionSiNo(String msj, String cabecera) {	
		return (JOptionPane.showConfirmDialog(this, msj, cabecera, JOptionPane.YES_NO_OPTION) == 0);

	}
	
	public float redondear(float numero){		
		return (float)Math.rint(numero*100)/100;		
	}

}
