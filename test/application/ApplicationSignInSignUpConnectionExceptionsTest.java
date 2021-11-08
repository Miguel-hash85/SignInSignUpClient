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
 *
 * @author Miguel Sánchez, Zeeshan Yaqoob, Aitor Ruiz de Gauna
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignInSignUpConnectionExceptionsTest extends ApplicationTest {

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationSignInSignUp.class);

    }

    @Test(expected = UserNotFoundException.class)
    public void test1UserNotFoundException() throws UserNotFoundException {
        doubleClickOn("#txtUserName");
        write("manolete");
        doubleClickOn("#passwordField");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        throw new UserNotFoundException();
    }

    @Test(expected = IncorrectPasswordException.class)
    public void test2IncorrectPasswordException() throws IncorrectPasswordException {
        clickOn("Aceptar");
        doubleClickOn("#txtUserName");
        write("Aitorrdg");
        doubleClickOn("#passwordField");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        throw new IncorrectPasswordException();
    }

    @Test(expected = UserAlreadyExistException.class)
    public void test3UserAlreadyExistException() throws UserAlreadyExistException {
        clickOn("Aceptar");
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
        verifyThat("Aceptar",NodeMatchers.isVisible());
        throw new UserAlreadyExistException();
        
    }

}
