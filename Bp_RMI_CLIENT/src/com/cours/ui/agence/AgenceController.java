package com.cours.ui.agence;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.cours.entities.Agence;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.cours.tools.Tools2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AgenceController implements Initializable {

    @FXML
    private TextField codeagtxt;

    @FXML
    private TextField adrsagtxt;

    @FXML
    private TextField telagtxt;
    @FXML
    void annuler(ActionEvent event) {
    	codeagtxt.setText("");
    	adrsagtxt.setText("");
    	telagtxt.setText("");
    }
    @FXML
    void retour(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/menu/Menuadmin.fxml";
		Tools2.myLoadPage(event, url,0);
    }
    @FXML
    void EnregistrerAg(ActionEvent event) throws RemoteException {
    	if (codeagtxt.getText().trim().equals("") || adrsagtxt.getText().trim().equals("")
				|| telagtxt.getText().trim().equals("")){
    		Notification.NotifSucces("succes", "veillez remplir tous les champs");
			return;
		}
    	ag.setCode(codeagtxt.getText());
    	ag.setAdresse(adrsagtxt.getText());
    	ag.setTel(telagtxt.getText());
		int ok = Fabrique.agdb.addAgence(ag);
		if (ok > 0) {
			Notification.NotifSucces("succes", "insertion reussit");
		} else {
			Notification.NotifError("erreur", "erreur d'insertion");
		}
    }

    @FXML
    Agence ag=new Agence();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fabrique.init();

	}

}

