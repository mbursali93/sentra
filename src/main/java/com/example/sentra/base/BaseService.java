package com.example.sentra.base;

import java.util.Optional;

public interface BaseService<T,D> {

    T create(D data, String id);

    Optional<T> findOne(String id, Object tokenData);

    T findAll();

    T update(String id, D data, Object tokenData);

    T delete(String id, Object tokenData);
}