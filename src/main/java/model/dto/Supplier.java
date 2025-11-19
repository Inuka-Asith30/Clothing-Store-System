package model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Supplier {
    private String supplierId;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;

}
