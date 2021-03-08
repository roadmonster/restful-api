package com.lihao.restfulapi.repository;

public interface ObjectRepository<T> {

    public void store(T t);

    public T retrieve(int id);

    public T search(String name);

    public T delete(int id);
}
