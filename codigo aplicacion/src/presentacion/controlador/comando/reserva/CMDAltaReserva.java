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
import negocio.reserva.TReserva;
import negocio.reserva.ValidarTReserva;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaReserva implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando;

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();

		if (new ValidarTReserva().transferCorrecto((TReserva) objeto))
		{
			if (serviciosReserva.añadirReserva((TReserva) objeto))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Se ha añadido la Reserva.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar la reserva. Error al insertar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar la reserva. Los datos no son válidos.");

		return respuestaComando;

	}

}
