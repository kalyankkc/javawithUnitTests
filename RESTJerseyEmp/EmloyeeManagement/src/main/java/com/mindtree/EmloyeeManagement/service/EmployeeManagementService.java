package com.mindtree.EmloyeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.EmloyeeManagement.entity.Employee;
import com.mindtree.EmloyeeManagement.exception.EmployeeDoesNotExistsExcpetion;

@Service
public interface EmployeeManagementService {
	
	/**
	 * @param employee
	 * @return Success Message
	 */
	String addEmployee(Employee employee);

	/**
	 * @return List of Employees
	 */
	List<Employee> getEemployees();

	/**
	 * @param employeeId
	 * @return Details Of A Employee
	 * @throws EmployeeDoesNotExistsExcpetion
	 */
	Employee getEmployeeById(long employeeId) throws EmployeeDoesNotExistsExcpetion;

	/**
	 * @param employeeId
	 * @return Success Message 
	 * @throws EmployeeDoesNotExistsExcpetion
	 */
	String deleteEmployeeById(long employeeId) throws EmployeeDoesNotExistsExcpetion;

}
