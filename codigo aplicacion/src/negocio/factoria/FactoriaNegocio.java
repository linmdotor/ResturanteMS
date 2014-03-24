/**
 * 
 * Factoria de la factoria de persistencia
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.factoria;

import negocio.factoria.imp.FactoriaNegocioImp;
import negocio.factura.SAFactura;
import negocio.plato.SAPlato;
import negocio.reserva.SAReserva;

public abstract class FactoriaNegocio {

	// Atributos

	private static FactoriaNegocio factoria;

	// Metodos

	public static FactoriaNegocio obtenerInstancia() {

		if (factoria == null)
			factoria = new FactoriaNegocioImp();

		return factoria;
	}

	public abstract SAPlato generaSAPlato();
	
	public abstract SAReserva generaSAReserva();
	
	public abstract SAFactura generaSAFactura();

}