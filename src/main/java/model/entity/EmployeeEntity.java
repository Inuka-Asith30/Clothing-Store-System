package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class EmployeeEntity {

    @Id
    private String employeeId;
    private String address;
    private String name;
    private String position;
    private String title;
}
