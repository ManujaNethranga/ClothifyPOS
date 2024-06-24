package edu.icet.clothify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "CategoryDetails")
public class CategoryEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String regDate;
    private Boolean isActive;

    @OneToMany(mappedBy = "category")
    List<SubCategoryEntity> list;

}
