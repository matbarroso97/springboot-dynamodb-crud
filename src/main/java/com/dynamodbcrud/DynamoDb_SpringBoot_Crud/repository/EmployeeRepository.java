package com.dynamodbcrud.DynamoDb_SpringBoot_Crud.repository;

import com.dynamodbcrud.DynamoDb_SpringBoot_Crud.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import java.util.*;

@Repository
public class EmployeeRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private DynamoDbTable<Employee> employeeTable;

    public EmployeeRepository(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    @PostConstruct
    private void init() {
        employeeTable = enhancedClient.table("Employee", TableSchema.fromBean(Employee.class));
        try {
            employeeTable.createTable();
        } catch (Exception e) {
            System.out.println("Tabela já existe ou erro: " + e.getMessage());
        }
    }

    public Employee save(Employee employee) {
        employeeTable.putItem(employee);
        return employee;
    }

    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employeeTable.getItem(Key.builder().partitionValue(id).build()));
    }

    public List<Employee> findAll() {
        return employeeTable.scan().items().stream().toList();
    }

    public void delete(String id) {
        employeeTable.deleteItem(Key.builder().partitionValue(id).build());
    }
}
