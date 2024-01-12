package ihm;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import hotel.Hotel;
import hotel.HotelSerialisation;
import hotel.IChambre;
import hotel.IClient;
import hotel.IRepas;
import hotel.IRepasFactory;
import hotel.IReservation;
import hotel.IReservationFactory;
import repas.RepasFactory;
import reservation.ReservationFactory;

public class HotelGUI implements ActionListener {
	
	private JFrame frame;
	private Hotel hotel;
	private HotelSerialisation hotelSerialisation;
	IReservationFactory reservationFactory = new ReservationFactory();
	IRepasFactory repasFactory = new RepasFactory();
	
	private JTextField nvlResDateDebTextField;
	private JTextField nvlResDateFinTextField;
	private JTextField modifResDateDebTextField;
	private JTextField modifResDateFinTextField;
	private JTextField annulerResDateDebTextField;
	private JTextField annulerResDateFinTextField;
	
	private JPanel menuPanel = new JPanel();
	private JButton btnAccueil = new JButton("Accueil");
	private JButton btnInformationsClient = new JButton("Informations client");
	private JButton btnNvlReserv = new JButton("Nouvelle reservation");
	private JButton btnModifReserv = new JButton("Modifier reservation");
	private JButton btnAnnulerReserv = new JButton("Annuler reservation");
	private JButton btnCommander = new JButton("Commander repas");
	private JButton btnQuitter = new JButton("Quitter");
	
	private JPanel mainPanel = new JPanel();
	private JPanel accueilPanel = new JPanel();
	private JPanel accTitlePanel = new JPanel();
	private JLabel lblAccueil = new JLabel("Accueil");
	private JPanel chambrePanel = new JPanel();
	private TextArea chambreTextArea = new TextArea();
	
	private JPanel reservationsPanel = new JPanel();
	private TextArea reservationsTextArea = new TextArea();
	
	private JPanel clientPanel = new JPanel();
	private JPanel cliTitlePanel = new JPanel();
	private JLabel lblClient = new JLabel("Informations client");
	private JPanel cliInfoPanel = new JPanel();
	private TextArea cliInfoTextArea = new TextArea();
	private JPanel cliRecherchePanel = new JPanel();
	private JLabel lblRechercheClient = new JLabel("Client :");
	private JComboBox<String> cliComboBox = new JComboBox<String>();
	
	private JPanel nvelleReservPanel = new JPanel();
	private JPanel nvlResTitlePanel = new JPanel();
	private JLabel lblNvlRes = new JLabel("Nouvelle reservation");
	private JPanel nvlResFormPanel = new JPanel();
	private JLabel lblNvlResTypeChambre = new JLabel("Type de chambre :");
	private JComboBox<String> nvlResTypeChComboBox = new JComboBox<String>();
	private JLabel lblNvlResDateDeFin = new JLabel("Date de fin (dd/mm/yyyy) :");
	private JLabel lblNvlResDateDeb = new JLabel("Date de debut (dd/mm/yyyy) :");
	private JPanel nvlResRecherchePanel = new JPanel();
	private JLabel lblNvlResClient = new JLabel("Client :");
	private JComboBox<String> nvlResComboBox = new JComboBox<String>();
	private JPanel nvlResButPanel = new JPanel();
	private JButton btnNvelleResValider = new JButton("Valider");
	
	private JPanel modifReservPanel = new JPanel();
	private JPanel modifResTitlePanel = new JPanel();
	private JLabel lblModifRes = new JLabel("Modifier reservation");
	private JPanel modifResFormPanel = new JPanel();
	private JLabel lblModifResTypeCh = new JLabel("Type de chambre :");
	private JComboBox<String> modifResTypeResComboBox = new JComboBox<String>();
	private JLabel lblModifResDateDeb = new JLabel("Date de debut (dd/mm/yyyy) :");
	private JLabel lblModifResDateFin = new JLabel("Date de fin (dd/mm/yyyy) :");
	private JPanel modifResRecherchePanel = new JPanel();
	private JLabel lblModifResClient = new JLabel("Client :");
	private JComboBox<String> modifResComboBox = new JComboBox<String>();
	private JPanel modifResButPanel = new JPanel();
	private JButton btnModifResValider = new JButton("Valider");
	
