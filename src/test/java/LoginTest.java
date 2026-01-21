import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import by.hobbygames.utils.*;
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
        Waits.waitFor(30);
        loginPage = new LoginPage();
    }

    @Test
    public void testLoginPopupTitleIsDisplayedAndTextIsCorrect() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isLoginPopupTitleDisplayed(),
                "The title is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.TITLE_TEXT, loginPage.getLoginPopupTitle());
    }

    @Test
    public void testCloseButtonIsDisplayedInLoginPopup() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isCloseButtonDisplayedInLoginPopup(),
                "The Close button is not displayed in the Login Popup.");
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
                "The Submit button is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.SUBMIT_BUTTON_TEXT,
                loginPage.getLoginPopupSubmitButtonTitle());
    }

    @Test
    public void testLoginFieldIsDisplayedAndPlaceholderTextIsCorrectInLoginPopup() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isLoginFieldDisplayedInLoginPopup(),
                "The Login field is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.LOGIN_FIELD_PLACEHOLDER,
                loginPage.getLoginPopupLoginFieldPlaceholderText());
    }

    @Test
    public void testPasswordFieldIsDisplayedAndPlaceholderTextIsCorrectInLoginPopup() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isPasswordFieldDisplayedInLoginPopup(),
                "The Password field is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.PASSWORD_FIELD_PLACEHOLDER,
                loginPage.getLoginPopupPasswordFieldPlaceholderText());
    }

    @Test
    public void testForgotModalLinkIsDisplayedAndTextIsCorrectInLoginPopup() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isForgotModalLinkDisplayedInLoginPopup(),
                "The Forgot Modal link is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.FORGOT_LINK_TEXT,
                loginPage.getLoginPopupForgotModalLinkText());
    }

    @Test
    public void testLoginPopupDisappearsAndForgotPopupAppearsWhenForgotModalLinkIsClicked() {
        loginPage.clickForgotModalLinkInLoginPopup();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed.");
        Waits.waitFor(50);
        Assertions.assertTrue(loginPage.isForgotPopupDisplayed());
    }

    @Test
    public void testRegisterModalLinkIsDisplayedAndTextIsCorrectInLoginPopup() {
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isRegisterModalLinkDisplayedInLoginPopup(),
                "The Register Modal link is not displayed in the Login Popup.");
        Assertions.assertEquals(loginPage.REGISTER_LINK_TEXT,
                loginPage.getLoginPopupRegisterModalLinkText());
    }

    @Test
    public void testLoginPopupDisappearsAndRegisterPopupAppearsWhenRegisterModalLinkIsClicked() {
        loginPage.clickRegisterModalLinkInLoginPopup();
        Assertions.assertFalse(homePage.isLoginPopupDisplayed(),
                "The Login Popup is displayed.");
        Waits.waitFor(50);
        Assertions.assertTrue(loginPage.isRegisterPopupDisplayed());
    }

    @Test
    public void tesGlobalLoaderAppearsWhenEnterButtonIsClicked() {
        Waits.waitFor(20);
        loginPage.clickSubmitButtonInLoginPopup();
        Waits.waitFor(20);
        Assertions.assertTrue(loginPage.isGlobalLoaderDisplayed());
    }

    @Test
    public void testErrorMessagesForLoginWithEmptyLoginAndPassword() {
        loginPage.clickSubmitButtonInLoginPopup();
        Waits.waitFor(10);

        Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for empty login is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_EMPTY_LOGIN, loginPage.getErrorTextForLoginField());

        Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for empty password is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_EMPTY_PASSWORD, loginPage.getErrorTextForPasswordField());
    }

    @Test
    public void testErrorMessagesForNotLoggedInEmailAndEmptyPassword() {
        Waits.waitFor(20);
        loginPage.putNotLoggedInEmailToLoginField();
        loginPage.clickSubmitButtonInLoginPopup();
        Waits.waitFor(100);
        System.out.println(loginPage.getErrorTextForLoginField());

        Assertions.assertTrue(loginPage.isErrorForIncorrectLoginInputDisplayed(),
                "The Error Message for login with not logged in email value is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_NOT_LOGGED_IN_EMAIL, loginPage.getErrorTextForLoginField());

        Assertions.assertTrue(loginPage.isErrorForIncorrectPasswordInputDisplayed(),
                "The Error Message for empty password is not displayed.");
        Assertions.assertEquals(loginPage.ERROR_TEXT_FOR_EMPTY_PASSWORD, loginPage.getErrorTextForPasswordField());
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }
}
