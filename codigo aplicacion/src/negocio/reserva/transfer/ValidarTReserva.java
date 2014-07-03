/**
 * 
 * Validador de datos del Transfer Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva.transfer;

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
