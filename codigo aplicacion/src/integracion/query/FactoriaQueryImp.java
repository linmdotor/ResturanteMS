/**
 * 
 */
package integracion.query;

import integracion.plato.PlatoPorPrecio;
import integracion.plato.PlatoPorStock;

public class FactoriaQueryImp extends FactoriaQuery 
{

	@Override
	public Query creaQuery(int numQuery) 
	{

		switch(numQuery)
		{
			case 1:
					return new PlatoPorStock();
					
			case 2:
					return new PlatoPorPrecio();
			
			
		}
		return null;
	}
	
	

	
}