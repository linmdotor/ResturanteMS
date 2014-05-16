/**
 * 
 * Comando Eliminar Plato
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
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

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();
		RespuestaCMD respuestaComando = null;
		
		int ID = -1;

		if ((Integer) objeto != -1) 
		{

			try {
				ID = serviciosProductosCarta.obtenerPlatos().get((Integer) objeto).getID();
				if (serviciosProductosCarta.eliminarPlato(ID))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito eliminando Plato.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. No se puede eliminar un plato que está en un pedido.");
		
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}

		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. Debe seleccionar un Plato.");

		return respuestaComando;

	}

}
