package presentacion.controlador;


public enum EnumComandos {

	// PLATO
	
	ANADIR_PLATO,
	ELIMINAR_PLATO,
	MODIFICAR_PLATO,
	OBTENERPLATOS,
	INICIAR_VISTA_PLATO,
	MODIFICAR_FORMULARIO_PLATO,
	LIMPIAR_FORMULARIO_PLATO,
	
	// RESERVA
	ANADIR_RESERVA,
	ELIMINAR_RESERVA,
	MODIFICAR_RESERVA,
	OBTENERRESERVAS,
	INICIAR_VISTA_RESERVA,
	MODIFICAR_FORMULARIO_RESERVA,
	LIMPIAR_FORMULARIO_RESERVA,
	
	// FACTURA
	ANADIR_FACTURA,
	ELIMINAR_FACTURA,
	OBTENERFACTURAS,
	INICIAR_VISTA_FACTURA,
	MODIFICAR_FORMULARIO_FACTURA,
	LIMPIAR_FORMULARIO_FACTURA,
	
	ANADIR_FACTURA_PLATO,
	INICIAR_VISTA_FACTURAPLATO,
	OBTENERFACTURAPLATOS,
	ANADIR_PLATO_A_FACTURA,
	QUITAR_PLATO_DE_FACTURA,
	LIMPIAR_FORMULARIO_FACTURAPLATO,
	
	IMPRIMIR_FACTURA,
	
	//MENSAJES DE PANTALLA	
	CORRECTO_PLATO,	 //es necesario 4, uno por ventana, para que limpie una u otra ventana en funci�n del evento que lo produzca
	CORRECTO_RESERVA,
	CORRECTO_FACTURA,
	CORRECTO_FACTURAPLATO,
	CORRECTO,
	ERROR;
}
