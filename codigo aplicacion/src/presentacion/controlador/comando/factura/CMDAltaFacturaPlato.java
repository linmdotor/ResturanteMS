package presentacion.controlador.comando.factura;

import java.util.ArrayList;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.transfer.TFacturaPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaFacturaPlato implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {

		ArrayList<TFacturaPlato> lista = (ArrayList<TFacturaPlato>) objeto;
		RespuestaCMD respuestaComando = null;
		
		if(lista.size() > 0)
		{
			SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
	
			try {
				if (serviciosFactura.anadirPlatosAFactura((ArrayList<TFacturaPlato>) objeto))
				{
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_FACTURAPLATO, "Se han a�adido los platos a la factura.");
				}
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar los platos. Asegurese de que no esos platos no est�n ya insertados.");
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}

		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar los platos. No hay ning�n plato en la factura.");
			
		return respuestaComando;
		
	}

}
