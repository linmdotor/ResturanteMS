/**
 * 
 * Comando Añadir Plato
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

public class CMDAltaPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando;

		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		if (new ValidarTPlato().transferCorrecto((TPlato) objeto))
		{
			if (serviciosPlato.añadirPlato((TPlato) objeto))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Se ha añadido el Plato.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar plato. Error al insertar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar plato. Los datos no son válidos.");
		
		return respuestaComando;

	}

}
