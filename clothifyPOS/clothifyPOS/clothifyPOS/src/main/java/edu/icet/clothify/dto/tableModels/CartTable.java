package edu.icet.clothify.dto.tableModels;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
public class CartTable {
    private Integer number;
    private String itemNo;
    private String name;
    private Integer qty;
    private Double discount;
    private Double unitPrice;
    private Double total;
}
