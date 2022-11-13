package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int empId) {
		Optional<Employee> result = employeeRepository.findById(empId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id: " + empId);
		}
		return theEmployee;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteById(int empId) {
		employeeRepository.deleteById(empId);
	}

}
