package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.EmployeeBo;
import edu.icet.clothify.bo.custom.UserBo;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.util.BoType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPageForm implements Initializable {
    public TextField RegisterUsername;
    public CheckBox RegisterClickBox;
    public TextField RegisterEmail;
    public BorderPane RegisterBorderPane;
    public Button btnRegisterFxid;
    public PasswordField RegisterPass1;
    public PasswordField RegisterPass2;
    public Text warningText;

    EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    private void RegisterBtn(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        String email = RegisterEmail.getText();
        ObservableList<User> userList = userBo.getAllUserDetails();
        Boolean isExistInUser = false;
        int count = 0;
        while(count<userList.size()){
            User  user = userList.get(count);
            if(user.getEmail().equals(email)){
                isExistInUser=true;
            }
            count++;
        }
        if(isExistInUser){
           warningText.setText("Email Already Used !!");
        }else{
            Boolean isEmailValid = emailCheckerFunc();

            if(isEmailValid){
                warningText.setText("");
                String userName = RegisterUsername.getText();
                String password = passwordEncrypt(RegisterPass2.getText());
                String userId = generateUserId();
                String type= "";
                ObservableList<Employee> allEmployeeDetails = employeeBo.getAllEmployeeDetails();
                int count2 = 0;
                while(count2<allEmployeeDetails.size()){
                    Employee  employee = allEmployeeDetails.get(count2);
                    if(employee.getEmail().equals(email)){
                        type = employee.getPosition();
                    }
                    count2++;
                }
                User user = new User(
                        userId,
                        userName,
                        email,
                        password,
                        type,
                        getLocalDate(),
                        true
                );
                boolean selected = RegisterClickBox.isSelected();
                if(selected){
                    Boolean isSaved = userBo.save(user);
                     if(isSaved){
                        reset();
                        new Alert(Alert.AlertType.INFORMATION,"User Account Successfully Created!! Please Login..").show();
                    }
                }else{
                    RegisterClickBox.setStyle("-fx-border-color: red ; -fx-border-opacity : 60%");
                }

            }else{
                warningText.setText("Enter Correct Email Please...");
            }
        }



    }

    private void reset(){
        RegisterUsername.clear();
        RegisterEmail.clear();
        RegisterPass2.clear();
        RegisterPass1.clear();
        RegisterClickBox.setStyle("-fx-border-color: none ; -fx-border-opacity : 60%");
    }

    @FXML
    private void RegisterPageLogin(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/loginPage.fxml")).load();
        RegisterBorderPane.getChildren().clear();
        RegisterBorderPane.setCenter(parent);
    }


    @FXML
    private void clickBox(ActionEvent actionEvent) {
        boolean selected = RegisterClickBox.isSelected();
        if(selected){
            RegisterClickBox.setStyle("-fx-border-color: none ; -fx-border-opacity : 40%");
        }else{
            RegisterClickBox.setStyle("-fx-border-color: red ; -fx-border-opacity : 40%");
        }
    }

    public String generateUserId(){
        int count = userBo.userCount();
        if (count==0){
            return "U001";
        }else{
            String lastEmployeeId = userBo.userLastId();
            Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
            Matcher matcher = pattern.matcher(lastEmployeeId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                return String.format("U%03d",number);
            }
        }
        return null;
    }


    private String getLocalDate(){
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);

    }

    public void PasswordChecker(KeyEvent keyEvent) {
        String password1 = RegisterPass1.getText();
        String password2 = RegisterPass2.getText();
        if(!password2.equals(password1)){
            RegisterPass2.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
        }else if(password2.equals(password1)){
            RegisterPass2.setStyle("-fx-border-color: green ;-fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 60%");
        }
    }

    private Boolean emailCheckerFunc(){
        String email = RegisterEmail.getText();
        Boolean isExits = false;
        ObservableList<Employee> allEmployeeDetails = employeeBo.getAllEmployeeDetails();
        int count = 0;
        while(count<allEmployeeDetails.size()){
            Employee  employee = allEmployeeDetails.get(count);
            if(employee.getEmail().equals(email)){
                isExits=true;
            }
            count++;
        }
        if(isExits){
            RegisterEmail.setStyle("-fx-border-color: green ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 60%");
            return true;
        }else{
            RegisterEmail.setStyle("-fx-border-color: red ; -fx-background-radius : 25px ; -fx-opacity : 50% ; -fx-font-size :18 ; -fx-border-radius : 25px ; -fx-border-width : 2px ; -fx-border-opacity : 40%");
            return false;


        }
    }

    public void emailValidator(MouseEvent mouseEvent) {

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
