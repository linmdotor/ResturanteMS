/**
 * 
 */
package negocio.factura.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.plato.DAOPlato;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.ArrayList;

import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.factura.TFacturaPlato;

public class SAFacturaImp implements SAFactura {

	// Metodos
	
	public ArrayList<TFactura> obtenerFacturas() throws Exception {
		
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TFactura> facturas = daoFactura.obtenerFacturas();
		if(facturas == null)
		{
			
			t.rollback();	
			throw new Exception("No existen facturas");
		}
		else
		{
			t.commit();	
		}

		TransactionManager.getInstance().eliminarTransaccion();
		
		return facturas;
	}

	public TFactura obtenerFactura(int ID) throws Exception {

		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TFactura tFactura = daoFactura.read(String.valueOf(ID));
		if(tFactura == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No existe la factura");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		return tFactura;
	}

	public boolean anadirFactura(TFactura tFactura) throws Exception {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		
		if(tFactura == null)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Factura nula");
		}
		if(tFactura.getDir_Cliente().equals("") || tFactura.getNombre_Cliente().equals("") 
				|| tFactura.getNIF_Cliente().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al cliente");
		}
		if(tFactura.getDir_Empresa().equals("")  || tFactura.getNIF_Empresa().equals("")  
		|| tFactura.getNombre_Empresa().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa a la empresa");
		}
		if(tFactura.getFecha().equals("") || tFactura.getHora().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa a la fecha/hora");
		}
		if(tFactura.getIVA() <0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al IVA");
		}
		if(tFactura.getID_Reserva()<0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al numero de la reserva");
		}
		if(tFactura.getTipo_Servicio().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al tipo de servicio");
		}
		
		
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();


		boolean b =  daoFactura.create(tFactura);
		if(b)
		{
			transaction.commit();
			TransactionManager.getInstance().eliminarTransaccion();
			return true;
		}
		else
		{
			transaction.rollback();
			
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudo crear la factura");
		
		}
	}

	public boolean eliminarFactura(int ID) throws Exception {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();
		if(ID < 0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Numero de factura no valido");
		}

		if(daoFactura.delete(ID))
		{
			transaction.commit();
			TransactionManager.getInstance().eliminarTransaccion();
			
		}
		return true;
	
	}

	public boolean anadirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos) throws Exception {
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		
		boolean resultado = true;
		for(TFacturaPlato t : listatFacturaPlatos)
		{
			resultado =  resultado && daoFactura.addPlatoAFactura(t);
			resultado =  resultado && daoPlato.actualizarStock(t);
		}
		
		if(resultado)
		{
			transaction.commit();
		}
		else
		{
			transaction.rollback();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return resultado;
	}

	@Override
	public boolean existeFacturaPlato(int ID) throws Exception {
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TFactura tFactura = daoFactura.getPlatosDeFactura(String.valueOf(ID));
		if(tFactura == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
		
}