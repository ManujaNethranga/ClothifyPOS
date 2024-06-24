package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.EmployeeBo;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeePageForm implements Initializable {
    public BorderPane EmployeeFormPane;
    public TextField txtSalary;
    public TextField txtEmail;
    public TextField txtMobileNumber;
    public TextField txtFixedLine;
    public TextField txtAddress;
    public TextField txtLastName;
    public TextField txtFirstName;
    public TextField txtEmployeeId;
    public TextField viewJobPosition;
    public TextField viewSalary;
    public TextField viewEmail;
    public TextField viewMobileNumber;
    public TextField viewFixedLine;
    public TextField viewEmployeeId;
    public TextField viewAddress;
    public TextField viewLastName;
    public TextField viewFirstName;


    private final EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);
    public ComboBox cmbJobPosition;
    public ComboBox cmbJobPositionReg;


    public void btnEmployeeExit(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        EmployeeFormPane.getChildren().clear();
        EmployeeFormPane.setCenter(parent);
    }

    public void btnSubmitEmployeeManagement(ActionEvent actionEvent) {
        Employee employee = new Employee(
                txtEmployeeId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtFixedLine.getText(),
                txtMobileNumber.getText(),
                Double.parseDouble(txtSalary.getText()),
                cmbJobPositionReg.getValue().toString(),
                getLocalDate(),
                true
        );
        boolean b =  employeeBo.saveEmployee(employee);
        if(b){
            reset();
            new Alert(Alert.AlertType.INFORMATION,"Employee Successfully Added!!").show();

        }
    }

    private void reset(){
        txtEmployeeId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtFixedLine.clear();
        txtMobileNumber.clear();
        txtSalary.clear();
        cmbJobPositionReg.setValue("");
        generateEmployeeId();
    }

    private String getLocalDate(){
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);

    }

    public void generateEmployeeId(){
        int count = employeeBo.empCount();
        if (count==0){
            txtEmployeeId.setText("EMP001");
        }else{
            String lastEmployeeId = employeeBo.lastEmpId();
            Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
            Matcher matcher = pattern.matcher(lastEmployeeId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                txtEmployeeId.setText(String.format("EMP%03d",number));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbJobPosition.getItems().addAll("Cashier","Admin","Manager","Clerk");
        cmbJobPositionReg.getItems().addAll("Cashier","Admin","Manager","Clerk");
        generateEmployeeId();
    }

    public void btnSearch(MouseEvent mouseEvent) {
        Employee employee=employeeBo.getById(viewEmployeeId.getText());
        viewFirstName.setText(employee.getFirstName());
        viewLastName.setText(employee.getLastName());
        viewAddress.setText(employee.getAddress());
        viewEmail.setText(employee.getEmail());
        viewFixedLine.setText(employee.getFixedNumber());
        viewMobileNumber.setText(employee.getMobileNumber());
        viewSalary.setText(employee.getSalary()+"");
        cmbJobPosition.setValue(employee.getPosition());
    }

    public void btnUpdate(ActionEvent actionEvent) {
        Employee employee = employeeBo.getById(viewEmployeeId.getText());
        employee.setId(viewEmployeeId.getText());
        employee.setFirstName(viewFirstName.getText());
        employee.setLastName(viewLastName.getText());
        employee.setAddress(viewAddress.getText());
        employee.setFixedNumber(viewFixedLine.getText());
        employee.setMobileNumber(viewMobileNumber.getText());
        employee.setEmail(viewEmail.getText());
        employee.setSalary(Double.parseDouble(viewSalary.getText()));
        employee.setPosition(cmbJobPosition.getValue().toString());
        Boolean b = employeeBo.updateEmployee(employee);
        if (b){
            resetView();
            new Alert(Alert.AlertType.INFORMATION,"Employee Successfully Updated!!").show();
        }
    }
    private void resetView(){
        viewFirstName.clear();
        viewLastName.clear();
        viewAddress.clear();
        viewEmail.clear();
        viewFixedLine.clear();
        viewMobileNumber.clear();
        viewSalary.clear();
        cmbJobPosition.setValue("");
        viewEmployeeId.clear();
    }

    public void btnViewAllEmployees(ActionEvent actionEvent) throws IOException {
        Parent  parent = new FXMLLoader(getClass().getResource("/view/AllEmployeeList.fxml" )).load();
        EmployeeFormPane.getChildren().clear();
        EmployeeFormPane.setCenter(parent);
    }
}
