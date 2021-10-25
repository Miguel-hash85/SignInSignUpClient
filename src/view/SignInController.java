/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author 2dam
 */
public class SignInController {
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
}
