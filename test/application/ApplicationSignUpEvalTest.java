/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import exceptions.UserAlreadyExistException;
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
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationSignUpEvalTest extends ApplicationTest {

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
    public void test2UserAlreadyExistException() {
        clickOn("#signUpLink");
        verifyThat("#paneSignUp", isVisible());
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
