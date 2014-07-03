/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato;

import java.util.ArrayList;

import negocio.plato.transfer.TPlato;

public interface SAPlato {

	public ArrayList<TPlato> obtenerPlatos() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorNombre() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorPrecio() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorStock() throws Exception;

	public TPlato obtenerPlato(int ID) throws Exception;

	public boolean anadirPlato(TPlato tPlato) throws Exception;

	public boolean eliminarPlato(int ID) throws Exception;

	public boolean modificarPlato(TPlato tPlato) throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock() throws Exception;

}
