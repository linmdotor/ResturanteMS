/**
 * 
 * Interfaz del DAO de Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.plato;

import java.util.ArrayList;

import negocio.factura.TFacturaPlato;
import negocio.plato.TPlato;

public interface DAOPlato {

	public ArrayList<TPlato> obtenerPlatos() throws Exception;

	public TPlato read(String ID_Plato) throws Exception; // implementado

	public boolean create(TPlato tPlato) throws Exception; // implementado

	public boolean delete(int ID_Plato) throws Exception; // implementado

	public boolean update(TPlato tPlato) throws Exception; //

	boolean actualizarStock(TFacturaPlato tFacturaPlato) throws Exception;
	
}