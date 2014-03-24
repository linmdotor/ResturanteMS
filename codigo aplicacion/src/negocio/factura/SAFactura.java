/**
 * 
 */
package negocio.factura;

import java.util.ArrayList;

public interface SAFactura {
	
	public ArrayList<TFactura> obtenerFacturas() throws Exception;

	public TFactura obtenerFactura(int ID) throws Exception;

	public boolean anadirFactura(TFactura tFactura) throws Exception;

	public boolean eliminarFactura(int ID) throws Exception;

	public boolean anadirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos) throws Exception;

	public boolean existeFacturaPlato(int ID) throws Exception;
	
}