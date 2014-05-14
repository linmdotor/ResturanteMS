/**
 * 
 * Comando Eliminar Plato
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

public class CMDBajaReserva implements CMD {

	// Metodos
	public RespuestaCMD ejecuta(Object objeto) {

		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();
		RespuestaCMD respuestaComando = null;
		int ID = -1;

		if ((Integer) objeto != -1)
		{

			try {
				ID = serviciosReserva.obtenerReservas().get((Integer) objeto).getID();
				if (serviciosReserva.eliminarReserva(ID))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_RESERVA, "Exito eliminando Reserva.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR,	"Error al eliminar reserva. Error al eliminar los datos.");
		
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error inesperado al eliminar reserva.");
				e.printStackTrace();
			}

		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar reserva. Debe seleccionar una Reserva.");

		return respuestaComando;

	}

}
