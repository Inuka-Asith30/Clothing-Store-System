package model.dto;

import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter
public class OrderDetails {
    private String orderId;
    private String productId;
    private Integer qty;
    private Integer discount;
}
