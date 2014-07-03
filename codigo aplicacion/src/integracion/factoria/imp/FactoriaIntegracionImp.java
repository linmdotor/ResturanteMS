/**
 * 
 * Factoria de persistencia
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.factoria.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.factura.imp.DAOFacturaImp;
import integracion.plato.DAOPlato;
import integracion.plato.imp.DAOPlatoImp;
import integracion.reserva.DAOReserva;
import integracion.reserva.imp.DAOReservaImp;

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
