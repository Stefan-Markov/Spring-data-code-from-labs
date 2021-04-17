package course.springdata.mapping.dto;

import course.springdata.mapping.entity.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ManagerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private List<EmployeeDto> employees;

    private int getSubordinatesNumber() {
        return employees.size();
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(" city: ").append(city);
        sb.append(", employees= ").append(getSubordinatesNumber()).append("\n");
        sb.append(" ").append(employees.stream().map(EmployeeDto::toString)
                .collect(Collectors.joining("\n")));
        return sb.toString();
    }
}
