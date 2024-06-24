package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategory {
    private String id;
    private String name;
    private String regDate;
    private Boolean isActive;

}
