/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva;

import java.util.ArrayList;

public interface SAReserva {

	public ArrayList<TReserva> obtenerReservas();

	public TReserva obtenerReserva(int ID);

	public boolean anadirReserva(TReserva tReserva);

	public boolean eliminarReserva(int ID);

	public boolean modificarReserva(TReserva tReserva);

}
