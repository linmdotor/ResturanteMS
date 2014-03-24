/**
 * 
 */
package negocio.factura;

/*
 * CREATE TABLE IF NOT EXISTS `Factura`(
	`ID_Factura` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`ID_Reserva` INTEGER UNSIGNED NOT NULL REFERENCES `Reserva`(`ID_Reserva`), 
	`Fecha` DATETIME NOT NULL, 
	`NIF_Empresa` VARCHAR(10) NOT NULL DEFAULT '51321321',
	`Nombre_Empresa` VARCHAR(25) NOT NULL DEFAULT 'Restaurante 2013',
	`Dir_Empresa` VARCHAR(50) NOT NULL DEFAULT 'Calle Falsa, 123',
	`NIF_Cliente` VARCHAR(10), 
	`Nombre_Cliente` VARCHAR(25), 
	`Dir_Cliente` VARCHAR(50), 
	`IVA` TINYINT NOT NULL DEFAULT '10', 
	`Tipo_Servicio` VARCHAR(20) NOT NULL DEFAULT 'Restauración', 

    PRIMARY KEY (`ID_Factura`) 
) ENGINE=INNODB;

 */
public class TFactura {
	
	private int ID_Factura;
	private int ID_Reserva;
	private String NIF_Empresa;
	private String Nombre_Empresa;
	private String Dir_Empresa;
	private String NIF_Cliente;
	private String Nombre_Cliente;
	private String Dir_Cliente;
	private String Fecha;
	private String Hora;
	private int IVA;
	private String Tipo_Servicio;
	
	/*
	 ******************Getters AND Setters*****************/
	
	/**
	 * @return the iD_Factura
	 */
	public int getID_Factura() {
		return ID_Factura;
	}
	
	/**
	 * @param iD_Factura the iD_Factura to set
	 */
	public void setID_Factura(int iD_Factura) {
		ID_Factura = iD_Factura;
	}
	
	/**
	 * @return the iD_Reserva
	 */
	public int getID_Reserva() {
		return ID_Reserva;
	}
	
	/**
	 * @param iD_Reserva the iD_Reserva to set
	 */
	public void setID_Reserva(int iD_Reserva) {
		ID_Reserva = iD_Reserva;
	}

	/**
	 * @return the nIF_Empresa
	 */
	public String getNIF_Empresa() {
		return NIF_Empresa;
	}
	
	/**
	 * @param nIF_Empresa the nIF_Empresa to set
	 */
	public void setNIF_Empresa(String nIF_Empresa) {
		NIF_Empresa = nIF_Empresa;
	}
	
	/**
	 * @return the nombre_Empresa
	 */
	public String getNombre_Empresa() {
		return Nombre_Empresa;
	}
	
	/**
	 * @param nombre_Empresa the nombre_Empresa to set
	 */
	public void setNombre_Empresa(String nombre_Empresa) {
		Nombre_Empresa = nombre_Empresa;
	}
	
	/**
	 * @return the dir_Empresa
	 */
	public String getDir_Empresa() {
		return Dir_Empresa;
	}
	
	/**
	 * @param dir_Empresa the dir_Empresa to set
	 */
	public void setDir_Empresa(String dir_Empresa) {
		Dir_Empresa = dir_Empresa;
	}
	
	/**
	 * @return the nIF_Cliente
	 */
	public String getNIF_Cliente() {
		return NIF_Cliente;
	}
	
	/**
	 * @param nIF_Cliente the nIF_Cliente to set
	 */
	public void setNIF_Cliente(String nIF_Cliente) {
		NIF_Cliente = nIF_Cliente;
	}
	
	/**
	 * @return the nombre_Cliente
	 */
	public String getNombre_Cliente() {
		return Nombre_Cliente;
	}
	
	/**
	 * @param nombre_Cliente the nombre_Cliente to set
	 */
	public void setNombre_Cliente(String nombre_Cliente) {
		Nombre_Cliente = nombre_Cliente;
	}
	
	
	
	/**
	 * @return the dir_Cliente
	 */
	public String getDir_Cliente() {
		return Dir_Cliente;
	}
	
	/**
	 * @param dir_Cliente the dir_Cliente to set
	 */
	public void setDir_Cliente(String dir_Cliente) {
		Dir_Cliente = dir_Cliente;
	}
	
	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}
	
	/**
	 * @return the iVA
	 */
	public int getIVA() {
		return IVA;
	}
	
	/**
	 * @param iVA the iVA to set
	 */
	public void setIVA(int iVA) {
		IVA = iVA;
	}
	
	/**
	 * @return the tipo_Servicio
	 */
	public String getTipo_Servicio() {
		return Tipo_Servicio;
	}
	
	/**
	 * @param tipo_Servicio the tipo_Servicio to set
	 */
	public void setTipo_Servicio(String tipo_Servicio) {
		Tipo_Servicio = tipo_Servicio;
	}

}