package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryParentDetailTbl {
    private String id;
    private String name;
    private String desc;
    private String regDate;
    private Boolean isActive;
}
