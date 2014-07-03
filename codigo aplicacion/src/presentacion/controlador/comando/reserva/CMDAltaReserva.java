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
import negocio.reserva.transfer.TReserva;
import negocio.reserva.transfer.ValidarTReserva;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaReserva implements CMD {

	// Metodos
	public RespuestaCMD ejecuta(Object objeto) {

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();
		RespuestaCMD respuestaComando = null;
		
		if (new ValidarTReserva().transferCorrecto((TReserva) objeto))
		{
			try {
				if (serviciosReserva.anadirReserva((TReserva) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Se ha a�adido la Reserva.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar la reserva. Error al insertar los datos.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar la reserva. Los datos no son v�lidos.");

		return respuestaComando;

	}

}
