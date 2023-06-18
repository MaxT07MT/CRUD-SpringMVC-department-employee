package com.demo.mvc.service;

import com.demo.mvc.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    List<Employee> findByDepId(int depId);
    int create(Employee employee);
    int update( Employee employee);
    int delete(Employee employee);
    boolean exist(int id);
}
