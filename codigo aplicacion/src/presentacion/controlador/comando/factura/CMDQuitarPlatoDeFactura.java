package presentacion.controlador.comando.factura;

import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDQuitarPlatoDeFactura implements CMD{

	public RespuestaCMD ejecuta(Object objeto) {
		
		return new RespuestaCMD(EnumComandos.QUITAR_PLATO_DE_FACTURA, objeto);

	}
	
}
