/**
 * 
 * Factoria de persistencia
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.factoria.imp;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.imp.SAFacturaImp;
import negocio.plato.SAPlato;
import negocio.plato.imp.SAPlatoImp;
import negocio.reserva.SAReserva;
import negocio.reserva.imp.SAReservaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	// Metodos	

	public SAPlato generaSAPlato() {

		return new SAPlatoImp();

	}
	
	public SAReserva generaSAReserva() {

		return new SAReservaImp();

	}
	
	public SAFactura generaSAFactura() {
	
		return new SAFacturaImp();
	
	}
	
}