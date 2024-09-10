package com.example.sentra.base;

public interface BaseService<T> {
    T create(Object data);

    T create(Object data, String id);

    // handle rest
}