/**
 * 
 */
package integracion.transaccion.imp;

import integracion.transaccion.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionMySQL implements Transaction {

	private java.sql.Connection connection = null;	

	public TransactionMySQL(String server,String bbdd,String user, String pass)
	{

		try
		{
			System.out.println("conectando a bbdd");
			Class.forName("com.mysql.jdbc.Driver");
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+server+"/"+bbdd, user, pass);
			System.out.println("conexion exitosa");
			System.out.println("----------------");
		}
		catch(SQLException e)
		{
			System.out.println("Error de MYSQL");
			System.out.println("--------------");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.print("Se ha encontrado el siguiente error al crear la transacción: " + e.getMessage());
		}	

	}

	
	public void start() throws Exception {
		try {
			connection.setAutoCommit(false);
			connection.prepareStatement("INICIO TRANSACCION");
		
		} catch (SQLException e) {
			throw new SQLException("Error al conectar");		
		}
	}

	public void commit() throws Exception {

		try {
			PreparedStatement commit = connection.prepareStatement("COMMIT");
			commit.execute();
		} catch (SQLException e) {
			rollback();
			throw new Exception("Error al realizar commit");		
		}		
	}


	public void rollback() throws Exception {

		try {
			PreparedStatement rollback = connection.prepareStatement("ROLLBACK");
			rollback.execute();
		} catch (SQLException e) {
			throw new Exception("Error al hacer rollback");		
		}
	}


	public Object getResource() {
		return connection;
	}


}