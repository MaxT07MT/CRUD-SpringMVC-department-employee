package com.demo.mvc.dao;

import com.demo.mvc.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeRepositoryHiber implements EmployeeRepository{
    @Autowired
    SessionFactory sessionFactory;
    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        Session session = sessionFactory.openSession();
        // return session.createQuery("SELECT Employee.id, Employee.firstname, Employee.lastname, Employee.age, Employee.phone, Employee.email, Employee.position, Employee.depId, Department.name FROM Employee INNER JOIN Department ON Department.id = Employee.depId").list();
        return session.createQuery("from Employee ").list();
    }

    @Override
    public Employee findById(int id) {
        return sessionFactory.getCurrentSession()
                .get(Employee.class, id);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findByDepId(int depId) {
        Session session = sessionFactory.openSession();
        return session.createQuery(" from  Employee  where depId = :depId")
                .setParameter("depId", depId)
                .list();
    }

    @Override
    public int create(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
        return 0;
    }
    @Override
    public int delete(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
        return 0;
    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public int update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
        return 0;
    }


}
