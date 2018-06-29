package com.cours.ui.menu;

import java.io.IOException;
import com.cours.tools.Tools2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuControllerAdmin {

    @FXML
    void loadAgence(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/agence/Agence.fxml";
		Tools2.myLoadPage(event, url,0);
    }
    @FXML
    void loadEmployer(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/employe/FEmploye.fxml";
		Tools2.myLoadPage(event, url,0);
    }

}
