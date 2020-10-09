package com.mindtree.EmloyeeManagement.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EmloyeeManagement.entity.Employee;
import com.mindtree.EmloyeeManagement.exception.EmployeeDoesNotExistsExcpetion;
import com.mindtree.EmloyeeManagement.exception.EmployeeManagementAppException;
import com.mindtree.EmloyeeManagement.service.EmployeeManagementService;

/**
 * @author m1056230
 *
 */
@RestController
@RequestMapping(path = "/employee-management")
public class EmployeeManagementController {

	@Autowired
	EmployeeManagementService employeeManagementService;

	@PostMapping(path = "/employees", produces = "application/json")
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "Add Employee");
		response.put("ERROR", false);
		response.put("BODY", employeeManagementService.addEmployee(employee));
		response.put("HTTP STATUS", HttpStatus.CREATED);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping(path = "/employees")
	public ResponseEntity<Map<String, Object>> getEmployees() {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("HTTP STATUS", HttpStatus.OK);
		response.put("HTTP STATUS CODE", 200);
		response.put("SUCCESS", true);
		response.put("BODY", employeeManagementService.getEemployees());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/employees/{employeeId}")
	public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable long employeeId)
			throws EmployeeManagementAppException {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "Employee Detail");
		response.put("SUCCESS", true);
		try {
			response.put("BODY", employeeManagementService.getEmployeeById(employeeId));
		} catch (EmployeeDoesNotExistsExcpetion e) {
			throw new EmployeeManagementAppException(e.getMessage());
		}
		response.put("HTTP STATUS", HttpStatus.OK);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/employees/{employeeId}")
	public ResponseEntity<Map<String, Object>> deleteEmployeeById(@PathVariable int employeeId)
			throws EmployeeManagementAppException {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("header", "Employee Detail");
		response.put("SUCCESS", true);
		try {
			response.put("BODY", employeeManagementService.deleteEmployeeById(employeeId));
		} catch (EmployeeDoesNotExistsExcpetion e) {
			throw new EmployeeManagementAppException(e.getMessage());
		}

		response.put("HTTP STATUS", HttpStatus.OK);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
