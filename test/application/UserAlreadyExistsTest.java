/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import exceptions.UserAlreadyExistException;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Miguel Sánchez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAlreadyExistsTest extends ApplicationTest{
    
    private TextField txtFullName;
    private TextField txtEmail;
    private TextField txtLogin;
    private PasswordField pswPassword;
    private PasswordField pswRepeatPassword;
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);

    }
    
    

    @Test
    public void test1UserAlreadyExists() {
        clickOn("#signUpLink");
        verifyThat("#paneSignUp", isVisible());
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
        clickOn("#btnSignUp");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat(new UserAlreadyExistException().getMessage(), NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
    
}
