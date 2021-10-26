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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Miguel Sanchez
 */
public class SignUpWindow extends Application{
    
    //private User user;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUpWindow.fxml"));
        try {
            Parent root = (Parent) loader.load();
            SignUpController controller = loader.getController();
            controller.setStage(primaryStage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignUpWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
    
    
}
