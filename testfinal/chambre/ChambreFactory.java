package chambre;

import hotel.IChambre;
import hotel.IChambreFactory;

public class ChambreFactory implements IChambreFactory {

	@Override
	public IChambre createChambre(String nom, int num) {
		
		if(nom.equals("luxeSimple"))
			return new ChambreLuxeLitSimple(num);
		
		else if(nom.equals("luxeDouble"))
			return new ChambreLuxeLitDouble(num);
		
		else if(nom.equals("normalSimple"))
			return new ChambreNormaleLitSimple(num);
		
		else if(nom.equals("normalDouble"))
			return new ChambreNormaleLitDouble(num);
		
		return null;
		
	}

}
