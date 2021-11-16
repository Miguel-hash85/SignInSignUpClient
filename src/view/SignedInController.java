/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author 2dam
 */
/**
 * Class that manage the signedInWindow.
 */
public class SignedInController {
    //Label that is associated to the label 'lblEmail' of the SignedInWindow.fxml
    @FXML
    private Label lblEmail;
    //Label that is associated to the label 'lblFullName' of the SignedInWindow.fxml
    @FXML
    private Label lblFullName;
    //Label that is associated to the label 'lblLogin' of the SignedInWindow.fxml
    @FXML
    private Label lblLogin;
    //Button that is associated to the Button 'btnExit' of the SignedInWindow.fxml
    @FXML
    private Button btnExit;
    //MenuItem that is associated to the MenuItem 'menuLogOut' of the SignedInWindow.fxml
    @FXML
    private MenuItem menuLogOut;
    //MenuItem that is associated to the MenuItem 'menuExit' of the SignedInWindow.fxml
    @FXML
    private MenuItem menuExit;
    private Stage stage;
    private User user;
    
    // Logger to record the events and trace out errors.
    private static final Logger LOGGER=Logger.getLogger("view.SignedInController");
    /**
     * Method that assign the value of the stage received to the class stage.
     * @param stage current window (SignedIn window).
     */
    public void setStage(Stage stage) {
        LOGGER.info("Stage set");
        this.stage = stage;
    }
    /**
     * Method that receive a Parent and initialize the stage
     * @param root the base class
     */
    public void initStage(Parent root){
        LOGGER.info("Stage initiated");
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure that you want to leave this sesion?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert.showAndWait();
                if (alert.getResult() != ButtonType.YES) {
                    event.consume();
                } else {
                    stage.close();
                    LOGGER.info("Application closed");
                }
            }
        };
        btnExit.addEventFilter(ActionEvent.ACTION, eventHandler);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, eventHandler);
        menuExit.setOnAction(this::close);
        menuLogOut.setOnAction(this::logOut);
        stage.show();
    }
    /**
     * Method that return the value of the user.
     * @return User
     */
    public User getUser() {
        return user;
    }
    /**
     * Method that assign the value of the user received to the class user.
     * @param user object that is received.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Method that set the text of the labels.
     */
    public void setLabelText() {
        LOGGER.info("Text of the labels established");
        lblEmail.setText(user.getEmail());
        lblLogin.setText(user.getLogin());
        lblFullName.setText(user.getFullname());
    }
    /**
     * Method that close the stage.
     * @param action that close the window.
     */
    public void close(ActionEvent action){
        LOGGER.info("Stage closed");
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    /** 
     * Method that close the stage and open the signInWindow.
     * @param action that close this stage and opens the SignInWindow.
     */
    public void logOut(ActionEvent action){
         LOGGER.info("Stage closed and signIn window opened");
         stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignInWindow.fxml"));
         Stage stageSignIn=new Stage();
         try {
            Parent root = (Parent) loader.load();         
            SignInController controller = loader.getController();    
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unexpected Error Ocurred",ButtonType.OK);
            alert.show();
        }
    }
}
