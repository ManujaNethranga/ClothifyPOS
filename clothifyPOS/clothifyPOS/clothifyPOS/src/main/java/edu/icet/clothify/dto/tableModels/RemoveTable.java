package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RemoveTable {
    private Integer number;
    private String itemNo;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;

}
