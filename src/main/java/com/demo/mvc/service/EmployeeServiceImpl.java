package com.demo.mvc.service;

import com.demo.mvc.dao.EmployeeRepository;
import com.demo.mvc.exception.EmployeeException;
import com.demo.mvc.models.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
       return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Employee> findByDepId(int depId) {
        return employeeRepository.findByDepId(depId);
    }

    @Override
    @Transactional
    public int create(Employee employee) {
        List<ConstraintViolation> violations = new Validator().validate(employee);
        if (!violations.isEmpty()) {
            throw new EmployeeException(violations);
        }
       return employeeRepository.create(employee);

    }

    @Override
    @Transactional
    public int update( Employee employee) {
        List<ConstraintViolation> violations = new Validator().validate(employee);
        if (!violations.isEmpty()){
            throw new EmployeeException(violations);
        }
        return employeeRepository.update(employee);
    }


    @Override
    @Transactional
    public boolean exist(int id) {
        return false;
    }

    @Override
    @Transactional
    public int delete(Employee employee) {
//        if (!employeeRepository.exist(id)){
//            throw new EmployeeException("cannot find employee with id " + id);
//      }
        return employeeRepository.delete(employee);
    }

}
