package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private String id;
    private String name;
    private String description;
    private String regDate;
    private Boolean isActive;

}
