/**
 * 
 * Factoria de la factoria de persistencia
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.factoria;

import integracion.factoria.imp.FactoriaIntegracionImp;
import integracion.factura.DAOFactura;
import integracion.plato.DAOPlato;
import integracion.reserva.DAOReserva;

public abstract class FactoriaIntegracion {

	// Atributos

	private static FactoriaIntegracion factoria;

	// Metodos

	public static FactoriaIntegracion obtenerInstancia() {

		if (factoria == null)
			factoria = new FactoriaIntegracionImp();

		return factoria;
	}

	public abstract DAOPlato generaDAOPlato();
	
	public abstract DAOReserva generaDAOReserva();
	
	public abstract DAOFactura generaDAOFactura();

}
