/**
 * 
 */
package integracion.transaccion;



public interface Transaction {
	
	public Boolean start();

	public Boolean commit();

	public Boolean rollback();

	public Object getResource();

	public Boolean lock();
}