package co.edu.utadeo.employeemanager.controller;

import co.edu.utadeo.employeemanager.exception.ImageUrlOutBoundsException;
import co.edu.utadeo.employeemanager.model.Employee;
import co.edu.utadeo.employeemanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://localhost", "http://localhost:8080"})
@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee = employeeService.findEmployeeByID(id);
        Map<String, String> response = new HashMap<>();
        if (employee == null) {
            response.put("message", "User by ID " + id + " was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee,
                                         BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Employee newEmployee;
        if (result.hasErrors()) {
            List<String> error = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                error.add("Field  " + err.getField() + " " + err.getDefaultMessage());
            }
            response.put("errors", error);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newEmployee = employeeService.addEmployee(employee);
        } catch (Exception e) {
            response.put("errors", "Error al insertar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        if (employee.getImageUrl().length() > 200) {
            throw new ImageUrlOutBoundsException("Image URL it's too long");
        }
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
