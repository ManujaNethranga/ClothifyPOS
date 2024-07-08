package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "productDetails")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
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

    @OneToMany(mappedBy = "productEntity")
    List<OrderDetailsEntity> list;
}
