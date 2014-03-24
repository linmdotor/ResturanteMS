/**
 * 
 * Comando Modificar Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.comando.reserva;

import negocio.factoria.FactoriaNegocio;
import negocio.reserva.SAReserva;
import negocio.reserva.TReserva;
import negocio.reserva.ValidarTReserva;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarReserva implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando = null;

		SAReserva serviciosReservas = FactoriaNegocio.obtenerInstancia().generaSAReserva();

		if (new ValidarTReserva().transferCorrecto((TReserva) objeto))
		{
			try {
				if (serviciosReservas.modificarReserva((TReserva) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Exito modificando la Reserva.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Error al insertar los datos.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Los datos no son v�lidos.");

		return respuestaComando;

	}

}
