package model.dto;


import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter
public class SystemAccess {
    private String employeeId;
    private String password;

}
