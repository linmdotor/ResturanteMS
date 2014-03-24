/**
 * 
 */
package negocio.factura;

import java.util.ArrayList;

public interface SAFactura {
	
	public ArrayList<TFactura> obtenerFacturas();

	public TFactura obtenerFactura(int ID);

	public boolean anadirFactura(TFactura tFactura);

	public boolean eliminarFactura(int ID);

	public boolean anadirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos);

	public boolean existeFacturaPlato(int ID);
	
}