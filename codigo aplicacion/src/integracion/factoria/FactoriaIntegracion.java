/**
 * 
 * Factoria de la factoria de persistencia
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.factoria;

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
