/**
 * 
 * Implementacion del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.plato.DAOPlato;
import integracion.plato.PlatoPorPrecio;
import integracion.plato.PlatoPorStock;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.ArrayList;

import negocio.plato.SAPlato;
import negocio.plato.TPlato;

public class SAPlatoImp implements SAPlato {

	// Metodos

	public ArrayList<TPlato> obtenerPlatos() throws Exception {
		
		DAOPlato daoPlato= FactoriaIntegracion.obtenerInstancia().generaDAOPlato();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TPlato> platos = daoPlato.obtenerPlatos();
		if(platos == null)
		{
			t.rollback();
		}
		else
		{
			t.commit();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return platos;

	}

	public TPlato obtenerPlato(int ID) throws Exception {

		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TPlato tPlato = daoPlato.read(String.valueOf(ID));
		if(tPlato == null)
		{
			t.rollback();
		}
		else
		{
			t.commit();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return tPlato;

	}

	public boolean anadirPlato(TPlato tPlato) throws Exception
	{
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();

		
		
		boolean b =  daoPlato.create(tPlato);
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

	public boolean eliminarPlato(int ID) throws Exception{

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();

		
		
		boolean b =  daoPlato.delete(ID);
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

	public boolean modificarPlato(TPlato tPlato)throws Exception {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();

		
		
		boolean b =  daoPlato.update(tPlato);
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
	
	@Override
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio() throws Exception{

		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorPrecio p = new PlatoPorPrecio();
		ArrayList<TPlato> platos = (ArrayList<TPlato>) p.execute(null);
		if(platos == null)
		{
			t.rollback();
		}
		else
		{
			t.commit();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return platos;
	}

	@Override
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock() throws Exception{
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorStock p = new PlatoPorStock();
		ArrayList<TPlato> platos = (ArrayList<TPlato>) p.execute(null);
		if(platos == null)
		{
			t.rollback();	
		}
		else
		{
			t.commit();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return platos;
	}

}
