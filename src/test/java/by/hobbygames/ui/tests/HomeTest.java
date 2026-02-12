package by.hobbygames.ui.tests;

import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class HomeTest {
    private HomePage homePage;

    @BeforeEach
    public void setupDriverAndOpenHomePageAndCloseCookieAlert() {
        homePage = new HomePage();
        homePage.open();
        homePage.acceptCookie();
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }

    @DisplayName("Login Button presence on the Home Page")
    @Test
    public void testLoginButtonIsDisplayedOnHomePage() {
        assertAll("Login Button presence, button text",
                () -> assertTrue(homePage.isLoginButtonDisplayed(),
                        "The Login button is not displayed on the Home Page."),
                () -> assertEquals(homePage.LOGIN_BUTTON_TITLE_TEXT, homePage.getLoginButtonText())
        );
    }

    @DisplayName("Login popup appearance when Login Button is clicked")
    @Test
    public void testLoginPopupAppearsWhenLoginButtonIsClicked() {
        homePage.clickLoginButton();
        assertTrue(homePage.isLoginPopupDisplayed());
    }
}
