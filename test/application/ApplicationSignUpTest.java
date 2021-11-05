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
public class ApplicationSignUpTest extends ApplicationTest{
    
     @BeforeClass
    public static void setUpClass() throws TimeoutException{
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);
        
    }

    @Test
    public void test1SignUp(){
        clickOn("#signUpLink");   
        verifyThat("#paneSignUp" , isVisible());
        clickOn("#txtFullName");
        write("Anselmo");
        clickOn("#txtEmail");
        write("anselmo@gmail.com");
        clickOn("#txtLogin");
        write("anselmo12");
        clickOn("#pswPassword");
        write("abcd*1234");
        clickOn("#pswRepeatPassword");
        write("abcd*1234");
        verifyThat("#signInPane" , isVisible());
    }
    
}
