/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva;

import java.util.ArrayList;

public interface SAReserva {

	public ArrayList<TReserva> obtenerReservas();

	public TReserva obtenerReserva(int ID);

	public boolean añadirReserva(TReserva tReserva);

	public boolean eliminarReserva(int ID);

	public boolean modificarReserva(TReserva tReserva);

}
