/**
 * Clase que comprueba si un valor dado es entero
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 * @version 13/06/2013
 * 
 */

package negocio;

public class ComprobadorEnteros {

	public boolean isNumeric(String cadena) {

		/**
		 * Comprueba si un String es un numero
		 * 
		 * @return boolean isNumeric
		 */
		try { // Comprueba si es un entero si no da excepcion

			Integer.parseInt(cadena);
			return true;

		} catch (NumberFormatException nfe) {

			return false;

		}

	}

}
