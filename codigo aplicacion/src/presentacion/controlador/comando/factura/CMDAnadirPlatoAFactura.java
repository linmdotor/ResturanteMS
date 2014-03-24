package presentacion.controlador.comando.factura;

import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAnadirPlatoAFactura implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
			
		return new RespuestaCMD(EnumComandos.ANADIR_PLATO_A_FACTURA, objeto);

	}

}
