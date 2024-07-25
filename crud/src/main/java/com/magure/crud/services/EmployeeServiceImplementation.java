package com.magure.crud.services;

import com.magure.crud.entities.EmployeeEntity;
import com.magure.crud.models.Employee;
import com.magure.crud.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        mapEmployeeToEntity(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Employee created successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapEntityToEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        mapEmployeeToEntity(employee, existingEmployee);
        employeeRepository.save(existingEmployee);
        return "Employee updated successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapEntityToEmployee(employeeEntity);
    }

    private void mapEmployeeToEntity(Employee employee, EmployeeEntity employeeEntity) {
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setName(employee.getName());
        employeeEntity.setPhoneNumber(employee.getPhoneNumber());
    }

    private Employee mapEntityToEmployee(EmployeeEntity employeeEntity) {
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getPhoneNumber(),
                employeeEntity.getEmail()
        );
    }
}
