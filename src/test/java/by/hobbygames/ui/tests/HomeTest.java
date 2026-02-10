package by.hobbygames.ui.tests;

import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;

public class HomeTest {
    private HomePage homePage;

    @BeforeEach
    public void setupDriverAndOpenHomePageAndCloseCookieAlert() {
        homePage = new HomePage();
        homePage.open();
        homePage.clickCookieAlertClose();
    }

    @Test
    public void testLoginButtonIsDisplayedOnHomePage() {
        Assertions.assertTrue(homePage.isLoginButtonDisplayed(),
                "The Login button is not displayed on the Home Page.");
        Assertions.assertEquals(homePage.LOGIN_BUTTON_TITLE_TEXT, homePage.getLoginButtonTitle());
    }

    @Test
    public void testLoginPopupAppearsWhenLoginButtonIsClicked() {
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.isLoginPopupDisplayed());
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }
}
