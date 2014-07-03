/**
 * 
 */
package integracion.query;

import integracion.query.imp.FactoriaQueryImp;



public abstract class FactoriaQuery {

	// Atributos

	private static FactoriaQuery factoria;

	// Metodos

	public static FactoriaQuery obtenerInstancia() {

		if (factoria == null)
			factoria = new FactoriaQueryImp();

		return factoria;
	}

	public abstract Query creaQuery(int numQuery) ;

}