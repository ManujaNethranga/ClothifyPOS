package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplierDetails")
public class SupplierEntity {
    @Id
    private String id;
    private String title;
    private String email;
    private String companyName;
    private String address;
    private String contact;
    private String regDate;
    private Boolean isActive;

    @OneToMany(mappedBy = "supplierEntity")
    List<ProductEntity> list;


}