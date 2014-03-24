/**
 * 
 * Interfaz del DAO de Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.reserva;

import java.util.ArrayList;

import negocio.reserva.TReserva;

public interface DAOReserva {

	public ArrayList<TReserva> obtenerReservas();

	public TReserva read(String ID_Reserva); 

	public boolean create(TReserva tReserva);

	public boolean delete(int ID_Reserva); 

	public boolean update(TReserva tReserva);
	
}