/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato;

import java.util.ArrayList;

public interface SAPlato {

	public ArrayList<TPlato> obtenerPlatos();

	public TPlato obtenerPlato(int ID);

	public boolean añadirPlato(TPlato tPlato);

	public boolean eliminarPlato(int ID);

	public boolean modificarPlato(TPlato tPlato);
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio();
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock();

}
