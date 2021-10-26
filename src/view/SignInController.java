/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.im.spi.InputMethod;
import java.beans.EventHandler;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
public class SignInController {
    // Button to sign in to the application
    @FXML
    private Button btnSignIn;
    // Button to close the application
    @FXML
    private Button btnExit;
    // Password field for user to enter passwor
    @FXML
    private PasswordField txtPasswd;
    // TextField for user to enter username
    @FXML
    private TextField txtUserName;
    // If user is not registered, this hyperlink will get to user to sign up.
    @FXML
    private Hyperlink signUpLink;
    // lablel that will be visible if user reached to max character limit for password
    @FXML
    private Label lblPasswdMax;
    //lablel that will be visible if user reached to max character limit for username
    @FXML
    private Label lblUserMax;
    
    private Stage stage;

    
    void setStage(Stage primaryStage) {
        this.stage=primaryStage;
    }

    void initStage(Parent root) {
         Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setResizable(false);
        btnSignIn.setDisable(true);
        btnExit.setOnAction(this::close);
        //btnSignIn.setOnAction(this::signIn);
        // txtUserName.textProperty().addListener((observable, oldvalue, newvalue),this::characterLimitArrived);
        lblPasswdMax.setVisible(false);
        lblUserMax.setVisible(false);
        stage.show();
        
       
    }
    public void close(ActionEvent action){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    public void signIn(ActionEvent action){
        
    }
    public void characterLimitArrived(){
        if(txtPasswd.getText().trim().equals("")||txtUserName.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please Fill both fields correctly",ButtonType.OK);
        }
    }
}
