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
	
	public ArrayList<TFactura> obtenerFacturas() {
		
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TFactura> facturas = daoFactura.obtenerFacturas();
		if(facturas == null)
		{
			t.rollback();	
		}
		else
		{
			t.commit();	
		}

		TransactionManager.getInstance().eliminarTransaccion();
		
		return facturas;
	}

	public TFactura obtenerFactura(int ID) {

		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TFactura tFactura = daoFactura.read(String.valueOf(ID));
		if(tFactura == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		return tFactura;
	}

	public boolean a�adirFactura(TFactura tFactura) {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
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
			return false;
		}
	}

	public boolean eliminarFactura(int ID) {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		boolean b =  daoFactura.delete(ID);
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
			return false;
		}
	}

	public boolean a�adirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos) {
		
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
	public boolean existeFacturaPlato(int ID) {
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