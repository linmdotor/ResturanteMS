/**
 * 
 */
package integracion.transaccion;



public interface Transaction {
	
	public Boolean start();

	public Boolean commit();

	public Boolean rollback();

	public java.sql.Connection getResource();

	public Boolean lock();
}