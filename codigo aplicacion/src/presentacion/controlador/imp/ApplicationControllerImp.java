/**
 * 
 * Controlador de Aplicacion
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.imp;

import presentacion.controlador.ApplicationController;
import presentacion.controlador.CMD;
import presentacion.controlador.Dispatcher;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.FactoriaCMD;

public class ApplicationControllerImp extends ApplicationController {

	// Metodos

	public void handleRequest(EnumComandos nombreComando, Object objeto) {

		FactoriaCMD factoriaComandos = FactoriaCMD.obtenerInstancia();

		CMD comando = factoriaComandos.generaComando(nombreComando);

		Dispatcher.obtenerInstancia().despachaRespuesta(comando.ejecuta(objeto));

	}

}
