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
import lk.ijse.gdse71.serenity_therapy.bo.custom.PatientBO;
import lk.ijse.gdse71.serenity_therapy.bo.custom.ProgramBO;
import lk.ijse.gdse71.serenity_therapy.dto.PatientDTO;
import lk.ijse.gdse71.serenity_therapy.dto.tm.PatientTM;
import lk.ijse.gdse71.serenity_therapy.entity.Program;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PatientTM, String> colProgram;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colId;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, Integer> colPhone;

    @FXML
    private Label lblId;

    @FXML
    private TableView<PatientTM> tblPatient;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        PatientTM patientTM = tblPatient.getSelectionModel().getSelectedItem();

        if (patientTM == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a patient!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            try {
                boolean isDeleted = patientBO.delete(patientTM.getId());
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "The patient is deleted!").show();
                    refresh();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the patient!").show();
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
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");
        cmbPatient.setStyle(cmbPatient.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }

        if (cmbPatient.getSelectionModel().getSelectedItem() == null) {
            cmbPatient.setStyle(cmbPatient.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidEmail && isValidPhone && cmbPatient.getSelectionModel().getSelectedItem() != null) {
            try {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setName(name);
                patientDTO.setEmail(email);
                patientDTO.setId(lblId.getText());
                patientDTO.setPhone(Integer.parseInt(phone));
                patientDTO.setDate(LocalDate.now());

                ArrayList<Program> programs = new ArrayList<>();
                Program program = programBO.findByName(cmbPatient.getSelectionModel().getSelectedItem());
                programs.add(program);

                patientDTO.setPrograms(programs);

                boolean isSaved = patientBO.save(patientDTO);

                if (isSaved) {
                    refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Patient saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save patient!").show();
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
        PatientTM patientTM = tblPatient.getSelectionModel().getSelectedItem();

        if (patientTM == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a patient!").show();
            return;
        }

        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");
        cmbPatient.setStyle(cmbPatient.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }

        if (cmbPatient.getSelectionModel().getSelectedItem() == null) {
            cmbPatient.setStyle(cmbPatient.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidEmail && isValidPhone && cmbPatient.getSelectionModel().getSelectedItem() != null) {
            try {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setName(name);
                patientDTO.setEmail(email);
                patientDTO.setId(lblId.getText());
                patientDTO.setPhone(Integer.parseInt(phone));
                patientDTO.setDate(LocalDate.now());

                ArrayList<Program> programs = new ArrayList<>();
                Program program = programBO.findByName(cmbPatient.getSelectionModel().getSelectedItem());
                programs.add(program);

                patientDTO.setPrograms(programs);

                boolean isUpdated = patientBO.update(patientDTO);

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
        PatientTM patientTM = tblPatient.getSelectionModel().getSelectedItem();

        lblId.setText(patientTM.getId());
        txtEmail.setText(patientTM.getEmail());
        txtName.setText(patientTM.getName());
        txtPhone.setText(Integer.toString(patientTM.getPhone()));
        cmbPatient.setValue(patientTM.getProgram());

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnSave.setDisable(true);
    }

    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    ProgramBO programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        loadNextPatientId();
        loadTable();
        try {
            loadPrograms();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadNextPatientId() {
        try {
            String nextId = patientBO.getNextId();
            lblId.setText(nextId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Can't connect to Database!").show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found!").show();
        }
    }

    private void loadTable() {
        try {
            ArrayList<PatientDTO> patientDTOS = patientBO.getAll();

            ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

            for (PatientDTO patientDTO : patientDTOS) {
                PatientTM patientTM = new PatientTM(
                        patientDTO.getId(),
                        patientDTO.getName(),
                        programBO.getName(patientBO.getProgramId(patientDTO.getId())),
                        patientDTO.getEmail(),
                        patientDTO.getPhone()
                );
                patientTMS.add(patientTM);
            }

            tblPatient.setItems(patientTMS);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Can't load data to the table!").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found!").show();
        }
    }

    public void refresh() {
        loadNextPatientId();
        loadTable();
        txtEmail.setText("");
        txtName.setText("");
        txtPhone.setText("");
        try {
            cmbPatient.getSelectionModel().clearSelection();
            cmbPatient.setValue(null);
            loadPrograms();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    private void loadPrograms() throws SQLException {
        try {
            ArrayList<String> jobRoles = (ArrayList<String>) programBO.getPrograms();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(jobRoles);
            cmbPatient.setItems(observableList);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found!").show();
        }
    }
}
