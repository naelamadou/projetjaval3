package com.cours.ui.compte;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.cours.entities.Agence;
import com.cours.entities.Client;
import com.cours.entities.Compte;
import com.cours.entities.CompteBloque;
import com.cours.entities.CompteCourant;
import com.cours.entities.CompteEpargne;
import com.cours.entities.User;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.cours.tools.Tools;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CompteController implements Initializable {

	@FXML
	private JFXTextField nomclitxt;
	@FXML
	private JFXButton ajouterCompteBtn;

	@FXML
	private JFXTextField prenomclitxt;

	@FXML
	private JFXTextField adrssclitxt;

	@FXML
	private JFXTextField emailclitxt;

	@FXML
	private JFXTextField telephoneclitxt;

	@FXML
	private JFXTextField salactutxt;

	@FXML
	private JFXTextField profession_cli;

	@FXML
	private JFXTextField infoemplotxt;

	@FXML
	private JFXTextField numcniclitxt;
	@FXML
	private JFXTextField numcompttbx;

	@FXML
	private JFXTextField numagencetbx;
	@FXML
	private JFXComboBox<Agence> numagencecbx;

	@FXML
	private JFXTextField cleribtbx;
	@FXML
	private AnchorPane anchorcompteCourant;

	@FXML
	private JFXTextField agiotxt;

	@FXML
	private JFXTextField raisonsocialtbx;
	@FXML
	private JFXTextField fraisOuverturetxt;

	@FXML
	private JFXTextField adressetbx;

	@FXML
	private JFXTextField numidemploytbx;
	@FXML
	private Label agiolabel;

	@FXML
	private Label numidlabel;

	@FXML
	private Label adresselabel;

	@FXML
	private Label raisonsociallabel;

	@FXML
	private TableView<Compte> tableComptes;

	@FXML
	private TableColumn<Compte, String> colonneNumero;

	@FXML
	private TableColumn<Compte, String> colonneType;

	@FXML
	private TableColumn<Compte, String> colonneAdresse;

	@FXML
	private TableColumn<Compte, Client> colonneClient;

	@FXML
	private TableColumn<Compte, String> colonneRIB;

	@FXML
	private TableColumn<Compte, User> colonneResponsable;
	@FXML
	private TableView<CompteCourant> tableCompteCourant;

	@FXML
	private TableColumn<CompteCourant, String> colonneAgio;

	@FXML
	private TableColumn<CompteCourant, String> colonneRaison;

	@FXML
	private TableColumn<CompteCourant, String> colonneIDEmployeur;

	@FXML
	private TableView<CompteBloque> tableBloque;

	@FXML
	private TableColumn<CompteBloque, Integer> colonneFrais;
	@FXML
	private TableView<CompteEpargne> tableEpargne;

	@FXML
	private TableColumn<CompteEpargne, Integer> colonneFraisEpargne;
	@FXML
	private JFXComboBox<String> comboxtype;
	@FXML
	private Label fraislabel;
	private ObservableList<String> listType = FXCollections.observableArrayList("compte courant", "compte bloquer",
			"compte epargne");
	List<Compte> listCompte =new ArrayList<Compte>();
	List<Client> list = new ArrayList<Client>();
	//List<Agence> listagence=new ArrayList<Agence>();

	Client cl = null;
	Agence agence=new Agence();


	private boolean validerFormClient() {
		if (nomclitxt.getText().trim().equals("") || prenomclitxt.getText().trim().equals("")
				|| adrssclitxt.getText().trim().equals("") || emailclitxt.getText().trim().equals("")
				|| telephoneclitxt.getText().trim().equals("") || salactutxt.getText().trim().equals("")
				|| profession_cli.getText().trim().equals("") || infoemplotxt.getText().trim().equals("")
				|| numcniclitxt.getText().trim().equals("")) {
			return false;
		}
		return true;
	}

	@FXML
	void addClient(ActionEvent event) throws RemoteException {
		if (nomclitxt.getText().trim().equals("") || prenomclitxt.getText().trim().equals("")
				|| adrssclitxt.getText().trim().equals("") || emailclitxt.getText().trim().equals("")
				|| telephoneclitxt.getText().trim().equals("") || salactutxt.getText().trim().equals("")
				|| profession_cli.getText().trim().equals("") || infoemplotxt.getText().trim().equals("")
				|| numcniclitxt.getText().trim().equals("")) {
			return;
		}

		cl.setNomCli(nomclitxt.getText());
		cl.setPrenomCli(prenomclitxt.getText());
		cl.setAdresseCli(adrssclitxt.getText());
		cl.setEmailCli(emailclitxt.getText());
		cl.setTelCli(telephoneclitxt.getText());
		cl.setSalactCli(Double.parseDouble(salactutxt.getText()));
		cl.setProfessionCli(profession_cli.getText());
		cl.setInfoEmployeurCli(infoemplotxt.getText());
		cl.setNumCNi(numcniclitxt.getText());

		int ok = Fabrique.cldb.addClient(cl);
		if (ok > 0) {
			Notification.NotifSucces("succes", "insertion reussit");
		} else {
			Notification.NotifError("erreur", "erreur d'insertion");
		}

	}

	//private IClient clDAO = new ClientDao();

	@FXML
	private void addCompte() {
		ajouterCompteBtn.setOnMouseClicked(value -> {
			try {

				if (numagencecbx.getValue() == null || cleribtbx.getText().equals("")) {

					Notification.NotifError("Attention", "veillez remplir la cle RIB et / ou choisir une agence!!");
					return;
				}
				if (validerFormClient() == false) {
					Notification.NotifError("Attention", "veillez remplir tout les champs!!");
					return;
				}
				if (cl == null) {

					cl = getClient();
					try {
						Fabrique.cldb.addClient(cl);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						cl = Fabrique.cldb.getClient(cl.getNumCNi());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (comboxtype.getValue() == null) {
					Notification.NotifError("Attention", "choisissez un type de compte!!");
					return;
				}
				if (comboxtype.getValue().equals("compte courant")) {
					if (agiotxt.getText().trim().equals("") || raisonsocialtbx.getText().trim().equals("")
							|| adressetbx.getText().trim().equals("") || numidemploytbx.getText().trim().equals("")) {
						Notification.NotifError("Attention", "renseignez les info du compte courant!!");
						return;
					}
					String num = null;
					try {
						num = Fabrique.cptdb.generateNumero() + cleribtbx.getText() + numagencecbx.getValue().getCode();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CompteCourant compteCourant = new CompteCourant();
					compteCourant.setNumCompte(num);
					compteCourant.setAdresseEmploy(adressetbx.getText());
					compteCourant.setAgio(Double.parseDouble(agiotxt.getText()));
					compteCourant.setAgence(numagencecbx.getValue());
					compteCourant.setCleRib(cleribtbx.getText());
					compteCourant.setNumIdentifieEmploy(numidemploytbx.getText());
					compteCourant.setClient(cl);
					compteCourant.setRaisonSocialEmploy(raisonsocialtbx.getText());
					compteCourant.setResponsable(Main.user);
					compteCourant.setType(comboxtype.getValue());
					int ok = 0;
					try {
						ok = Fabrique.cptdb.addCompte(compteCourant);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ok > 0) {
						Notification.NotifSucces("je rentre", "courant");
						Notification.NotifSucces("succes", "insertion reussit");
					} else {
						Notification.NotifError("erreur", "erreur d'insertion");
						Notification.NotifSucces("je rentre", "err courant");
					}
				}
				if (comboxtype.getValue().equals("compte epargne") || comboxtype.getValue().equals("compte bloquer")) {
					if (fraisOuverturetxt.getText().trim().equals("")) {
						Notification.NotifError("Attention", "renseignez les frais d'ouverture du compte!!");
						return;
					}
					if (comboxtype.getValue().equals("compte epargne")) {
						CompteEpargne compteEpargne = new CompteEpargne();
						String num = null;
						try {
							num = Fabrique.cptdb.generateNumero() + cleribtbx.getText() + numagencecbx.getValue().getCode();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						compteEpargne.setNumCompte(num);
						compteEpargne.setAgence(numagencecbx.getValue());
						compteEpargne.setCleRib(cleribtbx.getText());
						compteEpargne.setClient(cl);
						compteEpargne.setResponsable(Main.user);
						compteEpargne.setType(comboxtype.getValue());
						compteEpargne.setFraisOuverture(Double.parseDouble(fraisOuverturetxt.getText()));
						int ok = 0;
						try {
							ok = Fabrique.cptdb.addCompte(compteEpargne);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (ok > 0) {
							Notification.NotifSucces("je rentre", "succ eparg");
							Notification.NotifSucces("succes", "insertion reussit");
						} else {
							Notification.NotifSucces("je rentre", "err ep");
							Notification.NotifError("erreur", "erreur d'insertion");
							// Client c = compteEpargne.getClient();
							// numcniclitxt.setText(c.getNumCNi());
						}
					} else if (comboxtype.getValue().equals("compte bloquer")) {
						CompteBloque compteBloque = new CompteBloque();
						String num = null;
						try {
							num = Fabrique.cptdb.generateNumero() + cleribtbx.getText() + numagencecbx.getValue().getCode();
						} catch (Exception e) {
							e.printStackTrace();
						}
						compteBloque.setNumCompte(num);
						compteBloque.setAgence(numagencecbx.getValue());
						compteBloque.setCleRib(cleribtbx.getText());
						compteBloque.setClient(cl);
						compteBloque.setResponsable(Main.user);
						compteBloque.setType(comboxtype.getValue());
						compteBloque.setFraisDouverture(Double.parseDouble(fraisOuverturetxt.getText()));
						int ok = 0;
						try {
							ok = Fabrique.cptdb.addCompte(compteBloque);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (ok > 0) {
							Notification.NotifSucces("succes", "insertion reussit");
						} else {
							Notification.NotifError("erreur", "erreur d'insertion");
						}
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});

	}

	public void visible() {
		if (comboxtype.getValue().equals("compte courant")) {
			agiolabel.setVisible(true);
			numidlabel.setVisible(true);
			adresselabel.setVisible(true);
			raisonsociallabel.setVisible(true);
			agiotxt.setVisible(true);
			raisonsocialtbx.setVisible(true);
			adressetbx.setVisible(true);
			numidemploytbx.setVisible(true);
			fraisOuverturetxt.setVisible(false);
			fraislabel.setVisible(false);
		} else {
			cacher();

		}
	}

	int index = 1;
	String titre = "info";

	@FXML
	void deleteClient(ActionEvent event) {

	}

	private int recup;

	@FXML
	void modifClient(ActionEvent event) {
		/*
		 * // Client cl= new Client();
		 */

		// int ok=0;
		/*
		 * try { cl.setIdCl(recup); cl.setNomCli(nomclitxt.getText());
		 * cl.setPrenomCli(prenomclitxt.getText());
		 * cl.setAdresseCli(adrssclitxt.getText());
		 * cl.setEmailCli(emailclitxt.getText());
		 * cl.setTelCli(telephoneclitxt.getText());
		 * cl.setSalactCli(Double.parseDouble(salactutxt.getText()));
		 * cl.setProfessionCli(profession_cli.getText());
		 * cl.setInfoEmployeurCli(infoemplotxt.getText());
		 * cl.setNumCNi(numcniclitxt.getText()); //ok=cldb.update(cl);
		 * if(cldb.update(cl)!=0) { Notification.NotifSucces("confirmation",
		 * "Donnees modifier"); }else { Notification.NotifError("ERROR",
		 * "ERREUR DE CODE"); } } catch (Exception e) { e.printStackTrace(); }
		 *
		 * index = tableview_client.getSelectionModel().getSelectedIndex(); if
		 * (index < 0) { Notification.NotifError(titre, "selectioner une ligne"
		 * ); return; } try { cl.setIdCl(recup);
		 * cl.setNomCli(nomclitxt.getText());
		 * cl.setPrenomCli(prenomclitxt.getText());
		 * cl.setAdresseCli(adrssclitxt.getText());
		 * cl.setEmailCli(emailclitxt.getText());
		 * cl.setTelCli(telephoneclitxt.getText());
		 * cl.setSalactCli(Double.parseDouble(salactutxt.getText()));
		 * cl.setProfessionCli(profession_cli.getText());
		 * cl.setInfoEmployeurCli(infoemplotxt.getText());
		 * cl.setNumCNi(numcniclitxt.getText()); int ok = cldb.update(cl); if
		 * (ok != 0) { Notification.NotifSucces("confirmation",
		 * "Donnees modifier"); } else { Notification.NotifError("ERROR",
		 * "ERREUR DE CODE"); } } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void fillTableCompte() {

	}

	public void cacher() {
		agiolabel.setVisible(false);
		numidlabel.setVisible(false);
		adresselabel.setVisible(false);
		raisonsociallabel.setVisible(false);
		agiotxt.setVisible(false);
		raisonsocialtbx.setVisible(false);
		adressetbx.setVisible(false);
		numidemploytbx.setVisible(false);
		fraisOuverturetxt.setVisible(true);
		fraislabel.setVisible(true);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fabrique.init();
		fillTableCompte();
		//combo qui charge la liste des types instancier par defaut
		comboxtype.setItems(listType);
		loadClient();
		addCompte();
		numcompttbx.setDisable(true);
		cacher();
		//listCompte.clear();
		//combo qui charge la liste la liste dans la table Agence
		try {
			loadComboType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//affichage des donnees dans le tableview de tablComptes
		//colonneNumero.setCellValueFactory(new PropertyValueFactory<>("numCompte"));
		//colonneRIB.setCellValueFactory(new PropertyValueFactory<>("cleRib") );
		  //colonneType.setCellValueFactory(new PropertyValueFactory<>("type") );
		  //colonneAdresse.setCellValueFactory(new PropertyValueFactory<>("agence"));
		  //colonneClient.setCellValueFactory(new PropertyValueFactory<>("idClient") );
		  //colonneResponsable.setCellFactory(new PropertyValueFactory<>("responsable"));
		  try {
			//loadTableCompte();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//listCompte.addAll(cptdb.listeCompte());
		//listagence.addAll(agdb.getAgenceById(agence.getIdAg()));
		//list.addAll(cldb.getClientByid(cl.getIdCl()));

	}
	public void loadComboType() throws RemoteException{
		ObservableList<Agence> l_agence=FXCollections.observableArrayList();
		Fabrique.agdb.listeAgence()
					.stream()
					.forEach(s->{
						l_agence.add(s);
					});
		numagencecbx.setItems(l_agence);
	}
	/*public void loadTableCompte() throws RemoteException{
		ObservableList<Compte> l_compte=FXCollections.observableArrayList();
		Fabrique.cptdb.liste()
							.stream()
							.forEach(c->{
								l_compte.add(c);
							});
		tableComptes.setItems(l_compte);
	}*/

	public void annuler() {
		nomclitxt.setText("");
		prenomclitxt.setText("");
		adrssclitxt.setText("");
		emailclitxt.setText("");
		telephoneclitxt.setText("");
		salactutxt.setText("");
		profession_cli.setText("");
		infoemplotxt.setText("");
		numcniclitxt.setText("");
		nomclitxt.setDisable(false);
		prenomclitxt.setDisable(false);
		adrssclitxt.setDisable(false);
		emailclitxt.setDisable(false);
		telephoneclitxt.setDisable(false);
		salactutxt.setDisable(false);
		profession_cli.setDisable(false);
		infoemplotxt.setDisable(false);
	}

	public void selectLigne() {
		/*
		 * Client cl = tableview_client.getSelectionModel().getSelectedItem();
		 * nomclitxt.setText(cl.getNomCli());
		 * prenomclitxt.setText(cl.getPrenomCli());
		 * adrssclitxt.setText(cl.getAdresseCli());
		 * emailclitxt.setText(cl.getEmailCli());
		 * telephoneclitxt.setText(cl.getTelCli());
		 * salactutxt.setText(cl.getSalactCli() + "");
		 * profession_cli.setText(cl.getProfessionCli());
		 * infoemplotxt.setText(cl.getInfoEmployeurCli());
		 * numcniclitxt.setText(cl.getNumCNi());
		 */
	}

	@FXML
	void Retourd(ActionEvent event) throws IOException {
		String url = "/com/cours/ui/menu/Menu.fxml";
		Tools.myLoadPage(event, url);

	}

	public void DesactiverButton() {
		nomclitxt.setDisable(true);
		prenomclitxt.setDisable(true);
		adrssclitxt.setDisable(true);
		emailclitxt.setDisable(true);
		telephoneclitxt.setDisable(true);
		salactutxt.setDisable(true);
		profession_cli.setDisable(true);
		infoemplotxt.setDisable(true);
		// numcniclitxt.setDisable(true);
	}

	//private IClient clientdao = new ClientDao();

	private void loadClient() {
		numcniclitxt.focusedProperty().addListener((obs, oldMatricule, newMatricule) -> {
			try {
				if (!newMatricule) {

					cl = Fabrique.cldb.getClient(numcniclitxt.getText());

					if (cl != null) {
						nomclitxt.setText(cl.getNomCli());
						prenomclitxt.setText(cl.getPrenomCli());
						adrssclitxt.setText(cl.getAdresseCli());
						emailclitxt.setText(cl.getEmailCli());
						telephoneclitxt.setText(cl.getTelCli());
						salactutxt.setText(cl.getSalactCli() + "");
						profession_cli.setText(cl.getProfessionCli());
						infoemplotxt.setText(cl.getInfoEmployeurCli());
						numcniclitxt.setText(cl.getNumCNi());
						DesactiverButton();
					} else {
						nomclitxt.setText("");
						prenomclitxt.setText("");
						adrssclitxt.setText("");
						emailclitxt.setText("");
						telephoneclitxt.setText("");
						salactutxt.setText("");
						profession_cli.setText("");
						infoemplotxt.setText("");
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Client getClient() {
		Client c = new Client();
		c.setNomCli(nomclitxt.getText());
		c.setPrenomCli(prenomclitxt.getText());
		c.setAdresseCli(adrssclitxt.getText());
		c.setEmailCli(emailclitxt.getText());
		c.setTelCli(telephoneclitxt.getText());
		c.setSalactCli(Double.parseDouble(salactutxt.getText()));
		c.setProfessionCli(profession_cli.getText());
		c.setInfoEmployeurCli(infoemplotxt.getText());
		c.setNumCNi(numcniclitxt.getText());
		return c;
	}





}
