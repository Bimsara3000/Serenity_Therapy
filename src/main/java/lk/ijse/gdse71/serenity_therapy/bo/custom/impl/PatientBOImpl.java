package lk.ijse.gdse71.serenity_therapy.bo.custom.impl;

import lk.ijse.gdse71.serenity_therapy.bo.custom.PatientBO;
import lk.ijse.gdse71.serenity_therapy.dao.DAOFactory;
import lk.ijse.gdse71.serenity_therapy.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenity_therapy.dto.PatientDTO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public ArrayList<PatientDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> patients = (ArrayList<Patient>) patientDAO.getAll();

        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(patient.getId(),patient.getName(),patient.getEmail(),patient.getPhone(),patient.getDate()));
        }
        return patientDTOS;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return patientDAO.getNextId();
    }

    @Override
    public String getProgramId(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.getProgramId(id);
    }

    @Override
    public boolean save(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setDate(patientDTO.getDate());
        patient.setId(patientDTO.getId());
        patient.setPrograms(patientDTO.getPrograms());
        return patientDAO.save(patient);
    }

    @Override
    public boolean update(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setDate(patientDTO.getDate());
        patient.setId(patientDTO.getId());
        patient.setPrograms(patientDTO.getPrograms());
        return patientDAO.update(patient);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.delete(id);
    }
}
