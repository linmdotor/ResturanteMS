/**
 * 
 */
package integracion.transaccion.imp;

import integracion.transaccion.Transaction;

import java.sql.SQLException;

public class TransactionMySQL implements Transaction {

	private java.sql.Connection connection = null;

	

	public TransactionMySQL(String server,String bbdd,String user, String pass)
	{

		try
		{
			System.out.println("conectando a bbdd");
			Class.forName("com.mysql.jdbc.Driver");
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+server+"/"+bbdd, user,pass);
			System.out.println("conexion exitosa");
		}
		catch(SQLException e)
		{
			System.out.println("Error de MYSQL");
			//return false;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			//return false;
		}
		catch(Exception e)
		{
			System.out.print("Se ha encontrado el siguiente error " + e.getMessage());
		}	

	}

	
	public Boolean start() {
		try {
			connection.setAutoCommit(false);
		
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		// end-user-code
	}


	public Boolean commit() {

		try {
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;

	}


	public Boolean rollback() {

		try {
			connection.rollback();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		return false;

	}


	public java.sql.Connection getResource() {
		return connection;
	}


	public Boolean lock() {
		//connection.
		return null;
	}
}