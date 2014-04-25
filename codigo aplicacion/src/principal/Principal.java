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


public class Principal {

	// Metodos

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		new Principal().ejecuta();

	}

	public void ejecuta() {

		ApplicationController.obtenerInstancia().handleRequest(EnumComandos.INICIAR_VISTA_PRINCIPAL, null);
		
	}

}
