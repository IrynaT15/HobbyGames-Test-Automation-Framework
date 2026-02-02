import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SearchTest {
    private HomePage homePage;
    private SearchPage searchPage;

    @BeforeEach
    public void setupDriverAndOpenHomePageAndCloseCookieAlertAndClickLoginButton() {
        homePage = new HomePage();
        homePage.open();
        homePage.clickCookieAlertClose();
        homePage.clickLoginButton();
        searchPage = new SearchPage();
    }

    @AfterEach
    public void quitDriver() {
        Driver.quit();
    }

    @Test
    public void testSearchFieldAndSearchButtonArePresentOnPage() {
        assertAll("Search field and Search button on the page",
                () -> Assertions.assertTrue(searchPage.isSearchFieldDisplayed(),
                        "The Search field is not displayed on the page"),
                () -> Assertions.assertTrue(searchPage.isSearchButtonDisplayed(),
                        "The Search button is not displayed on the page")
        );
    }
}
