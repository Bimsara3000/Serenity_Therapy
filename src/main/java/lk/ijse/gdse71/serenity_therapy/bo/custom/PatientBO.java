package lk.ijse.gdse71.serenity_therapy.bo.custom;

import lk.ijse.gdse71.serenity_therapy.bo.SuperBO;
import lk.ijse.gdse71.serenity_therapy.dto.PatientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientBO extends SuperBO {
    public ArrayList<PatientDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;
    public String getProgramId(String id) throws SQLException, ClassNotFoundException;
    public boolean save(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    public boolean update(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
}
