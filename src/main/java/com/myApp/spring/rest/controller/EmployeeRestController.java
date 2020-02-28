package com.myApp.spring.rest.controller;

import com.myApp.spring.rest.entity.Employee;
import com.myApp.spring.rest.exception.EmployeeNotFoundException;
import com.myApp.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") int employeeId) {
        Employee theEmployee = employeeService.getEmployee(employeeId);
        if (theEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item ... instead of update
        employee.setId(0);
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
        Employee tempEmployee = employeeService.getEmployee(employeeId);
        if (tempEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        employeeService.deleteEmployee(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}