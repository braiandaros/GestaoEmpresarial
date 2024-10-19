package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuController {
	
	@FXML
	private Button btnid;
	@FXML
    private AnchorPane MenuPane;
	
	@FXML
	private void initialize() {
		centerMenuPane();
	}
	
	@FXML
	private void usuarios() {
		paginaAtual("Usuarios.fxml");
	}
	
	@FXML
	private void produtos() {
		paginaAtual("Produtos.fxml");
	}
	
	@FXML
	private void franquias() {
		paginaAtual("Franquias.fxml");
	}
	@FXML
	private void sair() {
		System.exit(0);
	}
	
	private void centerMenuPane() {
        if (MenuPane != null) {
            double centerX = (1920 - MenuPane.getPrefWidth()) / 2;
            double centerY = (1080 - MenuPane.getPrefHeight()) / 2;

            MenuPane.setLayoutX(centerX);
            MenuPane.setLayoutY(centerY);
        } else {
            System.out.println("loginPane is null!");
        }
    }
	
	private void paginaAtual(String Pagina) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Pagina));
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(Pagina));

			Scene currentScene = btnid.getScene();
			currentScene.setRoot(root);
			
			currentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
