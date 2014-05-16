/**
 * 
 * Comando Modificar Plato
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
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

		SAReserva serviciosReservas = FactoriaNegocio.obtenerInstancia().generaSAReserva();
		RespuestaCMD respuestaComando = null;
		
		if (new ValidarTReserva().transferCorrecto((TReserva) objeto))
		{
			try {
				if (serviciosReservas.modificarReserva((TReserva) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Exito modificando la Reserva.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Error al insertar los datos.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Los datos no son válidos.");

		return respuestaComando;

	}

}
