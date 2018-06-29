package com.cours.ui.virement;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.cours.entities.Compte;
import com.cours.entities.Operation;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VirementController implements Initializable {

    @FXML
    private Button okBtn;

    @FXML
    private TextField numeroCompteTfd;

    @FXML
    private Label nomclientlabel;

    @FXML
    private Label prenomclientlabel;

    @FXML
    private Label typecomptelabel;

    @FXML
    private Label soldecomptelabel;

    @FXML
    private Button okBtn1;

    @FXML
    private TextField numeroCompteTfd1;

    @FXML
    private Label nomclientlabel1;

    @FXML
    private Label prenomclientlabel1;

    @FXML
    private Label typecomptelabel1;

    @FXML
    private Label soldecomptelabel1;

    @FXML
    private JFXTextField montantTfd;
    @FXML
    private Button validerBtn;
    private Operation op;

    @FXML
	private void handleCompte(){
    	okBtn.setOnMouseClicked(arg0 -> {
			if (numeroCompteTfd.getText().trim().equals("")) {
				Notification.NotifError("Info", "Renseignez le numero du compte");
				return;
			}
			try {
				Compte c = Fabrique.cptdb.getCompte(numeroCompteTfd.getText());
				if (c == null) {
					Notification.NotifError("Info", "Ce numero de compte n'existe pas");
					return;
				}
				nomclientlabel.setText(c.getClient().getNomCli());
				prenomclientlabel.setText(c.getClient().getPrenomCli());
				typecomptelabel.setText(c.getType());
				soldecomptelabel.setText(c.getSolde() + "");
			} catch (RemoteException e) {

				e.printStackTrace();
			}

		});

    }
    @FXML
    private void handleCompte1(){
    	okBtn1.setOnMouseClicked(arg0 -> {
			if (numeroCompteTfd1.getText().trim().equals("")) {
				Notification.NotifError("Info", "Renseignez le numero du compte");
				return;
			}
			try {
				Compte c = Fabrique.cptdb.getCompte(numeroCompteTfd1.getText());
				if (c == null) {
					Notification.NotifError("Info", "Ce numero de compte n'existe pas");
					return;
				}
				nomclientlabel1.setText(c.getClient().getNomCli());
				prenomclientlabel1.setText(c.getClient().getPrenomCli());
				typecomptelabel1.setText(c.getType());
				soldecomptelabel1.setText(c.getSolde() + "");
			} catch (RemoteException e) {

				e.printStackTrace();
			}

		});

    }
    @FXML
	private void handleOperation() {
		//boolean test=true;
		op = null;
		validerBtn.setOnMouseClicked(value -> {
			 if(montantTfd.getText().trim().equals("")){
					Notification.NotifError("Info", "renseignez le montant a deposer");
					return;
			 }
			int ok;
			if(Integer.parseInt(montantTfd.getText())<Integer.parseInt(soldecomptelabel.getText())){
				try {
					ok=Fabrique.cptdb.UpdateSoldeDepot(Integer.parseInt(montantTfd.getText()),numeroCompteTfd.getText() );
					soldecomptelabel.setText(Integer.parseInt(soldecomptelabel.getText())+Integer.parseInt(montantTfd.getText())+"");
					ok=Fabrique.cptdb.RetraitPossible(Integer.parseInt(montantTfd.getText()),numeroCompteTfd1.getText() );
					soldecomptelabel1.setText(Integer.parseInt(soldecomptelabel1.getText())-Integer.parseInt(montantTfd.getText())+"");
					Notification.NotifSucces("succes", "operation reussit");


							} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handleCompte();
		handleCompte1();
		handleOperation();

	}

}
