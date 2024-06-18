package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EmployeePageForm {
    public BorderPane EmployeeFormPane;



    public void btnEmployeeExit(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        EmployeeFormPane.getChildren().clear();
        EmployeeFormPane.setCenter(parent);
    }
}
