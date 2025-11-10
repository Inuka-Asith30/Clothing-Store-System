package model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class Employee {
    private String employeeId;
    private String address;
    private String name;
    private String positon;
    private String title;
}
