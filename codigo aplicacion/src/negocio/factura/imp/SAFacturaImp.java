/**
 * 
 */
package negocio.factura.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.plato.DAOPlato;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.sql.Date;
import java.util.ArrayList;

import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.factura.TFacturaPlato;

public class SAFacturaImp implements SAFactura {

	// Metodos
	
	public ArrayList<TFactura> obtenerFacturas() throws Exception {
		
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TFactura> facturas = daoFactura.obtenerFacturas();
		if(facturas == null)
		{
			
			t.rollback();	
			throw new Exception("No existen facturas");
		}
		else
		{
			t.commit();	
		}

		TransactionManager.getInstance().eliminarTransaccion();
		
		return facturas;
	}

	public TFactura obtenerFactura(int ID) throws Exception {

		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TFactura tFactura = daoFactura.read(String.valueOf(ID));
		if(tFactura == null)
		{
			t.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No existe la factura");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		return tFactura;
	}

	public boolean anadirFactura(TFactura tFactura) throws Exception {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		
		if(tFactura == null)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Factura nula");
		}
		if(tFactura.getDir_Cliente().equals("") || tFactura.getNombre_Cliente().equals("") 
				|| tFactura.getNIF_Cliente().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al cliente");
		}
		
		//validacion dni cliente
		if(tFactura.getNIF_Cliente().length() <8 ||  tFactura.getNIF_Cliente().length() >9)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("El dni no tiene la longitud necesaria");
			
		}
		else
		{
			for(int i = 0; i < tFactura.getNIF_Cliente().length();i++)
			{
				if(i==tFactura.getNIF_Cliente().length()-1)
				{
					if(tFactura.getNIF_Cliente().toUpperCase().charAt(i)<'A' || 
						tFactura.getNIF_Cliente().toUpperCase().charAt(i)>'Z')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el dni no contiene una letra al final");
					}
				}
				else
				{
					if(tFactura.getNIF_Cliente().charAt(i)<'0' ||  tFactura.getNIF_Cliente().charAt(i)>'9')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el dni debe contener numeros");
					}
				}
			}
		}
		//comprobamos la hora
		
	
				
		if(tFactura.getDir_Empresa().equals("")  || tFactura.getNIF_Empresa().equals("")  
		|| tFactura.getNombre_Empresa().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa a la empresa");
		}
		
		//validacion nif empresa
		if(tFactura.getNIF_Empresa().length() <8 ||  tFactura.getNIF_Empresa().length() >9)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("El nif de la empresa no tiene la longitud necesaria");
			
		}
		else
		{
			for(int i = 0; i < tFactura.getNIF_Empresa().length();i++)
			{
				if(i==tFactura.getNIF_Empresa().length()-1)
				{
					if(tFactura.getNIF_Empresa().toUpperCase().charAt(i)<'A' || 
						tFactura.getNIF_Empresa().toUpperCase().charAt(i)>'Z')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el nif de la empresa no contiene una letra al final");
					}
				}
				else
				{
					if(tFactura.getNIF_Empresa().charAt(i)<'0' ||  tFactura.getNIF_Empresa().charAt(i)>'9')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el nif de la empresa debe contener numeros");
					}
				}
			}
		}
		
	
		if(tFactura.getFecha().equals("") || tFactura.getHora().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa a la fecha/hora");
		}
		if(tFactura.getIVA() <0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al IVA");
		}
		if(tFactura.getID_Reserva()<0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al numero de la reserva");
		}
		if(tFactura.getTipo_Servicio().equals(""))
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Falta informacion relativa al tipo de servicio");
		}
		
		
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();


		boolean b =  daoFactura.create(tFactura);
		if(b)
		{
			transaction.commit();
			TransactionManager.getInstance().eliminarTransaccion();
			return true;
		}
		else
		{
			transaction.rollback();
			
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudo crear la factura");
		
		}
	}

	public boolean eliminarFactura(int ID) throws Exception {

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();
		if(ID < 0)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("Numero de factura no valido");
		}

		if(daoFactura.delete(ID))
		{
			transaction.commit();
			TransactionManager.getInstance().eliminarTransaccion();
			
		}
		return true;
	
	}

	public boolean anadirPlatosAFactura(ArrayList<TFacturaPlato> listatFacturaPlatos) throws Exception {
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();
		DAOPlato daoPlato = FactoriaIntegracion.obtenerInstancia().generaDAOPlato();
		
		boolean resultado = true;
		for(TFacturaPlato t : listatFacturaPlatos)
		{
			resultado =  resultado && daoFactura.addPlatoAFactura(t);
			resultado =  resultado && daoPlato.actualizarStock(t);
		}
		
		if(resultado)
		{
			transaction.commit();
		}
		else
		{
			transaction.rollback();
		}
		
		TransactionManager.getInstance().eliminarTransaccion();
		
		return resultado;
	}

	@Override
	public boolean existeFacturaPlato(int ID) throws Exception {
		DAOFactura daoFactura = FactoriaIntegracion.obtenerInstancia().generaDAOFactura();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TFactura tFactura = daoFactura.getPlatosDeFactura(String.valueOf(ID));
		if(tFactura == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
		
}