/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author 2dam
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
    /**
     * Method that assign the value of the stage received to the class stage.
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Method that receive a Parent and initialize the stage
     * @param root 
     */
    public void initStage(Parent root){   
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        btnExit.setOnAction(this::close);
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
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Method that set the text of the labels.
     */
    public void setLabelText() {
        lblEmail.setText(user.getEmail());
        lblLogin.setText(user.getLogin());
        lblFullName.setText(user.getFullname());
    }
    /**
     * Method that close the window.
     * @param action 
     */
    public void close(ActionEvent action){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    /**
     * Method tha close this stage and opens the SignInWindow.
     * @param action 
     */
    public void logOut(ActionEvent action){
         stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignInWindow.fxml"));
         Stage stageSignIn=new Stage();
         try {
            Parent root = (Parent) loader.load();         
            SignInController controller = loader.getController();
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"ERROR WHILE LOGING OUT",ButtonType.OK);
            alert.show();
        }
    }
}
