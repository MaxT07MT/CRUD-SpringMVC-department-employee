package com.demo.mvc.dao;

import com.demo.mvc.models.Employee;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface EmployeeRepository {
        List<Employee> findAll();
        Employee findById(int id);
        List<Employee> findByDepId(int depId);
        int create(Employee employee);
        int update( Employee employee);
        int delete(Employee employee);
        boolean exist(int id);
    }