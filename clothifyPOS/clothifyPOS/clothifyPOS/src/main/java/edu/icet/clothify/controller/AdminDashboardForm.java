package edu.icet.clothify.controller;

import edu.icet.clothify.dto.User;
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

public class AdminDashboardForm implements Initializable {

    public BorderPane AdminDashboardPane;
    public Button btnEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = CurrentUserObject.getInstance().getUser();
        if(!(user.getType().equals("Admin"))){
            btnEmployee.setDisable(true);
        }
    }

    public void BtnEmployee(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/EmployeePage.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }

    public void btnOrders(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/OrderPage.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }

    public void btnSales(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/SalesPage.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/GenerateReports.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }

    public void btnQuotation(ActionEvent actionEvent) {
    }

    public void BtnInventory(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/InventoryManagement.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }

    public void btnSetting(ActionEvent actionEvent) {
    }

    public void BtnHelp(ActionEvent actionEvent) {
    }

    public void btnAdminDashboardExit(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/WelcomePage.fxml")).load();
        AdminDashboardPane.getChildren().clear();
        AdminDashboardPane.setCenter(parent);
    }


}
