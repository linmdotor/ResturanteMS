/**
 * 
 */
package negocio.plato;

/*
 * CREATE TABLE IF NOT EXISTS `Plato`(
	`ID_Plato` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`Nombre` VARCHAR(30) NOT NULL, 
	`Precio` FLOAT, 
	`Stock` INTEGER NOT NULL DEFAULT '0',
	
	PRIMARY KEY (ID_Plato)	
);

 */
public class TPlato {
	private int ID_Plato;
	private String Nombre;
	private float Precio;
	private int Stock;
	
	
	/*
	 ******************Getters AND Setters*****************/
	
	/**
	 * @return the iD_Plato
	 */
	public int getID() {
		return ID_Plato;
	}
	
	/**
	 * @param iD_Plato the iD_Plato to set
	 */
	public void setID(int iD_Plato) {
		ID_Plato = iD_Plato;
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
	 * @return the stock
	 */
	public int getStock() {
		return Stock;
	}
	
	
	
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		Stock = stock;
	}
}