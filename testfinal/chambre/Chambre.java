package chambre;

import java.io.Serializable;
import hotel.IChambre;

public abstract class Chambre implements IChambre, Serializable {

	private static final long serialVersionUID = 1L;
	private int numero;
	private int prix;
	
	
	public Chambre(int numero) {
		this.numero = numero;
		this.prix = 0;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public abstract String typeChambre();

}
