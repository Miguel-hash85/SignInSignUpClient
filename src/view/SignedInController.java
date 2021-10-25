/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void initStage(Parent root){
        Scene scene=new Scene(root);
        stage.setScene(scene);
        btnExit.setOnAction(this::close);
        menuExit.setOnAction(this::close);
        menuLogOut.setOnAction(this::logOut);
        stage.show();
    }

    public void setLabelText() {
        lblEmail.setText("aitorruizdegauna@gmail.com");
        lblLogin.setText("Aitorrdg");
        lblFullName.setText("Aitor Ruiz de Gauna");
    }
    public void close(ActionEvent action){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    public void logOut(ActionEvent action){
         stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignInWindow.fxml"));
         Stage stageSignIn=new Stage();
         try {
            Parent root = (Parent) loader.load();         
            SignedInController controller = loader.getController();
            controller.setLabelText();
            controller.setStage(stageSignIn);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignedInWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
