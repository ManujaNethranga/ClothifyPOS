package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "subcategoryDetail")
public class SubCategoryEntity {
    @Id
    private String id;
    private String name;
    private String regDate;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

    @OneToMany(mappedBy = "subCategoryEntity")
    List<ProductEntity>list;

}