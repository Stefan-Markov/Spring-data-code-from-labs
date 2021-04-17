package course.springdata.mapping.service;

import course.springdata.mapping.dao.EmployeeRepository;
import course.springdata.mapping.entity.Employee;
import course.springdata.mapping.exception.NonExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Employee with Id %s does not exist!", id)));
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if (employee.getManager() != null) {
            employee.getManager().getSubordinates().add(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee existing = getEmployeeById(employee.getId());
        Employee updated = employeeRepository.save(employee);
        if (existing.getManager() != null && !(existing.getManager().equals(employee.getManager()))) {
            existing.getManager().getSubordinates().remove(existing);
        }
        if (updated.getManager() != null && !updated.getManager().equals(existing.getManager())) {
            updated.getManager().getSubordinates().add(updated);
        }
        return updated;
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);
        if (employee.getManager() != null) {
            employee.getManager().getSubordinates().remove(employee);
        }
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Long getEmployeesCount() {
        return employeeRepository.count();
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepository.getManagers();
    }

    @Override
    public List<Employee> getAllManagersBornBefore(LocalDate date) {

        return employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(date);
    }
}
