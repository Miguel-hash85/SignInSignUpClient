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
    private DataEncapsulation data;
    private Signable signable;
    private static final Logger LOGGER = Logger.getLogger("view.SignUpController");

    /**
     *
     * @param root
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
     *
     * @return
     */
    public Signable getSignable() {
        return signable;
    }

    /**
     *
     * @param signable
     */
    public void setSignable(Signable signable) {
        this.signable = signable;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     *
     * @param action
     */
    public void signIn(ActionEvent action) {
        LOGGER.info("Stage closed");
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     *
     * @param action
     */
    public void signUp(ActionEvent action) {
        LOGGER.info("User sent for signUp");
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
                txtLogin.requestFocus();
            }else{
                txtFullName.requestFocus();
            }
            
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error Ocurred", ButtonType.OK);
            alert.show();
        }
    }

    private boolean validation() {
        boolean error=false;
        LOGGER.info("Validation of the email, password and repeatPassword");
        try {
            if (!txtEmail.getText().matches("[A-Za-z0-9._%+-]+@[a-z0-9.-]+.[A-Za-z]")) {
                lblEmailMax.setText("Error,Email not valid!");
                throw new Exception("Error,Email not valid!");
            }
            if (!pswRepeatPassword.getText().equalsIgnoreCase(pswPassword.getText())) {
                lblPasswordMax.setText("Error,Password does not match!");
                throw new Exception("Error,Password does not match!");
            }
        } catch (Exception ex) {
            error=true;
            btnSignUp.setDisable(true);
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
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
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

    private void characterLimitArrived(String string, Label label) {
        LOGGER.info("Validation of the length of fields");
        if (string.length() > 255) {
            label.setVisible(true);
            btnSignUp.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }

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
