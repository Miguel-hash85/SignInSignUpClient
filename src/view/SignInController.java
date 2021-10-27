/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import interfaces.Signable;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
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
    private User user;
    private Signable signable;

    void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    
    

    void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setResizable(false);
        btnSignIn.setDisable(true);
        btnExit.setOnAction(this::close);
        btnSignIn.setOnAction(this::signIn);
        txtUserName.textProperty().addListener(this::textChanged);
        txtPasswd.textProperty().addListener(this::textChanged);
       // signUpLink.setOnAction(this::signUp);
        lblPasswdMax.setVisible(false);
        lblUserMax.setVisible(false);
        stage.show();

    }

    /**
     *
     * @param action
     */
    public void close(ActionEvent action) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void textChanged(ObservableValue observable, Object oldValue, Object newValue) {
        if (!txtPasswd.getText().trim().equals("") && !txtUserName.getText().trim().equals("")) {
            btnSignIn.setDisable(false);
        } else {
            btnSignIn.setDisable(true);
        }
        characterLimitArrived(txtPasswd,lblPasswdMax);
        characterLimitArrived(txtUserName,lblUserMax);
    }
    
    /**
     *
     * @param action
     */
    public void signIn(ActionEvent action) {
        User userSignedIn = new User();
        user.setLogin(txtUserName.getText());
        user.setPassword(txtPasswd.getText());
       //userSignedIn=signIn(user);
    }

    /**
     *
     * @param action
     */
    public void signUp(ActionEvent action){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignInWindow.fxml"));
        Stage stageSignIn=new Stage();
         try {
            Parent root = (Parent) loader.load();         
           SignUpController controller = loader.getController();
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"ERROR WHILE SIGNING UP",ButtonType.OK);
        }
    }
    private void characterLimitArrived(TextField textField, Label label){
         if (textField.getText().length() > 255) {
            label.setVisible(true);
            btnSignIn.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }
}
