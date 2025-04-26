package lk.ijse.gdse71.serenity_therapy.dao.custom;

import lk.ijse.gdse71.serenity_therapy.dao.CrudDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {
    public List<String> getPrograms() throws SQLException, ClassNotFoundException;
    public Program findByName(String selectedItem) throws SQLException, ClassNotFoundException;
}
