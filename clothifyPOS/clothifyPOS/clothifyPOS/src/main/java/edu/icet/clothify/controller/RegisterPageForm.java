package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RegisterPageForm {
    public TextField RegisterUsername;
    public CheckBox RegisterClickBox;
    public TextField RegisterEmail;
    public TextField RegisterFirstPass;
    public TextField RegisterSecondPass;
    public BorderPane RegisterBorderPane;

    public void RegisterBtn(ActionEvent actionEvent) {
    }

    public void RegisterPageLogin(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/loginPage.fxml")).load();
        RegisterBorderPane.getChildren().clear();
        RegisterBorderPane.setCenter(parent);
    }
}
