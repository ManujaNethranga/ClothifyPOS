package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Employee;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {
    boolean saveEmployee(Employee employee);

    int empCount();

    String lastEmpId();

    Employee getById(String id);

    Boolean updateEmployee(Employee employee);

    ObservableList<Employee> getAllEmployeeDetails();

    Boolean isEmailExits(String email);
}
