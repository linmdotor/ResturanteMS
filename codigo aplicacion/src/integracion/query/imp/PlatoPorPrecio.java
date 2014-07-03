/**
 * 
 */
package integracion.query.imp;

import integracion.query.Query;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.plato.transfer.TPlato;
import negocio.plato.transfer.TPlatoBebida;
import negocio.plato.transfer.TPlatoComida;


public class PlatoPorPrecio implements Query {

	public Object execute(Object objeto) 
	{
		
		TransactionManager tManager = TransactionManager.getInstance();

		Transaction transaction;
		try {
			transaction = tManager.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		java.sql.Connection c = (Connection) transaction.getResource();
		ArrayList<TPlato> lista_comida = new ArrayList<TPlato>();
		ArrayList<TPlato> lista_bebida = new ArrayList<TPlato>();
		ArrayList<TPlato> lista_final = new ArrayList<TPlato>();
        try {
            // Preparamos la consulta
            ResultSet rs = c.createStatement().executeQuery("select p.*, c.Tipo from Plato p, Plato_Comida c where p.ID_Plato = c.ID_Plato_Comida order by p.Precio" + " FOR UPDATE");
 
            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            while (rs.next())
            {
            	TPlatoComida tComida = new TPlatoComida();
            	tComida.setID(rs.getInt("ID_Plato"));
            	tComida.setNombre(rs.getString("Nombre"));
            	tComida.setPrecio(rs.getFloat("Precio"));
            	tComida.setStock(rs.getInt("Stock"));
            	tComida.setTipo(rs.getString("Tipo"));
            	lista_comida.add(tComida);
            
            
            }
            rs = c.createStatement().executeQuery("select p.*, b.Alcoholica from Plato p, Plato_Bebida b where p.ID_Plato = b.ID_Plato_Bebida order by p.Precio" + " FOR UPDATE");
            
            while (rs.next())
            {
            	TPlatoBebida tBebida = new TPlatoBebida();
            	tBebida.setID(rs.getInt("ID_Plato"));
            	tBebida.setNombre(rs.getString("Nombre"));
            	tBebida.setPrecio(rs.getFloat("Precio"));
            	tBebida.setStock(rs.getInt("Stock"));
            	tBebida.setAlcoholica(rs.getBoolean("Alcoholica"));
            	lista_bebida.add(tBebida);
            
            }
            
            //ordena los platos para que sigan estando por precio
            int i=0, j=0; //indices para comida y bebida
            
            //va avanzando en las 2 listas hasta que una se queda sin elementos
            while(( i < lista_comida.size() && lista_comida.get(i) != null) && 
            		(j < lista_bebida.size() && lista_bebida.get(j) != null))
            {
            	if(lista_comida.get(i).getPrecio() < lista_bebida.get(j).getPrecio())
            	{
            		lista_final.add(lista_comida.get(i));
            		i++;
            	}
            	else
            	{
            		lista_final.add(lista_bebida.get(j));
            		j++;
            	}
            }
            //luego recoge el resto de elementos de la que reste
            while( i < lista_comida.size() && lista_comida.get(i) != null)
            {
            	lista_final.add(lista_comida.get(i));
        		i++;
            }
            
            while(j < lista_bebida.size() && lista_bebida.get(j) != null)
            {
            	lista_final.add(lista_bebida.get(j));
        		j++;
            }
            
        } catch (SQLException ex) {
            System.out.println("Imposible realizar consulta ... FAIL");
			return false;
        }
    	
		return lista_final;

	}
}