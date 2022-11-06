package ru.kifor4ik.repository;

import jakarta.validation.ConstraintViolationException;

public interface CRUDrepository <T>{

    public boolean createOrUpdate(T item);
    public T getById(Long id);
    public boolean delete(T item);
}
