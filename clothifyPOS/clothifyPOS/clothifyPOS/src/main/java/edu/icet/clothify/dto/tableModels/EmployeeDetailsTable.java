package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetailsTable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String fixedNumber;
    private String mobileNumber;
    private double salary;
    private String jobPosition;
    private String joinDate;
}
