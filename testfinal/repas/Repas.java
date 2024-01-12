package repas;

import java.io.Serializable;

import hotel.IRepas;

public abstract class Repas implements IRepas, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int prix;
	
	public Repas() {
		this.prix = 0;
	}

	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
}
