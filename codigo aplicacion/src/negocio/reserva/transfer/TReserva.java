/**
 * 
 */
package negocio.reserva.transfer;

/*
 * CREATE TABLE IF NOT EXISTS `Reserva`(
	`ID_Reserva` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`DNI` VARCHAR(10), 
	`Nombre` VARCHAR(25) NOT NULL, 
	`Fecha` DATETIME NOT NULL, 
	`Telefono` VARCHAR(10), 
	`N_Comensales` SMALLINT UNSIGNED NOT NULL, 
	
	PRIMARY KEY (`ID_Reserva`)
)

 */
public class TReserva {
	private int ID_Reserva;
	private String DNI;
	private String Nombre;
	private String Fecha;
	private String Hora;
	private String Telefono;
	private int N_Comensales;
	
	/*
	 ******************Getters AND Setters*****************/
	
	/**
	 * @return the iD_Reserva
	 */
	public int getID() {
		return ID_Reserva;
	}
	/**
	 * @param iD_Reserva the iD_Reserva to set
	 */
	public void setID(int iD_Reserva) {
		ID_Reserva = iD_Reserva;
	}
	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return Fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	/**
	 * @return the hora
	 */
	public String getHora() {
		return Hora;
	}
	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		Hora = hora;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return Telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	/**
	 * @return the n_Comensales
	 */
	public int getN_Comensales() {
		return N_Comensales;
	}
	/**
	 * @param n_Comensales the n_Comensales to set
	 */
	public void setN_Comensales(int n_Comensales) {
		N_Comensales = n_Comensales;
	}
	
}