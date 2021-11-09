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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import logic.SignableFactory;

/**
 *
 * @author Zeeshan Yaqoob
 */
public class SignInController {

    private static final Logger LOGGER = Logger.getLogger("view.SignInController");

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
    // An object of user to work with
    private User user;
    // An object of interface to ge implementation
    private Signable signable;
    // An object of signableFactory to call and recieve an object of signableLogicImplementation.
    private SignableFactory signableFactory;

    /**
     *
     * @param primaryStage is principal window of application.
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
        LOGGER.info("Stage set");
    }

    /**
     *
     * @param
     */
    public void initStage(Parent root) {
        LOGGER.info("Stage initiated");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setResizable(false);
        btnSignIn.setDisable(true);
        btnExit.setOnAction(this::close);
        btnSignIn.setOnAction(this::signIn);
        txtUserName.textProperty().addListener(this::textChanged);
        txtPasswd.textProperty().addListener(this::textChanged);
        signUpLink.setOnAction(this::signUp);
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
        LOGGER.info("Stage closed");
    }

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void textChanged(ObservableValue observable, String oldValue, String newValue) {
        if (!txtPasswd.getText().trim().equals("") && !txtUserName.getText().trim().equals("")) {
            btnSignIn.setDisable(false);
        } else {
            btnSignIn.setDisable(true);
        }
        characterLimitArrived(txtPasswd, lblPasswdMax);
        characterLimitArrived(txtUserName, lblUserMax);
        LOGGER.info("Text changed");
    }

    /**
     *
     * @param action
     */
    public void signIn(ActionEvent action) {
        LOGGER.info("User sent for signIn");
        try {
            User userSignedIn;
            user = new User();
            signableFactory = new SignableFactory();
            user.setLogin(txtUserName.getText());
            user.setPassword(txtPasswd.getText());
            signable = signableFactory.getSignableImplementation();
            userSignedIn = signable.signIn(user);
            sendUser(userSignedIn);
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } catch (UserNotFoundException | IncorrectPasswordException | ConnectionRefusedException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.show();
            txtUserName.requestFocus();
        } catch (Exception ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error Ocurred", ButtonType.OK);
            alert.show();
        }

    }

    /**
     *
     * @param action
     */
    public void signUp(ActionEvent action) {
        LOGGER.info("User sent for signUp");
        signableFactory = new SignableFactory();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUpWindow.fxml"));
        Stage stageSignUp = new Stage();
        try {
            Parent root = (Parent) loader.load();
            SignUpController controller = loader.getController();
            controller.setSignable(signableFactory.getSignableImplementation());
            controller.setStage(stageSignUp);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR WHILE SIGNING UP", ButtonType.OK);
        }
    }

    private void characterLimitArrived(TextField textField, Label label) {
        LOGGER.info("character limit evaluation");
        if (textField.getText().length() > 255) {
            label.setVisible(true);
            btnSignIn.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }

    private void sendUser(User user) {
        LOGGER.info("User sent to show information in SignedInWindow");
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
        Stage stageSignIn = new Stage();
        try {
            Parent root = (Parent) loader.load();
            SignedInController controller = loader.getController();
            controller.setUser(user);
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR WHILE SIGNING UP", ButtonType.OK);
        }
    }
}
