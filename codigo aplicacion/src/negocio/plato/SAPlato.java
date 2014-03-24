/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato;

import java.util.ArrayList;

public interface SAPlato {

	public ArrayList<TPlato> obtenerPlatos();

	public TPlato obtenerPlato(int ID);

	public boolean anadirPlato(TPlato tPlato);

	public boolean eliminarPlato(int ID);

	public boolean modificarPlato(TPlato tPlato);
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio();
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock();

}
