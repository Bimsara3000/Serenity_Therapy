package lk.ijse.gdse71.serenity_therapy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.gdse71.serenity_therapy.bo.BOFactory;
import lk.ijse.gdse71.serenity_therapy.bo.custom.UserBO;
import lk.ijse.gdse71.serenity_therapy.dto.UserDTO;
import lk.ijse.gdse71.serenity_therapy.util.encription.PasswordEncryption;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static String jobRole;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        String userID = txtUserID.getText();
        String password = txtPassword.getText();

        if (userID.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter username & password").show();
        } else {
            try {
                ArrayList<UserDTO> userDTOS = userBO.getAll();

                for (UserDTO userDTO : userDTOS) {
                    if (userDTO.getId().equals(userID)) {
                        if (PasswordEncryption.verifyPassword(password, userDTO.getRole())) {
                            jobRole = userDTO.getPassword();
                            anchLogin.getChildren().clear();
                            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));

                            anchLogin.getChildren().add(load);
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Username is incorrect!").show();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
