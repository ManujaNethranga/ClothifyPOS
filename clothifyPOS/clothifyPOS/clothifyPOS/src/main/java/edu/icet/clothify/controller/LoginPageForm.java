package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoginPageForm {
    public TextField txtLoginPass;
    public TextField txtLoginEmail;
    public Button btnLoginPage;
    public BorderPane LoginPageBorderPane;

    public void LoginPageRegister(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/registerPage.fxml")).load();
        LoginPageBorderPane.getChildren().clear();
        LoginPageBorderPane.setCenter(parent);
    }

    public void RegisterBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        LoginPageBorderPane.getChildren().clear();
        LoginPageBorderPane.setCenter(parent);
    }

    public void LoginForgotPass(MouseEvent mouseEvent) throws IOException {

    }
}
