/**
 * 
 * Implementacion del DAO de Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package integracion.plato;

import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.factura.TFacturaPlato;
import negocio.plato.TPlato;
import negocio.plato.TPlatoBebida;
import negocio.plato.TPlatoComida;

public class DAOPlatoImp implements DAOPlato {


	@Override
	public ArrayList<TPlato> obtenerPlatos()  throws Exception
	{
	
		ArrayList<TPlato> listaPlatos = null;
		
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			ResultSet rs =  c.createStatement().executeQuery("SELECT p.*,c.Tipo "
															+ "FROM Plato p, Plato_Comida c  "
															+ "WHERE p.ID_Plato = c.ID_Plato_Comida "
															+ "ORDER BY c.Tipo, p.Nombre"
															+ " FOR UPDATE");
			
			listaPlatos = new ArrayList<TPlato>();
			while(rs.next()) {
				
				TPlatoComida tPlatoAux = new TPlatoComida();
				
				tPlatoAux.setID(rs.getInt("ID_Plato"));
				tPlatoAux.setNombre(rs.getString("Nombre"));
				tPlatoAux.setPrecio(rs.getFloat("Precio"));
				tPlatoAux.setStock(rs.getInt("Stock"));
				tPlatoAux.setTipo(rs.getString("Tipo"));
				
				listaPlatos.add(tPlatoAux);

			}
			rs =  c.createStatement().executeQuery("SELECT p.*,b.Alcoholica "
													+ "FROM Plato p, Plato_Bebida b  "
													+ "WHERE p.ID_Plato = b.ID_Plato_Bebida "
													+ "ORDER BY b.Alcoholica, p.Nombre"
													+ " FOR UPDATE");
			
			while(rs.next()) {
				
				TPlatoBebida tPlatoAux = new TPlatoBebida();
				
				tPlatoAux.setID(rs.getInt("ID_Plato"));
				tPlatoAux.setNombre(rs.getString("Nombre"));
				tPlatoAux.setPrecio(rs.getFloat("Precio"));
				tPlatoAux.setStock(rs.getInt("Stock"));
				int alcoholica = rs.getInt("Alcoholica");
				if(alcoholica == 0)
				{
					tPlatoAux.setAlcoholica(false);
				}
				else
				{
					tPlatoAux.setAlcoholica(true);
				}
				
				
				listaPlatos.add(tPlatoAux);

			}
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
			
		} catch (Exception e) {			
			e.printStackTrace();	
			throw new Exception ("Error inesperado en la BBDD");
		}
 
        return  listaPlatos;	
	}
	
	public TPlato read(String ID_Plato) throws Exception 
	{ 
		
		TransactionManager tManager = TransactionManager.getInstance();
		
		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		
		java.sql.Connection c = (Connection)transaction.getResource();
		TPlatoComida tPlatoComida = new TPlatoComida();
		TPlatoBebida tPlatoBebida = new TPlatoBebida();
			
		try {
			
			ResultSet rs =  c.createStatement().executeQuery("SELECT p.*,c.Tipo "
															+ "FROM Plato p, Plato_Comida c "
															+ "WHERE p.ID_Plato = c.ID_plato_Comida AND p.ID_Plato = " + ID_Plato
															+ " FOR UPDATE");
			
			if (rs.next()) {
				
				tPlatoComida.setID(rs.getInt("ID_Plato"));
				tPlatoComida.setNombre(rs.getString("Nombre"));
				tPlatoComida.setPrecio(rs.getFloat("Precio"));
				tPlatoComida.setStock(rs.getInt("Stock"));
				tPlatoComida.setTipo(rs.getString("Tipo"));
				return tPlatoComida;
			}
			else
			{
				rs =  c.createStatement().executeQuery("SELECT p.*,b.Alcoholica "
														+ "FROM Plato p, Plato_Bebida b "
														+ "WHERE p.ID_Plato = b.ID_plato_Bebida AND p.ID_Plato = " + ID_Plato
														+ " FOR UPDATE");
				
				if (rs.next()) {
					
					tPlatoBebida.setID(rs.getInt("ID_Plato"));
					tPlatoBebida.setNombre(rs.getString("Nombre"));
					tPlatoBebida.setPrecio(rs.getFloat("Precio"));
					tPlatoBebida.setStock(rs.getInt("Stock"));
					int alcoholica = rs.getInt("Alcoholica");
					if(alcoholica == 0)
					{
						tPlatoBebida.setAlcoholica(false);
					}
					else
					{
						tPlatoBebida.setAlcoholica(true);
					}
					return tPlatoBebida;
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {		
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}

		return null;
		
	}


	@Override
	public boolean create(TPlato tPlato) throws Exception 
	{
		
		TransactionManager tManager = TransactionManager.getInstance();
		
		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		java.sql.Connection c = (Connection)transaction.getResource();
			
		try {
			c.createStatement().executeUpdate("INSERT INTO Plato (Nombre, Precio, Stock) VALUES ('" + tPlato.getNombre() + "', "  + tPlato.getPrecio()  + ", "  + tPlato.getStock() + ")");
			
			ResultSet rs = c.createStatement().executeQuery("SELECT ID_Plato "
															+ "FROM Plato "
															+ "WHERE  Nombre = '" +  tPlato.getNombre() +"'"
															+ " FOR UPDATE");
			
			int id = 0;
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			if(tPlato instanceof TPlatoComida) 
			{
				TPlatoComida p = (TPlatoComida) tPlato;
				c.createStatement().executeUpdate("INSERT INTO Plato_Comida (ID_Plato_Comida, Tipo) VALUES (" + id + ", '"  + p.getTipo() +"')");
			
				return true;
				
			}
			
			if(tPlato instanceof TPlatoBebida) 
			{
				TPlatoBebida p = (TPlatoBebida) tPlato;
				int esAlcoholica = 0;
				if(p.isAlcoholica())
				{
					esAlcoholica = 1;
				}
				c.createStatement().executeUpdate("INSERT INTO Plato_Bebida (ID_Plato_Bebida, Alcoholica) VALUES (" + id + ", "  + esAlcoholica +")");
				
				return true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {		
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}
		
		return false;
	
	}

	@Override
	public boolean delete(int ID_Plato) throws Exception 
	{
		
		TransactionManager tManager = TransactionManager.getInstance();
		
		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		java.sql.Connection c = (Connection)transaction.getResource();
		
		int rdo = 0;
			
		try {
			//la base de datos no hace un "on delete cascade" porque las facturas son elementos que se deben conservar.
			rdo = c.createStatement().executeUpdate("DELETE FROM Plato WHERE ID_Plato =" + ID_Plato);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {		
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}
		
		if(rdo == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}

	@Override
	public boolean update(TPlato tPlato) throws Exception 
	{
		TransactionManager tManager = TransactionManager.getInstance();
		
		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		java.sql.Connection c = (Connection)transaction.getResource();
			
		try {
			c.createStatement().executeUpdate("UPDATE Plato SET Nombre = '" + tPlato.getNombre() +"',  Precio = " + tPlato.getPrecio() + " , Stock = " + tPlato.getStock() + " "
											+ "WHERE ID_Plato = " + tPlato.getID() );
			
			if(tPlato instanceof TPlatoComida) 
			{
				TPlatoComida p = (TPlatoComida) tPlato;
				c.createStatement().executeUpdate("UPDATE Plato_Comida SET Tipo = '" + p.getTipo() + "' "
												+ "WHERE ID_Plato_Comida = " + tPlato.getID() );
				
				return true;
				
			}
			
			if(tPlato instanceof TPlatoBebida) 
			{
				TPlatoBebida p = (TPlatoBebida) tPlato;
				int esAlcoholica = 0;
				if(p.isAlcoholica())
				{
					esAlcoholica = 1;
				}
				
				c.createStatement().executeUpdate("UPDATE Plato_Bebida SET Alcoholica = " + esAlcoholica + " "
												+ "WHERE ID_Plato_Bebida = " + tPlato.getID() );
				
				return true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {		
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}
		
		return false;
	}

	@Override
	public boolean actualizarStock(TFacturaPlato tFacturaPlato) throws Exception {
		
		TransactionManager tManager = TransactionManager.getInstance();
		
		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		java.sql.Connection c = (Connection)transaction.getResource();		
			
		try {
			
			ResultSet rs =  c.createStatement().executeQuery("SELECT * from Plato WHERE ID_Plato = " + tFacturaPlato.getID_Plato() + " FOR UPDATE");		
			if(rs.next())
			{
				int stock = rs.getInt("Stock") - tFacturaPlato.getCantidad();
				c.createStatement().executeUpdate("UPDATE Plato SET Stock = " + stock + " WHERE ID_Plato = " + tFacturaPlato.getID_Plato() );
				
				return true;
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {		
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}	
		
		return false;
	}

}
