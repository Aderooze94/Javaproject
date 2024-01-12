package ihm;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import chambre.ChambreFactory;
import client.ClientFactory;
import hotel.Hotel;
import hotel.HotelSerialisation;
import hotel.IChambre;
import hotel.IChambreFactory;
import hotel.IClient;
import hotel.IClientFactory;
import hotel.IRepas;
import hotel.IRepasFactory;
import hotel.IReservation;
import hotel.IReservationFactory;
import repas.RepasFactory;
import reservation.ReservationFactory;

public class HotelConsole {
	
	public static void main(String[] args) {
		System.out.println("Bienvenue dans le meilleur hotel du monde");
		
		Hotel hotel = new Hotel();
		HotelSerialisation hotelSerialisation = new HotelSerialisation();
		IRepasFactory repasFactory = new RepasFactory();
		IChambreFactory chambreFactory = new ChambreFactory();
		IClientFactory clientFactory = new ClientFactory();
		IReservationFactory reservationFactory = new ReservationFactory();
		
		hotel = hotelSerialisation.charger();
		
		// ******** Ajouter fonction pour creer chambre et client ******** 
		
//		for(int i = 0; i < 3; i++) {
//			hotel.creerChambre("luxeDouble", chambreFactory);
//			hotel.creerChambre("luxeSimple", chambreFactory);
//			hotel.creerChambre("normalSimple", chambreFactory);
//			hotel.creerChambre("normalDouble", chambreFactory);
//		}
//		
//		hotel.creerClient("Chau", "Celine", clientFactory);
//		hotel.creerClient("Brouassin", "Tanguy", clientFactory);
//		hotel.creerClient("Nguyen", "Kevin", clientFactory);
//		hotel.creerClient("Taabdante", "Imane", clientFactory);
//		hotel.creerClient("Razafimahefa", "Axel", clientFactory);
//		hotel.creerClient("Velin", "Nicolas", clientFactory);
//		hotel.creerClient("Alauddin", "Ouahiza", clientFactory);
//		hotel.creerClient("Turgut", "Nergiz", clientFactory);
//		hotel.creerClient("Donato", "Cj", clientFactory);
//		hotel.creerClient("Hu", "Dylan", clientFactory);
//		hotel.creerClient("Duval", "Malo", clientFactory);
//		hotel.creerClient("Kalifa", "Ethane", clientFactory);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM y HH:mm:ss");
		Scanner scan = new Scanner(System.in);
		String rep = null;

		do {
			System.out.println("Que voulez-vous faire ? (tapez h pour plus d'informations)");
			rep = scan.next().toLowerCase().substring(0, 1);
			switch(rep) {
				
				case "h":
					afficherCommandes();
					break;
					
				case "d":
					for(IChambre c : hotel.getChambres()) {
						System.out.println("Chambre numero : " + c.getNumero());
						System.out.println("Type de chambre : " + c.typeChambre());
						System.out.println();
					}
					break;
					
				case "r":
					if(hotel.getReservations().size() == 0)
						System.out.println("Aucune chambre n'est reservee");
					
					for (HashMap.Entry<IClient, IReservation> r : hotel.getReservations().entrySet()) {
						if(hotel.getChambres().contains(r.getValue().getChambre()))
							System.out.println("La chambre " + r.getValue().getChambre().getNumero() + " est reservee");
					}
					break;
					
				case "l":
					IClient client = chooseClient(scan, hotel);
					IChambre chambre = chooseChambre(scan, hotel);
					
					Date dateDeb = null;
					Date dateFin = null;
					do {
						System.out.println("Veuillez choisir une date de debut : ");
						dateDeb = chooseDate(scan, calendar);
						System.out.println("Veuillez choisir une date de fin : ");
						dateFin = chooseDate(scan, calendar);
					}
					while(dateDeb.compareTo(dateFin) > 0);
					
					hotel.reserver(chambre, dateDeb, dateFin, client, reservationFactory);
					System.out.println("Vous avez reserve la chambre " + chambre.getNumero());
					System.out.println("Vous avez pris l'offre " + chambre.typeChambre());
					System.out.println("du " + simpleDateFormat.format(dateDeb) + " au " + simpleDateFormat.format(dateFin));
					break;
					
				case "n":
					IClient clientModif = chooseClient(scan, hotel);
					IReservation r = chooseReservation(scan, hotel, clientModif);
					if(r == null)
						System.out.println("Aucune reservation correspondante");
					else 
						modifierReservation(scan, hotel, r, calendar, clientModif);
					break;
					
				case "o":
					IClient clientSuppr = chooseClient(scan, hotel);
					IReservation r2 = chooseReservation(scan, hotel, clientSuppr);
					if(r2 == null)
						System.out.println("Aucune reservation correspondante");
					else {
						hotel.supprimerReservation(clientSuppr);
						System.out.println("Suppression effectuee");
					}
					break;
					
				case "c" :
					IClient client2 = chooseClient(scan, hotel);
					System.out.println("Quelle commande ? (petit-dejeuner, dejeuner, diner)");
					String repas = scan.next().toLowerCase();
					if(repas.equals("petit-dejeuner") || repas.equals("dejeuner") || repas.equals("diner")) {
						hotel.commanderRepas(client2, repas, repasFactory);
						System.out.println("Commande effectuee");
					}
					else
						System.out.println("Repas non disponible");
					break;
					
				case "f":
					IClient client3 = chooseClient(scan, hotel);
					enregistrerFacture(client3, hotel);
					break;
			
			}
		}
		while(!rep.equals("q"));
		hotelSerialisation.sauvegarder(hotel);
		scan.close();
		
	}
	
