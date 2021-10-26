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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class SignedInWindow extends Application {

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // logger.info("Initializing SignedIn Stage");
        User u = new User();
        u.setEmail("aitorruizdegauna@gmail.com");
        u.setFullname("Aitor Ruiz de Gauna");
        u.setLogin("Aitorrdg");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignedInWindow.fxml"));
        try {

            Parent root = (Parent) loader.load();
            SignedInController controller = loader.getController();
            controller.setUser(u);
            controller.setLabelText();
            controller.setStage(primaryStage);
            controller.initStage(root);

        } catch (IOException ex) {
            Logger.getLogger(SignedInWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
