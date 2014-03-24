/**
 * 
 * Factoria de Comandos
 * 
 * @author Marco González, Juan Carlos * @author Martínez Dotor, Jesús * @author Picado Álvarez, María * @author Rojas Morán, Amy Alejandra * @author Serrano Álvarez, José * @author Vargas Paredes, Jhonny
 *  
 */

package presentacion.controlador.imp;

import java.util.HashMap;
import java.util.Map;

import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.FactoriaCMD;
import presentacion.controlador.comando.factura.CMDAltaFactura;
import presentacion.controlador.comando.factura.CMDAltaFacturaPlato;
import presentacion.controlador.comando.factura.CMDAñadirPlatoAFactura;
import presentacion.controlador.comando.factura.CMDBajaFactura;
import presentacion.controlador.comando.factura.CMDImprimirFactura;
import presentacion.controlador.comando.factura.CMDIniciarVistaFactura;
import presentacion.controlador.comando.factura.CMDIniciarVistaFacturaPlato;
import presentacion.controlador.comando.factura.CMDModificarFormularioFactura;
import presentacion.controlador.comando.factura.CMDObtenerFacturaPlatos;
import presentacion.controlador.comando.factura.CMDObtenerFacturas;
import presentacion.controlador.comando.factura.CMDQuitarPlatoDeFactura;
import presentacion.controlador.comando.plato.CMDAltaPlato;
import presentacion.controlador.comando.plato.CMDBajaPlato;
import presentacion.controlador.comando.plato.CMDIniciarVistaPlato;
import presentacion.controlador.comando.plato.CMDModificarFormularioPlato;
import presentacion.controlador.comando.plato.CMDModificarPlato;
import presentacion.controlador.comando.plato.CMDObtenerPlatos;
import presentacion.controlador.comando.reserva.CMDAltaReserva;
import presentacion.controlador.comando.reserva.CMDBajaReserva;
import presentacion.controlador.comando.reserva.CMDIniciarVistaReserva;
import presentacion.controlador.comando.reserva.CMDModificarFormularioReserva;
import presentacion.controlador.comando.reserva.CMDModificarReserva;
import presentacion.controlador.comando.reserva.CMDObtenerReservas;

public class FactoriaCMDImp extends FactoriaCMD {

	private Map<EnumComandos, CMD> map_cmd;

	public FactoriaCMDImp() {

		map_cmd = new HashMap<EnumComandos, CMD>();

		// COMANDOS PLATO
		
		map_cmd.put(EnumComandos.AÑADIR_PLATO, new CMDAltaPlato());
		map_cmd.put(EnumComandos.ELIMINAR_PLATO, new CMDBajaPlato());
		map_cmd.put(EnumComandos.MODIFICAR_PLATO, new CMDModificarPlato());
		map_cmd.put(EnumComandos.OBTENERPLATOS, new CMDObtenerPlatos());
		map_cmd.put(EnumComandos.INICIAR_VISTA_PLATO, new CMDIniciarVistaPlato());
		map_cmd.put(EnumComandos.MODIFICAR_FORMULARIO_PLATO, new CMDModificarFormularioPlato());
		
		// COMANDOS RESERVA
		
		map_cmd.put(EnumComandos.AÑADIR_RESERVA, new CMDAltaReserva());
		map_cmd.put(EnumComandos.ELIMINAR_RESERVA, new CMDBajaReserva());
		map_cmd.put(EnumComandos.MODIFICAR_RESERVA, new CMDModificarReserva());
		map_cmd.put(EnumComandos.OBTENERRESERVAS, new CMDObtenerReservas());
		map_cmd.put(EnumComandos.INICIAR_VISTA_RESERVA, new CMDIniciarVistaReserva());
		map_cmd.put(EnumComandos.MODIFICAR_FORMULARIO_RESERVA, new CMDModificarFormularioReserva());
		
		// COMANDOS FACTURA
		
		map_cmd.put(EnumComandos.AÑADIR_FACTURA, new CMDAltaFactura());
		map_cmd.put(EnumComandos.ELIMINAR_FACTURA, new CMDBajaFactura());
		map_cmd.put(EnumComandos.OBTENERFACTURAS, new CMDObtenerFacturas());
		map_cmd.put(EnumComandos.INICIAR_VISTA_FACTURA, new CMDIniciarVistaFactura());
		map_cmd.put(EnumComandos.MODIFICAR_FORMULARIO_FACTURA, new CMDModificarFormularioFactura());
		
		map_cmd.put(EnumComandos.AÑADIR_FACTURA_PLATO, new CMDAltaFacturaPlato());
		map_cmd.put(EnumComandos.INICIAR_VISTA_FACTURAPLATO, new CMDIniciarVistaFacturaPlato());
		map_cmd.put(EnumComandos.OBTENERFACTURAPLATOS, new CMDObtenerFacturaPlatos());
		map_cmd.put(EnumComandos.AÑADIR_PLATO_A_FACTURA, new CMDAñadirPlatoAFactura());
		map_cmd.put(EnumComandos.QUITAR_PLATO_DE_FACTURA, new CMDQuitarPlatoDeFactura());	
		
		map_cmd.put(EnumComandos.IMPRIMIR_FACTURA, new CMDImprimirFactura());	
	}

	// Metodos

	public CMD generaComando(EnumComandos nombreComando) {

		return map_cmd.get(nombreComando);

	}

}
