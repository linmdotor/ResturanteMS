package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDIniciarVistaFactura implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {
		
		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
		RespuestaCMD respuesta = null;
		
		try {
			respuesta =  new RespuestaCMD(EnumComandos.INICIAR_VISTA_FACTURA, serviciosFactura.obtenerFacturas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;

	}

}
