/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import exceptions.ConnectionRefusedException;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;
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
 * @author Miguel Sanchez, Aitor Ruiz de Gauna
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignInSignUpConnectionExceptionsTest extends ApplicationTest {

    private TextField txtUserName;
    private PasswordField passwordField;

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);

    }
    /**
     * Test that will confirm when server is not online, connection get refused
     */
    @Test
    public void test1ConnectionRefusedException(){
        clickOn("#txtUserName");
        write("a");
        clickOn("#txtPasswd");
        write("a");
        clickOn("#btnSignIn");
        verifyThat(new ConnectionRefusedException().getMessage(), NodeMatchers.isVisible());
    }
    /**
     * Test that will confirm when server is not online, connection get refused
     */
    @Test
    public void test2ConnectionRefusedException(){
        clickOn("Aceptar");
        clickOn("#signUpLink");
        verifyThat("#paneSignUp", isVisible());
        clickOn("#txtFullName");
        write("Felipe");
        clickOn("#txtEmail");
        write("felipe@gmail.com");
        clickOn("#txtLogin");
        write("felipe13");
        clickOn("#pswPassword");
        write("abcd*1234");
        clickOn("#pswRepeatPassword");
        write("abcd*1234");
        clickOn("#btnSignUp");
        verifyThat(new ConnectionRefusedException().getMessage(),NodeMatchers.isVisible());
    }

}
