package com.dynamodbcrud.DynamoDb_SpringBoot_Crud.service;

import com.dynamodbcrud.DynamoDb_SpringBoot_Crud.entity.Employee;
import com.dynamodbcrud.DynamoDb_SpringBoot_Crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null || employee.getId().isBlank()) {
            employee.setId(UUID.randomUUID().toString());
        }
        return repository.save(employee);
    }

    public Employee update(String id, Employee updatedEmployee) {
        Optional<Employee> existingOpt = repository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        Employee existing = existingOpt.get();
        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());

        return repository.save(existing); // putItem sobrescreve
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
