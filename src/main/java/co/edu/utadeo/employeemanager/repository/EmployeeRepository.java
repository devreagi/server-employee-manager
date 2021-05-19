package co.edu.utadeo.employeemanager.repository;

import co.edu.utadeo.employeemanager.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  void deleteEmployeeById(Long id);

  Optional<Employee> findEmployeeById(Long id);
}
