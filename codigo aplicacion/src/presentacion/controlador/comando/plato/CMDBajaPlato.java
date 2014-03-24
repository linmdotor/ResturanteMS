/**
 * 
 * Comando Eliminar Plato
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
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

		RespuestaCMD respuestaComando = null;

		SAPlato serviciosProductosCarta = FactoriaNegocio.obtenerInstancia().generaSAPlato();

		int ID = -1;

		if ((Integer) objeto != -1) 
		{

			try {
				ID = serviciosProductosCarta.obtenerPlatos().get((Integer) objeto).getID();
				if (serviciosProductosCarta.eliminarPlato(ID))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_PLATO, "Exito eliminando Plato.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. No se puede eliminar un plato que est� en un pedido.");
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar plato. Debe seleccionar un Plato.");

		return respuestaComando;

	}

}