	public static void afficherCommandes() {
		System.out.println("d : afficher les details des chambres");
		System.out.println("r : afficher les chambres reservees");
		System.out.println("l : reserver une chambre");
		System.out.println("n : modifier une reservation");
		System.out.println("o : supprimer une r�servation");
		System.out.println("c : commander un repas");
		System.out.println("f : afficher la facture");
		System.out.println("q : quitter");
		System.out.println();
	}
	
	public static void modifierReservation(Scanner scan, Hotel hotel, IReservation r, Calendar calendar, IClient client) {
		
		IChambre chambre = r.getChambre();
		Date dateDeb = r.getStartDate();
		Date dateFin = r.getEndDate();
		
		System.out.println("Nouvelle chambre ? (o/n)");
		char propChambre = scan.next().charAt(0);
		if(propChambre == 'o')
			chambre = chooseChambre(scan, hotel);
		else
			System.out.println("Chambre non modifiee");
		
		System.out.println("Nouvelle date de debut ? (o/n)");
		char propDateDeb = scan.next().charAt(0);
		if(propDateDeb == 'o')
			dateDeb = chooseDate(scan, calendar);
		else
			System.out.println("Date de debut non modifiee");
		
		System.out.println("Nouvelle date de fin ? (o/n)");
		char propDateFin = scan.next().charAt(0);
		if(propDateFin == 'o')
			dateFin = chooseDate(scan, calendar);
		else
			System.out.println("Date de Fin non modifiee");
		
		hotel.modifierReservation(client, chambre, dateDeb, dateFin);
		System.out.println("Modification terminee");
	}
	
	public static IReservation chooseReservation(Scanner scan, Hotel hotel, IClient client) {
		return hotel.getReservation(client);
	}
	
	public static IClient chooseClient(Scanner scan, Hotel hotel) {
		System.out.println("Nom du client ? ");
		String nom = scan.next();
		
		IClient client = hotel.getClient(nom);
		if(client != null)
			System.out.println("Client selectionne : " + client.getPrenom() + " " + client.getNom());
		else {
			System.out.println("Client non trouve, veuillez reessayer");
			client = chooseClient(scan, hotel);
		}
	
		return client;
	}
	
	public static IChambre chooseChambre(Scanner scan, Hotel hotel) {
		
		System.out.println("Types de chambre : ");
		System.out.println("Chambre de luxe lit double");
		System.out.println("Chambre de luxe lit simple");
		System.out.println("Chambre normale lit double");
		System.out.println("Chambre normale lit simple \n");
		scan.nextLine();
		System.out.println("Quelle chambre ?");
		String chambre = scan.nextLine();
		IChambre c = hotel.getChambre(chambre);
		
		if(c != null)
			System.out.println("Vous avez choisi l'offre : " + c.typeChambre());
		else {
			System.out.println("Type de chambre non trouve/non disponible. Veuillez reessayer");
			c = chooseChambre(scan, hotel);
		}
				
		return c;
	}
	
	public static Date chooseDate(Scanner scan, Calendar calendar) {		
		System.out.println("Annee : ");
		int year = scan.nextInt();
		System.out.println("Mois : ");
		int month = scan.nextInt();
		System.out.println("Jour : ");
		int day = scan.nextInt();
		System.out.println("Heure : ");
		int hour = scan.nextInt();
		System.out.println("Minute : ");
		int minute = scan.nextInt();
		System.out.println("Seconde : ");
		int second = scan.nextInt();
		
		calendar.set(year, month, day, hour, minute, second);
		return calendar.getTime();
	}
	
	public static void enregistrerFacture(IClient client, Hotel hotel) {
		System.out.println("Facture :");
		
		IReservation r = hotel.getReservation(client);
		
		if(r != null) {
			int prixRepas = 0;
			int total = 0;
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM y HH:mm:ss");
			int nbJours = (int) ChronoUnit.DAYS.between(r.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), r.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			
			for(int i = 0; i < nbJours; i++)
				total += r.getChambre().getPrix();
			
			System.out.println("Nom : " + client.getNom() + "\n");
			System.out.println("Prenom : " + client.getPrenom() + "\n");
			System.out.println("Offre choisie : " + r.getChambre().typeChambre() + "\n");
			System.out.println("Debut sejour : " + simpleDateFormat.format(r.getStartDate()) + "\n");
			System.out.println("Fin sejour : " + simpleDateFormat.format(r.getEndDate()) + "\n");
			System.out.println("Nombre de jours : " + nbJours + "\n");
			System.out.println("Prix de la chambre : " + r.getChambre().getPrix() + " � par jour \n");	
			
			for(IRepas repas : client.getRepas())
				prixRepas += repas.getPrix();
			
			total += prixRepas;
			
			System.out.println("Prix repas : " + prixRepas + " �");
			System.out.println("Total : " + total + " �");
		}
		
	}

}
