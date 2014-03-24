/**
 * 
 * Factoria de persistencia
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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