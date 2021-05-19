package co.edu.utadeo.employeemanager.service;

import co.edu.utadeo.employeemanager.model.Employee;
import java.util.List;

public interface IEmployeeService {

  Employee addEmployee(Employee employee);

  List<Employee> findAllEmployee();

  Employee updateEmployee(Employee employee);

  Employee findEmployeeByID(Long id);

  void deleteEmployee(Long id);

}