	private JPanel annulerReservPanel = new JPanel();
	private JPanel annulerResTitlePanel = new JPanel();
	private JLabel lblAnnulerRes = new JLabel("Annuler reservation");
	private JPanel annulerResFormPanel = new JPanel();
	private JLabel lblAnnulerResTypeCh = new JLabel("Type de chambre :");
	private JComboBox<String> annulerResTypeChComboBox = new JComboBox<String>();
	private JLabel lblAnnulerResDateDeb = new JLabel("Date de debut (dd/mm/yyyy) :");
	private JLabel lblAnnulerResDateFin = new JLabel("Date de fin (dd/mm/yyyy) :");
	private JPanel annulerResRecherchePanel = new JPanel();
	private JLabel lblAnnulerResClient = new JLabel("Client :");
	private JComboBox<String> annulerResComboBox = new JComboBox<String>();
	private JPanel annulerResButPanel = new JPanel();
	private JButton btnAnnulerResValider = new JButton("Valider");
	
	private JPanel commanderPanel = new JPanel();
	private JPanel commanderTitlePanel = new JPanel();
	private JLabel lblCommanderUnRepas = new JLabel("Commander un repas");
	private JPanel commanderFormPanel = new JPanel();
	private JLabel lblRepas = new JLabel("Repas :");
	private JComboBox<String> repasComboBox = new JComboBox<String>();
	private JPanel commanderRecherchePanel = new JPanel();
	private JLabel lblCommanderClient = new JLabel("Client :");
	private JComboBox<String> commanderComboBox = new JComboBox<String>();
	private JPanel commanderButPanel = new JPanel();
	private JButton btnCommanderValider = new JButton("Valider");
	
	public HotelGUI() {
		hotelSerialisation = new HotelSerialisation();
		hotel = hotelSerialisation.charger();
		
		initialize(hotel);
		initializeActions();
	}
	
	public static void main(String[] args) {
		new HotelGUI();
	}

