/**
 * 
 */
package negocio.factura;

public class ValidarTFacturaPlato {
	
	public boolean transferCorrecto(TFacturaPlato tFacturaPlato) {

		boolean transferCorrecto = false;

		if (tFacturaPlato.getPrecio() > 0
				&& tFacturaPlato.getCantidad() > 0)
			
			transferCorrecto = true;

		return transferCorrecto;

	}

}