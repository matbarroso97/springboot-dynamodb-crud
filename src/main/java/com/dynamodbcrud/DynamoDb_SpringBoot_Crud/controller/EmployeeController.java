package com.dynamodbcrud.DynamoDb_SpringBoot_Crud.controller;

import com.dynamodbcrud.DynamoDb_SpringBoot_Crud.entity.Employee;
import com.dynamodbcrud.DynamoDb_SpringBoot_Crud.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody Employee employee) {
        Employee saved = service.save(employee);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Funcionário criado com sucesso");
        response.put("data", saved);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @RequestBody Employee employee) {
        Employee updated = service.update(id, employee);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Funcionário atualizado com sucesso");
        response.put("data", updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Lista de funcionários");
        response.put("data", service.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        return service.findById(id)
                .map(employee -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "success");
                    response.put("message", "Funcionário encontrado");
                    response.put("data", employee);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("status", "error");
                    error.put("message", "Funcionário não encontrado");
                    return ResponseEntity.status(404).body(error);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Funcionário deletado com sucesso");
        return ResponseEntity.ok(response); // HTTP 200 com JSON
    }
}
