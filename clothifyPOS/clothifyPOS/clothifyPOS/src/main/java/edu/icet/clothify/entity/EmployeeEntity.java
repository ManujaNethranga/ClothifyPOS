package edu.icet.clothify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table (name = "EmployeeManagement")
public class EmployeeEntity {
    @Id
    private String id;
    private String firstName;
    private String LastName;
    private String address;
    private String email;
    private String fixedNumber;
    private String mobileNumber;
    private Double salary;
    private String position;
    private String joinDate;
}
