package co.edu.utadeo.employeemanager.service;

import co.edu.utadeo.employeemanager.model.Employee;
import co.edu.utadeo.employeemanager.repository.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(
      EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee addEmployee(Employee employee) {
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> findAllEmployee() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Employee findEmployeeByID(Long id) {
    return employeeRepository.findEmployeeById(id).orElse(null);
  }

  @Override
  public void deleteEmployee(Long id) {
    employeeRepository.deleteEmployeeById(id);
  }
}
