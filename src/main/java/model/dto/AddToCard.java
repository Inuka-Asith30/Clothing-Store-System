package model.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
public class AddToCard {
    private String productId;
    private String productName;
    private String Category;
    private Double unitPrice;
    private Integer qty;
    private Integer discount;
}
