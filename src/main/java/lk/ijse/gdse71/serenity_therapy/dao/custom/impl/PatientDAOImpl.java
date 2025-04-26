package lk.ijse.gdse71.serenity_therapy.dao.custom.impl;

import lk.ijse.gdse71.serenity_therapy.bo.exeception.NotFoundException;
import lk.ijse.gdse71.serenity_therapy.config.FactoryConfiguration;
import lk.ijse.gdse71.serenity_therapy.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;
import lk.ijse.gdse71.serenity_therapy.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Patient> getAll() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Query<Patient> query = session.createQuery("from Patient", Patient.class);
        return query.list();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();

        String lastId = session
                .createQuery("SELECT p.id FROM Patient p ORDER BY p.id", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            session.close();
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    @Override
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            List<Program> programs = patient.getPrograms();
            for (int i = 0; i < programs.size(); i++) {
                Program managedProgram = session.merge(programs.get(i));
                programs.set(i, managedProgram); // replace with the managed one
            }

            session.persist(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            List<Program> programs = patient.getPrograms();
            for (int i = 0; i < programs.size(); i++) {
                Program managedProgram = session.merge(programs.get(i));
                programs.set(i, managedProgram); // replace with the managed one
            }

            Patient patient1 = session.get(Patient.class, patient.getId());

            if (patient1 != null) {
                patient1.setPrograms(programs);
                patient1.setName(patient.getName());
                patient1.setEmail(patient.getEmail());
                patient1.setPhone(patient.getPhone());
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class, id);
            if (patient == null) {
                throw new NotFoundException("Patient not found");
            }

            Patient patient1 = session.get(Patient.class, patient.getId());
            Program program = session.get(Program.class, patient.getPrograms().get(0).getId());

            patient1.getPrograms().remove(program);
            program.getPatients().remove(patient);

            session.saveOrUpdate(patient1);
            session.saveOrUpdate(program);

            session.remove(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getName(String id) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String getProgramId(String id) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Patient patient = session.get(Patient.class, id);
        List<Program> programs = patient.getPrograms();

        if (!programs.isEmpty()) {
            session.close();
            return programs.get(0).getId();
        }
        session.close();
        return "";
    }
}
