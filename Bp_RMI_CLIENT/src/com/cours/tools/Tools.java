package com.cours.tools;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tools {

	private void loadPage(ActionEvent event, String url) throws IOException{
		//on cache l'ancienne fenetre
		((Node) event.getSource()).getScene().getWindow().hide();
		//creation d'une nouvelle scene
		Parent root = FXMLLoader.load(getClass().getResource(url));
		Scene scene = new Scene(root);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("la banque du peuple");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void myLoadPage(ActionEvent event, String url) throws IOException{
		(new Tools()).loadPage(event, url);
	}
}
