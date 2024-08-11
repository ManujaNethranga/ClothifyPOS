package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenerateReportsForm implements Initializable {
    public BorderPane GenerateReportsPane;
    public Button btnDailySales;
    public Button btnMonthlySales;
    public Button btnAnnualSales;
    public Button btnSalesByEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!(CurrentUserObject.getInstance().getUser().getType().equals("Admin"))){
            btnDailySales.setDisable(true);
            btnAnnualSales.setDisable(true);
            btnMonthlySales.setDisable(true);
            btnSalesByEmployee.setDisable(true);
        }
    }

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent  parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        GenerateReportsPane.getChildren().clear();
        GenerateReportsPane.setCenter(parent);
    }

    public void btnDailySales(ActionEvent actionEvent) {
    }

    public void btnMonthlySales(ActionEvent actionEvent) {
    }

    public void btnAnnualSales(ActionEvent actionEvent) {
    }

    public void btnSalesByEmployee(ActionEvent actionEvent) {
    }

    public void btnSummaryReport(ActionEvent actionEvent) {
    }

    public void btnStockReport(ActionEvent actionEvent) {
    }

    public void btnLowStock(ActionEvent actionEvent) {
    }

    public void btnDetailReport(ActionEvent actionEvent) {
    }

    public void btnPurchaseHistory(ActionEvent actionEvent) {
    }

    public void btnEmpDetailsReport(ActionEvent actionEvent) {
    }

    public void btnSalaryReport(ActionEvent actionEvent) {
    }


}
