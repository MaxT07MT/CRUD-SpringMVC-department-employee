package com.demo.mvc.service;

import com.demo.mvc.models.Department;

import java.util.List;

public interface DepartmentService {


    List<Department> getAll();
    Department findById(int id);

    void create(Department department);

    void update(Department department);

    void delete(Department department);
}