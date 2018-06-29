package com.cours.ui.consulterCompte;

import java.io.IOException;

import com.cours.tools.Tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConsulterCompteController {

    @FXML
    void loadOperation(ActionEvent event) throws IOException {
    	String url = "/com/cours/ui/operation/FOperation.fxml";
		Tools.myLoadPage(event, url);

    }

}