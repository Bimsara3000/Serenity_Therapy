package lk.ijse.gdse71.serenity_therapy.config;

import lk.ijse.gdse71.serenity_therapy.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Program.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(lk.ijse.gdse71.serenity_therapy.entity.Session.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null)
                ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
