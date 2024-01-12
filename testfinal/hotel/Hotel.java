package hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<IChambre> chambres;
	private List<IClient> clients;
	private Map<IClient, IReservation> reservations;
	private int nbChambre;
	
	public Hotel() {		
		chambres = new ArrayList<>();
		clients = new ArrayList<>();
		reservations = new HashMap<>();
		nbChambre = 0;
	}
	
	public void createChambre(String nom, IChambreFactory f) {
		chambres.add(f.createChambre(nom, new Integer(nbChambre++)));
	}
	
	public void createClient(String nom, String prenom, IClientFactory f) {
		clients.add(f.createClient(nom, prenom));
	}

	public List<IChambre> getChambres() {
		return chambres;
	}

	public List<IClient> getClients() {
		return clients;
	}
	
	public Map<IClient, IReservation> getReservations() {
		return reservations;
	}
	
	public boolean propose(IChambre chambre) {
		for (HashMap.Entry<IClient, IReservation> r : reservations.entrySet()) {
			if(chambres.contains(r.getValue().getChambre()) && r.getValue().getChambre() == chambre)
				return false;
		}
		return true;
	}
	
	public IChambre getChambre(String chambre) {	
		for(IChambre c : chambres) {
			if(chambre.contains(c.typeChambre()) && propose(c))
				return c;				
		}	
		throw new NullPointerException();
	}
	
	public IClient getClient(String nom) {
		IClient client = null;
		
		for(IClient c : clients) {
			if(c.getNom().equals(nom))
				client = c;
		}
		return client;
	}
	
	public IReservation getReservation(IClient client) {
		return reservations.get(client);
	}
	
	public void reserver(IChambre chambre, Date dateDeb, Date dateFin, IClient client, IReservationFactory f) {
		reservations.put(client, f.createReservation(chambre, dateDeb, dateFin));
	}
	
	public void modifierReservation(IClient client, IChambre chambre, Date dateDeb, Date dateFin) {		
		reservations.get(client).setReservation(dateDeb, dateFin, chambre);
	}
	
	public void supprimerReservation(IClient client) {
		reservations.remove(client);
	}
	
	public void commanderRepas(IClient client, String repas, IRepasFactory f) {	
		client.getRepas().add(f.commander(repas));
	}

}
