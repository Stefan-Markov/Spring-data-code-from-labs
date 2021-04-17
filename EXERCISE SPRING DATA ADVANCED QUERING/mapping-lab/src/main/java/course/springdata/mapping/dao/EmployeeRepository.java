package course.springdata.mapping.dao;

import course.springdata.mapping.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e from Employee e where e.subordinates is not empty ")
    List<Employee> getManagers();

    List<Employee> findAllByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
