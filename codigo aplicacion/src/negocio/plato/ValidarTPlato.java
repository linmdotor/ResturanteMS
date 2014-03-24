/**
 * 
 * Validador de datos del Transfer Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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
