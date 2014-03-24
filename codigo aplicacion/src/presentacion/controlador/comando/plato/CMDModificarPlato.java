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
import negocio.plato.TPlato;
import negocio.plato.ValidarTPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando = null;

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		if (new ValidarTPlato().transferCorrecto((TPlato) objeto))
		{
			try {
				if (serviciosProductosCarta.modificarPlato((TPlato) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito modificando el Plato.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Plato. Error al insertar los datos.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar plato. Los datos no son v�lidos.");
			
		return respuestaComando;

	}

}
