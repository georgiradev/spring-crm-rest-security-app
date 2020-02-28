package com.myApp.spring.rest.service;

import com.myApp.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void deleteEmployee(int id);

}
