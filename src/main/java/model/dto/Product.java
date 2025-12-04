package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Product {
    private String supplierId;
    private String productId;
    private String productName;
    private String category;
    private String size;
    private Double unitPrice;
    private Integer qtyOnHand;
}
