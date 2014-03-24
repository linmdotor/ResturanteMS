/**
 * 
 */
package negocio.factura;

public class ValidarTFactura {
	
	public boolean transferCorrecto(TFactura tFactura) {

		boolean transferCorrecto = false;

		if (tFactura.getNIF_Empresa().length() > 0
				&& tFactura.getNombre_Empresa().length() > 0
				&& tFactura.getDir_Empresa().length() > 0
				&& tFactura.getFecha().length() > 0
				&& tFactura.getHora().length() > 0
				&& tFactura.getIVA() > 0
				&& tFactura.getTipo_Servicio().length() > 0)
			
			transferCorrecto = true;

		return transferCorrecto;

	}

}