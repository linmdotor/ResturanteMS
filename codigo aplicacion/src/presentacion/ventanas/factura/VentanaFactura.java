/**
 * 
 * Vista del Almacen de Platos
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.ventanas.factura;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.ComprobadorEnteros;
import negocio.factura.TFactura;
import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;
import presentacion.ventanas.MiModeloTabla;

@SuppressWarnings("serial")
public class VentanaFactura extends JFrame {

	private static VentanaFactura ventana; //instancia singleton
	
	private JTextField textFieldID;	
	private JTextField textFieldID_Reserva;	
	private JTextField textFieldNIF_Empresa;
	private JTextField textFieldNombre_Empresa;	
	private JTextField textFieldDir_Empresa;	
	private JTextField textFieldNIF_Cliente;	
	private JTextField textFieldNombre_Cliente;	
	private JTextField textFieldDir_Cliente;
	private JTextField textFieldFecha;
	private JTextField textFieldHora;	
	private JTextField textFieldIVA;	
	private JTextField textFieldTipo_Servicio;

	private MiModeloTabla modelo;
	private JTable tbFacturas;
	private Vector fila;

	// Mutadores y Accedentes

	public JTable getTbFacturas() {
		return tbFacturas;
	}

	public void setTbFacturas(JTable tbFacturas) {
		this.tbFacturas = tbFacturas;
	}

	//GetInstance
	public static VentanaFactura obtenerInstancia() {

		if (ventana == null)
			ventana = new VentanaFactura();

		return ventana;
	}
	
	// Constructor
	public VentanaFactura() {

		setTitle("Gestión de Facturas");
		setResizable(false);
		setVisible(false);
		setSize(1100, 420);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBounds(874, 5, 215, 380);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);
		
		JButton btnAnadirFactura= new JButton("Nueva Factura");
		btnAnadirFactura.setBounds(9, 310, 192, 26);
		panelFormulario.add(btnAnadirFactura);
		btnAnadirFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ANADIR_FACTURA, obtenerFactura());
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERFACTURAS, null);

			}
		});

		JButton btnAnadirPlatosFactura= new JButton("Añadir Platos a la Factura");
		btnAnadirPlatosFactura.setBounds(9, 340, 192, 26);
		panelFormulario.add(btnAnadirPlatosFactura);
		btnAnadirPlatosFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_FACTURAPLATO, obtenerFactura());
				
			}
		});

		JLabel lblAadirNuevaFactura = new JLabel("Añadir Factura:");
		lblAadirNuevaFactura.setFont(new Font("Dialog", Font.BOLD, 13));
		lblAadirNuevaFactura.setBounds(12, 12, 192, 16);
		panelFormulario.add(lblAadirNuevaFactura);
		lblAadirNuevaFactura.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(5, 46, 15, 16);
		panelFormulario.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(30, 44, 40, 20);
		textFieldID.setEditable(false);
		panelFormulario.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblID_Reserva = new JLabel("ID_Reserva:");
		lblID_Reserva.setBounds(85, 46, 100, 16);
		panelFormulario.add(lblID_Reserva);
		
		textFieldID_Reserva = new JTextField();
		textFieldID_Reserva.setBounds(160, 44, 40, 20);
		panelFormulario.add(textFieldID_Reserva);
		textFieldID_Reserva.setColumns(10);

		JLabel lblNIF_Empresa = new JLabel("NIF empresa:");
		lblNIF_Empresa.setBounds(5, 84, 75, 16);
		panelFormulario.add(lblNIF_Empresa);

		textFieldNIF_Empresa = new JTextField();
		textFieldNIF_Empresa.setBounds(85, 82, 116, 20);
		panelFormulario.add(textFieldNIF_Empresa);
		textFieldNIF_Empresa.setColumns(10);

		JLabel lblNombre_Empresa = new JLabel("Empresa:");
		lblNombre_Empresa.setBounds(5, 104, 75, 16);
		panelFormulario.add(lblNombre_Empresa);
		
		textFieldNombre_Empresa = new JTextField();
		textFieldNombre_Empresa.setBounds(85, 102, 116, 20);
		panelFormulario.add(textFieldNombre_Empresa);
		textFieldNombre_Empresa.setColumns(10);
		
		JLabel lblDir_Empresa = new JLabel("Dir. empresa:");
		lblDir_Empresa.setBounds(5, 124, 78, 16);
		panelFormulario.add(lblDir_Empresa);
		
		textFieldDir_Empresa = new JTextField();
		textFieldDir_Empresa.setBounds(85, 122, 116, 20);
		panelFormulario.add(textFieldDir_Empresa);
		textFieldDir_Empresa.setColumns(10);
		
		JLabel lblNIF_Cliente = new JLabel("NIF cliente:");
		lblNIF_Cliente.setBounds(5, 144, 75, 16);
		panelFormulario.add(lblNIF_Cliente);
		
		textFieldNIF_Cliente = new JTextField();
		textFieldNIF_Cliente.setBounds(85, 142, 116, 20);
		panelFormulario.add(textFieldNIF_Cliente);
		textFieldNIF_Cliente.setColumns(10);
		
		JLabel lblNombre_Cliente = new JLabel("Cliente:");
		lblNombre_Cliente.setBounds(5, 164, 75, 16);
		panelFormulario.add(lblNombre_Cliente);
		
		textFieldNombre_Cliente = new JTextField();
		textFieldNombre_Cliente.setBounds(85, 162, 116, 20);
		panelFormulario.add(textFieldNombre_Cliente);
		textFieldNombre_Cliente.setColumns(10);
		
		JLabel lblDir_Cliente = new JLabel("Dir. cliente:");
		lblDir_Cliente.setBounds(5, 184, 75, 16);
		panelFormulario.add(lblDir_Cliente);
		
		textFieldDir_Cliente = new JTextField();
		textFieldDir_Cliente.setBounds(85, 182, 116, 20);
		panelFormulario.add(textFieldDir_Cliente);
		textFieldDir_Cliente.setColumns(10);

		JLabel lblFecha = new JLabel("Día:");
		lblFecha.setBounds(5, 214, 30, 16);
		panelFormulario.add(lblFecha);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(30, 212, 70, 20);
		textFieldFecha.setEditable(false);
		panelFormulario.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(110, 214, 45, 16);
		panelFormulario.add(lblHora);
		
		textFieldHora = new JTextField();
		textFieldHora.setBounds(145, 212, 55, 20);
		textFieldHora.setEditable(false);
		panelFormulario.add(textFieldHora);
		textFieldHora.setColumns(10);
		
		JLabel lblIVA = new JLabel("IVA:");
		lblIVA.setBounds(5, 242, 30, 16);
		panelFormulario.add(lblIVA);
		
		textFieldIVA = new JTextField("21");
		textFieldIVA.setBounds(30, 240, 30, 20);
		panelFormulario.add(textFieldIVA);
		textFieldIVA.setColumns(10);
		
		JLabel lblComensales = new JLabel("Servicio:");
		lblComensales.setBounds(65, 242, 70, 16);
		panelFormulario.add(lblComensales);

		textFieldTipo_Servicio = new JTextField("Restauración");
		textFieldTipo_Servicio.setBounds(115, 240, 85, 20);;
		panelFormulario.add(textFieldTipo_Servicio);
		textFieldTipo_Servicio.setColumns(10);

		JButton btnBorrarFormulario = new JButton("Borrar Formulario");
		btnBorrarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiarFormulario();						
			}
		});

		btnBorrarFormulario.setFont(new Font("Dialog", Font.BOLD, 9));
		btnBorrarFormulario.setBounds(83, 271, 118, 22);
		panelFormulario.add(btnBorrarFormulario);

		JPanel panelLista = new JPanel();
		panelLista.setBounds(10, 5, 864, 383);
		getContentPane().add(panelLista);
		panelLista.setLayout(null);

		JButton btnEliminarFactura= new JButton("Eliminar Factura Seleccionada");
		btnEliminarFactura.setBounds(0, 340, 864, 26);
		panelLista.add(btnEliminarFactura);
		btnEliminarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (mensajeConfirmacionSiNo("¿Realmente desea eliminar la factura?", "Confirmar eliminar Factura"))
				{
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ELIMINAR_FACTURA, getTbFacturas().getSelectedRow() );
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERFACTURAS, null);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 864, 320);
		panelLista.add(scrollPane);

		tbFacturas = new JTable();
		scrollPane.setViewportView(tbFacturas);
		tbFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				if (getTbFacturas().getSelectedRow() != -1) //hay alguna fila seleccionada
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.MODIFICAR_FORMULARIO_FACTURA, getTbFacturas().getSelectedRow() );	
				
			}
		});

	}

	// Metodos

	public void actualizar(Object object) {

		if (object == null)
			rellenarTabla(new ArrayList<TFactura>());
		else			
			rellenarTabla((ArrayList<TFactura>) object);

		setVisible(true);
		repaint();

	}

	public void rellenarTabla(ArrayList<TFactura> lista) {

		modelo = new MiModeloTabla();

		modelo.addColumn("ID");
		modelo.addColumn("ID_RESER.");
		modelo.addColumn("NIF EMP.");
		modelo.addColumn("EMPRESA");
		modelo.addColumn("DIR. EMP.");
		modelo.addColumn("NIF CLIEN.");
		modelo.addColumn("CLIENTE");
		modelo.addColumn("DIR. CLIEN.");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		modelo.addColumn("IVA");
		modelo.addColumn("SERVICIO");

		for (int i = 0; i < lista.size(); i++) {

			fila = new Vector();
		    TFactura t = lista.get(i);
			fila.add(t.getID_Factura());
			fila.add(t.getID_Reserva());
			fila.add(t.getNIF_Empresa());
			fila.add(t.getNombre_Empresa());
			fila.add(t.getDir_Empresa());
			fila.add(t.getNIF_Cliente());
			fila.add(t.getNombre_Cliente());
			fila.add(t.getDir_Cliente());
			fila.add(t.getFecha());
			fila.add(t.getHora());
			fila.add(t.getIVA());
			fila.add(t.getTipo_Servicio());
			
			modelo.addRow(fila);
		}

		tbFacturas.setModel(modelo);
		
	}

	public TFactura obtenerFactura() {

		int ID = -1;
		int ID_Reserva = -1;
		int IVA = -1;
		
		ComprobadorEnteros comprobadorEntero = new ComprobadorEnteros();

		if (comprobadorEntero.isNumeric(textFieldID.getText())) {
			ID = Integer.parseInt(textFieldID.getText());
		}
		
		if (comprobadorEntero.isNumeric(textFieldID_Reserva.getText())) {
			ID_Reserva = Integer.parseInt(textFieldID_Reserva.getText());
		}
		
		if (comprobadorEntero.isNumeric(textFieldIVA.getText())) {
			IVA = Integer.parseInt(textFieldIVA.getText());
		}

		//Crea un tipo de transfer TFactura
		TFactura t = new TFactura();
		
			t.setID_Factura(ID);
			t.setID_Reserva(ID_Reserva);
			t.setNIF_Empresa(textFieldNIF_Empresa.getText());
			t.setNombre_Empresa(textFieldNombre_Empresa.getText());
			t.setDir_Empresa(textFieldDir_Empresa.getText());
			t.setNIF_Cliente(textFieldNIF_Cliente.getText());
			t.setNombre_Cliente(textFieldNombre_Cliente.getText());
			t.setDir_Cliente(textFieldDir_Cliente.getText());					
			Date date = new Date();
			//obtener la hora y salida por pantalla con formato:
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			//obtener la fecha y salida por pantalla con formato:
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			t.setFecha(dateFormat.format(date));
			t.setHora(hourFormat.format(date));
			t.setIVA(IVA);
			t.setTipo_Servicio(textFieldTipo_Servicio.getText());

		return t;
		
	}

	public void modificarFormulario(Object objeto) {

		TFactura tFactura = (TFactura) objeto;
		
		textFieldID.setText(Integer.toString(tFactura.getID_Factura()));
		textFieldID_Reserva.setText(Integer.toString(tFactura.getID_Reserva()));
		textFieldNIF_Empresa.setText(tFactura.getNIF_Empresa());
		textFieldNombre_Empresa.setText(tFactura.getNombre_Empresa());
		textFieldDir_Empresa.setText(tFactura.getDir_Empresa());
		textFieldNIF_Cliente.setText(tFactura.getNIF_Cliente());
		textFieldNombre_Cliente.setText(tFactura.getNombre_Cliente());
		textFieldDir_Cliente.setText(tFactura.getDir_Cliente());
		textFieldFecha.setText(tFactura.getFecha());
		textFieldHora.setText(tFactura.getHora());
		textFieldIVA.setText(String.valueOf(tFactura.getIVA()));
		textFieldTipo_Servicio.setText(tFactura.getTipo_Servicio());

	}

	public void limpiarFormulario()
	{
		textFieldID.setText("");
		textFieldID_Reserva.setText("");
		textFieldNIF_Empresa.setText("");
		textFieldNombre_Empresa.setText("");
		textFieldDir_Empresa.setText("");
		textFieldNIF_Cliente.setText("");
		textFieldNombre_Cliente.setText("");
		textFieldDir_Cliente.setText("");
		textFieldFecha.setText("");
		textFieldHora.setText("");
		textFieldIVA.setText("21");
		textFieldTipo_Servicio.setText("Restauración");
	}
	
	private boolean mensajeConfirmacionSiNo(String msj, String cabecera) {	
		return (JOptionPane.showConfirmDialog(this, msj, cabecera, JOptionPane.YES_NO_OPTION) == 0);

	}

	public String getID() {
		return textFieldID.getText();
	}
	
}