package ru.devlifestatistic.dao.abstraction;

import ru.devlifestatistic.dao.interfaces.GenericDao;

import java.io.Serializable;

public abstract class AbstractDao<T, PK extends Serializable> implements GenericDao<T, PK> {

}
