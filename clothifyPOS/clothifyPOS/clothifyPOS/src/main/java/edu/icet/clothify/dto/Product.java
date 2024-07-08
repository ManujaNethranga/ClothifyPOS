package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.swing.text.StyledEditorKit;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String size;
    private String regDate;
    private Boolean isActive;
    private Blob img;

}
