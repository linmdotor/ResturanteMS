/**
 * 
 */
package integracion.plato;

import integracion.query.Query;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.plato.TPlato;
import negocio.plato.TPlatoBebida;
import negocio.plato.TPlatoComida;


public class PlatoPorStock implements Query {

	public Object execute(Object objeto) 
	{
		
		TransactionManager tManager = TransactionManager.getInstance();

		Transaction transaction = tManager.getTransaction();
		java.sql.Connection c = (Connection)transaction.getResource();

		//esto lo hago para probar que realiza consultas
		//realizar consulta
		ArrayList<TPlato> lista = new ArrayList<TPlato>();
        try {
            // Preparamos la consulta
           // Statement s = t.getConnection().createStatement();
            ResultSet rs = c.createStatement().executeQuery("select p.*, c.Tipo from Plato p, Plato_Comida c where p.ID_Plato = c.ID_Plato_Comida order by p.Stock");
 
            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            while (rs.next())
            {
            	TPlatoComida tComida = new TPlatoComida();
            	tComida.setID(rs.getInt("ID_Plato"));
            	tComida.setNombre(rs.getString("Nombre"));
            	tComida.setPrecio(rs.getFloat("Precio"));
            	tComida.setStock(rs.getInt("Stock"));
            	tComida.setTipo(rs.getString("Tipo"));
            	//tComida.setTipo(tComida.rs.getString("Tipo"));
            	lista.add(tComida);
            
            
            }
            rs = c.createStatement().executeQuery("select p.*, b.Alcoholica from Plato p, Plato_Bebida b where p.ID_Plato = b.ID_Plato_Bebida order by p.Stock");
            
            while (rs.next())
            {
            	TPlatoBebida tBebida = new TPlatoBebida();
            	tBebida.setID(rs.getInt("ID_Plato"));
            	tBebida.setNombre(rs.getString("Nombre"));
            	tBebida.setPrecio(rs.getFloat("Precio"));
            	tBebida.setStock(rs.getInt("Stock"));
            	tBebida.setAlcoholica(rs.getBoolean("Alcoholica"));
            	lista.add(tBebida);
            
            }
            
        } catch (SQLException ex) {
            System.out.println("Imposible realizar consulta ... FAIL");
			return false;
        }
    
		return lista;
	
	}
}