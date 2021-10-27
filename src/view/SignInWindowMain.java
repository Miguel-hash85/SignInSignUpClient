/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class SignInWindowMain extends Application {

    

    @Override
    public void start(Stage primaryStage) {
            
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInWindow.fxml"));
            SignInController controller;
            Parent root= (Parent)loader.load();
            controller=loader.getController();
            primaryStage.setTitle("SignIn");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignInWindowMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    
}
