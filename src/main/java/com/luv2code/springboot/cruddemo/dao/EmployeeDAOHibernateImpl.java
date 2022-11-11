package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

//	define fields for EntityManager
	private EntityManager entityManager;

//	set-up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {

//		get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

//		create the query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

//		execute the query and get the results
		List<Employee> employees = query.getResultList();

//		return the results
		return employees;
	}

	@Override
	public Employee findById(int empId) {

//		get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

//		get the employee
		Employee employee = currentSession.get(Employee.class, empId);

//		return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {

//		get current session
		Session currentSession = entityManager.unwrap(Session.class);

//		save the employee
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int empId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Employee> query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", empId);
		query.executeUpdate();
	}

}
