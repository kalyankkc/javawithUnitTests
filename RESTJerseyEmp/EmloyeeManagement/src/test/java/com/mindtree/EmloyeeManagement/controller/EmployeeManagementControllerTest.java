package com.mindtree.EmloyeeManagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.EmloyeeManagement.entity.Employee;
import com.mindtree.EmloyeeManagement.exception.EmployeeManagementAppException;
import com.mindtree.EmloyeeManagement.service.EmployeeManagementService;

@WebMvcTest(value = EmployeeManagementController.class)
@WebAppConfiguration
public class EmployeeManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeManagementService employeeManagementService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@InjectMocks
    private EmployeeManagementController employeeManagementController;
	


	@Test
	void testAddEmployee() throws Exception {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeManagementService.addEmployee(employee)).thenReturn("Employee details saved successfully");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee-management/employees")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee));

		mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();
	}

	@Test
	void testGetEmployees() throws Exception {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");
		Employee employee1 = new Employee(2, "parthu", 23, "tth", "male");

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		employees.add(employee1);

		when(employeeManagementService.getEemployees()).thenReturn(employees);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee-management/employees")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employees));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testgetEmployeeById() throws Exception {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeManagementService.getEmployeeById(1)).thenReturn(employee);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee-management/employees/1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testDeleteEmployeeById() throws Exception {
		Employee employee = new Employee(1, "venkat", 23, "rcm", "male");

		when(employeeManagementService.deleteEmployeeById(1)).thenReturn("Employee Details Deleted Successfully");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee-management/employees/1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	/*
	 * @Test() void testgetEmployeeByIdException() throws
	 * EmployeeManagementAppException, Exception {
	 * 
	 * 
	 * 
	 * when(employeeManagementService.getEmployeeById(4)).thenReturn(null);
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.get("/employee-management/employees/4")
	 * .contentType(MediaType.APPLICATION_JSON);
	 * 
	 * mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
	 * 
	 * when(employeeManagementService.getEmployeeById(2)).thenThrow(new
	 * EmployeeManagementAppException());
	 * when(employeeManagementController.getEmployeeById(4)).thenThrow(new
	 * EmployeeManagementAppException()); }
	 * 
	 * 
	 * Assertions.assertThrows(EmployeeDoesNotExistsExcpetion.class, () -> {
	 * MvcResult result = mockMvc.perform(
	 * MockMvcRequestBuilders.get("/employee-management/employees/4").accept(
	 * MediaType.APPLICATION_JSON)) .andReturn(); });
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Test void testDeleteEmployeeByIdException() throws Exception {
	 * 
	 * Employee employee = new Employee(1, "venkat", 23, "rcm", "male");
	 * 
	 * 
	 * when(employeeManagementService.deleteEmployeeById(1)).
	 * thenReturn("Employee Details Deleted Successfully");
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.delete("/employee-management/employees/90")
	 * .contentType(MediaType.APPLICATION_JSON);
	 * 
	 * mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
	 * }
	 */

}
