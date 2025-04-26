package lk.ijse.gdse71.serenity_therapy.bo.custom.impl;

import lk.ijse.gdse71.serenity_therapy.bo.custom.ProgramBO;
import lk.ijse.gdse71.serenity_therapy.dao.DAOFactory;
import lk.ijse.gdse71.serenity_therapy.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenity_therapy.dao.custom.ProgramDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Program;

import java.sql.SQLException;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);

    @Override
    public String getName(String programId) throws SQLException, ClassNotFoundException {
        return programDAO.getName(programId);
    }

    @Override
    public List<String> getPrograms() throws SQLException, ClassNotFoundException {
        return programDAO.getPrograms();
    }

    @Override
    public Program findByName(String selectedItem) throws SQLException, ClassNotFoundException {
        return programDAO.findByName(selectedItem);
    }
}
