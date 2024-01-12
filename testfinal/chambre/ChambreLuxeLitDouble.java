package chambre;

public class ChambreLuxeLitDouble extends Chambre {
	
	private static final long serialVersionUID = 1L;
	private static final int price = 100;

	public ChambreLuxeLitDouble(int numero) {
		super(numero);
		setPrix(price);
	}

	@Override
	public String typeChambre() {
		return "Chambre de luxe avec un lit double";
	}

}
