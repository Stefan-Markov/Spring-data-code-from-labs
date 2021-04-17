package course.springdata.mapping.service;

import course.springdata.mapping.entity.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee deleteEmployee(Long id);

    Long getEmployeesCount();

    List<Employee> getAllManagers();

    List<Employee> getAllManagersBornBefore(LocalDate date);
}
