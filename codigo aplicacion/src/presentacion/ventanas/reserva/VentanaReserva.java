/**
 * 
 * Vista del Almacen de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.ventanas.reserva;

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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.ComprobadorEnteros;
import negocio.reserva.TReserva;
import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;
import presentacion.ventanas.MiModeloTabla;

public class VentanaReserva extends JFrame {


	private JTextField textFieldID;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldFecha;
	private JTextField textFieldHora;	
	private JTextField textFieldTelefono;	
	private JTextField textFieldComensales;
	
	private Vector fila;
	private MiModeloTabla modelo;
	private JTable tbReservas;	

	private JScrollPane scrollPanel;

	// Mutadores y Accedentes

	public JTable getTbReservas() {
		return tbReservas;
	}

	public void setTbReservas(JTable tbReservas) {
		this.tbReservas = tbReservas;
	}

	// Constructor
	public VentanaReserva(Object objeto) {

		setTitle("Gesti�n de Reservas");
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

		JButton btnAadirReserva = new JButton("A\u00F1adir Reserva");
		btnAadirReserva.setBounds(9, 276, 192, 26);
		panelFormulario.add(btnAadirReserva);
		btnAadirReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ANADIR_RESERVA, obtenerReserva());
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERRESERVAS, obtenerReserva());

			}
		});

		JButton btnModificarReserva = new JButton("Modificar Reserva");
		btnModificarReserva.setBounds(9, 310, 192, 26);
		panelFormulario.add(btnModificarReserva);
		btnModificarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.MODIFICAR_RESERVA, obtenerReserva());
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERRESERVAS, obtenerReserva());

			}
		});

		JLabel lblAadirNuevaReserva = new JLabel("A\u00F1adir o Modificar Reserva:");
		lblAadirNuevaReserva.setFont(new Font("Dialog", Font.BOLD, 13));
		lblAadirNuevaReserva.setBounds(12, 12, 192, 16);
		panelFormulario.add(lblAadirNuevaReserva);
		lblAadirNuevaReserva.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(64, 46, 14, 16);
		panelFormulario.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(83, 44, 118, 20);
		textFieldID.setEditable(false);
		panelFormulario.add(textFieldID);
		textFieldID.setColumns(10);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(55, 81, 24, 16);
		panelFormulario.add(lblDNI);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(83, 79, 118, 20);
		panelFormulario.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 114, 58, 16);
		panelFormulario.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 112, 118, 20);
		panelFormulario.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("D�a:");
		lblFecha.setBounds(5, 144, 30, 16);
		panelFormulario.add(lblFecha);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(30, 142, 70, 20);
		panelFormulario.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(110, 144, 45, 16);
		panelFormulario.add(lblHora);
		
		textFieldHora = new JTextField();
		textFieldHora.setBounds(145, 142, 55, 20);
		panelFormulario.add(textFieldHora);
		textFieldHora.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(25, 172, 70, 16);
		panelFormulario.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(83, 170, 118, 20);
		panelFormulario.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblComensales = new JLabel("N� Comens.:");
		lblComensales.setBounds(10, 200, 70, 16);
		panelFormulario.add(lblComensales);

		textFieldComensales = new JTextField();
		textFieldComensales.setBounds(83, 198, 118, 20);
		panelFormulario.add(textFieldComensales);
		textFieldComensales.setColumns(10);

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

		JButton btnEliminarReserva = new JButton("Eliminar Reserva Seleccionado");
		btnEliminarReserva.setBounds(0, 310, 564, 26);
		panelLista.add(btnEliminarReserva);
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(mensajeConfirmacionSiNo("�Realmente desea eliminar la reserva?", "Confirmar eliminar Reserva"))
				{
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.ELIMINAR_RESERVA, getTbReservas().getSelectedRow() );
					ApplicationController.obtenerInstancia().handleRequest(EnumComandos.OBTENERRESERVAS, obtenerReserva());
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 564, 291);
		panelLista.add(scrollPane);

		tbReservas = new JTable();
		scrollPane.setViewportView(tbReservas);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {

						if (getTbReservas().getSelectedRow() != -1)
							ApplicationController.obtenerInstancia().handleRequest(EnumComandos.MODIFICAR_FORMULARIO_RESERVA, getTbReservas().getSelectedRow() );	
						
					}
				});

		actualizar(objeto);

	}

	// Metodos

	public void actualizar(Object object) {

		if (object == null)
			rellenarTabla(new ArrayList<TReserva>());
		else			
			rellenarTabla((ArrayList<TReserva>) object);

		repaint();

	}

	public void rellenarTabla(ArrayList<TReserva> lista) {

		modelo = new MiModeloTabla();

		modelo.addColumn("ID");
		modelo.addColumn("DNI");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		modelo.addColumn("TELEFONO");
		modelo.addColumn("N� COMENSALES");

		for (int i = 0; i < lista.size(); i++) {

			fila = new Vector();
		    TReserva t = lista.get(i);
			fila.add(t.getID());
			fila.add(t.getDNI());
			fila.add(t.getNombre());
			fila.add(t.getFecha());
			fila.add(t.getHora());
			fila.add(t.getTelefono());
			fila.add(t.getN_Comensales());
			
			modelo.addRow(fila);
		}

		tbReservas.setModel(modelo);
		
	}

	public TReserva obtenerReserva() {

		int ID = -1;
		int comensales = -1;
		
		ComprobadorEnteros comprobadorEntero = new ComprobadorEnteros();

		if (comprobadorEntero.isNumeric(textFieldID.getText())) {
			ID = Integer.parseInt(textFieldID.getText());
		}

		if (comprobadorEntero.isNumeric(textFieldComensales.getText())) {
			comensales = Integer.parseInt(textFieldComensales.getText());
		}

		//Crea un tipo de transfer TReserva
		TReserva t = new TReserva();
		
			t.setID(ID);
			t.setDNI(textFieldDNI.getText());
			t.setNombre(textFieldNombre.getText());
			t.setFecha(textFieldFecha.getText());
			t.setHora(textFieldHora.getText());
			t.setTelefono(textFieldTelefono.getText());
			t.setN_Comensales(comensales);

		return t;
	}

	public void modificarFormulario(Object objeto) {

		TReserva tReserva = (TReserva) objeto;
		
		textFieldID.setText(Integer.toString(tReserva.getID()));
		textFieldDNI.setText(tReserva.getDNI());
		textFieldNombre.setText(tReserva.getNombre());
		textFieldFecha.setText(tReserva.getFecha());
		textFieldHora.setText(tReserva.getHora());
		textFieldTelefono.setText(tReserva.getTelefono());
		textFieldComensales.setText(Integer.toString(tReserva.getN_Comensales()));

	}
	
	public void limpiarFormulario()
	{
		textFieldID.setText("");
		textFieldDNI.setText("");
		textFieldNombre.setText("");
		textFieldFecha.setText("");
		textFieldHora.setText("");
		textFieldTelefono.setText("");
		textFieldComensales.setText("");
	}
	
	private boolean mensajeConfirmacionSiNo(String msj, String cabecera) {	
		return (JOptionPane.showConfirmDialog(this, msj, cabecera, JOptionPane.YES_NO_OPTION) == 0);

	}

}
