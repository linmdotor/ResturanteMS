/**
 * 
 */
package integracion.reserva;

import integracion.transaccion.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.reserva.TReserva;

public class DAOReservaImp implements DAOReserva {

	@Override
	public boolean existeReserva(int ID_Reserva)
	{
		boolean resultado = false;

		try {

			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			if( c.createStatement().executeQuery("SELECT * FROM Reserva WHERE ID_Reserva = " + ID_Reserva + " FOR UPDATE").next()) //existe una reserva
				resultado = true;		
		
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@Override
	public ArrayList<TReserva> obtenerReservas() {
				
		ArrayList<TReserva> listaReservas = null;
			
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Reserva ORDER BY Fecha, Hora" + " FOR UPDATE");
			
			listaReservas = new ArrayList<TReserva>();
			while(rs.next()) {
					
				TReserva tReservaAux = new TReserva();
				tReservaAux.setID(rs.getInt("ID_Reserva"));
				tReservaAux.setDNI(rs.getString("DNI"));
				tReservaAux.setNombre(rs.getString("Nombre"));
				tReservaAux.setFecha(rs.getDate("Fecha").toString());
				tReservaAux.setHora(rs.getTime("Hora").toString());
				tReservaAux.setTelefono(rs.getString("Telefono"));
				tReservaAux.setN_Comensales(rs.getShort("N_Comensales"));
				
				listaReservas.add(tReservaAux);
			}
			
		} catch (SQLException e) {
				
		e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();			
		}
		
		return listaReservas;
			
	}
		 	 

	@Override
	public TReserva read(String ID_Reserva) {
		
		
		TReserva tReserva = null;
			
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Reserva WHERE ID_Reserva = " + ID_Reserva + " FOR UPDATE");
				
			tReserva = new TReserva();
			if (rs.next()) {
					
				tReserva.setID(rs.getInt("ID_Reserva"));
				tReserva.setDNI(rs.getString("DNI"));
				tReserva.setNombre(rs.getString("Nombre"));
				tReserva.setFecha(rs.getDate("Fecha").toString());
				tReserva.setHora(rs.getTime("Hora").toString());
				tReserva.setTelefono(rs.getString("Telefono"));
				tReserva.setN_Comensales(rs.getShort("N_Comensales"));
			
			}
			
		} catch (SQLException e) {
				
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();			
		}
		
			
		return tReserva;
			
	}

	@Override
	public boolean create(TReserva tReserva) {
	
		int rdo = 0;		
			
		try {
			Connection c = (Connection)  TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			rdo = c.createStatement().executeUpdate("INSERT INTO Reserva (DNI, Nombre, Fecha, Hora, Telefono, N_Comensales) "
													+ "VALUES ('" + tReserva.getDNI() + "', '"  + tReserva.getNombre() + "', '"  + tReserva.getFecha() + "', '"  + tReserva.getHora() + "', '"  + tReserva.getTelefono() + "', "  + tReserva.getN_Comensales() + ")");
				
		} catch (SQLException e) {
				
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();			
		}
	
		return rdo >= 1;
		
	}

	@Override
	public boolean delete(int ID_Reserva) {
				
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD	
			
			return c.createStatement().executeUpdate("DELETE FROM Reserva WHERE ID_Reserva = " + ID_Reserva) >= 1;
				
		} catch (SQLException e) {
				
			e.printStackTrace();
			return false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(TReserva tReserva) {
		
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD

			return c.createStatement().executeUpdate("UPDATE Reserva SET DNI = '" + tReserva.getDNI() + "' , Nombre = '" + tReserva.getNombre() + "' , Fecha = '" + tReserva.getFecha() + "' , Hora = '" + tReserva.getHora() + "' , Telefono = '" + tReserva.getTelefono() + "' , N_Comensales = " + tReserva.getN_Comensales() + " "
													+ "WHERE ID_Reserva = " + tReserva.getID()) >= 1;
				
		} catch (SQLException e) {
				
			e.printStackTrace();			
			return false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
		
}