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

    public void btnManageProducts(ActionEvent actionEvent) {
    }

    public void btnManageStock(ActionEvent actionEvent) {
    }

    public void btnManageSubCategory(ActionEvent actionEvent) {
    }

    public void btnManageCategories(ActionEvent actionEvent) {
    }

    public void btnManageSuppliers(ActionEvent actionEvent) {
    }
}
