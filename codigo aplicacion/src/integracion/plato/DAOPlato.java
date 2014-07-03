/**
 * 
 * Interfaz del DAO de Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.plato;

import java.util.ArrayList;

import negocio.factura.transfer.TFacturaPlato;
import negocio.plato.transfer.TPlato;

public interface DAOPlato {

	public ArrayList<TPlato> obtenerPlatos() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorNombre() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorPrecio() throws Exception;
	
	public ArrayList<TPlato> obtenerPlatosPorStock() throws Exception;

	public TPlato read(String ID_Plato) throws Exception; 

	public boolean create(TPlato tPlato) throws Exception; 

	public boolean delete(int ID_Plato) throws Exception; 

	public boolean update(TPlato tPlato) throws Exception; 

	boolean actualizarStock(TFacturaPlato tFacturaPlato) throws Exception;
	
}