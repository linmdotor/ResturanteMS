/**
 * 
 */
package integracion.transaccion.imp;

import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread,Transaction> concurrentHashMap;

	public TransactionManagerImp() {
		 
		concurrentHashMap = new ConcurrentHashMap<Thread,Transaction>();

	}
	@Override
	public void nuevaTransaccion() {
		
		TransactionMySQL t;
		
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
		} else {
			
			t = TransactionFactory.getInstance().crearTransactionMySQL();
			concurrentHashMap.put(Thread.currentThread(), t);
			
		}
		
	}

	@Override
	public void eliminarTransaccion() {
		
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			TransactionMySQL t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
			try {
				
				t.commit();
			
			} catch (Exception e) {
					
				e.printStackTrace();
			
			}
			
			concurrentHashMap.remove(Thread.currentThread());
			
		}
		
	}

	@Override
	public Transaction getTransaction() {
		
		TransactionMySQL t = null ;
		
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
		}
		
		return t;
	}
	
}