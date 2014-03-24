/**
 * 
 * Interfaz del DAO de Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
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