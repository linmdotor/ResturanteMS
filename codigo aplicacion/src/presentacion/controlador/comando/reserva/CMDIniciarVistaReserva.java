/**
 * 
 * Comando Anadir de Plato
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

public class CMDIniciarVistaReserva implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia()
				.generaSAReserva();
		
		return new RespuestaCMD(EnumComandos.INICIAR_VISTA_RESERVA, serviciosReserva.obtenerReservas());

	}

}
