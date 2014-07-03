/**
 * 
 * Comando Modificar Plato
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

public class CMDModificarPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();
		RespuestaCMD respuestaComando = null;
		
		if (new ValidarTPlato().transferCorrecto((TPlato) objeto))
		{
			try {
				if (serviciosProductosCarta.modificarPlato((TPlato) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito modificando el Plato.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Plato. Error al insertar los datos.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar plato. Los datos no son v�lidos.");
			
		return respuestaComando;

	}

}
