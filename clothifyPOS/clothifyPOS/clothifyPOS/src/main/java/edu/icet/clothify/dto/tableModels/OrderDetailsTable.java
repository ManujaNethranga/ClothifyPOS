package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailsTable {
    private String orderId;
    private String itemId;
    private String name;
    private Double price;
    private Integer quantity;
    private Double discount;
    private Double total;
}
