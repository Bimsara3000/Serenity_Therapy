package lk.ijse.gdse71.serenity_therapy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane anchLogin;

    @FXML
    private Button btnLog;

    @FXML
    private Label lblRRS;

    @FXML
    private ImageView picLogo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    private VBox vBoxLogin;

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        anchLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));

        anchLogin.getChildren().add(load);
    }

}
