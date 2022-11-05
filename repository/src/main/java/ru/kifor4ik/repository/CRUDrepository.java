package ru.kifor4ik.repository;

public interface CRUDrepository <T>{

    public void create(T item);
    public T getById(Long id);
    public void update(T item);
    public void delete(Long id);
    public void softDelete(Long id);
}
