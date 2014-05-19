package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDIniciarVistaFacturaPlato implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {

		SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();
		
		TFactura t = (TFactura)objeto;
		RespuestaCMD respuesta = null;
		SAFactura serviciosfactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();		
		try {
			if (!serviciosfactura.existeFacturaPlato(t.getID_Factura()))
			{
				if(t.getID_Factura() != -1)
					respuesta = new RespuestaCMD(EnumComandos.INICIAR_VISTA_FACTURAPLATO, serviciosPlato.obtenerPlatos());
				else
					respuesta = new RespuestaCMD(EnumComandos.ERROR, "Debe seleccionar una factura para poder anadir platos");
			}
			else
				respuesta =  new RespuestaCMD(EnumComandos.ERROR, "No puede volver a insertar platos en una factura finalizada");
		} catch (Exception e) {
			respuesta =  new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
			e.printStackTrace();
		}
		return respuesta;
	}

}
