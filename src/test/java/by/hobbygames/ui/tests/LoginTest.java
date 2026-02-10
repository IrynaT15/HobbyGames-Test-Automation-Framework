package by.hobbygames.ui.tests;

import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import by.hobbygames.testdata.credentials.*;
import by.hobbygames.testdata.errors.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginTest {
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void setupDriverAndOpenHomePageAndCloseCookieAlertAndClickLoginButton() {
        homePage = new HomePage();
        homePage.open();
        homePage.clickCookieAlertClose();
        homePage.clickLoginButton();
        loginPage = new LoginPage();
    }

    @Test
    public void testLoginPopupContainsAllRequiredElements() {
        assertAll("Login popup UI",
                () -> Assertions.assertTrue(loginPage.isLoginPopupTitleDisplayed(),
                        "The Title is not displayed in the Login Popup."),
                () -> Assertions.assertTrue(loginPage.isLoginFieldDisplayedInLoginPopup(),
                        "The Login field is not displayed in the Login Popup."),
                () -> Assertions.assertTrue(loginPage.isPasswordFieldDisplayedInLoginPopup(),
                        "The [Password] field is not displayed in the Login Popup."),
                () -> Assertions.assertTrue(loginPage.isSubmitButtonDisplayedInLoginPopup(),
                        "The [Submit] button is not displayed in the Login Popup."),
                () -> Assertions.assertTrue(loginPage.isForgotModalLinkDisplayedInLoginPopup(),
                        "The Forgot Modal link is not displayed in the Login Popup."),
                () -> Assertions.assertTrue(loginPage.isRegisterModalLinkDisplayedInLoginPopup(),
                        "The Register Modal link is not displayed in the Login Popup.")
        );
    }

    @Test
    public void testLoginPopupDisappearsWhenCloseButtonIsClicked() {
        loginPage.clickCloseButton();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed after [Close] button is clicked.");
    }

    @Test
    public void testLoginPopupDisappearsAndForgotPopupAppearsWhenForgotModalLinkIsClicked() {
        loginPage.clickForgotModalLink();
        assertAll("Navigate from Login popup to Forgot popup",
                () -> Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                        "The Login Popup is displayed."),
                () -> Assertions.assertTrue(loginPage.isForgotPopupDisplayed(),
                "Forgot popup is not displayed.")
        );
    }

    @Test
    public void testLoginPopupDisappearsAndRegisterPopupAppearsWhenRegisterModalLinkIsClicked() {
        loginPage.clickRegisterModalLink();
        assertAll("Navigate from Login popup to Register popup",
                () -> Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                        "The Login Popup is displayed."),
                () -> Assertions.assertTrue(loginPage.isRegisterPopupDisplayed(),
                "Register popup is not displayed.")
        );
    }

    @Test
    public void testErrorMessagesForLoginWithEmptyLoginAndPassword() {
        loginPage.clickSubmitButton();
        assertAll("Submoy Login with missing credentials",
                () -> Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                        "The Error Message for empty login is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.LOGIN_IS_NOT_PROVIDED,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error message for empty login is not correct."),
                () -> Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for empty password is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT),
                "The Error Message for empty password is not correct.")
        );
    }

    @Test
    public void testErrorMessagesForNotLoggedInEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, LoginCredentials.NOT_REGISTERED_EMAIL);
        assertAll("Login with not registered email",
                () -> Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for login with not logged in email value is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.LOGIN_WITH_NOT_REGISTERED_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error Message for login with not logged in email value is not correct.")
        );
    }

    @Test
    public void testErrorMessagesForInvalidEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, LoginCredentials.INVALID_EMAIL);
        assertAll("Login with invalid email",
                () -> Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                        "The Error Message for login with invalid email is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.LOGIN_WITH_INVALID_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error Message for login with invalid email is not correct.")
        );
    }

    @Test
    public void testErrorMessagesForInvalidPassword() {
        loginPage.putValueAndSubmit(loginPage.PASSWORD_FIELD, LoginCredentials.PASSWORD);
        assertAll("",
                () -> Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                        "The Error Message for invalid password is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.PASSWORD_IS_WRONG,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT),
                "The Error Message for invalid password is not correct.")
        );
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }
}
