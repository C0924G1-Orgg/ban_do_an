package com.example.case_study_module_3.controller.service;

import java.util.List;

public interface IService<T> {
    List<T> getAll();
    void save(T t);
    boolean deleteById(int id);
    boolean update(T t);

}

