package edu.icet.clothify.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetails {
    private Integer id;
    private String orderIdVal;
    private String itemNo;
    private Integer quantity;

}
