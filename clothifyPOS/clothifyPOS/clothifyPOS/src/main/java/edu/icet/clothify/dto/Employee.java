package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
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
    private Boolean isActive;
}
