package model.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class Employee {
    private String employeeId;
    private String title;
    private String name;
    private Double salary;
    private String address;
    private String position;
    private String email;
    private String phoneNumber;
}
