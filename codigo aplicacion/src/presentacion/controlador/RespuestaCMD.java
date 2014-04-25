package presentacion.controlador;

/*
 * Clase para devolver la respuesta de un comando.
 * Contiene un evento, del EnumComandos, y un objeto cualquiera 
 * (que se podrá parsear al objeto que corresponda en cada momento).
 */
public class RespuestaCMD {

	private EnumComandos evento;
	private Object objeto;

	public RespuestaCMD(EnumComandos evento, Object objeto) {

		this.setEvento(evento);
		this.setObjeto(objeto);

	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public EnumComandos getEvento() {
		return evento;
	}

	public void setEvento(EnumComandos evento) {
		this.evento = evento;
	}

}
