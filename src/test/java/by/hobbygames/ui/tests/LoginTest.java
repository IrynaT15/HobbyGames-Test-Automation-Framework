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

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }

    @DisplayName("UI elements of the Login popup")
    @Test
    public void testLoginPopupContainsAllRequiredElements() {
        assertAll("Login popup UI",
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.TITLE, "Title"),
                        "The Title is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.LOGIN_FIELD, "Login Field"),
                        "The Login Field is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.PASSWORD_FIELD, "Password Field"),
                        "The Password fField is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.SUBMIT_BUTTON, "Submit Button"),
                        "The Submit Button is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.FORGOT_LINK, "Forgot Link"),
                        "The Forgot Modal link is not displayed in the Login Popup."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.REGISTER_LINK, "Register Link"),
                        "The Register Modal link is not displayed in the Login Popup.")
        );
    }

    @DisplayName("Closing Login Popup when Close Button is clicked")
    @Test
    public void testLoginPopupDisappearsWhenCloseButtonIsClicked() {
        loginPage.clickElement(loginPage.CLOSE_BUTTON, "Close Button");
        assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is not closed when Close Button is clicked.");
    }

    @DisplayName("Navigation from Login Popup to Forgot Popup when Forgot Link is clicked")
    @Test
    public void testLoginPopupDisappearsAndForgotPopupAppearsWhenForgotModalLinkIsClicked() {
        loginPage.clickElement(loginPage.FORGOT_LINK, "Forgot Link");
        assertAll("Navigation from Login popup to Forgot popup",
                () -> assertFalse(homePage.isLoginPopupDisplayed(), "The Login Popup is displayed"),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.FORGOT_POPUP, "Forgot Popup"),
                        "The Forgot Popup is not displayed.")
        );
    }

    @DisplayName("Navigation from Login Popup to Register Popup when Register Link is clicked")
    @Test
    public void testLoginPopupDisappearsAndRegisterPopupAppearsWhenRegisterModalLinkIsClicked() {
        loginPage.clickElement(loginPage.REGISTER_LINK, "Register Link");
        assertAll("Navigation from Login Popup to Register popup",
                () -> assertFalse(homePage.isLoginPopupDisplayed(),"The Login Popup is displayed."),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.REGISTER_POPUP, "Register Popup"),
                        "The Register Popup is not displayed.")
        );
    }

    @DisplayName("Error Messages for attempt to authorise with empty fields")
    @Test
    public void testErrorMessagesForLoginWithEmptyLoginAndPassword() {
        loginPage.clickElement(loginPage.SUBMIT_BUTTON, "Submit Button");
        assertAll("Submit Login with empty fields",
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.REGISTER_POPUP,
                                "Login Error Message"),
                        "The Error Message for empty login is not displayed."),
                () -> assertEquals(UiLoginErrors.LOGIN_IS_NOT_PROVIDED,
                        loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT)),
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.ERROR_PASSWORD_INPUT,
                                "Password Error Message"),
                        "The Error Message for empty password is not displayed."),
                () -> assertEquals(UiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT))
        );
    }

    @DisplayName("Error Message: logging with not registered email")
    @Test
    public void testErrorMessagesForNotRegisteredEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, TestLoginData.generateUnregisteredEmail());
        assertAll("Login with not registered email",
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.ERROR_LOGIN_INPUT, "Login Error Message"),
                "The Error Message for login with not logged in email value is not displayed."),
                () -> assertEquals(UiLoginErrors.LOGIN_WITH_NOT_REGISTERED_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT))
        );
    }

    @DisplayName("Error Message: logging with invalid email")
    @Test
    public void testErrorMessagesForInvalidEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, TestLoginData.generateInvalidEmail());
        assertAll("Login with invalid email",
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.ERROR_LOGIN_INPUT, "Error Message"),
                        "The Error Message for login with invalid email is not displayed."),
                () -> assertEquals(UiLoginErrors.LOGIN_WITH_INVALID_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT))
        );
    }

    @DisplayName("Error Message: logging with invalid password")
    @Test
    public void testErrorMessagesForInvalidPassword() {
        loginPage.putValueAndSubmit(loginPage.PASSWORD_FIELD, TestLoginData.PASSWORD);
        assertAll("",
                () -> assertTrue(loginPage.isElementDisplayed(loginPage.ERROR_PASSWORD_INPUT, "Error Message"),
                        "The Error Message for invalid password is not displayed."),
                () -> assertEquals(UiLoginErrors.PASSWORD_IS_WRONG,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT))
        );
    }
}
