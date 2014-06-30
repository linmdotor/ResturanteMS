/**
 * 
 */
package integracion.query;

import integracion.plato.PlatoPorNombre;
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
					return new PlatoPorNombre();
		
			case 2:
					return new PlatoPorPrecio();
					
			case 3:
					return new PlatoPorStock();
			
			
		}
		return null;
	}
	
	

	
}