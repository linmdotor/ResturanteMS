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
	public void nuevaTransaccion() throws Exception {
		
		TransactionMySQL t;
		
		//hay una transacción en curso, no debe crear una nueva.
		if(concurrentHashMap.containsKey(Thread.currentThread())) { 
			
			throw new Exception("Ya existe una transacción en curso. No se puede crear una nueva hasta que finalice la anterior.");
			
		} else {
			
			t = TransactionFactory.getInstance().crearTransactionMySQL();
			concurrentHashMap.put(Thread.currentThread(), t);
			
		}
		
	}

	@Override
	public void eliminarTransaccion() throws Exception {
		
		//hay una transacción en marcha
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			TransactionMySQL t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
			try {
				
				t.commit();
			
			} catch (Exception e) {
					
				throw new Exception("No se ha podido realizar el commit al cerrar la transacción.");					
			}
			
			concurrentHashMap.remove(Thread.currentThread());
			
		}
		else //no existe  transacción actual
		{
			throw new Exception("No existe una transacción en curso. No se puede eliminar la transación.");
			
		}
		
	}

	@Override
	
	public Transaction getTransaction() throws Exception {
		
		TransactionMySQL t = null ;
		
		//Hay transaccion en marcha
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
		}
		else //No existe transacción en curso.
		{
			//se devuelve null
		}
		
		return t;
	}
	
}