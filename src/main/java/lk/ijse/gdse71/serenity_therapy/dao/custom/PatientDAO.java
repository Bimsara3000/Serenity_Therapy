package lk.ijse.gdse71.serenity_therapy.dao.custom;

import lk.ijse.gdse71.serenity_therapy.dao.CrudDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;

import java.sql.SQLException;

public interface PatientDAO extends CrudDAO<Patient> {
    public String getProgramId(String id) throws SQLException, ClassNotFoundException;
}
