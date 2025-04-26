package lk.ijse.gdse71.serenity_therapy.bo.custom;

import lk.ijse.gdse71.serenity_therapy.bo.SuperBO;
import lk.ijse.gdse71.serenity_therapy.entity.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBO extends SuperBO {
    public String getName(String programId) throws SQLException, ClassNotFoundException;
    public List<String> getPrograms() throws SQLException, ClassNotFoundException;
    public Program findByName(String selectedItem) throws SQLException, ClassNotFoundException;
}
