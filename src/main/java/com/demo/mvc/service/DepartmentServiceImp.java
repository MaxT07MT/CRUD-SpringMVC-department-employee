package com.demo.mvc.service;

import com.demo.mvc.dao.DepartmentRepository;
import com.demo.mvc.exception.DepartmentException;
import com.demo.mvc.models.Department;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;



    @Transactional(readOnly = true)
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }

    @Transactional
    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    @Transactional
    public void create(Department department) {
        List<ConstraintViolation> violations = new Validator().validate(department);
        if (!violations.isEmpty()) {
            throw new DepartmentException(violations);
        }
        departmentRepository.create(department);
    }

    @Transactional
    public void update(Department department) {
        List<ConstraintViolation> violations = new Validator().validate(department);
        if (!violations.isEmpty()) {
            throw new DepartmentException(violations);
        }
        departmentRepository.update(department);
    }
    @Transactional
    public void delete(Department department) {
        departmentRepository.delete(department);

    }



}
