/**
 * 
 */
package integracion.factura.imp;

import integracion.factura.DAOFactura;
import integracion.transaccion.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.factura.transfer.TFactura;
import negocio.factura.transfer.TFacturaPlato;

public class DAOFacturaImp implements DAOFactura {

	@Override
	public ArrayList<TFactura> obtenerFacturas() throws Exception {
			
		ArrayList<TFactura> listaFacturas = null; 
		
		try {
			
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
					
			ResultSet rs =  c.createStatement().executeQuery("SELECT * FROM Factura ORDER BY Fecha, Hora" + " FOR UPDATE");
					
			listaFacturas = new ArrayList<TFactura>();
			while(rs.next()) {
						
				TFactura tFacturaAux = new TFactura();
						
				tFacturaAux.setID_Factura(rs.getInt("ID_Factura"));
				tFacturaAux.setID_Reserva(rs.getInt("ID_Reserva"));
				tFacturaAux.setNIF_Empresa(rs.getString("NIF_Empresa"));
				tFacturaAux.setNombre_Empresa(rs.getString("Nombre_Empresa"));
				tFacturaAux.setDir_Empresa(rs.getString("Dir_Empresa"));
				tFacturaAux.setNIF_Cliente(rs.getString("NIF_Cliente"));
				tFacturaAux.setNombre_Cliente(rs.getString("Nombre_Cliente"));
				tFacturaAux.setDir_Cliente(rs.getString("Dir_Cliente"));
				tFacturaAux.setFecha(rs.getString("Fecha"));
				tFacturaAux.setHora(rs.getString("Hora"));
				tFacturaAux.setIVA(rs.getShort("IVA"));
				tFacturaAux.setTipo_Servicio(rs.getString("Tipo_Servicio"));
					
				listaFacturas.add(tFacturaAux);

			}
				
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}
		
        return  listaFacturas;
				
	}

	@Override
	public TFactura read(String ID_Factura) throws Exception {
		
		TFactura tFactura = null;
			
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
				
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Factura WHERE ID_Factura = " + ID_Factura + " FOR UPDATE");
							
			tFactura = new TFactura();
			if (rs.next()) {
					
				tFactura = new TFactura();
					
				tFactura.setID_Factura(rs.getInt("ID_Factura"));
				tFactura.setID_Reserva(rs.getInt("ID_Reserva"));
				tFactura.setNIF_Empresa(rs.getString("NIF_Empresa"));
				tFactura.setNombre_Empresa(rs.getString("Nombre_Empresa"));
				tFactura.setDir_Empresa(rs.getString("Dir_Empresa"));
				tFactura.setNIF_Cliente(rs.getString("NIF_Cliente"));
				tFactura.setNombre_Cliente(rs.getString("Nombre_Cliente"));
				tFactura.setDir_Cliente(rs.getString("Dir_Cliente"));
				tFactura.setFecha(rs.getString("Fecha"));
				tFactura.setHora(rs.getString("Hora"));
				tFactura.setIVA(rs.getShort("IVA"));
				tFactura.setTipo_Servicio(rs.getString("Tipo_Servicio"));
				
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {
			e.printStackTrace();	
			throw new Exception ("Error inesperado en la BBDD");
		}
 
		return  tFactura; 
		
	}

	@Override
	public boolean create(TFactura tFactura) {
	
			
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			if(tFactura.getID_Reserva() != 0)
				return c.createStatement().executeUpdate("INSERT INTO Factura (ID_Reserva, Fecha, Hora, NIF_Empresa, Nombre_Empresa, Dir_Empresa, NIF_Cliente, Nombre_Cliente, Dir_Cliente, IVA, Tipo_Servicio) "
													+ "VALUES (" + tFactura.getID_Reserva() + ", '" + tFactura.getFecha() + "', '" + tFactura.getHora() + "', '" + tFactura.getNIF_Empresa() + "', '" + tFactura.getNombre_Empresa() + "', '" + tFactura.getDir_Empresa() + "', '" + tFactura.getNIF_Cliente() + "', '" + tFactura.getNombre_Cliente() + "', '" + tFactura.getDir_Cliente() + "', " + tFactura.getIVA() + ", '" + tFactura.getTipo_Servicio() + "')") >= 1;
			else 
				return c.createStatement().executeUpdate("INSERT INTO Factura (Fecha, Hora, NIF_Empresa, Nombre_Empresa, Dir_Empresa, NIF_Cliente, Nombre_Cliente, Dir_Cliente, IVA, Tipo_Servicio) "
					+ "VALUES ('" + tFactura.getFecha() + "', '" + tFactura.getHora() + "', '" + tFactura.getNIF_Empresa() + "', '" + tFactura.getNombre_Empresa() + "', '" + tFactura.getDir_Empresa() + "', '" + tFactura.getNIF_Cliente() + "', '" + tFactura.getNombre_Cliente() + "', '" + tFactura.getDir_Cliente() + "', " + tFactura.getIVA() + ", '" + tFactura.getTipo_Servicio() + "')") >= 1;
													
		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}	

	@Override
	public boolean delete(int ID_Factura) {	
		
		
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD		

			return c.createStatement().executeUpdate("DELETE FROM Factura WHERE ID_Factura = " + ID_Factura) >= 1;

		} catch (SQLException e) {

			e.printStackTrace();			
			return false;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(TFactura tFactura) {
	
		try {
			Connection c = (Connection)TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD			

			return c.createStatement().executeUpdate("UPDATE Factura SET ID_Reserva = " + tFactura.getID_Reserva() + " , Fecha = '" + tFactura.getFecha() + "' , Hora = '" + tFactura.getHora() + "' , NIF_Empresa = '" + tFactura.getNIF_Empresa() + "' , Nombre_Empresa = '" + tFactura.getNombre_Empresa() + "' , Dir_Empresa = '" + tFactura.getDir_Empresa() + "' , NIF_Cliente = '" + tFactura.getNIF_Cliente() + "' , Nombre_Cliente = '" + tFactura.getNombre_Cliente() + "' , Dir_Cliente = '" + tFactura.getDir_Cliente() + "' , IVA = " + tFactura.getIVA() + " , Tipo_Servicio = '" + tFactura.getTipo_Servicio() + "' "
													+ "WHERE ID_Factura = " + tFactura.getID_Factura()) >= 1;

		} catch (SQLException e) {

			e.printStackTrace();			
			return false;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addPlatoAFactura(TFacturaPlato t) {
				
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
			
			return c.createStatement().executeUpdate("INSERT INTO Factura_Plato (ID_Factura, ID_Plato, Precio, Cantidad) "
													+ "VALUES (" + t.getID_Factura() + ", " + t.getID_Plato() + ", " + t.getPrecio() + ", " + t.getCantidad() + ")") >= 1;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TFactura getPlatosDeFactura(String ID_Factura) throws Exception {
		
		TFactura tFactura = null;
			
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaction().getResource(); // Obtenemos conexion con la BBDD
				
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Factura_Plato WHERE ID_Factura = " + ID_Factura + " FOR UPDATE");
				
			if (rs.next()) {
					
				tFactura = new TFactura();
				
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new Exception ("Problema con SQL de la BBDD");
		} catch (Exception e) {			
			e.printStackTrace();
			throw new Exception ("Error inesperado en la BBDD");
		}
 
		return  tFactura;
	}
}