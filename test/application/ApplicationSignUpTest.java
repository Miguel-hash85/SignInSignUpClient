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
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
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
    private static final String LIMIT_CHARACTERS="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

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
        verifyThat("Signed Up Correctly", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#signInPane", isVisible());
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
        doubleClickOn("#txtFullName");
        eraseText(1);
        doubleClickOn("#txtEmail");
        clickOn("#txtEmail");
        eraseText(1);
        doubleClickOn("#txtLogin");
        eraseText(1);
        doubleClickOn("#pswPassword");
        eraseText(1);
        doubleClickOn("#pswRepeatPassword");
        eraseText(1);
        verifyThat("#btnSignUp", isDisabled());
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
        
        txtFullName.setText(LIMIT_CHARACTERS);
        verifyThat("#btnSignUp", isDisabled());
        txtEmail.setText(LIMIT_CHARACTERS);
        verifyThat("#btnSignUp", isDisabled());
        txtLogin.setText(LIMIT_CHARACTERS);
        verifyThat("#btnSignUp", isDisabled());
        pswPassword.setText(LIMIT_CHARACTERS);
        verifyThat("#btnSignUp", isDisabled());
        pswRepeatPassword.setText(LIMIT_CHARACTERS);
        verifyThat("#btnSignUp", isDisabled());
        doubleClickOn("#txtFullName");
        eraseText(1);
        doubleClickOn("#txtEmail");     
        eraseText(1);
        doubleClickOn("#txtLogin");
        eraseText(1);
        doubleClickOn("#pswPassword");
        eraseText(1);
        doubleClickOn("#pswRepeatPassword");
        eraseText(1);
    }
    @Test
    public void test4UserAlreadyExistException() {
        clickOn("#txtFullName");
        write("Aitor Ruiz de Gauna");
        clickOn("#txtEmail");
        write("aitorruizdegauna@gmail.com");
        clickOn("#txtLogin");
        write("Aitorrdg");
        clickOn("#pswPassword");
        write("abcd*1234");
        clickOn("#pswRepeatPassword");
        write("abcd*1234");
        clickOn("#btnSignUp");
        verifyThat(new UserAlreadyExistException().getMessage(), NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
}
