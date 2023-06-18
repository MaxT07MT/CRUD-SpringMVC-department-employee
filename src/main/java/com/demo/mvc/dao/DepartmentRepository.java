package com.demo.mvc.dao;

import com.demo.mvc.models.Department;
import org.hibernate.query.Query;

import java.util.List;

public interface DepartmentRepository {

    List<Department> getAll();

    Department findById(int id);

    void create(Department department);

    void update(Department department);

    void delete(Department department);

}