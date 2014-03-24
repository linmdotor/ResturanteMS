/**
 * 
 * Interfaz del DAO de Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.plato;

import java.util.ArrayList;

import negocio.factura.TFacturaPlato;
import negocio.plato.TPlato;

public interface DAOPlato {

	public ArrayList<TPlato> obtenerPlatos();

	public TPlato read(String ID_Plato); // implementado

	public boolean create(TPlato tPlato); // implementado

	public boolean delete(int ID_Plato); // implementado

	public boolean update(TPlato tPlato); //

	boolean actualizarStock(TFacturaPlato tFacturaPlato);
	
}