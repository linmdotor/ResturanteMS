/**
 * 
 * Despachador de vistas
 * 
 * @author Marco Gonzï¿½lez, Juan Carlos * @author Martï¿½nez Dotor, Jesï¿½s * @author Picado ï¿½lvarez, Marï¿½a * @author Rojas Morï¿½n, Amy Alejandra * @author Serrano ï¿½lvarez, Josï¿½ * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.imp;

import presentacion.controlador.Dispatcher;
import presentacion.controlador.RespuestaCMD;
import presentacion.ventanas.VentanaCorrecto;
import presentacion.ventanas.VentanaError;
import presentacion.ventanas.VentanaPrincipal;
import presentacion.ventanas.factura.VentanaFactura;
import presentacion.ventanas.factura.VentanaFacturaPlato;
import presentacion.ventanas.plato.VentanaPlato;
import presentacion.ventanas.reserva.VentanaReserva;

public class DispatcherImp extends Dispatcher {


	public void despachaRespuesta(RespuestaCMD respuestaCMD) {

		switch (respuestaCMD.getEvento()) {
	
			case INICIAR_VISTA_PRINCIPAL:
				VentanaPrincipal.obtenerInstancia().setVisible(true);
				break;
			
			//PLATO
				
			case INICIAR_VISTA_PLATO:
				VentanaPlato.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
	
			case MODIFICAR_FORMULARIO_PLATO:
				VentanaPlato.obtenerInstancia().modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERPLATOS:
				VentanaPlato.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
				
			//RESERVA
				
			case INICIAR_VISTA_RESERVA:
				VentanaReserva.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
	
			case MODIFICAR_FORMULARIO_RESERVA:
				VentanaReserva.obtenerInstancia().modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERRESERVAS:
				VentanaReserva.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
				
			//FACTURA
				
			case INICIAR_VISTA_FACTURA:
				VentanaFactura.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
	
			case MODIFICAR_FORMULARIO_FACTURA:
				VentanaFactura.obtenerInstancia().modificarFormulario(respuestaCMD.getObjeto());
				break;
	
			case OBTENERFACTURAS:
				VentanaFactura.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
				

				
			case INICIAR_VISTA_FACTURAPLATO:
				VentanaFacturaPlato.obtenerInstancia().actualizarID(VentanaFactura.obtenerInstancia().getID());
				VentanaFacturaPlato.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;	
				
			case OBTENERFACTURAPLATOS:
				VentanaFacturaPlato.obtenerInstancia().actualizar(respuestaCMD.getObjeto());
				break;
				
			case ANADIR_PLATO_A_FACTURA:
				VentanaFacturaPlato.obtenerInstancia().anadirPlato(respuestaCMD.getObjeto());
				break;
				
			case QUITAR_PLATO_DE_FACTURA:
				VentanaFacturaPlato.obtenerInstancia().quitarPlato(respuestaCMD.getObjeto());
				break;
				
			//CORECTOS
	
			case CORRECTO_PLATO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				VentanaPlato.obtenerInstancia().limpiarFormulario();
				break;
			
			case CORRECTO_RESERVA:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				VentanaReserva.obtenerInstancia().limpiarFormulario();
				break;
			
			case CORRECTO_FACTURA:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				VentanaFactura.obtenerInstancia().limpiarFormulario();
				break;
			
			case CORRECTO_FACTURAPLATO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				VentanaFacturaPlato.obtenerInstancia().limpiarFormulario(false);
				break;
				
			//GENÉRICOS
			
			case CORRECTO:
				new VentanaCorrecto((String) respuestaCMD.getObjeto());
				break;
	
			case ERROR:
				new VentanaError((String) respuestaCMD.getObjeto());
				break;
				
			default:
				new VentanaError("Lo sentimos, hay un error. Vuelva a comprobar la validez de los datos.");
				break;
		}

	}

}
