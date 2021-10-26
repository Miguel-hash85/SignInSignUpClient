/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    public void initStage(Parent root){
        //stage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene=new Scene(root);
        stage.setScene(scene);
        hypLogIn.setOnAction(this::signIn);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void signIn(ActionEvent action){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
}
