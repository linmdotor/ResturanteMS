/**
 * 
 * Implementacion del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.plato.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.plato.DAOPlato;
import integracion.query.FactoriaQuery;
import integracion.query.imp.PlatoPorNombre;
import integracion.query.imp.PlatoPorPrecio;
import integracion.query.imp.PlatoPorStock;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.plato.SAPlato;
import negocio.plato.transfer.TPlato;

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
	
	public ArrayList<TPlato> obtenerPlatosPorNombre() throws Exception {
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		DAOPlato daoPlato= FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		ArrayList<TPlato> platos = daoPlato.obtenerPlatosPorNombre();
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
	
	public ArrayList<TPlato> obtenerPlatosPorPrecio() throws Exception {
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		DAOPlato daoPlato= FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		ArrayList<TPlato> platos = daoPlato.obtenerPlatosPorPrecio();
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

	public ArrayList<TPlato> obtenerPlatosPorStock() throws Exception {
	
	TransactionManager.getInstance().nuevaTransaccion();
	Transaction t = TransactionManager.getInstance().getTransaction();
	t.start();
	DAOPlato daoPlato= FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
	ArrayList<TPlato> platos = daoPlato.obtenerPlatosPorStock();
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
		
		ArrayList<TPlato> platos = daoPlato.obtenerPlatosPorNombre();
		if(platos == null)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudieron obtener los platos para verificar el nombre, intentelo de nuevo");		
		}
		else //comprueba si el nombre ya existe, plato a plato
		{
			Iterator<TPlato> it = platos.iterator();
			while(it.hasNext()) {
				
				if(it.next().getNombre().compareTo(tPlato.getNombre()) == 0) {
					
					transaction.rollback();
					TransactionManager.getInstance().eliminarTransaccion();
					
					throw new Exception("Ya existe un plato con el mismo nombre");
					
				} 
				
			}
		}
		
		if(tPlato.getPrecio() <= 0) {
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			
			throw new Exception("El precio de debe de ser mayor que 0");
			
		}
		
		if(tPlato.getStock() < 0) {
			
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			
			throw new Exception("El stock debe ser mayor o igual que 0");
			
		}

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

		if(ID < 0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Numero de plato no valido");
		}
		
		if(daoPlato.read(Integer.toString(ID)) == null)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No existe el plato con ID" + ID);
		}
		
		if(daoPlato.delete(ID))
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
		
		//comprueba si le ha cambiado el nombre, y si el nombre nuevo ya existe en la lista
		if(daoPlato.read(Integer.toString(tPlato.getID())).getNombre().compareTo(tPlato.getNombre()) != 0) //ha cambiado el nombre
		{
			ArrayList<TPlato> platos = daoPlato.obtenerPlatosPorNombre();
			if(platos == null)
			{
				transaction.rollback();
				TransactionManager.getInstance().eliminarTransaccion();
				throw new Exception("No se pudieron obtener los platos para verificar el nombre, intentelo de nuevo");		
			}
			else 
			{
				Iterator<TPlato> it = platos.iterator();
				while(it.hasNext()) {
					//Falla si le quiero cambiar el nombre y está repetido
					if(it.next().getNombre().compareTo(tPlato.getNombre()) == 0) {
						
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						
						throw new Exception("Ya existe un plato con el mismo nombre");
						
					} 
					
				}
			}
		}
		
		if(tPlato.getPrecio() <= 0) {
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			
			throw new Exception("El precio de debe de ser mayor que 0");
			
		}
		
		if(tPlato.getStock() < 0) {
			
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			
			throw new Exception("El stock debe ser mayor o igual que 0");
			
		}
		
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
	
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorNombre() throws Exception {
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorNombre p = (PlatoPorNombre) FactoriaQuery.obtenerInstancia().creaQuery(1);
		
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
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorPrecio() throws Exception{

		
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

	@Override
	public ArrayList<TPlato> obtenerPlatosOrdenadosPorStock() throws Exception{
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		PlatoPorPrecio p = (PlatoPorPrecio) FactoriaQuery.obtenerInstancia().creaQuery(3);
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
