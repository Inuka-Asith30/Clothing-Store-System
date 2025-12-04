package model.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
public class AddToCardEntity {
    private String productId;
    private String productName;
    private Double unitPrice;
    private Integer qty;
    private Integer discount;
    private Double total;
}
