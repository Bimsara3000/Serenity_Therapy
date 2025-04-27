package lk.ijse.gdse71.serenity_therapy.dao.custom.impl;

import lk.ijse.gdse71.serenity_therapy.bo.exeception.NotFoundException;
import lk.ijse.gdse71.serenity_therapy.config.FactoryConfiguration;
import lk.ijse.gdse71.serenity_therapy.dao.custom.UserDAO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;
import lk.ijse.gdse71.serenity_therapy.entity.Program;
import lk.ijse.gdse71.serenity_therapy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Query<User> query = session.createQuery("from User", User.class);
        return query.list();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();

        String lastId = session
                .createQuery("SELECT u.id FROM User u ORDER BY u.id DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            session.close();
            return String.format("U%03d", newIdIndex);
        }
        return "U001";
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            session.persist(user);
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
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            User user1 = session.get(User.class, user.getId());

            if (user1 != null) {
                user1.setRole(user.getRole());
                user1.setName(user.getName());
                user1.setPassword(user.getPassword());
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
            User user = session.get(User.class, id);
            if (user == null) {
                throw new NotFoundException("User not found");
            }

            session.remove(user);
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
}
