import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;

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
    public void testLoginPopupTitleIsDisplayedAndTextIsCorrect() {
        Assertions.assertTrue(loginPage.isLoginPopupTitleDisplayed(),
                "The Title is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.TITLE_TEXT, loginPage.getPageTitle(),
                "The [Title] text is not correct.");
    }

    @Test
    public void testCloseButtonIsDisplayedInLoginPopup() {
        Assertions.assertTrue(loginPage.isCloseButtonDisplayedInLoginPopup(),
                "The [Close] button is not displayed in the Login Popup.");
    }

    @Test
    public void testLoginPopupDisappearsWhenCloseButtonIsClicked() {
        loginPage.clickCloseButtonInLoginPopup();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed.");
    }

    @Test
    public void testSubmitButtonIsDisplayedAndTextIsCorrectInLoginPopup() {
        Assertions.assertTrue(loginPage.isSubmitButtonDisplayedInLoginPopup(),
                "The [Submit] button is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.SUBMIT_BUTTON_TEXT,
                loginPage.getSubmitButtonTitle(),
                "[Submit] button text is not correct.");
    }

    @Test
    public void testLoginFieldIsDisplayedAndPlaceholderTextIsCorrectInLoginPopup() {
        Assertions.assertTrue(loginPage.isLoginFieldDisplayedInLoginPopup(),
                "The Login field is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.LOGIN_FIELD_PLACEHOLDER,
                loginPage.getLoginFieldPlaceholderText(),
                "Login field placeholder is not displayed.");
    }

    @Test
    public void testPasswordFieldIsDisplayedAndPlaceholderTextIsCorrectInLoginPopup() {
        Assertions.assertTrue(loginPage.isPasswordFieldDisplayedInLoginPopup(),
                "The [Password] field is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.PASSWORD_FIELD_PLACEHOLDER,
                loginPage.getPasswordFieldPlaceholderText(),
                "Password field placeholder is not displayed.");
    }

    @Test
    public void testForgotModalLinkIsDisplayedAndTextIsCorrectInLoginPopup() {
        Assertions.assertTrue(loginPage.isForgotModalLinkDisplayedInLoginPopup(),
                "The Forgot Modal link is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.FORGOT_LINK_TEXT,
                loginPage.getForgotModalLinkText(),
                "The Forgot link test is not correct.");
    }

    @Test
    public void testLoginPopupDisappearsAndForgotPopupAppearsWhenForgotModalLinkIsClicked() {
        loginPage.clickForgotModalLinkInLoginPopup();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed.");
        Assertions.assertTrue(loginPage.isForgotPopupDisplayed(),
                "Forgot popup is not displayed.");
    }

    @Test
    public void testRegisterModalLinkIsDisplayedAndTextIsCorrectInLoginPopup() {
        Assertions.assertTrue(loginPage.isRegisterModalLinkDisplayedInLoginPopup(),
                "The Register Modal link is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.REGISTER_LINK_TEXT,
                loginPage.getRegisterModalLinkText(),
                "Register link text is not correct.");
    }

    @Test
    public void testLoginPopupDisappearsAndRegisterPopupAppearsWhenRegisterModalLinkIsClicked() {
        loginPage.clickRegisterModalLinkInLoginPopup();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed.");
        Assertions.assertTrue(loginPage.isRegisterPopupDisplayed(),
                "Register popup is not displayed.");
    }

    @Test
    public void testErrorMessagesForLoginWithEmptyLoginAndPassword() {
        loginPage.clickSubmitButtonInLoginPopup();
        Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for empty login is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_EMPTY_LOGIN,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error message for empty login is not correct.");

        Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for empty password is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_EMPTY_PASSWORD,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT),
                "The Error Message for empty password is not correct.");
    }

    @Test
    public void testErrorMessagesForNotLoggedInEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, loginPage.NOT_LOGGED_IN_EMAIL);

        Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for login with not logged in email value is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_NOT_LOGGED_IN_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error Message for login with not logged in email value is not correct.");
    }

    @Test
    public void testErrorMessagesForInvalidEmail() {
        loginPage.putValueAndSubmit(loginPage.LOGIN_FIELD, loginPage.INVALID_EMAIL);

        Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for login with invalid email is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_INVALID_EMAIL,
                loginPage.getErrorMessage(loginPage.ERROR_LOGIN_INPUT),
                "The Error Message for login with invalid email is not correct.");
    }

    @Test
    public void testErrorMessagesForInvalidPassword() {
        loginPage.putValueAndSubmit(loginPage.PASSWORD_FIELD, loginPage.INVALID_PASSWORD);

        Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for invalid password is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_INVALID_PASSWORD,
                loginPage.getErrorMessage(loginPage.ERROR_PASSWORD_INPUT),
                "The Error Message for invalid password is not correct.");
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }
}
