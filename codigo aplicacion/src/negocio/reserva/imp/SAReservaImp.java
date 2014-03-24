/**
 * 
 * Implementacion del Servicio de Aplicacion de Platos
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.reserva.DAOReserva;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.ArrayList;

import negocio.reserva.SAReserva;
import negocio.reserva.TReserva;

public class SAReservaImp implements SAReserva {

	// Metodos

	public ArrayList<TReserva> obtenerReservas() {

		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TReserva> reservas = daoReserva.obtenerReservas();
		if(reservas == null)
		{
			t.rollback();
		}
		else
		{
			t.commit();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return reservas;

	}

	public TReserva obtenerReserva(int ID) {

		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TReserva tReserva = daoReserva.read(String.valueOf(ID));
		if(tReserva == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		return tReserva;

	}

	public boolean añadirReserva(TReserva tReserva) 
	{
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();


		boolean b =  daoReserva.create(tReserva);
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

	public boolean eliminarReserva(int ID) {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		
		
		boolean b =  daoReserva.delete(ID);
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

	public boolean modificarReserva(TReserva tReserva) {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		
		
		boolean b =  daoReserva.update(tReserva);
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

}
