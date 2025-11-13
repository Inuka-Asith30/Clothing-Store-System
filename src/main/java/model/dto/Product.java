package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Product {
    private String SupplierId;
    private String ProductId;
    private String ProductName;
    private String Category;
    private String PackSize;
    private Double unitPrice;
    private Integer qtyOnHand;
}
