/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import java.util.concurrent.TimeoutException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;

import org.testfx.framework.junit.ApplicationTest;
import view.SignInController;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignInTest extends ApplicationTest{
    
    private TextField txtUserName;
    private PasswordField passwordField;
    
    
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException{
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);
        
    }
    
    @Test
    public void test1SignInButtonIsEnabled() {
        
        //check btnSignIn when password is empty
        txtUserName=lookup("#txtUserName").query();
        clickOn("#txtUserName");
        write("username");
        verifyThat("#btnSignIn", isDisabled());
        doubleClickOn("#txtUserName");
        eraseText(txtUserName.getText().length());
        
        //check btnSignIn when tstUsername is empty
        passwordField=lookup("#txtPasswd").query();
        clickOn("#txtPasswd");
        write("userpassword");
        verifyThat("#btnSignIn", isDisabled());
        doubleClickOn("#txtPasswd");
        eraseText(passwordField.getText().length());
        
        //check btnSIgnIn when maximum characters in txtUsername is reached
        clickOn("#txtUserName");
        txtUserName.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("#btnSignIn", isDisabled());
        
        
        //check btnSIgnIn when maximum characters in passwordField is reached
        clickOn("#txtPasswd");
        passwordField.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("#lblUserMax", isVisible());
        verifyThat("#lblPasswdMax", isVisible());
        verifyThat("#btnSignIn", isDisabled());
        doubleClickOn("#txtUserName");
        eraseText(1);
        doubleClickOn("#txtPasswd");
        eraseText(1);

    }

     @Test
    public void test2SignIn(){
        clickOn("#txtUserName");
        write("Aitorrdg");
        clickOn("#txtPasswd");
        write("abcd*1234");
        verifyThat("#btnSignIn",isEnabled());
        clickOn("#btnSignIn");
        verifyThat("#panelSignedIn" , isVisible());
    }
    
    @Test
    public void test3Logout(){
        clickOn("#menu");
        clickOn("#menuLogOut");       
        verifyThat("#signInPane" , isVisible());
    }    
}
