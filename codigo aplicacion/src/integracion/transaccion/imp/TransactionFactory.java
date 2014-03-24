/**
 * 
 */
package integracion.transaccion.imp;

public abstract class TransactionFactory {

	private static TransactionFactory instance;

	public static TransactionFactory getInstance() {
		if (instance == null)
			instance = new TransactionFactoryImp();

		return instance;
	}

	public abstract TransactionMySQL crearTransactionMySQL();
}