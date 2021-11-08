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
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignUpTest extends ApplicationTest {

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
    public void test1SignUp() {
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
        clickOn("Aceptar");

    }

    @Test
    public void test2ButtonSignUpDisabled() {

        clickOn("#signUpLink");
        verifyThat("#paneSignUp", isVisible());
        clickOn("#txtFullName");
        write("Anselmo");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#txtEmail");
        write("z@z.com");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#txtLogin");
        write("z");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#pswPassword");
        write("z");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#pswRepeatPassword");
        write("z");
        verifyThat("#btnSignUp", isEnabled());
    }

    @Test
    public void test3MaxCharacterReached() {
        txtFullName = lookup("#txtFullName").query();
        txtEmail = lookup("#txtEmail").query();
        txtLogin = lookup("#txtLogin").query();
        pswPassword = lookup("#pswPassword").query();
        pswRepeatPassword = lookup("#pswRepeatPassword").query();
        txtFullName.setText("");
        txtEmail.setText("");
        txtLogin.setText("");
        pswPassword.setText("");
        pswRepeatPassword.setText("");
        
        txtFullName.setText("\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"");
        verifyThat("#btnSignUp", isDisabled());
        txtEmail.setText("\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"");
        verifyThat("#btnSignUp", isDisabled());
        txtLogin.setText("\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"");
        verifyThat("#btnSignUp", isDisabled());
        pswPassword.setText("\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n"
                + "                + \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"");
        verifyThat("#btnSignUp", isDisabled());
        pswRepeatPassword.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("#btnSignUp", isDisabled());
    }

}
