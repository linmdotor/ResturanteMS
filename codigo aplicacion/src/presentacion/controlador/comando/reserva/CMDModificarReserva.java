/**
 * 
 * Comando Modificar Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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

		RespuestaCMD respuestaComando;

		SAReserva serviciosReservas = FactoriaNegocio.obtenerInstancia().generaSAReserva();

		if (new ValidarTReserva().transferCorrecto((TReserva) objeto))
		{
			if (serviciosReservas.modificarReserva((TReserva) objeto))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Exito modificando la Reserva.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Error al insertar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Reserva. Los datos no son válidos.");

		return respuestaComando;

	}

}
