package course.springdata.mapping.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private LocalDate birthday;
    private String city;
    private String managerLastName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(" salary: ").append(salary);
        sb.append(" birthday: ").append(birthday);
        sb.append(" Manager name: ").append(managerLastName);
        sb.append(" ,city: ").append(city);
        return sb.toString();
    }
}
