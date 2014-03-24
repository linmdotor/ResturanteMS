/**
 * 
 * Vista Principal
 * 
 * @author Marco González, Juan Carlos 
 * @author Martínez Dotor, Jesús 
 * @author Picado Álvarez, María 
 * @author Rojas Morán, Amy Alejandra 
 * @author Serrano Álvarez, José 
 * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;

public class VentanaPrincipal extends JFrame {

	// Constructor
	public VentanaPrincipal() {
		
		//PROPIEDADES DE LA VENTANA ---------------------------
		this.setLocationByPlatform(true);
		setTitle("Restaurante");
		setResizable(false);
		setVisible(true);
		setSize(256, 200);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//PLATOS ---------------------------
		JButton btnAlmacenDePlatos = new JButton("Gestionar PLATOS");
		btnAlmacenDePlatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_PLATO, null);
				
			}
		});
		btnAlmacenDePlatos.setBounds(38, 30, 183, 26);
		getContentPane().add(btnAlmacenDePlatos);
		
		//RESERVAS ---------------------------
		JButton btnReservas = new JButton("Gestionar RESERVAS");
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_RESERVA, null);
				
			}
		});
		btnReservas.setBounds(38, 80, 183, 26);
		getContentPane().add(btnReservas);
				
		//FACTURAS ---------------------------
		JButton btnFacturas = new JButton("Gestionar FACTURAS");
		btnFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_FACTURA, null);
				
			}
		});
		btnFacturas.setBounds(38, 130, 183, 26);
		getContentPane().add(btnFacturas);

	}
}
