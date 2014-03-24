/**
 * 
 */
package integracion.transaccion.imp;

import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread,Transaction> concurrentHashMap;

	public TransactionManagerImp()
	{
		 concurrentHashMap = new ConcurrentHashMap<Thread,Transaction>();

	}
	@Override
	public void nuevaTransaccion() {
		TransactionMySQL t = TransactionFactory.getInstance().crearTransactionMySQL();
		concurrentHashMap.put(Thread.currentThread(), t);
		
	}

	@Override
	public void eliminarTransaccion() {
		concurrentHashMap.remove(Thread.currentThread());
	}

	@Override
	public Transaction getTransaction() {
		return concurrentHashMap.get(Thread.currentThread());
	}
	
	
}