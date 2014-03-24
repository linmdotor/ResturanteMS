/**
 * 
 */
package negocio.factura;

import java.util.ArrayList;

public interface SAFactura {
	
	public ArrayList<TFactura> obtenerFacturas();

	public TFactura obtenerFactura(int ID);

	public boolean a�adirFactura(TFactura tFactura);

	public boolean eliminarFactura(int ID);

	public boolean a�adirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos);

	public boolean existeFacturaPlato(int ID);
	
}