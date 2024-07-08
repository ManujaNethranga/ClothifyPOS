package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductDetailTable {
    private String productId;
    private String name;
    private String description;
    private String sCategory;
    private String supplier;
    private String size;
    private Boolean isActive;
    private String date;
}
