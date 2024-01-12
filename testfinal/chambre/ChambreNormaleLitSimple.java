package chambre;

public class ChambreNormaleLitSimple extends Chambre {

	private static final long serialVersionUID = 1L;
	private static final int price = 25;

	public ChambreNormaleLitSimple(int numero) {
		super(numero);
		setPrix(price);
	}

	@Override
	public String typeChambre() {
		return "Chambre normale avec un lit simple";
	}

}
