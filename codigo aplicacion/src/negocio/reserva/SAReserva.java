/**
 * 
 * Interfaz del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva;

import java.util.ArrayList;

import negocio.reserva.transfer.TReserva;

public interface SAReserva {

	public ArrayList<TReserva> obtenerReservas() throws Exception;

	public TReserva obtenerReserva(int ID) throws Exception;

	public boolean anadirReserva(TReserva tReserva) throws Exception;

	public boolean eliminarReserva(int ID) throws Exception;

	public boolean modificarReserva(TReserva tReserva) throws Exception;

}
