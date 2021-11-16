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
import java.util.Optional;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.SignableFactory;

/**
 *
 * @author Zeeshan Yaqoob
 */
/**
 * Class that manage the SignInWindow.
 *
 * @author Aitor
 */
public class SignInController {

    // Logger to record the events and trace out errors.
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
    // private SignableFactory signableFactory;
    private SignableFactory signableFactory;

    /**
     * Method that set the value of the stage.
     *
     * @param primaryStage is principal window of application.
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
        LOGGER.info("Stage set");
    }

    /**
     * Method that iniciates the stage.
     *
     * @param root base class Scene is created, and defines the intial state of
     * window.
     */
    public void initStage(Parent root) {
        LOGGER.info("Stage initiated");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setResizable(false);
        signableFactory = new SignableFactory();
        signable = signableFactory.getSignableImplementation();

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert.showAndWait();
                if (alert.getResult() != ButtonType.YES) {
                    t.consume();
                } else {
                    stage.close();
                    LOGGER.info("Application closed");
                }
            }
        };

        btnExit.addEventFilter(ActionEvent.ACTION, eventHandler);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, eventHandler);
        btnSignIn.setDisable(true);
        btnSignIn.setOnAction(this::signIn);
        txtUserName.textProperty().addListener(this::textChanged);
        txtUserName.setPromptText("Introduce your username");
        txtUserName.setTooltip(new Tooltip("Introduce your username"));
        txtPasswd.textProperty().addListener(this::textChanged);
        txtPasswd.setPromptText("Introduce your password");
        txtPasswd.setTooltip(new Tooltip("Introduce your password"));
        signUpLink.setOnAction(this::signUp);
        lblPasswdMax.setVisible(false);
        lblUserMax.setVisible(false);
        stage.show();

    }

    /**
     * This method observe the username and password texts to manage the state
     * of signIn button.
     *
     * @param observable, object that has listener, being observed.
     * @param oldValue indicates the old value(could be default).
     * @param newValue indicates the newly introduced value.
     *
     *
     */
    public void textChanged(ObservableValue observable, String oldValue, String newValue) {
        if (!txtPasswd.getText().trim().equals("") && !txtUserName.getText().trim().equals("")) {
            btnSignIn.setDisable(false);
        } else {
            btnSignIn.setDisable(true);
        }
        // to check the limit of characters introduced in username and password fields.
        characterLimitArrived(txtPasswd, lblPasswdMax);
        characterLimitArrived(txtUserName, lblUserMax);
        // LOGGER.info("Text changed");
    }

    /**
     * Method that get the information from window and make a call to the
     * interface Signable depending on the action.
     *
     * @param action that controls the signIn action.
     *
     */
    public void signIn(ActionEvent action) {
        LOGGER.info("User sent for signIn");
        try {
            // User that will receive its details.
            User userSignedIn;
            // User to send details for processing.
            user = new User();
            user.setLogin(txtUserName.getText());
            user.setPassword(txtPasswd.getText());
            //user send to signableLogicImplementation and user recieved as userSignedIn with full details.
            userSignedIn = signable.signIn(user);
            LOGGER.info("User sent to show information in SignedInWindow");
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
            Stage stageSignIn = new Stage();
            Parent root = (Parent) loader.load();
            SignedInController controller = loader.getController();
            controller.setUser(userSignedIn);
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.initStage(root);
            //Initialization of signedIn window.
            stage.close();
        } catch (UserNotFoundException | IncorrectPasswordException | ConnectionRefusedException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.show();
            if (ex instanceof UserNotFoundException) {
                txtUserName.requestFocus();
            } else if (ex instanceof IncorrectPasswordException) {
                txtPasswd.requestFocus();
            } else {
                txtUserName.requestFocus();
            }
        } catch (Exception ex) {
            LOGGER.info("Error while signing in");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error Ocurred", ButtonType.OK);
            alert.show();
        }

    }

    /**
     * Method will initiate SignedUp window.
     *
     * @param action that controls that signUp action.
     *
     */
    public void signUp(ActionEvent action) {
        LOGGER.info("User sent for signUp");

        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_SHOWING));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUpWindow.fxml"));
        Stage stageSignUp = new Stage();
        try {
            Parent root = (Parent) loader.load();
            SignUpController controller = loader.getController();
            //to get the implementation of signableFactory
            controller.setSignable(signable);
            controller.setStage(stageSignUp);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR WHILE SIGNING UP", ButtonType.OK);
        }
    }

    /**
     * Method to check the character limit of a textfield.
     *
     * @param textField, receives the textfield from textChanged method.
     * @param label, receives the label from the textChanged method.
     */
    private void characterLimitArrived(TextField textField, Label label) {
        //if textfield length is higher than 255 character, label will be visible to warn the user.
        if (textField.getText().length() > 255) {
            label.setVisible(true);
            btnSignIn.setDisable(true);
        } else {
            label.setVisible(false);
        }
    }

}
