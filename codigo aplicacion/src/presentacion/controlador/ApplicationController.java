/**
 * 
 * Factoria del Controlador de Aplicacion
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador;

import presentacion.controlador.imp.ApplicationControllerImp;


public abstract class ApplicationController {

	// Atributos

	private static ApplicationController controladorAplicacion; //instancia singleton

	// Metodos

	public static ApplicationController obtenerInstancia() {

		if (controladorAplicacion == null)
			controladorAplicacion = new ApplicationControllerImp();

		return controladorAplicacion;
	}

	public abstract void handleRequest(EnumComandos nombreComando, Object objeto);

}
