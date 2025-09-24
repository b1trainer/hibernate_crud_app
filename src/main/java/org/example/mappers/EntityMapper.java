package org.example.mappers;

public interface EntityMapper<T, U> {
    T createDto(U entity);

    U createEntity(T entity);
}
