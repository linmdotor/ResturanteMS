/**
 * 
 * Comando Eliminar Plato
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

public class CMDBajaPlato implements CMD {

	// Metodos

	public RespuestaCMD ejecuta(Object objeto) {

		RespuestaCMD respuestaComando;

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		int ID = -1;

		if ((Integer) objeto != -1) 
		{

			ID = serviciosProductosCarta.obtenerPlatos().get((Integer) objeto).getID();

			if (serviciosProductosCarta.eliminarPlato(ID))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito eliminando Plato.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. No se puede eliminar un plato que esté en un pedido.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. Debe seleccionar un Plato.");

		return respuestaComando;

	}

}
