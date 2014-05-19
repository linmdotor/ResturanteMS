/**
 * 
 */
package integracion.factura;

import java.util.ArrayList;

import negocio.factura.TFactura;
import negocio.factura.TFacturaPlato;

public interface DAOFactura {
	
	public ArrayList<TFactura> obtenerFacturas() throws Exception;

	public TFactura read(String ID_Factura) throws Exception; 

	public boolean create(TFactura tFactura); 

	public boolean delete(int ID_Factura); 

	public boolean update(TFactura tFactura);

	public boolean addPlatoAFactura(TFacturaPlato t);

	public TFactura getPlatosDeFactura(String ID_Factura) throws Exception;	
}