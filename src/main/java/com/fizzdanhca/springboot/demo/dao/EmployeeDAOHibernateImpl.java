package com.fizzdanhca.springboot.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fizzdanhca.springboot.demo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{
    // định nghĩa biến cho entityManager
	private EntityManager entityManager;
	
	// tạo constructor để tiêm nào
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	
	public List<Employee> findAll() {
		// lấy hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// tạo query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		// thực thi truy vấn và đưa ra list KQ
		List<Employee> employees = theQuery.getResultList();
		// trả lại kết quả
		return employees;
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		
		// lấy hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// lấy employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		// return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// lấy hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// lưu lại hoặc cập nhật
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		// lấy current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		// xóa nó
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId",Employee.class);
	
	    theQuery.setParameter("employeeId", theId);
		
	    theQuery.executeUpdate();
	}

}
