/**
 * 
 * Factoria de persistencia
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.factoria;

import integracion.factura.DAOFactura;
import integracion.factura.DAOFacturaImp;
import integracion.plato.DAOPlato;
import integracion.plato.DAOPlatoImp;
import integracion.reserva.DAOReserva;
import integracion.reserva.DAOReservaImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	// Metodos	

	public DAOPlato generaDAOPlato() {
		return new DAOPlatoImp();
	}
	
	public DAOReserva generaDAOReserva() {
		return new DAOReservaImp();
	}
	
	public DAOFactura generaDAOFactura() {
		return new DAOFacturaImp();
	}

}
