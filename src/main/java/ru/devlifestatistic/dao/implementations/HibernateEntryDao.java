package ru.devlifestatistic.dao.implementations;

import org.hibernate.Session;
import ru.devlifestatistic.dao.abstraction.AbstractDao;
import ru.devlifestatistic.dao.interfaces.EntryDao;
import ru.devlifestatistic.model.Entry;

import java.sql.SQLException;
import java.util.List;

public class HibernateEntryDao extends AbstractDao<Entry, Integer> implements EntryDao {

    @Override
    public Entry create() throws SQLException {
        return null;
    }

    @Override
    public Entry persist(Entry object) {
        Session session = null;
        try {
            session = HibernateDaoFactory.getSessionFactory().openSession();
            System.out.println("sessionFactory: "+HibernateDaoFactory.getSessionFactory());
            System.out.println("session: "+session);
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        return null;
    }

    @Override
    public Entry getByPK(int key) {
        Entry entry = null;
        Session session = null;
        try {
            session = HibernateDaoFactory.getSessionFactory().openSession();
            entry = (Entry) session.get(Entry.class, key);
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        return entry;
    }

    @Override
    public void update(Entry object) throws SQLException {

    }

    @Override
    public List<Entry> getAll() throws SQLException {
        return null;
    }
}
