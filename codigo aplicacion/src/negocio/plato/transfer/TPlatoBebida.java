/**
 * 
 */
package negocio.plato.transfer;

/*
 * CREATE TABLE IF NOT EXISTS `Plato_Bebida`(
	`ID_Plato_Bebida` INTEGER UNSIGNED NOT NULL REFERENCES `Plato`(`ID_Plato`),
	`Alcoholica` TINYINT NOT NULL DEFAULT '0' , -- True=0 ; False=1
	
    PRIMARY KEY (`ID_Plato_Bebida`)
) ENGINE=INNODB;

 */
public class TPlatoBebida extends TPlato{

	private boolean Alcoholica;
	
	/*
	 ******************Getters AND Setters*****************/
	
	
	/**
	 * @return the alcoholica
	 */
	public boolean isAlcoholica() {
		return Alcoholica;
	}

	/**
	 * @param alcoholica the alcoholica to set
	 */
	public void setAlcoholica(boolean alcoholica) {
		Alcoholica = alcoholica;
	}
	
	
	
	
}
