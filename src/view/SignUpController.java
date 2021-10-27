/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        btnSignUp.setDisable(true);
        hypLogIn.setOnAction(this::signIn);
        btnSignUp.setOnAction(this::signUp);
        txtFullName.textProperty().addListener(this::textChanged);
        txtEmail.textProperty().addListener(this::textChanged);
        txtLogin.textProperty().addListener(this::textChanged);
        pswPassword.textProperty().addListener(this::textChanged);
        pswRepeatPassword.textProperty().addListener(this::textChanged);
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
            Validation();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
            Stage stageSignIn = new Stage();
        } catch (Exception ex) {
            btnSignUp.setDisable(true);
            if (ex.getMessage().equalsIgnoreCase("Error,Email not valid!")) {
                lblEmailMax.setVisible(true);
                txtEmail.requestFocus();
            }else if (ex.getMessage().equalsIgnoreCase("Error,Password does not match!")){
                lblPasswordMax.setVisible(true);
                pswPassword.requestFocus();
            }

        }

    }

    private void Validation() throws Exception {
        //Username

        //Email
        if (!txtEmail.getText().matches("[A-Za-z0-9._%+-]+@[a-z0-9.-]+.com")) {
            lblEmailMax.setText("Error,Email not valid!");
            throw new Exception("Error,Email not valid!");
        }
        //Password
        if (!pswRepeatPassword.getText().equalsIgnoreCase(pswPassword.getText())) {
            lblPasswordMax.setText("Error,Password does not match!");
            throw new Exception("Error,Password does not match!");
        }
    }

    public void textChanged(ObservableValue observable, Object oldValue, Object newValue) {
        if (!txtEmail.getText().trim().equals("") && !txtFullName.getText().trim().equals("")
                && !txtLogin.getText().trim().equals("") && !pswPassword.getText().trim().equals("")
                && !pswRepeatPassword.getText().trim().equals("")) {
            btnSignUp.setDisable(false);
        } else {
            btnSignUp.setDisable(true);
        }
        characterLimitArrived(txtEmail.getText(), lblEmailMax);
        characterLimitArrived(txtFullName.getText(), lblFullNameMax);
        characterLimitArrived(txtLogin.getText(), lblLoginMax);
        characterLimitArrived(pswPassword.getText(), lblPasswordMax);
        characterLimitArrived(pswRepeatPassword.getText(), lblPasswordMax);
    }

    private void characterLimitArrived(String string, Label label) {
        if (string.length() > 255) {
            label.setVisible(true);
            btnSignUp.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }
}
