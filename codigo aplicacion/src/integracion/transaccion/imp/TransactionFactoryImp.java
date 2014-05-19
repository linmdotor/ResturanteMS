/**
 * 
 */
package integracion.transaccion.imp;

public class TransactionFactoryImp extends TransactionFactory 
{
	@Override
	public TransactionMySQL crearTransactionMySQL() {
		return new TransactionMySQL("localhost","Restaurante_DB","root","123456");
	}
	
}