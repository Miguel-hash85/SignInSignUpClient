/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author 2dam
 */
public class SignUpController {

    @FXML
    private Button btnSignUp;
    //Button that is associated to the button 'btnSignUp' of the SignUpWindow.fxml
    @FXML
    private Hyperlink hypLogIn;
    //Hyperlink that is associated to the hyperlink 'hypLogIn' of the SignUpWindow.fxml
    @FXML
    private TextField txtFullName;
    //TextField that is associated to the textfield 'txtFullName' of the SignUpWindow.fxml
    @FXML
    private TextField txtEmail;
    //TextField that is associated to the textfield 'txtEmail' of the SignUpWindow.fxml
    @FXML
    private Label lblFullNameMax;
    //Label that is associated to the label 'lblFullNameMax' of the SignUpWindow.fxml
    @FXML
    private Label lblEmailMax;
    //Label that is associated to the label 'lblEmailMax' of the SignUpWindow.fxml
    @FXML
    private TextField txtLogin;
    //TextField that is associated to the textfield 'txtLogin' of the SignUpWindow.fxml
    @FXML
    private PasswordField pswPassword;
    //PasswordField that is associated to the passwordfield 'pswPassword' of the SignUpWindow.fxml
    @FXML
    private PasswordField pswRepeatPassword;
    //PasswordField that is associated to the passwordfield 'pswRepeatPassword' of the SignUpWindow.fxml 
    @FXML
    private Label lblLoginMax;
    //Label that is associated to the label 'lblLoginMax' of the SignUpWindow.fxml
    @FXML
    private Label lblPasswordMax;
    //Label that is associated to the label 'lblPasswordMax' of the SignUpWindow.fxml

    private Stage stage;
    private User user;

    public void initStage(Parent root) {
        //stage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        lblFullNameMax.setVisible(false);
        lblEmailMax.setVisible(false);
        lblLoginMax.setVisible(false);
        lblPasswordMax.setVisible(false);
        hypLogIn.setOnAction(this::signIn);
        btnSignUp.setOnAction(this::signUp);
        stage.show();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void signIn(ActionEvent action) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void signUp(ActionEvent action) {
        try {
            fullNameValidation();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
            Stage stageSignIn = new Stage();
        } catch (Exception ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fullNameValidation() throws Exception {
        if (txtFullName.getText().length() >= 255) {
            throw new Exception("Maximum character limit reached!");
        } else if (txtFullName.getText().trim().isEmpty()) {
            throw new Exception("Empty Full Name field!");
        }
    }

    private void emailValidation() throws Exception {
        if (txtFullName.getText().trim().isEmpty()) {
            throw new Exception("Empty Email field!");
        } else if (txtEmail.getText().length() >= 255) {
            throw new Exception("Maximum character limit reached!");
        } else if (!txtEmail.getText().matches("[A-Za-z0-9._%+-]+@[A-Z0-9.-]+[a-z]+.com")) {
            throw new Exception("Error, Email not valid!");
        }

    }
}
