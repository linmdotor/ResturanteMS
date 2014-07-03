package integracion.transaccion.imp;

import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionFactory;
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
		
		//hay una transacci�n en curso, no debe crear una nueva.
		if(concurrentHashMap.containsKey(Thread.currentThread())) { 
			
			throw new Exception("Ya existe una transaccion en curso. No se puede crear una nueva hasta que finalice la anterior.");
			
		} else {
			
			t = TransactionFactory.getInstance().crearTransactionMySQL();
			concurrentHashMap.put(Thread.currentThread(), t);
			
		}
		
	}

	@Override
	public void eliminarTransaccion() throws Exception {
		
		//hay una transaccion en marcha
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			TransactionMySQL t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
			try {
				
				t.commit();
			
			} catch (Exception e) {
					
				throw new Exception("No se ha podido realizar el commit al cerrar la transaccion.");					
			}
			
			concurrentHashMap.remove(Thread.currentThread());
			
		}
		else //no existe  transaccion actual
		{
			throw new Exception("No existe una transaccion en curso. No se puede eliminar la transacion.");
			
		}
		
	}

	@Override
	
	public Transaction getTransaction() throws Exception {
		
		TransactionMySQL t = null ;
		
		//Hay transaccion en marcha
		if(concurrentHashMap.containsKey(Thread.currentThread())) {
			
			t = (TransactionMySQL) concurrentHashMap.get(Thread.currentThread());
			
		}
		else //No existe transacci�n en curso.
		{
			//se devuelve null
		}
		
		return t;
	}
	
}