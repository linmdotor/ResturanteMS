/**
 * 
 * Comando Añadir de Plato
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.comando.plato;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDIniciarVistaPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		RespuestaCMD respuestaComando = new RespuestaCMD(EnumComandos.INICIAR_VISTA_PLATO, serviciosPlato.obtenerPlatos());

		return respuestaComando;

	}

}
