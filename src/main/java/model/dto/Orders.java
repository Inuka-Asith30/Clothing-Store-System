package model.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter

public class Orders {
    private String orderId;
    private LocalDate orderDate;
    private String orderStatus;
}
