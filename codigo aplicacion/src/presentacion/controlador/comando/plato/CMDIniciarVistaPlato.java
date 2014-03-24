/**
 * 
 * Comando Anadir de Plato
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

public class CMDIniciarVistaPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		RespuestaCMD respuestaComando = null;
		try {
			respuestaComando = new RespuestaCMD(EnumComandos.INICIAR_VISTA_PLATO, serviciosPlato.obtenerPlatos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return respuestaComando;

	}

}
