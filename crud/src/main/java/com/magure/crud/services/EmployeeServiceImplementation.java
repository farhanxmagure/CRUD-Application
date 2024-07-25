package com.magure.crud.services;

import com.magure.crud.entities.EmployeeEntity;
import com.magure.crud.models.Employee;
import com.magure.crud.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setName(employee.getName());
        employeeEntity.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.save(employeeEntity);
        return "Employee created successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhoneNumber(employeeEntity.getPhoneNumber());
            emp.setName(employeeEntity.getName());

            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(existingEmployee);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.save(existingEmployee);
        return "Update successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setEmail(employeeEntity.getEmail());
        employee.setPhoneNumber(employeeEntity.getPhoneNumber());
        employee.setName(employeeEntity.getName());
        return employee;
    }
}