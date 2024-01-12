package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hotel.IClient;
import hotel.IRepas;

public class Client implements IClient, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private List<IRepas> repas;
	
	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.repas = new ArrayList<>();
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public List<IRepas> getRepas() {
		return this.repas;
	}

}
