package chambre;

public class ChambreNormaleLitDouble extends Chambre {

	private static final long serialVersionUID = 1L;
	private static final int price = 50;

	public ChambreNormaleLitDouble(int numero) {
		super(numero);
		setPrix(price);
	}

	@Override
	public String typeChambre() {
		return "Chambre normale avec un lit double";
	}

}