	private void initialize(Hotel hotel) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 729, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		menuPanel.setBackground(new Color(30, 144, 255));
		menuPanel.setBounds(10, 11, 172, 370);
		frame.getContentPane().add(menuPanel);
		
	
		btnAccueil.setForeground(Color.WHITE);
		btnAccueil.setBackground(new Color(30, 144, 255));
		
	
		btnInformationsClient.setForeground(Color.WHITE);
		btnInformationsClient.setBackground(new Color(30, 144, 255));
		
		
		btnNvlReserv.setForeground(Color.WHITE);
		btnNvlReserv.setBackground(new Color(30, 144, 255));
		
		
		btnModifReserv.setForeground(Color.WHITE);
		btnModifReserv.setBackground(new Color(30, 144, 255));
		
		
		btnAnnulerReserv.setForeground(Color.WHITE);
		btnAnnulerReserv.setBackground(new Color(30, 144, 255));
		
		
		btnCommander.setForeground(Color.WHITE);
		btnCommander.setBackground(new Color(30, 144, 255));
		menuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuPanel.add(btnAccueil);
		menuPanel.add(btnInformationsClient);
		menuPanel.add(btnNvlReserv);
		menuPanel.add(btnModifReserv);
		menuPanel.add(btnAnnulerReserv);
		menuPanel.add(btnCommander);
		
		
		btnQuitter.setForeground(Color.RED);
		btnQuitter.setBackground(Color.WHITE);
		menuPanel.add(btnQuitter);
		
		
		mainPanel.setBounds(192, 11, 511, 370);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		
		accueilPanel.setBackground(Color.WHITE);
		accueilPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		mainPanel.add(accueilPanel, "accueilPanel");
		accueilPanel.setLayout(null);
		
		
		accTitlePanel.setBackground(new Color(30, 144, 255));
		accTitlePanel.setBounds(10, 11, 491, 30);
		accueilPanel.add(accTitlePanel);
		
		
		lblAccueil.setForeground(Color.WHITE);
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		accTitlePanel.add(lblAccueil);
		
		
		chambrePanel.setBackground(Color.WHITE);
		chambrePanel.setBorder(new TitledBorder(null, "Liste des chambres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		chambrePanel.setBounds(10, 52, 241, 307);
		accueilPanel.add(chambrePanel);
		chambrePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		chambreTextArea.setEditable(false);
		for(IChambre c : hotel.getChambres()) {
			chambreTextArea.append("numero de chambre : " + c.getNumero() + "\n");
			chambreTextArea.append("Type de chambre : " + c.typeChambre() + "\n\n");
		}
		chambrePanel.add(chambreTextArea);
		
		
		reservationsPanel.setBackground(Color.WHITE);
		reservationsPanel.setBorder(new TitledBorder(null, "Liste des reservations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		reservationsPanel.setBounds(261, 52, 240, 307);
		accueilPanel.add(reservationsPanel);
		reservationsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		reservationsTextArea.setEditable(false);
		infoReservations();
		reservationsPanel.add(reservationsTextArea);
		
		
		clientPanel.setLayout(null);
		clientPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		clientPanel.setBackground(Color.WHITE);
		mainPanel.add(clientPanel, "clientPanel");
		
		
		cliTitlePanel.setBackground(new Color(30, 144, 255));
		cliTitlePanel.setBounds(10, 11, 491, 30);
		clientPanel.add(cliTitlePanel);
		
		
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setForeground(Color.WHITE);
		cliTitlePanel.add(lblClient);
		
		
		cliInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cliInfoPanel.setBackground(Color.WHITE);
		cliInfoPanel.setBounds(10, 93, 491, 266);
		clientPanel.add(cliInfoPanel);
		cliInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		cliInfoTextArea.setEditable(false);		
		cliInfoPanel.add(cliInfoTextArea);
		
		
		cliRecherchePanel.setBackground(Color.WHITE);
		cliRecherchePanel.setBounds(10, 52, 491, 30);
		clientPanel.add(cliRecherchePanel);
		cliRecherchePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblRechercheClient.setHorizontalAlignment(SwingConstants.CENTER);
		cliRecherchePanel.add(lblRechercheClient);
		
		for(IClient c : hotel.getClients())
			cliComboBox.addItem(c.getNom());
		cliRecherchePanel.add(cliComboBox);
		
		
		mainPanel.add(nvelleReservPanel, "nvelleReservPanel");
		nvelleReservPanel.setLayout(null);
		nvelleReservPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		nvelleReservPanel.setBackground(Color.WHITE);
		
		
		nvlResTitlePanel.setBackground(new Color(30, 144, 255));
		nvlResTitlePanel.setBounds(10, 11, 491, 30);
		nvelleReservPanel.add(nvlResTitlePanel);
		
		
		lblNvlRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNvlRes.setForeground(Color.WHITE);
		nvlResTitlePanel.add(lblNvlRes);
		
		
		nvlResFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		nvlResFormPanel.setBackground(Color.WHITE);
		nvlResFormPanel.setBounds(10, 92, 491, 217);
		nvelleReservPanel.add(nvlResFormPanel);
		nvlResFormPanel.setLayout(new GridLayout(3, 2, 0, 0));
		
		
		lblNvlResTypeChambre.setHorizontalAlignment(SwingConstants.CENTER);
		nvlResFormPanel.add(lblNvlResTypeChambre);
		
		
		nvlResTypeChComboBox.addItem("Chambre de luxe lit double");
		nvlResTypeChComboBox.addItem("Chambre de luxe lit simple");
		nvlResTypeChComboBox.addItem("Chambre normale lit double");
		nvlResTypeChComboBox.addItem("Chambre normale lit simple");
		nvlResFormPanel.add(nvlResTypeChComboBox);
		
		
		lblNvlResDateDeb.setHorizontalAlignment(SwingConstants.CENTER);
		nvlResFormPanel.add(lblNvlResDateDeb);
		
		nvlResDateDebTextField = new JTextField();
		nvlResFormPanel.add(nvlResDateDebTextField);
		nvlResDateDebTextField.setColumns(10);
		
		
		lblNvlResDateDeFin.setHorizontalAlignment(SwingConstants.CENTER);
		nvlResFormPanel.add(lblNvlResDateDeFin);
		
		nvlResDateFinTextField = new JTextField();
		nvlResFormPanel.add(nvlResDateFinTextField);
		nvlResDateFinTextField.setColumns(10);
		

		nvlResRecherchePanel.setBounds(10, 52, 491, 29);
		nvelleReservPanel.add(nvlResRecherchePanel);
		nvlResRecherchePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblNvlResClient.setHorizontalAlignment(SwingConstants.CENTER);
		nvlResRecherchePanel.add(lblNvlResClient);
		
		for(IClient c : hotel.getClients()) {
			if(hotel.getReservation(c) == null)
				nvlResComboBox.addItem(c.getNom());
		}
		nvlResRecherchePanel.add(nvlResComboBox);
		
		
		nvlResButPanel.setBackground(Color.WHITE);
		nvlResButPanel.setBorder(new LineBorder(new Color(30, 144, 255)));
		nvlResButPanel.setBounds(10, 320, 491, 39);
		nvelleReservPanel.add(nvlResButPanel);
		nvlResButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnNvelleResValider.setForeground(Color.WHITE);
		btnNvelleResValider.setBackground(new Color(30, 144, 255));
		nvlResButPanel.add(btnNvelleResValider);
		
		
		mainPanel.add(modifReservPanel, "modifReservPanel");
		modifReservPanel.setLayout(null);
		modifReservPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		modifReservPanel.setBackground(Color.WHITE);
		
		
		modifResTitlePanel.setBackground(new Color(30, 144, 255));
		modifResTitlePanel.setBounds(10, 11, 491, 30);
		modifReservPanel.add(modifResTitlePanel);
		
		
		lblModifRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifRes.setForeground(Color.WHITE);
		modifResTitlePanel.add(lblModifRes);
		
		
		modifResFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		modifResFormPanel.setBackground(Color.WHITE);
		modifResFormPanel.setBounds(10, 92, 491, 217);
		modifReservPanel.add(modifResFormPanel);
		modifResFormPanel.setLayout(new GridLayout(3, 2, 0, 0));
		
		
		lblModifResTypeCh.setHorizontalAlignment(SwingConstants.CENTER);
		modifResFormPanel.add(lblModifResTypeCh);
		
		modifResTypeResComboBox.addItem("Chambre de luxe lit double");
		modifResTypeResComboBox.addItem("Chambre de luxe lit simple");
		modifResTypeResComboBox.addItem("Chambre normale lit double");
		modifResTypeResComboBox.addItem("Chambre normale lit simple");
		modifResFormPanel.add(modifResTypeResComboBox);
		
		
		lblModifResDateDeb.setHorizontalAlignment(SwingConstants.CENTER);
		modifResFormPanel.add(lblModifResDateDeb);
		
		modifResDateDebTextField = new JTextField();
		modifResDateDebTextField.setColumns(10);
		modifResFormPanel.add(modifResDateDebTextField);
		
		
		lblModifResDateFin.setHorizontalAlignment(SwingConstants.CENTER);
		modifResFormPanel.add(lblModifResDateFin);
		
		modifResDateFinTextField = new JTextField();
		modifResDateFinTextField.setColumns(10);
		modifResFormPanel.add(modifResDateFinTextField);
		
		
		modifResRecherchePanel.setBounds(10, 52, 491, 29);
		modifReservPanel.add(modifResRecherchePanel);
		modifResRecherchePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblModifResClient.setHorizontalAlignment(SwingConstants.CENTER);
		modifResRecherchePanel.add(lblModifResClient);
		
		for(IClient c : hotel.getClients()) {
			if(hotel.getReservation(c) != null)
				modifResComboBox.addItem(c.getNom());
		}
		modifResRecherchePanel.add(modifResComboBox);
		
		
		modifResButPanel.setBorder(new LineBorder(new Color(30, 144, 255)));
		modifResButPanel.setBackground(Color.WHITE);
		modifResButPanel.setBounds(10, 320, 491, 39);
		modifReservPanel.add(modifResButPanel);
		modifResButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnModifResValider.setForeground(Color.WHITE);
		btnModifResValider.setBackground(new Color(30, 144, 255));
		modifResButPanel.add(btnModifResValider);
		
		
		mainPanel.add(annulerReservPanel, "annulerReservPanel");
		annulerReservPanel.setLayout(null);
		annulerReservPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		annulerReservPanel.setBackground(Color.WHITE);
		
		
		annulerResTitlePanel.setBackground(new Color(30, 144, 255));
		annulerResTitlePanel.setBounds(10, 11, 491, 30);
		annulerReservPanel.add(annulerResTitlePanel);
		
		
		lblAnnulerRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnulerRes.setForeground(Color.WHITE);
		annulerResTitlePanel.add(lblAnnulerRes);
		
		
		annulerResFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		annulerResFormPanel.setBackground(Color.WHITE);
		annulerResFormPanel.setBounds(10, 92, 491, 217);
		annulerReservPanel.add(annulerResFormPanel);
		annulerResFormPanel.setLayout(new GridLayout(3, 2, 0, 0));
		
		
		lblAnnulerResTypeCh.setHorizontalAlignment(SwingConstants.CENTER);
		annulerResFormPanel.add(lblAnnulerResTypeCh);
		
		annulerResTypeChComboBox.addItem("Chambre de luxe lit double");
		annulerResTypeChComboBox.addItem("Chambre de luxe lit simple");
		annulerResTypeChComboBox.addItem("Chambre normale lit double");
		annulerResTypeChComboBox.addItem("Chambre normale lit simple");
		annulerResFormPanel.add(annulerResTypeChComboBox);
		
		
		lblAnnulerResDateDeb.setHorizontalAlignment(SwingConstants.CENTER);
		annulerResFormPanel.add(lblAnnulerResDateDeb);
		
		annulerResDateDebTextField = new JTextField();
		annulerResDateDebTextField.setColumns(10);
		annulerResFormPanel.add(annulerResDateDebTextField);
		
		
		lblAnnulerResDateFin.setHorizontalAlignment(SwingConstants.CENTER);
		annulerResFormPanel.add(lblAnnulerResDateFin);
		
		annulerResDateFinTextField = new JTextField();
		annulerResDateFinTextField.setColumns(10);
		annulerResFormPanel.add(annulerResDateFinTextField);
		
		
		annulerResRecherchePanel.setBounds(10, 52, 491, 29);
		annulerReservPanel.add(annulerResRecherchePanel);
		annulerResRecherchePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblAnnulerResClient.setHorizontalAlignment(SwingConstants.CENTER);
		annulerResRecherchePanel.add(lblAnnulerResClient);
		
		for(IClient c : hotel.getClients()) {
			if(hotel.getReservation(c) != null)
				annulerResComboBox.addItem(c.getNom());
		}
		annulerResRecherchePanel.add(annulerResComboBox);
		
		
		annulerResButPanel.setBorder(new LineBorder(new Color(30, 144, 255)));
		annulerResButPanel.setBackground(Color.WHITE);
		annulerResButPanel.setBounds(10, 320, 491, 39);
		annulerReservPanel.add(annulerResButPanel);
		annulerResButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnAnnulerResValider.setForeground(Color.WHITE);
		btnAnnulerResValider.setBackground(new Color(30, 144, 255));
		annulerResButPanel.add(btnAnnulerResValider);
		
		
		mainPanel.add(commanderPanel, "commanderPanel");
		commanderPanel.setLayout(null);
		commanderPanel.setBorder(new LineBorder(new Color(30, 144, 255), 5));
		commanderPanel.setBackground(Color.WHITE);
		
		
		commanderTitlePanel.setBackground(new Color(30, 144, 255));
		commanderTitlePanel.setBounds(10, 11, 491, 30);
		commanderPanel.add(commanderTitlePanel);
		
		
		lblCommanderUnRepas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommanderUnRepas.setForeground(Color.WHITE);
		commanderTitlePanel.add(lblCommanderUnRepas);
		
		
		commanderFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		commanderFormPanel.setBackground(Color.WHITE);
		commanderFormPanel.setBounds(10, 162, 491, 50);
		commanderPanel.add(commanderFormPanel);
		commanderFormPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblRepas.setHorizontalAlignment(SwingConstants.CENTER);
		commanderFormPanel.add(lblRepas);
		
		repasComboBox.addItem("Petit-dejeuner");
		repasComboBox.addItem("Dejeuner");
		repasComboBox.addItem("Diner");
		commanderFormPanel.add(repasComboBox);
		
		
		commanderRecherchePanel.setBounds(10, 52, 491, 29);
		commanderPanel.add(commanderRecherchePanel);
		commanderRecherchePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		lblCommanderClient.setHorizontalAlignment(SwingConstants.CENTER);
		commanderRecherchePanel.add(lblCommanderClient);
		
		for(IClient c : hotel.getClients()) {
			if(hotel.getReservation(c) != null)
				commanderComboBox.addItem(c.getNom());
		}	
		commanderRecherchePanel.add(commanderComboBox);
		
		
		commanderButPanel.setBorder(new LineBorder(new Color(30, 144, 255)));
		commanderButPanel.setBackground(Color.WHITE);
		commanderButPanel.setBounds(10, 320, 491, 39);
		commanderPanel.add(commanderButPanel);
		commanderButPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnCommanderValider.setForeground(Color.WHITE);
		btnCommanderValider.setBackground(new Color(30, 144, 255));
		commanderButPanel.add(btnCommanderValider);
		
		frame.setVisible(true);
	}
	
	private void initializeActions() {
		 btnAccueil.setActionCommand("accueil");
		 btnAccueil.addActionListener(this);
		 btnInformationsClient.setActionCommand("infoClient");
		 btnInformationsClient.addActionListener(this);
		 btnNvlReserv.setActionCommand("nvelleReserv");
		 btnNvlReserv.addActionListener(this);
		 btnModifReserv.setActionCommand("modifReserv");
		 btnModifReserv.addActionListener(this);
		 btnAnnulerReserv.setActionCommand("annulerReserv");
		 btnAnnulerReserv.addActionListener(this);
		 btnCommander.setActionCommand("commander");
		 btnCommander.addActionListener(this);
		 btnQuitter.setActionCommand("quitter");
		 btnQuitter.addActionListener(this);
		 
		 commanderComboBox.addActionListener(this);
		 cliComboBox.addActionListener(this);
		 annulerResComboBox.addActionListener(this);
		 annulerResTypeChComboBox.addActionListener(this);
		 modifResComboBox.addActionListener(this);
		 modifResTypeResComboBox.addActionListener(this);
		 nvlResComboBox.addActionListener(this);
		 nvlResTypeChComboBox.addActionListener(this);
		 repasComboBox.addActionListener(this);
		 
		 btnNvelleResValider.setActionCommand("nvelleResValider");
		 btnNvelleResValider.addActionListener(this);
		 btnModifResValider.setActionCommand("modifResValider");
		 btnModifResValider.addActionListener(this);
		 btnAnnulerResValider.setActionCommand("annulerResValider");
		 btnAnnulerResValider.addActionListener(this);
		 btnCommanderValider.setActionCommand("commanderValider");
		 btnCommanderValider.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		actionButtons(e);
		infoReservations();
		infoClient();
		nouvelleReservation(e);
		modifierReservation(e);
		annulerReservation(e);
		commanderRepas(e);
	}
	
	private void nouvelleReservation(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "nvelleResValider":
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String dateDebTextField = nvlResDateDebTextField.getText();
				String dateFinTextField = nvlResDateFinTextField.getText();
				try {
					Date dateDeb = formatter.parse(dateDebTextField);
					Date dateFin = formatter.parse(dateFinTextField);
					IChambre chambre = hotel.getChambre(nvlResTypeChComboBox.getSelectedItem().toString());
					IClient client = hotel.getClient(nvlResComboBox.getSelectedItem().toString());
					hotel.reserver(chambre, dateDeb, dateFin, client, reservationFactory);
					nvlResComboBox.removeItem(client.getNom());
					modifResComboBox.addItem(client.getNom());
					annulerResComboBox.addItem(client.getNom());
					commanderComboBox.addItem(client.getNom());
					JOptionPane.showMessageDialog(null, "Reservation effectuee", "Nouvelle reservation", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erreur format date", "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "Plus d'offres disponibles pour ce type de chambre", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				break;
		}
		
		nvlResDateDebTextField.setText("");
		nvlResDateFinTextField.setText("");
	}
	
	private void infoReservations() {
		reservationsTextArea.setText("");
		if(hotel.getReservations().size() == 0)
			reservationsTextArea.append("Aucune reservation");
		
		for (HashMap.Entry<IClient, IReservation> r : hotel.getReservations().entrySet()) {
			if(hotel.getChambres().contains(r.getValue().getChambre()))
				reservationsTextArea.append("La chambre " + r.getValue().getChambre().getNumero() + " est reservee \n\n");
		}
	}
	
	private void modifierReservation(ActionEvent e) {
		
		IClient client = null;
		IReservation reservation = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			client = hotel.getClient(modifResComboBox.getSelectedItem().toString());
			reservation = hotel.getReservation(client);
		}
		catch (NullPointerException ep) {
			
		}
		
		switch(e.getActionCommand()) {
			case "modifResValider":
				
				String dateDebTextField = modifResDateDebTextField.getText();
				String dateFinTextField = modifResDateFinTextField.getText();
				try {
					Date dateDeb = formatter.parse(dateDebTextField);
					Date dateFin = formatter.parse(dateFinTextField);
					
					IChambre chambreClient = reservation.getChambre();
					IChambre chambre = null;
					
					if(chambreClient.typeChambre() == modifResTypeResComboBox.getSelectedItem().toString())
						chambre = chambreClient;
					else
						chambre = hotel.getChambre(modifResTypeResComboBox.getSelectedItem().toString());
					
					hotel.modifierReservation(client, chambre, dateDeb, dateFin);
					JOptionPane.showMessageDialog(null, "Modification effectuee", "Modifier reservation", JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erreur format date", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
		}
		
		try {
			modifResTypeResComboBox.setSelectedItem(reservation.getChambre().typeChambre());
			modifResDateDebTextField.setText(formatter.format(reservation.getStartDate()));
			modifResDateFinTextField.setText(formatter.format(reservation.getEndDate()));
		}
		catch (NullPointerException ep2) {
		
		}
	
	}
	
	private void annulerReservation(ActionEvent e) {
		
		IClient client = null;
		try {
			client = hotel.getClient(annulerResComboBox.getSelectedItem().toString());
			IReservation reservation = hotel.getReservation(client);
			annulerResTypeChComboBox.setSelectedItem(reservation.getChambre().typeChambre());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			annulerResDateDebTextField.setText(formatter.format(reservation.getStartDate()));		
			annulerResDateFinTextField.setText(formatter.format(reservation.getEndDate()));
		}
		catch (NullPointerException ep) {
			
		}
		
		switch(e.getActionCommand()) {
		case "annulerResValider":
			int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment annuler cette reservation ?", "Annuler reservation", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION) {
				nvlResComboBox.addItem(client.getNom());
				modifResComboBox.removeItem(client.getNom());
				annulerResComboBox.removeItem(client.getNom());
				commanderComboBox.removeItem(client.getNom());
				hotel.supprimerReservation(client);
				JOptionPane.showMessageDialog(null, "Annulation effectuee", "Annuler reservation", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
		
		annulerResTypeChComboBox.setEditable(false);
		annulerResDateDebTextField.setEditable(false);
		annulerResDateFinTextField.setEditable(false);
		
	}
	
	private void infoClient() {

		for(IClient c : hotel.getClients()) {
			if(cliComboBox.getSelectedItem().equals(c.getNom())) {
				IReservation r = hotel.getReservation(c);
				cliInfoTextArea.setText("");
				if(r != null) {
					int prixRepas = 0;
					int total = 0;
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM y HH:mm:ss");
					int nbJours = (int) ChronoUnit.DAYS.between(r.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), r.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					
					for(int i = 0; i < nbJours; i++)
						total += r.getChambre().getPrix();
					
					cliInfoTextArea.append("Nom : " + c.getNom() + "\n");
					cliInfoTextArea.append("Prenom : " + c.getPrenom() + "\n");
					cliInfoTextArea.append("Offre choisie : " + r.getChambre().typeChambre() + "\n");
					cliInfoTextArea.append("Debut sejour : " + simpleDateFormat.format(r.getStartDate()) + "\n");
					cliInfoTextArea.append("Fin sejour : " + simpleDateFormat.format(r.getEndDate()) + "\n");
					cliInfoTextArea.append("Nombre de jours : " + nbJours + "\n");
					cliInfoTextArea.append("Prix de la chambre : " + r.getChambre().getPrix() + " � par jour \n");		
					
					for(IRepas repas : c.getRepas())
						prixRepas += repas.getPrix();
					
					total += prixRepas;
					
					cliInfoTextArea.append("Prix repas : " + prixRepas + " � \n");
					cliInfoTextArea.append("Total : " + total + " � \n");
				}
				else
					cliInfoTextArea.append("Aucune reservation \n");
			}
		}
		
	}
	
	private void commanderRepas(ActionEvent e) {
		
		IClient client = null;
		
		try {
			client = hotel.getClient(commanderComboBox.getSelectedItem().toString());
		}
		catch (NullPointerException ep) {
			
		}
		
		switch (e.getActionCommand()) {
			case "commanderValider":
				hotel.commanderRepas(client, repasComboBox.getSelectedItem().toString(), repasFactory);
				JOptionPane.showMessageDialog(null, "Commande effectuee", "Commander repas", JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}

	private void actionButtons(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "accueil":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "accueilPanel");
			break;
		case "infoClient":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "clientPanel");
			break;
		case "nvelleReserv":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "nvelleReservPanel");
			break;
		case "modifReserv":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "modifReservPanel");
			break;
		case "annulerReserv":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "annulerReservPanel");
			break;
		case "commander":
			((CardLayout) mainPanel.getLayout()).show(mainPanel, "commanderPanel");
			break;
		case "quitter":
			hotelSerialisation.sauvegarder(hotel);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			break;
		}
	}
	

}
