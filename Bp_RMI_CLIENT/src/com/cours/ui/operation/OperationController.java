package com.cours.ui.operation;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;

import com.cours.entities.Compte;
import com.cours.entities.Operation;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.cours.tools.Tools;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class OperationController implements Initializable {

	@FXML
	private RadioButton depotRbtn;

	@FXML
	private Label soldecomptelabel;



	@FXML
	private Label nomclientlabel;

	@FXML
	private RadioButton retraitRbtn;

	@FXML
	private Label prenomclientlabel;

	@FXML
	private RadioButton fermeturebtn;

	@FXML
	private Label typecomptelabel;

	@FXML
	private Button okBtn;
	@FXML
	private Button validerBtn;
	@FXML
	private TextField numeroCompteTfd;
    @FXML
    private DatePicker dateoperationpicker;
	@FXML
	private JFXTextField montantTfd;

	@FXML
	private ToggleGroup tg;

	private Operation op;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handleOperation();
		handleCompte();
		retraitRbtn.setToggleGroup(tg);
		//virementrbtn.setToggleGroup(tg);
		depotRbtn.setToggleGroup(tg);
		dateoperationpicker.setValue(LocalDate.now());

	}

	@FXML
	private void handleCompte() {
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
	private void handleOperation() {
		//boolean test=true;
		op = null;
		validerBtn.setOnMouseClicked(value -> {
			if (depotRbtn.isSelected() == true) {
				if(montantTfd.getText().trim().equals(""))
				{
					Notification.NotifError("Info", "renseignez le montant a deposer");
					return;
				}
			int ok;
			try {
				ok=Fabrique.cptdb.UpdateSoldeDepot(Integer.parseInt(montantTfd.getText()),numeroCompteTfd.getText() );
				Notification.NotifSucces("succes", "operation reussit");
				soldecomptelabel.setText(Integer.parseInt(soldecomptelabel.getText())+Integer.parseInt(montantTfd.getText())+"");
						} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(retraitRbtn.isSelected() == true){
				if(montantTfd.getText().trim().equals(""))
				{
					Notification.NotifError("Info", "renseignez le montant a deposer");
					return;
				}
			int ok;
			try {
				ok=Fabrique.cptdb.RetraitPossible(Integer.parseInt(montantTfd.getText()),numeroCompteTfd.getText() );
				Notification.NotifSucces("succes", "operation reussit");
				soldecomptelabel.setText(Integer.parseInt(soldecomptelabel.getText())-Integer.parseInt(montantTfd.getText())+"");
						} catch (Exception e) {
				e.printStackTrace();
			}

		}

		});
	}

    @FXML
    void ChargerVirement(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/virement/Virement.fxml";
		Tools.myLoadPage(event, url);
    }

}
