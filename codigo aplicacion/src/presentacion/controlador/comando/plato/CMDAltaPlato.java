/**
 * 
 * Comando Anadir Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.comando.plato;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import negocio.plato.transfer.TPlato;
import negocio.plato.transfer.ValidarTPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();
		RespuestaCMD respuestaComando = null;
		
		if (new ValidarTPlato().transferCorrecto((TPlato) objeto))
		{
			try {
				if (serviciosPlato.anadirPlato((TPlato) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Se ha a�adido el Plato.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar plato. Error al insertar los datos.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar plato. Los datos no son v�lidos.");
		
		return respuestaComando;

	}

}
