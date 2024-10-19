package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @FXML
    private TextField userid;
    @FXML
    private PasswordField senhaid;
    @FXML
    private AnchorPane loginPane;

    public void initialize() {
        connect();
        
        centerLoginPane();
    }

    private void centerLoginPane() {
        if (loginPane != null) {
            double centerX = (1920 - loginPane.getPrefWidth()) / 2;
            double centerY = (1080 - loginPane.getPrefHeight()) / 2;

            loginPane.setLayoutX(centerX);
            loginPane.setLayoutY(centerY);
        } else {
            System.out.println("loginPane is null!");
        }
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/prova", "root", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void logar() {
        String nome = userid.getText();
        String senha = senhaid.getText();
        
        try {
            pst = con.prepareStatement("SELECT * FROM usuarios WHERE nome = ? AND senha = ?");
            pst.setString(1, nome);
            pst.setString(2, senha);
            
            rs = pst.executeQuery();

            if (rs.next()) {
                paginaAtual("Menu.fxml");
            } else {
                String mensagem = nome.isEmpty() && senha.isEmpty() ? 
                    "Valores inválidos." : "Login ou senha incorretos.";
                JOptionPane.showMessageDialog(null, mensagem, "Ocorreu um erro!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void paginaAtual(String pagina) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pagina));
            AnchorPane root = loader.load();

            Scene currentScene = senhaid.getScene();
            currentScene.setRoot(root);
            currentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}