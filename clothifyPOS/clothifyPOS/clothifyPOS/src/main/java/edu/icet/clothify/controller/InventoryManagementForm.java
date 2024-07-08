package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class InventoryManagementForm {
    public BorderPane inventoryManagementPane;

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        inventoryManagementPane.getChildren().clear();
        inventoryManagementPane.setCenter(parent);
    }

    public void btnManageProducts(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/productPage.fxml")).load();
        inventoryManagementPane.getChildren().clear();
        inventoryManagementPane.setCenter(parent);
    }

    public void btnManageStock(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/StockManagePage.fxml")).load();
        inventoryManagementPane.getChildren().clear();
        inventoryManagementPane.setCenter(parent);
    }

    public void btnManageSubCategory(ActionEvent actionEvent) {
    }

    public void btnManageCategories(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/categoryPage.fxml")).load();
        inventoryManagementPane.getChildren().clear();
        inventoryManagementPane.setCenter(parent);
    }

    public void btnManageSuppliers(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/SupplierPage.fxml")).load();
        inventoryManagementPane.getChildren().clear();
        inventoryManagementPane.setCenter(parent);
    }
}
