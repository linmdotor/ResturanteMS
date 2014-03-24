package presentacion.controlador.comando.plato;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarFormularioPlato implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
		
		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();
		
		RespuestaCMD respuestaCMD = new RespuestaCMD(EnumComandos.ERROR, "No se ha podido cargar el Plato seleccionado");
						
		respuestaCMD = new RespuestaCMD(EnumComandos.MODIFICAR_FORMULARIO_PLATO, serviciosPlato.obtenerPlatos().get( (Integer) objeto ) );
		
		return respuestaCMD;		
	}
	
}
