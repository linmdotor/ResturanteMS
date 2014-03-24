/**
 * 
 * Comando Añadir Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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

		RespuestaCMD respuestaComando = new RespuestaCMD(EnumComandos.OBTENERRESERVAS,	serviciosReserva.obtenerReservas());

		return respuestaComando;

	}

}
