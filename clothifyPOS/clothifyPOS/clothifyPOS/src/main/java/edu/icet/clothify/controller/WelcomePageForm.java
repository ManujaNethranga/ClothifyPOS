package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class WelcomePageForm {
    public BorderPane WelcomeBorderPane;

    public void WelcomeLoginBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml")).load();
        WelcomeBorderPane.getChildren().clear();
        WelcomeBorderPane.setCenter(parent);
    }

    public void WelcomeRegisterBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/RegisterPage.fxml")).load();
        WelcomeBorderPane.getChildren().clear();
        WelcomeBorderPane.setCenter(parent);
    }
}
