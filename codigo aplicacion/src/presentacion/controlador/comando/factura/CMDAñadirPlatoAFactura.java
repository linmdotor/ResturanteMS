package presentacion.controlador.comando.factura;

import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDA�adirPlatoAFactura implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
			
		return new RespuestaCMD(EnumComandos.A�ADIR_PLATO_A_FACTURA, objeto);

	}

}
