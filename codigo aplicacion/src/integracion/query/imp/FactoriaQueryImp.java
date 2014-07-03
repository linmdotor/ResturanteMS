/**
 * 
 */
package integracion.query.imp;

import integracion.query.FactoriaQuery;
import integracion.query.Query;

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