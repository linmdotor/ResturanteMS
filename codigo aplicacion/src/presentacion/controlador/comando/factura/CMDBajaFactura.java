package presentacion.controlador.comando.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDBajaFactura implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
		
		RespuestaCMD respuestaComando = null;

		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();

		int ID = -1;

		if ((Integer) objeto != -1) 
		{

			
			try {
				ID = serviciosFactura.obtenerFacturas().get((Integer) objeto).getID_Factura();

				if (serviciosFactura.eliminarFactura(ID))
					respuestaComando = new RespuestaCMD(EnumComandos.CORRECTO_FACTURA, "Exito eliminando Factura.");
				else
					respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "No se ha podido eliminar factura. Error al eliminar los datos.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			respuestaComando = new RespuestaCMD(EnumComandos.ERROR, "Error al eliminar factura. Debe seleccionar una Factura.");

		return respuestaComando;
	}

}
