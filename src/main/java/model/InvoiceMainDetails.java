package model;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class InvoiceMainDetails {
    private String orderId;
    private String custId;
}
