package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.UserBo;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.UserEntity;
import edu.icet.clothify.util.BoType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.modelmapper.ModelMapper;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ForgotPasswordForm {
    public BorderPane ForgotBorderPane;
    public TextField txtEmail;
    public TextField txtOtp;
    public Button btnSendOtp;
    public Button btnResetPassword;
    public PasswordField txtPass1;
    public PasswordField txtPass2;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);


    public void btnExit(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml")).load();
        ForgotBorderPane.getChildren().clear();
        ForgotBorderPane.setCenter(parent);
    }

    public void initialize(){
        btnSendOtp.setOnAction( actionEvent -> {
            try {
                sendOtp();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
    int max = 999999;
    int min = 100000;

    String otpNumber =  String.valueOf((int)(Math.random()*(max-min+1)+min));

    public void sendOtp() throws MessagingException {
        String adminEmail = "clothifypos@gmail.com";
        String recipient = txtEmail.getText();
        String otpNum = otpNumber;
        String password = "fpffibmyqqxxqmpm";

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(adminEmail, password);
                    }
                });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(adminEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject("OTP Number");
            //num = String.format("%14d", otp);
            message.setText(otpNum);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }

    }

    public void OtpNumberChecker(KeyEvent keyEvent) {
        String otp = txtOtp.getText();
        if(otp.equals(otpNumber)){
            System.out.println("Otp Number is Correct");
            txtPass1.setEditable(true);
            txtPass2.setEditable(true);
        }else{
            System.out.println("Otp Number is inCorrect");
        }
    }



    public void resetPassword(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        btnResetPassword.setDisable(true);
        String password = txtPass2.getText();
        String email = txtEmail.getText();
        UserEntity entity = userBo.getIdFromEmail(email);
        String encrypedPass = passwordEncrypt(password);
        entity.setPassword(encrypedPass);
        Boolean isSaved = userBo.update(entity);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Password Reset Successfully!!").show();
            txtPass1.clear();
            txtPass2.clear();
            txtEmail.clear();
            txtOtp.clear();
        }
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

    public void ConfirmPasswordChecker(KeyEvent keyEvent) {
        String password = txtPass1.getText();
        String confirmPassword = txtPass2.getText();
        if(password.equals(confirmPassword)){
            txtPass2.setStyle("-fx-border-color: green ; -fx-border-opacity : 60%");
            btnResetPassword.setDisable(false);
        }else{
            txtPass2.setStyle("-fx-border-color: red ; -fx-border-opacity : 60%");
            btnResetPassword.setDisable(true);
        }
    }
}
