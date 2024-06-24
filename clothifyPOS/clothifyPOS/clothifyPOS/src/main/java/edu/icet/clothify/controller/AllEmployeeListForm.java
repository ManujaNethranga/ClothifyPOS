package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.EmployeeBo;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.dto.tableModels.EmployeeDetailsTable;
import edu.icet.clothify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllEmployeeListForm implements Initializable {
    public BorderPane AllEmployeeBorderPane;
    public TableView tblEmployeeDetails;
    public TableColumn colEmpId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colEmail;
    public TableColumn colFixedNumber;
    public TableColumn colMobileNumber;
    public TableColumn colSalary;
    public TableColumn colJobPosition;
    public TableColumn colJoinDate;
    public TableColumn colIsActive;

    EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void btnAllEmployeeExit(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/EmployeePage.fxml")).load();
        AllEmployeeBorderPane.getChildren().clear();
        AllEmployeeBorderPane.setCenter(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFixedNumber.setCellValueFactory(new PropertyValueFactory<>("fixedNumber"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colJobPosition.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        colIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        LoadEmployeeDetails();
    }

    private void LoadEmployeeDetails() {
        ObservableList<Employee> allEmployees =employeeBo.getAllEmployeeDetails();
        ObservableList<EmployeeDetailsTable> employeeDetailsTables = FXCollections.observableArrayList();
        allEmployees.forEach(element ->{
            EmployeeDetailsTable table = new EmployeeDetailsTable(
                    element.getId(),
                    element.getFirstName(),
                    element.getLastName(),
                    element.getEmail(),
                    element.getFixedNumber(),
                    element.getMobileNumber(),
                    element.getSalary(),
                    element.getPosition(),
                    element.getJoinDate(),
                    element.getIsActive()
            );
            employeeDetailsTables.add(table);
        });
        tblEmployeeDetails.setItems(employeeDetailsTables);
    }
}
