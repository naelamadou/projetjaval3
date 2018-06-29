package application;

import java.io.IOException;
import com.cours.entities.User;
import com.cours.tools.Fabrique;
import com.cours.tools.Notification;
import com.cours.tools.Tools;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {
	@FXML
	private JFXTextField logintxt;

	public static User user;
	@FXML
	private JFXPasswordField passwordtxt;


	public void getConnexion(ActionEvent event) throws IOException {
		String titre = "info";
		if (logintxt.getText().trim().equals("") || passwordtxt.getText().trim().equals("")) {
			Notification.NotifError(titre, "veillez remplir tout les champs!!");
		} else {

			Fabrique.init();
			 user = Fabrique.udb.getConnexion(logintxt.getText(), passwordtxt.getText());
			if (user != null) {
				if (user.getProfil().equals("Responsable des comptes")) {
					String url = "/com/cours/ui/menu/Menu.fxml";
					Tools.myLoadPage(event, url);
					Notification.NotifSucces(titre, "connexion reussit");
				}else if(user.getProfil().equals("administrateur")){
					String url = "/com/cours/ui/menu/Menuadmin.fxml";
					Tools.myLoadPage(event, url);
					Notification.NotifSucces(titre, "connexion reussit");
				}

			} else {
				Notification.NotifError(titre, "login ou mot de passe incorrect");
			}
		}
	}

	public void showMessage(String titre, String message) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(titre);
		a.setTitle(message);
		a.showAndWait();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			//Parent root=FXMLLoader.load(getClass().getResource("/com/cours/ui/compte/Compte.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
