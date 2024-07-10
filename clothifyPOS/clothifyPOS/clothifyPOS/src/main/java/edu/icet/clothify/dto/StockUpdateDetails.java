package edu.icet.clothify.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StockUpdateDetails {
    private Integer id;
    private String productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String date;
}
