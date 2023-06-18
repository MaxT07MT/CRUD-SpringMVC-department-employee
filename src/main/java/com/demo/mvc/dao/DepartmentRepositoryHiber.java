package com.demo.mvc.dao;

import com.demo.mvc.models.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryHiber implements DepartmentRepository {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    HibernateTransactionManager transactionManager;

    public DepartmentRepositoryHiber(SessionFactory sessionFactory, HibernateTransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.transactionManager = transactionManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Department ").list();
    }
    @Override
    public Department findById(int id) {
        return sessionFactory.getCurrentSession()
                .get(Department.class, id);
    }

    @Override
    public void create(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.save(department);

    }
    @Override
    public void update(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.update(department);

    }
    @Override
    public void delete(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(department);

    }
}