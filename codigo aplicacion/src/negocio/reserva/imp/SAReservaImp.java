/**
 * 
 * Implementacion del Servicio de Aplicacion de Platos
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package negocio.reserva.imp;

import integracion.factoria.FactoriaIntegracion;
import integracion.reserva.DAOReserva;
import integracion.transaccion.Transaction;
import integracion.transaccion.TransactionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;
import negocio.reserva.SAReserva;
import negocio.reserva.TReserva;

public class SAReservaImp implements SAReserva {

	// Metodos

	public ArrayList<TReserva> obtenerReservas() throws Exception{

		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		
		ArrayList<TReserva> reservas = daoReserva.obtenerReservas();
		if(reservas == null)
		{
			t.rollback();
			
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudieron obtener las reservas");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		
		
		return reservas;

	}

	public TReserva obtenerReserva(int ID) throws Exception{

		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction t = TransactionManager.getInstance().getTransaction();
		t.start();
		TReserva tReserva = daoReserva.read(String.valueOf(ID));
		if(tReserva == null)
		{
			t.rollback();
			
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudo obtener la reserva porque el numero de reserva no existe");
		}
		else
		{
			t.commit();
			TransactionManager.getInstance().eliminarTransaccion();
		}
		
		return tReserva;

	}

	public boolean anadirReserva(TReserva tReserva) throws Exception
	{
		
		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();


		//validacion dni cliente
		if(tReserva.getDNI().length() <8 ||  tReserva.getDNI().length() >9)
		{
			transaction.rollback();
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("El dni no tiene la longitud necesaria");
			
		}
		else
		{
			for(int i = 0; i < tReserva.getDNI().length();i++)
			{
				if(i==tReserva.getDNI().length()-1)
				{
					if(tReserva.getDNI().toUpperCase().charAt(i)<'A' || 
						tReserva.getDNI().toUpperCase().charAt(i)>'Z')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el dni no contiene una letra al final");
					}
				}
				else
				{
					if(tReserva.getDNI().charAt(i)<'0' ||  tReserva.getDNI().charAt(i)>'9')
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("el dni debe contener numeros");
					}
				}
			}
		}
		
		try //try catch para los casting 
		{
			int hora = Integer.parseInt(tReserva.getHora().split(":")[0]);
			int min = Integer.parseInt(tReserva.getHora().split(":")[1]);
			int seg = Integer.parseInt(tReserva.getHora().split(":")[2]);
			
			if(hora < 0 || hora >23)
			{
				transaction.rollback();
				TransactionManager.getInstance().eliminarTransaccion();
				throw new Exception("la hora no es correcta");
			}
			if(min < 0 || min > 59)
			{
				transaction.rollback();
				TransactionManager.getInstance().eliminarTransaccion();
				throw new Exception("los minutos no son correctos");
			}
			if(seg <0 || seg > 59)
			{
				transaction.rollback();
				TransactionManager.getInstance().eliminarTransaccion();
				throw new Exception("los segundos no son correctos");
			}
			//comprobamos la fecha que sea correcta
			int ano = Integer.parseInt(tReserva.getFecha().split("-")[0]);
			int mes = Integer.parseInt(tReserva.getFecha().split("-")[1]);
			int dia = Integer.parseInt(tReserva.getFecha().split("-")[2]);
			if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) 
			{//meses 31
			
				if(dia >31)
				{
					transaction.rollback();
					TransactionManager.getInstance().eliminarTransaccion();
					throw new Exception("El dia no puede ser mayor de 31");
				}
			} 
			else 
			{//meses 30 o menos
				if (mes == 4 || mes == 6 || mes == 9 || mes == 11) 
				{//meses 30
					if(dia >30)
					{
						transaction.rollback();
						TransactionManager.getInstance().eliminarTransaccion();
						throw new Exception("El dia no puede ser mayor de 30");
					}
				} 
				else 
				{//febrero
					if ((ano%4 == 0 && ano % 100 != 0) || ano % 400 == 0) 
					{//es bisiesto
						if(dia >29)
						{
							transaction.rollback();
							TransactionManager.getInstance().eliminarTransaccion();
							throw new Exception("El dia no puede ser mayor de 29");
						}
					}
					else
					{//no bisiesto
						if(dia >28)
						{
							transaction.rollback();
							TransactionManager.getInstance().eliminarTransaccion();
							throw new Exception("El dia no puede ser mayor de 28");
						}
					}
				}
			}
		} catch (Exception e) {
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pueden introducir letras en la fecha u hora");
		}
		if(daoReserva.create(tReserva))
		{
			transaction.commit();
			TransactionManager.getInstance().eliminarTransaccion();
			return true;
		}
		else
		{
			transaction.rollback();
			
			TransactionManager.getInstance().eliminarTransaccion();
			throw new Exception("No se pudo añadir la reserva");
		}
	}

	public boolean eliminarReserva(int ID) throws Exception{

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		
		
		boolean b =  daoReserva.delete(ID);
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
			throw new Exception("No se pudo eliminar la reserva");
		}

	}

	public boolean modificarReserva(TReserva tReserva) throws Exception{

		TransactionManager.getInstance().nuevaTransaccion();
		Transaction transaction = TransactionManager.getInstance().getTransaction();
		transaction.start();
		DAOReserva daoReserva = FactoriaIntegracion.obtenerInstancia().generaDAOReserva();

		
		
		boolean b =  daoReserva.update(tReserva);
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
			throw new Exception("No se pudo modificar la reserva");
		}

	}

}
