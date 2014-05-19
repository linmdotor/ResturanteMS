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
import integracion.query.FactoriaQuery;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.ArrayList;

import negocio.plato.SAPlato;
import negocio.plato.TPlato;

public class SAPlatoImp implements SAPlato {

	// Metodos

	public ArrayList<TPlato> obtenerPlatos() throws Exception {
		
	

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		DAOPlato daoPlato= FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		ArrayList<TPlato> platos = daoPlato.obtenerPlatos();
		if(platos == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudieron obtener los platos, intentelo de nuevo");
			
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		
		
		return platos;

	}

	public TPlato obtenerPlato(int ID) throws Exception {

		

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		TPlato tPlato = daoPlato.read(String.valueOf(ID));
		if(tPlato == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudo obtener el plato porque su id no existe");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		
		
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
			throw new Exception("No se pudo añadir el plato");
		
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
			throw new Exception("No se pudo eliminar el plato");
			
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
			throw new Exception("No se pudo modificar el plato");
		}

	}
	
	@Override
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio() throws Exception{

		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorPrecio p = (PlatoPorPrecio) FactoriaQuery.obtenerInstancia().creaQuery(1);
		
		ArrayList<TPlato> platos = (ArrayList<TPlato>) p.execute(null);
		if(platos == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudieron obtener los platos");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		
		
		return platos;
	}

	@Override
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock() throws Exception{
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorPrecio p = (PlatoPorPrecio) FactoriaQuery.obtenerInstancia().creaQuery(2);
		ArrayList<TPlato> platos = (ArrayList<TPlato>) p.execute(null);
		if(platos == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudieron obtener los platos");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		
		
		return platos;
	}

}
