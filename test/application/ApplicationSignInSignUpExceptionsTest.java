/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import exceptions.IncorrectPasswordException;
import exceptions.UserAlreadyExistException;
import exceptions.UserNotFoundException;
import java.util.concurrent.TimeoutException;
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
 * Class of tests to test if user already exist, wrong password or user not found.
 * @author Miguel Sánchez, Zeeshan Yaqoob, Aitor Ruiz de Gauna
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignInSignUpExceptionsTest extends ApplicationTest {
    

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);

    }
/**
 * Test to confirm that introduced user does not exist in database.
 */
    @Test
    public void test1UserNotFoundException() {
        doubleClickOn("#txtUserName");
        write("manolete");
        clickOn("#txtPasswd");
        write("a");
        clickOn("#btnSignIn");
        verifyThat(new UserNotFoundException().getMessage(), NodeMatchers.isVisible());
        clickOn("Aceptar");
        doubleClickOn("#txtUserName");
        eraseText(1);
        doubleClickOn("#txtPasswd");
        eraseText(1);
    }
/**
 * Test to confirm that the introduced password is not correct for introduced user.
 */
    @Test
    public void test2IncorrectPasswordException(){
        doubleClickOn("#txtUserName");
        write("Aitorrdg");
        doubleClickOn("#txtPasswd");
        write("a");
        clickOn("#btnSignIn");
        verifyThat(new IncorrectPasswordException().getMessage(), NodeMatchers.isVisible());
        clickOn("Aceptar");
        doubleClickOn("#txtUserName");
        eraseText(1);
        doubleClickOn("#txtPasswd");
        eraseText(1);
    }
/**
 * Test to confirm that at the time of signUp introduced login already exist in database.
 */
    @Test
    public void test3UserAlreadyExistException() {
        clickOn("#signUpLink");
        verifyThat("#paneSignUp", isVisible());
        clickOn("#txtFullName");
        write("Felipe");
        clickOn("#txtEmail");
        write("felipe@gmail.com");
        clickOn("#txtLogin");
        write("a");
        clickOn("#pswPassword");
        write("abcd*1234");
        clickOn("#pswRepeatPassword");
        write("abcd*1234");
        clickOn("#btnSignUp");
        verifyThat(new UserAlreadyExistException().getMessage(),NodeMatchers.isVisible());
        clickOn("Aceptar");
    }
}
