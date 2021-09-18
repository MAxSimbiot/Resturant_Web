package dao;

import exception.FailedDAOException;

import java.util.List;

/**
 * Interface, that describes basic methods required in DAO implementations
 * @param <K> stands for database key
 * @param <E> stands for entity
 */

public interface AbstractDAO<K, E> {

    List<E> getAll() throws FailedDAOException;

    boolean update(E entity) throws FailedDAOException;

    boolean delete(K k) throws FailedDAOException;

    boolean create(E entity) throws FailedDAOException;

}
