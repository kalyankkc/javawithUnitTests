package com.mindtree.EmloyeeManagement.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EmloyeeManagement.entity.Employee;
import com.mindtree.EmloyeeManagement.exception.EmployeeDoesNotExistsExcpetion;
import com.mindtree.EmloyeeManagement.repository.EmployeeRepository;
import com.mindtree.EmloyeeManagement.service.EmployeeManagementService;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "Employee Details Saved Successfully";
	}

	@Override
	public List<Employee> getEemployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) throws EmployeeDoesNotExistsExcpetion {
		Employee employee = new Employee();
		if (employeeRepository.existsById(employeeId))
			employee = employeeRepository.getOne(employeeId);
		else
			throw new EmployeeDoesNotExistsExcpetion("Employee does not exists with this ID");
		return employee;
	}

	@Override
	public String deleteEmployeeById(long employeeId) throws EmployeeDoesNotExistsExcpetion {

		if (employeeRepository.existsById(employeeId))
			employeeRepository.deleteById(employeeId);
		else
			throw new EmployeeDoesNotExistsExcpetion("Employee does exists with this ID");

		return "Deleted Successfully";
	}

}
