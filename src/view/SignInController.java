/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import exceptions.UserNotFoundException;
import exceptions.ConnectionRefusedException;
import exceptions.IncorrectPasswordException;
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
    // Stage that will be used to initiate other windows
    private Stage stage;
    private User user;
    private Signable signable;
    private SignableFactory signableFactory;

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
        characterLimitArrived(txtPasswd, lblPasswdMax);
        characterLimitArrived(txtUserName, lblUserMax);
    }

    /**
     *
     * @param action
     */
    public void signIn(ActionEvent action) {
        User userSignedIn = new User();
        user.setLogin(txtUserName.getText());
        user.setPassword(txtPasswd.getText());
        try {
            signable = signableFactory.getSignableImplementation();
            userSignedIn = signable.signIn(user);
            sendUser(userSignedIn);
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (UserNotFoundException userNotFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR, userNotFound.getErrorMessage(), ButtonType.OK);
            alert.show();
        } catch (IncorrectPasswordException passwordError) {
            Alert alert = new Alert(Alert.AlertType.ERROR, passwordError.getErrorMessage(), ButtonType.OK);
            alert.show();
        } catch (ConnectionRefusedException connectionError) {
            Alert alert = new Alert(Alert.AlertType.ERROR, connectionError.getErrorMessage(), ButtonType.OK);
            alert.show();
        }

    }

    /**
     *
     * @param action
     */
    public void signUp(ActionEvent action) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUpWindow.fxml"));
        Stage stageSignIn = new Stage();
        try {
            Parent root = (Parent) loader.load();
            SignUpController controller = loader.getController();
            controller.setSignable(signableFactory.getSignableImplementation);
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR WHILE SIGNING UP", ButtonType.OK);
        }
    }

    private void characterLimitArrived(TextField textField, Label label) {
        if (textField.getText().length() > 255) {
            label.setVisible(true);
            btnSignIn.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }

    private void sendUser(User user) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
        Stage stageSignIn = new Stage();
        try {
            Parent root = (Parent) loader.load();
            SignUpController controller = loader.getController();
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.setUser(user);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR WHILE SIGNING UP", ButtonType.OK);
        }
    }
}
