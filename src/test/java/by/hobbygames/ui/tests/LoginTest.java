package by.hobbygames.ui.tests;

import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import by.hobbygames.testdata.credentials.*;
import by.hobbygames.testdata.errors.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("UI elements of the Login popup")
    @Test
    public void testLoginPopupContainsAllRequiredElements() {
        assertAll("Login popup UI",
                () -> assertTrue(loginPage.isLoginPopupTitleDisplayed(),
                        "The Title is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isLoginFieldDisplayedInLoginPopup(),
                        "The Login field is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isPasswordFieldDisplayedInLoginPopup(),
                        "The [Password] field is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isSubmitButtonDisplayedInLoginPopup(),
                        "The [Submit] button is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isForgotModalLinkDisplayedInLoginPopup(),
                        "The Forgot Modal link is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isRegisterModalLinkDisplayedInLoginPopup(),
                        "The Register Modal link is not displayed in the Login Popup.")
        );
    }

    @DisplayName("Close Login popup with [X]] button")
    @Test
    public void testLoginPopupDisappearsWhenCloseButtonIsClicked() {
        loginPage.clickCloseButton();
        assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed after [Close] button is clicked.");
    }

    @DisplayName("Navigate from Login popup to Forgot popup with [Не помните?] link")
    @Test
    public void testLoginPopupDisappearsAndForgotPopupAppearsWhenForgotModalLinkIsClicked() {
        loginPage.clickForgotModalLink();
        assertAll("Navigate from Login popup to Forgot popup",
                () -> assertFalse(homePage.isLoginPopupDisplayed(),
                        "The Login Popup is displayed."),
                () -> assertTrue(loginPage.isForgotPopupDisplayed(),
                "Forgot popup is not displayed.")
        );
    }

    @DisplayName("Navigate from Login popup to Register popup with [Регистрация нового пользователя] link")
    @Test
    public void testLoginPopupDisappearsAndRegisterPopupAppearsWhenRegisterModalLinkIsClicked() {
        loginPage.clickRegisterModalLink();
        assertAll("Navigate from Login popup to Register popup",
                () -> assertFalse(homePage.isLoginPopupDisplayed(),
                        "The Login Popup is displayed."),
                () -> assertTrue(loginPage.isRegisterPopupDisplayed(),
                "Register popup is not displayed.")
        );
    }

    @DisplayName("Error Message: logging with empty fields")
    @Test
    public void testErrorMessagesForLoginWithEmptyLoginAndPassword() {
        loginPage.clickSubmitButton();
        assertAll("Submit Login with empty fields",
                () -> assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                        "The Error Message for empty login is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.LOGIN_IS_NOT_PROVIDED,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT)),
                () -> assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for empty password is not displayed."),
                () -> Assertions.assertEquals(UiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT))
        );
    }

    @DisplayName("Error Message: logging with not registered email")
    @Test
    public void testErrorMessagesForNotRegisteredEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, LoginCredentials.generateNotRegisteredEmail());
        assertAll("Login with not registered email",
                () -> assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for login with not logged in email value is not displayed."),
                () -> assertEquals(UiLoginErrors.LOGIN_WITH_NOT_REGISTERED_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT))
        );
    }

    @DisplayName("Error Message: logging with invalid email")
    @Test
    public void testErrorMessagesForInvalidEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, LoginCredentials.generateInvalidEmail());
        assertAll("Login with invalid email",
                () -> assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                        "The Error Message for login with invalid email is not displayed."),
                () -> assertEquals(UiLoginErrors.LOGIN_WITH_INVALID_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT))
        );
    }

    @DisplayName("Error Message: logging with invalid password")
    @Test
    public void testErrorMessagesForInvalidPassword() {
        loginPage.putValueAndSubmit(loginPage.PASSWORD_FIELD, LoginCredentials.PASSWORD);
        assertAll("",
                () -> assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                        "The Error Message for invalid password is not displayed."),
                () -> assertEquals(UiLoginErrors.PASSWORD_IS_WRONG,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT))
        );
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }
}
