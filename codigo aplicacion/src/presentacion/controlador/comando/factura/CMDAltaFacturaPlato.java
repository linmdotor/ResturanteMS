package presentacion.controlador.comando.factura;

import java.util.ArrayList;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFacturaPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDAltaFacturaPlato implements CMD {

	@Override
	public RespuestaCMD ejecuta(Object objeto) {
	
		RespuestaCMD respuestaComando;
		
		ArrayList<TFacturaPlato> lista = (ArrayList<TFacturaPlato>) objeto;
	
		if(lista.size() > 0)
		{
			SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();
	
			if (serviciosFactura.anadirPlatosAFactura((ArrayList<TFacturaPlato>) objeto))
			{
				respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_FACTURAPLATO, "Se han a�adido los platos a la factura.");
			}
			else
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar los platos. Asegurese de que no esos platos no est�n ya insertados.");

		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al insertar los platos. No hay ning�n plato en la factura.");
			
		return respuestaComando;
		
	}

}
