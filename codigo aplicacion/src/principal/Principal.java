/**
 * 
 * Clase principal que ejecutara toda la aplicacion
 * 
 * @author Marco González, Juan Carlos
 * @author Martínez Dotor, Jesús
 * @author Picado Álvarez, María
 * @author Rojas Morán, Amy Alejandra
 * @author Serrano Álvarez, José
 * @author Vargas Paredes, Jhonny
 *  
 */

package principal;
import presentacion.controlador.ApplicationController;
import presentacion.controlador.EnumComandos;
/*
 *
 */
import presentacion.ventanas.VentanaPrincipal;


public class Principal {

	// Metodos

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Principal principal = new Principal();

		principal.ejecuta();

	}

	public void ejecuta() {

		ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_PRINCIPAL, null);
		
	}

}
