/**
 * 
 * Factoria de persistencia
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
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
