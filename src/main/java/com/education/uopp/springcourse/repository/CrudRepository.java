package com.education.uopp.springcourse.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T create(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(T source, T target);

    void delete(ID id);
}
