/**
 * 
 * Comando Anadir Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.comando.plato;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDObtenerPlatos implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia()
				.generaSAPlato();

		RespuestaCMD respuestaComando = new RespuestaCMD(EnumComandos.OBTENERPLATOS,
				serviciosProductosCarta.obtenerPlatos());

		return respuestaComando;

	}

}
