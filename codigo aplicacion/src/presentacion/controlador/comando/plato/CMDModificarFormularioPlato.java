package presentacion.controlador.comando.plato;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarFormularioPlato implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
		
		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();		
		RespuestaCMD respuesta = null;
						
		try {
			respuesta = new RespuestaCMD(EnumComandos.MODIFICAR_FORMULARIO_PLATO, serviciosPlato.obtenerPlatos().get( (Integer) objeto ) );
		} catch (Exception e) {
			respuesta = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
			e.printStackTrace();
		}
		
		return respuesta;		
	}
	
}
