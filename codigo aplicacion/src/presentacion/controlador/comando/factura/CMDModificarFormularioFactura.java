package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarFormularioFactura implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {

		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
		
		RespuestaCMD respuestaCMD = new RespuestaCMD(EnumComandos.ERROR, "No se ha podido cargar la Factura seleccionada");
						
		respuestaCMD = new RespuestaCMD(EnumComandos.MODIFICAR_FORMULARIO_FACTURA, serviciosFactura.obtenerFacturas().get( (Integer) objeto ) );
		
		return respuestaCMD;
	}

}
