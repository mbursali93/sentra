package com.example.sentra.base;

import java.util.Optional;

public interface BaseService<T,D, U> {

    T create(D data, String userId);

    T findOne(String id, String userId);

    T findAll(String userId);

    T update(String id, U data, String userId);

    T delete(String id, String userId);
}