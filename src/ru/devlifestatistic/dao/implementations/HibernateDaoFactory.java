package ru.devlifestatistic.dao.implementations;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.devlifestatistic.dao.interfaces.DaoFactory;
import ru.devlifestatistic.dao.interfaces.EntryDao;

import java.sql.SQLException;

public class HibernateDaoFactory implements DaoFactory {
    private static String HIBERNATE_CFG = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().addResource(HIBERNATE_CFG).configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public static SessionFactory getSessionFactory() throws SQLException {
        return sessionFactory;
    }

    public static EntryDao getEntryDao() {
        return new HibernateEntryDao();
    }
}
