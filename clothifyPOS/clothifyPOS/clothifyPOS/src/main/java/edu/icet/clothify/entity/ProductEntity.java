package edu.icet.clothify.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEntity {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String size;
    private String regDate;
    private Boolean isActive;
    private Blob img;

    @ManyToOne
    @JoinColumn(name = "subcategoryId")
    SubCategoryEntity subCategoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    SupplierEntity supplierEntity;
}
