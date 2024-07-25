package com.magure.crud.services;

import com.magure.crud.models.Employee;
import java.util.List;
import java.util.ArrayList;


public interface EmployeeService {
    String createEmployee(Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
    Employee readEmployee(Long id);
    
}
