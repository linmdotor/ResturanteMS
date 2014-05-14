package presentacion.controlador.comando.reserva;

import negocio.factoria.FactoriaNegocio;
import negocio.reserva.SAReserva;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

public class CMDModificarFormularioReserva implements CMD {

	public RespuestaCMD ejecuta(Object objeto) {
		
		SAReserva serviciosReserva = FactoriaNegocio.obtenerInstancia().generaSAReserva();		
		RespuestaCMD respuesta = null; 
						
		try {
			respuesta = new RespuestaCMD(EnumComandos.MODIFICAR_FORMULARIO_RESERVA, serviciosReserva.obtenerReservas().get( (Integer) objeto ) );
		} catch (Exception e) {
			respuesta = new RespuestaCMD(EnumComandos.ERROR, "Error Inesperado. No se ha podido cargar la Reserva seleccionada");
			e.printStackTrace();
		}
		
		return respuesta;		
	}
	
}
