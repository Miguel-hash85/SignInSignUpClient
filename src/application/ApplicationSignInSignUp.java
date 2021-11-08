/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import view.SignInController;

/**
 *
 * @author 2dam
 */
public class ApplicationSignInSignUp extends Application {
    
    private static final Logger LOGGER=Logger.getLogger("application.ApplicationSignInSignUp.class");

        
    

    @Override
    public void start(Stage primaryStage) {
        
        LOGGER.info("App started");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignInWindow.fxml"));
        try {
            
            Parent root= (Parent) loader.load();
            SignInController  controller=loader.getController();
            controller.setStage(primaryStage);
            controller.initStage(root);
           
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    
}
