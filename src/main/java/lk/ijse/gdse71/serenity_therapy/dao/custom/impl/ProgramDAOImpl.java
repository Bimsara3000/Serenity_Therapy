package lk.ijse.gdse71.serenity_therapy.dao.custom.impl;

import lk.ijse.gdse71.serenity_therapy.config.FactoryConfiguration;
import lk.ijse.gdse71.serenity_therapy.dao.custom.ProgramDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;
import lk.ijse.gdse71.serenity_therapy.entity.Program;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Program> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Program dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Program dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getName(String id) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Program program = session.get(Program.class, id);

        if (program != null) {
            session.close();
            return program.getName();
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getPrograms() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        List<String> programs = session.createQuery("SELECT p.name FROM Program p", String.class)
                .getResultList();

        session.close();
        return programs;
    }

    @Override
    public Program findByName(String selectedItem) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Program program = session.createQuery(
                        "FROM Program WHERE name = :name", Program.class)
                .setParameter("name", selectedItem)
                .uniqueResult();

        session.close();
        return program;
    }
}
