/**
 * 
 */
package integracion.transaccion;

import integracion.transaccion.imp.TransactionFactoryImp;
import integracion.transaccion.imp.TransactionMySQL;

public abstract class TransactionFactory {

	private static TransactionFactory instance;

	public static TransactionFactory getInstance() {
		if (instance == null)
			instance = new TransactionFactoryImp();

		return instance;
	}

	public abstract TransactionMySQL crearTransactionMySQL();
}