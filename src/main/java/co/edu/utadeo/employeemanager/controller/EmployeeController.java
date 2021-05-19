package co.edu.utadeo.employeemanager.controller;

import co.edu.utadeo.employeemanager.model.Employee;
import co.edu.utadeo.employeemanager.service.EmployeeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(path = "/all")
  public ResponseEntity<List<Employee>> getAllemployees() {
    List<Employee> employees = employeeService.findAllEmployee();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping(path = "/find/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
    Employee employee = employeeService.findEmployeeByID(id);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @PostMapping(path = "/add")
  public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    Employee employee1 = employeeService.addEmployee(employee);
    return new ResponseEntity<>(employee1, HttpStatus.CREATED);
  }

  @PutMapping(path = "/update")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    Employee updateemployee = employeeService.updateEmployee(employee);
    return new ResponseEntity<>(updateemployee, HttpStatus.OK);
  }

  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
