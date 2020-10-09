package com.mindtree.EmloyeeManagement.service.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mindtree.EmloyeeManagement.entity.Employee;
import com.mindtree.EmloyeeManagement.exception.EmployeeDoesNotExistsExcpetion;
import com.mindtree.EmloyeeManagement.exception.EmployeeManagementAppException;
import com.mindtree.EmloyeeManagement.repository.EmployeeRepository;

public class EmployeeManagementServiceImplTest {

	@InjectMocks
	EmployeeManagementServiceImpl employeeManagementServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddEmployee() {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		String message = employeeManagementServiceImpl.addEmployee(employee);
		assertEquals("Employee Details Saved Successfully", message);
	}

	@Test
	void testGetEemployees() {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");
		Employee employee1 = new Employee(2, "parthu", 23, "tth", "male");

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		employees.add(employee1);

		when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> employeesList = employeeManagementServiceImpl.getEemployees();
		assertEquals(employee.getEmployeeName(), employeesList.get(0).getEmployeeName());
	}

	@Test
	void testGetEmployeeById() throws EmployeeManagementAppException {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeRepository.getOne(anyLong())).thenReturn(employee);
		when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
		Employee emp1 = employeeManagementServiceImpl.getEmployeeById(1);

		verify(employeeRepository, times(1)).getOne(employee.getEmployeeId());
		Employee emp = employeeManagementServiceImpl.getEmployeeById(employee.getEmployeeId());
		assertEquals(employee.getEmployeeName(), emp.getEmployeeName());
	}

	@Test
	void testDeleteEmployeeById() throws EmployeeManagementAppException {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
		employeeManagementServiceImpl.deleteEmployeeById(1);
		verify(employeeRepository, times(1)).deleteById(employee.getEmployeeId());
		String message = employeeManagementServiceImpl.deleteEmployeeById(1);
		assertEquals("Deleted Successfully", message);
	}

	@Test
	void testGetEmployeeByIdException() {
		
		when(employeeRepository.getOne(anyLong())).thenReturn(null);
		Assertions.assertThrows(EmployeeManagementAppException.class,
				() -> employeeManagementServiceImpl.getEmployeeById(4));

		
	}

	@Test
	void testDeleteEmployeeByIdException() throws EmployeeDoesNotExistsExcpetion {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(false);
		Assertions.assertThrows(EmployeeManagementAppException.class,
				() -> employeeManagementServiceImpl.deleteEmployeeById(1));

	}

}
