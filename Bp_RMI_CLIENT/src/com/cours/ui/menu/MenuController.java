package com.cours.ui.menu;

import java.io.IOException;

import com.cours.tools.Tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {
    @FXML
    void loadClient(ActionEvent event) throws IOException {
    	String url="/com/cours/ui/compte/FCompte.fxml";
		Tools.myLoadPage(event, url);
    }

    @FXML
    void loadOperation(ActionEvent event) throws IOException {
    	//String url = "/com/cours/ui/consulterCompte/ConsulterCompte.fxml";
		//Tools.myLoadPage(event, url);
    	String url = "/com/cours/ui/operation/FOperation.fxml";
		Tools.myLoadPage(event, url);
    }
}
