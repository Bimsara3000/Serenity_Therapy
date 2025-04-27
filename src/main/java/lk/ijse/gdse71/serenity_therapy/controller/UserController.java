package lk.ijse.gdse71.serenity_therapy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.serenity_therapy.bo.BOFactory;
import lk.ijse.gdse71.serenity_therapy.bo.custom.UserBO;
import lk.ijse.gdse71.serenity_therapy.dto.UserDTO;
import lk.ijse.gdse71.serenity_therapy.dto.tm.UserTM;
import lk.ijse.gdse71.serenity_therapy.util.encription.PasswordEncryption;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<UserTM, String> colId;

    @FXML
    private TableColumn<UserTM, String> colName;

    @FXML
    private TableColumn<UserTM, String> colPassword;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private Label lblId;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        UserTM userTM = tblUser.getSelectionModel().getSelectedItem();

        if (userTM == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a user!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            try {
                boolean isDeleted = userBO.delete(userTM.getId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "The user is deleted!").show();
                    refresh();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the user!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "DB Error!").show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Class not found!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        refresh();
    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) {
        String name = txtName.getText();
        String password = PasswordEncryption.encryptPassword(txtPassword.getText());
        String role = txtRole.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: #7367F0;");
        txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidRole = role.matches(namePattern);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidRole) {
            txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: red;");
        }

        if (password.isEmpty()) {
            txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidRole && !password.isEmpty()) {
            try {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(name);
                userDTO.setPassword(password);
                userDTO.setRole(role);
                userDTO.setId(lblId.getText());

                boolean isSaved = userBO.save(userDTO);

                if (isSaved) {
                    refresh();
                    new Alert(Alert.AlertType.INFORMATION, "User saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database error!").show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Class not found!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        UserTM userTM = tblUser.getSelectionModel().getSelectedItem();

        if (userTM == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a user!").show();
            return;
        }

        String name = txtName.getText();
        String password = PasswordEncryption.encryptPassword(txtPassword.getText());
        String role = txtRole.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidRole = password.matches(namePattern);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidRole) {
            txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidRole) {
            try {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(name);
                userDTO.setPassword(password);
                userDTO.setRole(role);
                userDTO.setId(lblId.getText());

                boolean isUpdated = userBO.update(userDTO);

                if (isUpdated) {
                    refresh();
                    new Alert(Alert.AlertType.INFORMATION, "User updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database error!").show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Class not found!").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        UserTM userTM = tblUser.getSelectionModel().getSelectedItem();

        lblId.setText(userTM.getId());
        txtPassword.setText(userTM.getPassword());
        txtName.setText(userTM.getName());
        txtRole.setText(userTM.getRole());

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnSave.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        loadNextUserId();
        loadTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadTable() {
        try {
            ArrayList<UserDTO> userDTOS = userBO.getAll();

            ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

            for (UserDTO userDTO : userDTOS) {
                UserTM userTM = new UserTM(
                        userDTO.getId(),
                        userDTO.getName(),
                        userDTO.getPassword(),
                        userDTO.getRole()
                );
                userTMS.add(userTM);
            }

            tblUser.setItems(userTMS);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Can't load data to the table!").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found!").show();
        }
    }

    private void loadNextUserId() {
        try {
            String nextId = userBO.getNextId();
            lblId.setText(nextId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Can't connect to Database!").show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found!").show();
        }
    }

    public void refresh() {
        loadNextUserId();
        loadTable();
        txtRole.setText("");
        txtName.setText("");
        txtPassword.setText("");

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }
}
