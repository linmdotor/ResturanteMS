/**
 * 
 * Despachador de vistas
 * 
 * @author Marco Gonz�lez, Juan Carlos * @author Mart�nez Dotor, Jes�s * @author Picado �lvarez, Mar�a * @author Rojas Mor�n, Amy Alejandra * @author Serrano �lvarez, Jos� * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.imp;

import presentacion.controlador.Dispatcher;
import presentacion.controlador.RespuestaCMD;
import presentacion.ventanas.VentanaCorrecto;
import presentacion.ventanas.VentanaError;
import presentacion.ventanas.factura.VentanaFactura;
import presentacion.ventanas.factura.VentanaFacturaPlato;
import presentacion.ventanas.plato.VentanaPlato;
import presentacion.ventanas.reserva.VentanaReserva;

public class DispatcherImp extends Dispatcher {

	private VentanaPlato vistaPlato;
	private VentanaReserva vistaReserva;
	private VentanaFactura vistaFactura;
	private VentanaFacturaPlato vistaFacturaPlato;

	public DispatcherImp() {

		vistaPlato = new VentanaPlato(null);
		vistaReserva = new VentanaReserva(null);
		vistaFactura = new VentanaFactura(null);
		vistaFacturaPlato = new VentanaFacturaPlato(null);

	}

	public void despachaRespuesta(RespuestaCMD respuestaCMD) {

		switch (respuestaCMD.getEvento()) {
	
			case INICIAR_VISTA_PLATO:
				vistaPlato.actualizar(respuestaCMD.getObjeto());
				vistaPlato.setVisible(true);
				break;
	
			case MODIFICAR_FORMULARIO_PLATO:
				vistaPlato.modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERPLATOS:
				vistaPlato.actualizar(respuestaCMD.getObjeto());
				break;
				
			case INICIAR_VISTA_RESERVA:
				vistaReserva.actualizar(respuestaCMD.getObjeto());
				vistaReserva.setVisible(true);
				break;
	
			case MODIFICAR_FORMULARIO_RESERVA:
				vistaReserva.modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERRESERVAS:
				vistaReserva.actualizar(respuestaCMD.getObjeto());
				break;
				
			case INICIAR_VISTA_FACTURA:
				vistaFactura.actualizar(respuestaCMD.getObjeto());
				vistaFactura.setVisible(true);
				break;
	
			case MODIFICAR_FORMULARIO_FACTURA:
				vistaFactura.modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERFACTURAS:
				vistaFactura.actualizar(respuestaCMD.getObjeto());
				break;
				
			case INICIAR_VISTA_FACTURAPLATO:
				vistaFacturaPlato.actualizarID(vistaFactura.getID());
				vistaFacturaPlato.actualizar(respuestaCMD.getObjeto());
				vistaFacturaPlato.setVisible(true);
				break;	
				
			case OBTENERFACTURAPLATOS:
				vistaFacturaPlato.actualizar(respuestaCMD.getObjeto());
				break;
				
			case ANADIR_PLATO_A_FACTURA:
				vistaFacturaPlato.anadirPlato(respuestaCMD.getObjeto());
				break;
				
			case QUITAR_PLATO_DE_FACTURA:
				vistaFacturaPlato.quitarPlato(respuestaCMD.getObjeto());
				break;
	
			case CORRECTO_PLATO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				vistaPlato.limpiarFormulario();
				break;
			
			case CORRECTO_RESERVA:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				vistaReserva.limpiarFormulario();
				break;
			
			case CORRECTO_FACTURA:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				vistaFactura.limpiarFormulario();
				break;
			
			case CORRECTO_FACTURAPLATO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				vistaFacturaPlato.limpiarFormulario();
				vistaFacturaPlato.setVisible(false);
				break;
			
			case CORRECTO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				break;
	
			case ERROR:
				new VentanaError((String) respuestaCMD.getObjeto());
				break;
				
			default:
				new VentanaError("Lo sentimos, hay un error . Vuelva a comprobar la validez de los datos.");

		}

	}

}
