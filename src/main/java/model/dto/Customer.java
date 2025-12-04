package model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class Customer {
    private String customerId;
    private String title;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
}
