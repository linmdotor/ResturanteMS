/**
 * 
 * Comando Anadir Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.comando.reserva;

import negocio.factoria.FactoriaNegocio;
import negocio.reserva.SAReserva;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDObtenerReservas implements CMD {

	// Metodos
	public RespuestaCMD ejecuta(Object objeto) {

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();

		RespuestaCMD respuestaComando = null;
		try {
			respuestaComando = new RespuestaCMD(EnumComandos.OBTENERRESERVAS,	serviciosReserva.obtenerReservas());
		} catch (Exception e) {
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
			e.printStackTrace();
		}

		return respuestaComando;

	}

}
