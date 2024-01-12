package repas;

import hotel.IRepas;
import hotel.IRepasFactory;

public class RepasFactory implements IRepasFactory {

	@Override
	public IRepas commander(String repas) {
		
		if(repas.toLowerCase().contains("petit-dejeuner"))
			return new PetitDejeuner();
		
		else if(repas.toLowerCase().contains("dejeuner"))
			return new Dejeuner();
		
		else if(repas.toLowerCase().contains("diner"))
			return new Diner();
		
		return null;
	}

}
