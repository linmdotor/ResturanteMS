/**
 * 
 */
package integracion.transaccion;

import integracion.transaccion.imp.TransactionManagerImp;

public abstract class TransactionManager {
	// Atributos

		private static TransactionManager transactionManager;

		// Metodos

		public static TransactionManager getInstance() {

			if (transactionManager == null)
				transactionManager = new TransactionManagerImp();

			return transactionManager;
		}

		public abstract void nuevaTransaccion();
		
		public abstract void eliminarTransaccion();

		public abstract Transaction getTransaction();
}