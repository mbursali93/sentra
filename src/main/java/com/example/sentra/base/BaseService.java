package com.example.sentra.base;

public interface BaseService<T,D> {

    T create(D data, String id);

    T findOne(String id, Object tokenData);

    T findAll();

    T update(String id, D data, Object tokenData);

    T delete(String id, Object tokenData);
}