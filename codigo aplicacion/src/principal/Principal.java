/**
 * 
 * Clase principal que ejecutara toda la aplicacion
 * 
 * @author Marco Gonz�lez, Juan Carlos
 * @author Mart�nez Dotor, Jes�s
 * @author Picado �lvarez, Mar�a
 * @author Rojas Mor�n, Amy Alejandra
 * @author Serrano �lvarez, Jos�
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
