/**
 * 
 */
package negocio.factura.transfer;

public class ValidarTFacturaPlato {
	
	public boolean transferCorrecto(TFacturaPlato tFacturaPlato) {

		boolean transferCorrecto = false;

		if (tFacturaPlato.getPrecio() > 0
				&& tFacturaPlato.getCantidad() > 0 
				&& tFacturaPlato.getID_Factura() >0 
				&& tFacturaPlato.getID_Plato() >0)
		{
			transferCorrecto = true;
		}
		return transferCorrecto;

	}

}