/**
 * 
 * Implementacion del Servicio de Aplicacion de Platos
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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

	public ArrayList<TPlato> obtenerPlatos() {
		
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

	public TPlato obtenerPlato(int ID) {

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

	public boolean añadirPlato(TPlato tPlato) 
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

	public boolean eliminarPlato(int ID) {

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

	public boolean modificarPlato(TPlato tPlato) {

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
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio() {

		
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
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock() {
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
