/**
 * 
 * Validador de datos del Transfer Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva;

public class ValidarTReserva {

	public boolean transferCorrecto(TReserva tReserva) {

		boolean transferCorrecto = false;

		if (tReserva.getNombre().length() > 0
				&& tReserva.getFecha().length() > 0
				&& tReserva.getHora().length() > 0
				&& tReserva.getN_Comensales() > 0)
			transferCorrecto = true;

		return transferCorrecto;

	}

}
