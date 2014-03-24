/**
 * 
 * Validador de datos del Transfer Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato;

public class ValidarTPlato {

	public boolean transferCorrecto(TPlato tProductoCarta) {

		boolean transferCorrecto = false;

		if (tProductoCarta.getPrecio() > 0
				&& tProductoCarta.getStock() > 0
				&& tProductoCarta.getNombre().length() > 0)
			transferCorrecto = true;

		return transferCorrecto;

	}

}
