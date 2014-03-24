/**
 * 
 * Comando Eliminar Plato
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

public class CMDBajaReserva implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando;

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();

		int ID = -1;

		if ((Integer) objeto != -1)
		{

			ID = serviciosReserva.obtenerReservas().get((Integer) objeto).getID();

			if (serviciosReserva.eliminarReserva(ID))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Exito eliminando Reserva.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR,	"Error al eliminar reserva. Error al eliminar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar reserva. Debe seleccionar una Reserva.");

		return respuestaComando;

	}

}
