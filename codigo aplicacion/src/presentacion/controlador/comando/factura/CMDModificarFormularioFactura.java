package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarFormularioFactura implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {

		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
		
		RespuestaCMD respuesta = null;
						
		try {
			respuesta = new RespuestaCMD(EnumComandos.MODIFICAR_FORMULARIO_FACTURA, serviciosFactura.obtenerFacturas().get( (Integer) objeto ) );
		} catch (Exception e) {
			respuesta = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
			e.printStackTrace();
		}
		
		return respuesta;
	}

}
