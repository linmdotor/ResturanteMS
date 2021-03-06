package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.transfer.TFactura;
import negocio.factura.transfer.ValidarTFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaFactura implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {

		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
		RespuestaCMD respuestaComando = null;
		
		if (new ValidarTFactura().transferCorrecto((TFactura) objeto))
		{
			try {
				if (serviciosFactura.anadirFactura((TFactura) objeto))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_FACTURA, "Se ha a�adido la Factura.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar factura. Error al insertar los datos.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar factura. Tiene que rellenar todos los campos.");

		return respuestaComando;
		
	}

}
