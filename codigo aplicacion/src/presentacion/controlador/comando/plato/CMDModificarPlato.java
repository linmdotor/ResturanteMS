/**
 * 
 * Comando Modificar Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
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

		RespuestaCMD respuestaComando;

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		if (new ValidarTPlato().transferCorrecto((TPlato) objeto))
		{
			if (serviciosProductosCarta.modificarPlato((TPlato) objeto))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito modificando el Plato.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar Plato. Error al insertar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al modificar plato. Los datos no son válidos.");
			
		return respuestaComando;

	}

}
