/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.DataEncapsulation;
import classes.User;
import classes.UserPrivilege;
import classes.UserStatus;
import exceptions.ConnectionRefusedException;
import exceptions.UserAlreadyExistException;
import interfaces.Signable;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Miguel Sánchez,Aitor Ruiz de Gauna.
 */
/**
 * Class that manage the SignUpWindow.
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
    private DataEncapsulation data;
    private Signable signable;
    
    // Logger to record the events and trace out errors.
    private static final Logger LOGGER = Logger.getLogger("view.SignUpController");

    /**
     * Method that iniciate the stage.
     * @param root base class
     */
    public void initStage(Parent root) {
        LOGGER.info("Stage initiated");
        stage.initModality(Modality.APPLICATION_MODAL);
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

    /**
     * Method that return an object of the interface Signable.
     * @return an object of interface Signable.
     */
    public Signable getSignable() {
        return signable;
    }

    /**
     * Method that set a value for the Signable object.
     * @param signable, receives an object of interface Signable.
     */
    public void setSignable(Signable signable) {
        this.signable = signable;
    }

    /**
     * Method that return an User.
     * @return an object user,
     */
    public User getUser() {
        return user;
    }

    /**
     * Method that set a value for the User.
     * @param user, receives an object user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method that set a value for the Stage.
     * @param stage receives an object stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Method that close this stage.
     * @param action if user prefer to signIn, that will close the current window.
     */
    public void signIn(ActionEvent action) {
        LOGGER.info("Stage closed");
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Method that send an user to the server for a signUp and close the stage.
     * @param action once the user is signedUp without error current window will get closed.
     */
    
    //sign up event
    public void signUp(ActionEvent action) {
        LOGGER.info("User sent for signUp");
        //the method validation returns a boolean needed to signUp or not
        try {
            if(!validation()){
                setUserInfo();
            signable.signUp(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Signed Up Correctly", ButtonType.OK);
            alert.show();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            }
            
        } catch (UserAlreadyExistException | ConnectionRefusedException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.show();
            if(ex instanceof UserAlreadyExistException){
                txtLogin.requestFocus();//focus returned to txtLogin
            }else{
                txtFullName.requestFocus();//focus returned to txtFullName
            }
            
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error Ocurred", ButtonType.OK);
            alert.show();
        }
    }
    /**
     * Method to validate the email and control that password and repeat would always be same.
     * @return a boolean if the validation goes well or not.
     */
    private boolean validation() {
        boolean error=false;
        LOGGER.info("Validation of the email, password and repeatPassword");
        try {//here we validate if the email matches with a patern
            if (!txtEmail.getText().matches("[A-Za-z0-9._%+-]+@[a-z0-9.-]+.[A-Za-z]")) {
                lblEmailMax.setText("Error,Email not valid!");
                throw new Exception("Error,Email not valid!");
            }//here we compare pswPassword and pswRepeatPassword
            if (!pswRepeatPassword.getText().equalsIgnoreCase(pswPassword.getText())) {
                lblPasswordMax.setText("Error,Password does not match!");
                throw new Exception("Error,Password does not match!");
            }
        } catch (Exception ex) {
            error=true;
            btnSignUp.setDisable(true);
            //if we have an exception, we throw and take it here, where the messages are managed
            if (ex.getMessage().equalsIgnoreCase("Error,Email not valid!")) {
                lblEmailMax.setVisible(true);
                txtEmail.requestFocus();
            } else if (ex.getMessage().equalsIgnoreCase("Error,Password does not match!")) {
                lblPasswordMax.setVisible(true);
                pswPassword.requestFocus();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error Ocurred", ButtonType.OK);
                alert.show();
            }
        }finally{
            return error;
        }
    }

    /**
     * This method observe the username and password texts to manage the state of signIn button.
     * @param observable, object that has listener, being observed.
     * @param oldValue indicates the old value(could be default).
     * @param newValue indicates the newly introduced value.
     * 
     */
    
    //this method observes the text changes
    public void textChanged(ObservableValue observable, Object oldValue, Object newValue) {
        LOGGER.info("Analysis of the text field values");
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
    /**
     * Method to check the character limit of a textfield.
     * @param string that has the value of the corresponding textField.
     * @param label that is the corresponding label with the error message.
     */
    private void characterLimitArrived(String string, Label label) {
        LOGGER.info("Validation of the length of fields");
        if (string.length() > 255) {
            label.setVisible(true);
            btnSignUp.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }
    /**
     * Method to set assign details to an user.
     */
    private void setUserInfo() {
        LOGGER.info("User information set");
        user = new User();
        user.setFullname(txtFullName.getText());
        user.setEmail(txtEmail.getText());
        user.setLogin(txtLogin.getText());
        user.setPassword(pswPassword.getText());
        user.setLastPasswordChange(LocalDateTime.now());
        user.setPrivilege(UserPrivilege.USER);
        user.setStatus(UserStatus.ENABLED);
    }
}
