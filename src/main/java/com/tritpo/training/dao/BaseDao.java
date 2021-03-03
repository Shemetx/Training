package com.tritpo.training.dao;


import com.tritpo.training.specification.SqlSpecification;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    /**
     * The method designed for the process of saving a objects in database.
     *
     * @param entity an object type T that should be saved to the database
     * @return an object type T which was saved in the database
     */
    <S extends T> S add(S entity);
    /**
     * The method designed for the process of removing an object from database.
     *
     * @param entity identifier of object that should be removed from the database
     * @param specification a {@link SqlSpecification} a object
     *                         that contains last part of sql query and parameters
     */
    void delete(T entity, SqlSpecification specification);
    /**
     * The method designed for the process of updating an object in database.
     *
     * @param specification a {@link SqlSpecification} a object
     *                         that contains last part of sql query and parameters
     * @return integer of objects which was affected by update
     */
    int update(SqlSpecification specification);
    /**
     * Performs a parameterized read query to the database,
     * expecting a single result in the form of an object of type T with the specified identifier.
     *
     * @param specification a {@link SqlSpecification} a object
     *                      that contains last part of sql query and parameters
     * @return a {@link Optional} implementation with object
     */
    Optional<T> query(SqlSpecification specification);
    /**
     * Performs a parameterized read query to a database to find all object type T.
     *
     * @param sqlSpecification a {@link SqlSpecification} a object
     *                     that contains last part of sql query and parameters
     * @return a {@link List} implementation with all finding objects
     */
    List<T> queryAll(SqlSpecification sqlSpecification);
}
