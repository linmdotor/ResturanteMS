/**
 * 
 */
package negocio.factura.transfer;

/*
 * CREATE TABLE IF NOT EXISTS `Factura_Plato`(
	`ID_Factura` INTEGER UNSIGNED NOT NULL REFERENCES `Factura`(`ID_Factura`), 
	`ID_Plato` INTEGER UNSIGNED NOT NULL REFERENCES `Plato`(`ID_Plato`), 
	`Precio` FLOAT NOT NULL, 
	`Cantidad` TINYINT NOT NULL, 
	
	PRIMARY KEY (`ID_Factura`, `ID_Plato`), 
	FOREIGN KEY (`ID_Factura`) REFERENCES `Factura`(`ID_Factura`) ON DELETE CASCADE, 
	FOREIGN KEY (`ID_Plato`) REFERENCES `Plato`(`ID_Plato`) ON DELETE NO ACTION -- es lo mismo que ON DELETE RESTRICT (no permite la eliminación del padre)
) ENGINE=INNODB;

 */
public class TFacturaPlato {
	
	private int ID_Factura;
	private int ID_Plato;
	private float Precio;
	private int Cantidad;
	
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
	 * @return the iD_Plato
	 */
	public int getID_Plato() {
		return ID_Plato;
	}

	/**
	 * @param iD_Plato the iD_Plato to set
	 */
	public void setID_Plato(int iD_Plato) {
		ID_Plato = iD_Plato;
	}

	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return Precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		Precio = precio;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return Cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

}