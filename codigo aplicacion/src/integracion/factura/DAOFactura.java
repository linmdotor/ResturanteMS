/**
 * 
 */
package integracion.factura;

import java.util.ArrayList;

import negocio.factura.transfer.TFactura;
import negocio.factura.transfer.TFacturaPlato;

public interface DAOFactura {
	
	public ArrayList<TFactura> obtenerFacturas() throws Exception;

	public TFactura read(String ID_Factura) throws Exception; 

	public boolean create(TFactura tFactura) throws Exception; 

	public boolean delete(int ID_Factura) throws Exception; 

	public boolean update(TFactura tFactura) throws Exception;

	public boolean addPlatoAFactura(TFacturaPlato t) throws Exception;

	public TFactura getPlatosDeFactura(String ID_Factura) throws Exception;	
}