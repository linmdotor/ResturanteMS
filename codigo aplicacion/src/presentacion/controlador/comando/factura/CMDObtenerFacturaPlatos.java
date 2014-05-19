package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.plato.SAPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDObtenerFacturaPlatos implements CMD {

	// Metodos

		public RespuestaCMD ejecuta(Object objeto) {

			SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();

			RespuestaCMD respuestaComando = null;
			try {
				respuestaComando = new RespuestaCMD(EnumComandos.OBTENERFACTURAPLATOS,	serviciosPlato.obtenerPlatos());
			} catch (Exception e) {
				respuestaComando = new RespuestaCMD(EnumComandos.ERROR, e.getMessage());
				e.printStackTrace();
			}

			return respuestaComando;

		}

}
