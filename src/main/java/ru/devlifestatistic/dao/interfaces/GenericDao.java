package ru.devlifestatistic.dao.interfaces;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    public T create() throws SQLException;
    public T persist(T object);
    public T getByPK(int key);
    public void update(T object) throws SQLException;
    public List<T> getAll() throws SQLException;

}
