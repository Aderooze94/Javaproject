package chambre;

public class ChambreLuxeLitSimple extends Chambre {
	
	private static final long serialVersionUID = 1L;
	private static final int price = 75;

	public ChambreLuxeLitSimple(int numero) {
		super(numero);
		setPrix(price);
	}

	@Override
	public String typeChambre() {
		return "Chambre de luxe avec un lit simple";
	}

}
