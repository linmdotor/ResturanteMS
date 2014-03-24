package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.factura.ValidarTFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaFactura implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {
	
		RespuestaCMD respuestaComando;

		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();

		if (new ValidarTFactura().transferCorrecto((TFactura) objeto))
		{
			if (serviciosFactura.anadirFactura((TFactura) objeto))
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_FACTURA, "Se ha a�adido la Factura.");
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar factura. Error al insertar los datos.");
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar factura. Los datos no son v�lidos.");

		return respuestaComando;
		
	}

}
