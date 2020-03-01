package com.myApp.spring.rest.service;

import com.myApp.spring.rest.entity.Employee;
import com.myApp.spring.rest.exception.EmployeeNotFoundException;
import com.myApp.spring.rest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = null;
        if (employeeRepository.findById(employeeId).isPresent()) {
            employee = employeeRepository.findById(employeeId).get();
        } else {
            throw new EmployeeNotFoundException("Employee id not found " + employeeId);
        }
        return employee;
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}





