package lk.ijse.gdse71.serenity_therapy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    private Button btnPatient;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnSession;

    @FXML
    private Button btnTherapist;

    @FXML
    private Button btnUser;

    @FXML
    private AnchorPane content;

    @FXML
    void navigateToPatientPage(ActionEvent event) {
        navigateTo("/view/PatientView.fxml");
    }

    @FXML
    void navigateToPaymentPage(ActionEvent event) {

    }

    @FXML
    void navigateToProgramPage(ActionEvent event) {

    }

    @FXML
    void navigateToSessionPage(ActionEvent event) {

    }

    @FXML
    void navigateToTherapistPage(ActionEvent event) {

    }

    @FXML
    void navigateToUserPage(ActionEvent event) {
        navigateTo("/view/UserView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/PatientView.fxml");

        switch (LoginController.jobRole) {
            case "Receptionist": btnUser.setDisable(true);
        }
    }

    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
