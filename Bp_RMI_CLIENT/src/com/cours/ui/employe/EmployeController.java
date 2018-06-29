package com.cours.ui.employe;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.cours.entities.Agence;
import com.cours.entities.Employe;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.cours.tools.Tools2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmployeController implements Initializable {

    @FXML
    private TextField nomcomplettxt;

    @FXML
    private TextField adressetxt;

    @FXML
    private TextField telephonetxt;

    @FXML
    private ComboBox<Agence> agencecbx;
    Employe emp=new Employe();
    @FXML
    void EnregistrerEmp(ActionEvent event) throws RemoteException {
    	if(nomcomplettxt.getText().trim().equals("") || adressetxt.getText().trim().equals("")
				|| telephonetxt.getText().trim().equals("")|| agencecbx.getValue().equals("")){
    		Notification.NotifSucces("succes", "veillez remplir tous les champs");
    		return;
    	}
    	emp.setNomComplet(nomcomplettxt.getText());
    	emp.setAdresse(adressetxt.getText());
    	emp.setTel(telephonetxt.getText());
    	emp.setAgence(agencecbx.getValue());
		int ok = Fabrique.empdb.addEmploye(emp);
		if (ok > 0) {
			Notification.NotifSucces("succes", "insertion reussit");
		} else {
			Notification.NotifError("erreur", "erreur d'insertion");
		}


    }

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/menu/Menuadmin.fxml";
		Tools2.myLoadPage(event, url,0);

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fabrique.init();
		try {
			loadComboType();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loadComboType() throws RemoteException{
		ObservableList<Agence> l_agence=FXCollections.observableArrayList();
		Fabrique.agdb.listeAgence()
					.stream()
					.forEach(s->{
						l_agence.add(s);
					});
		agencecbx.setItems(l_agence);
	}


}
