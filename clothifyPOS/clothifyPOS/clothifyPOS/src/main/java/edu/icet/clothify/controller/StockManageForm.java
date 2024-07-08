package edu.icet.clothify.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StockManageForm {
    public BorderPane StockPane;

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        StockPane.getChildren().clear();
        StockPane.setCenter(parent);
    }
}
