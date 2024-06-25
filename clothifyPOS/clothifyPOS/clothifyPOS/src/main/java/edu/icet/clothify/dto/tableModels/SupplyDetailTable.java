package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplyDetailTable {
    private String id;
    private String address;
    private String companyName;
    private String contactNo;
    private String email;
    private Boolean isActive;
    private String regDate;
    private String title;
}
