package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String id;
    private String title;
    private String email;
    private String companyName;
    private String address;
    private String contact;
    private String regDate;
    private Boolean isActive;

}
