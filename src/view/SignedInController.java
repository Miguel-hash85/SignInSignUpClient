/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
        stage.show();
    }

    public void setLabelText() {
        lblEmail.setText("aitorruizdegauna@gmail.com");
        lblLogin.setText("Aitorrdg");
        lblFullName.setText("Aitor Ruiz de Gauna");
    }
}
