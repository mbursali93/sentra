package com.example.sentra.base;

import java.util.Optional;

public interface BaseService<T,D> {

    T create(D data, String userId);

    Optional<T> findOne(String id, String userId);

    T findAll(String userId);

    T update(String id, D data, Object tokenData);

    T delete(String id, Object tokenData);
}