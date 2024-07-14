package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.UserBo;
import edu.icet.clothify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginPageForm implements Initializable {
    public TextField txtLoginPass;
    public TextField txtLoginEmail;
    public Button btnLoginPage;
    public BorderPane LoginPageBorderPane;
    public Text DatabaseNull;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void LoginPageRegister(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/RegisterPage.fxml")).load();
        LoginPageBorderPane.getChildren().clear();
        LoginPageBorderPane.setCenter(parent);
    }

    public void LoginForgotPass(MouseEvent mouseEvent) throws IOException {

    }

    public void LoginBtn(ActionEvent actionEvent) throws IOException, NoSuchAlgorithmException {
//        ObservableList<User> allUserDetails = userBo.getAllUserDetails();
//        if(allUserDetails!=null){
//            String email = txtLoginEmail.getText();
//            String pass = txtLoginPass.getText();
//            String passEn = passwordEncrypt(pass);
//            Boolean isCorrect = false;
//            Boolean isActive = false;
//            int count = 0;
//            while(count<allUserDetails.size()) {
//                User user1 = allUserDetails.get(count);
//                if (user1.getPassword().equals(passEn) && user1.getEmail().equals(email)) {
//                    isActive = user1.getIsActive();
//                    isCorrect = true;
//                }
//                count++;
//            }
//            if(isActive){
//
//                if(isCorrect){
//                    new Alert(Alert.AlertType.INFORMATION,"Welcome Back !!").show();
                    Parent parent = new FXMLLoader (getClass().getResource("/view/AdminDashboardPage.fxml")).load();
                    LoginPageBorderPane.getChildren().clear();
                    LoginPageBorderPane.setCenter(parent);
//                }else {
//                    txtLoginPass.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
//                    txtLoginEmail.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
//                }
//            }else{
//                new Alert(Alert.AlertType.INFORMATION,"Your Account is Deactivated Please Contact your Admin..");
//            }
//        }else{
//            txtLoginPass.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
//            txtLoginEmail.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
//            DatabaseNull.setText("First,Create New Account");
//        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private String passwordEncrypt(String password) throws NoSuchAlgorithmException {

        /* Plain-text password initialization. */
        String encryptedpassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        /* Display the unencrypted and encrypted passwords. */
        return encryptedpassword;

    }
}
