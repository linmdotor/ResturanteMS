package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDObtenerFacturas implements CMD {

	// Metodos

		public RespuestaCMD ejecuta(Object objeto) {

			SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();

			RespuestaCMD respuestaComando = new RespuestaCMD(EnumComandos.OBTENERFACTURAS, serviciosFactura.obtenerFacturas());

			return respuestaComando;

		}

}
