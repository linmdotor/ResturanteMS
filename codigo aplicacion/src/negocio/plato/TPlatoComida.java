package negocio.plato;

public class TPlatoComida extends TPlato{
/*
 * CREATE TABLE IF NOT EXISTS `Plato_Comida`(
	`ID_Plato_Comida` INTEGER UNSIGNED NOT NULL REFERENCES `Plato`(`ID_Plato`),
	`Tipo` ENUM('Entrante', '1er Plato', '2º Plato', 'Postre'),
	
	PRIMARY KEY (`ID_Plato_Comida`)
);
 */	
	
	private String tipo;

	
	
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	/*
	public void setTipo(String tipo) {
		this.tipo = tipo;
		String jar = TPlatoComida.Tipo.Primero.toString();
	}; */
	
}
