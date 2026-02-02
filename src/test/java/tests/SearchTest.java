package tests;

import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import testdata.keywords.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    @Nested
    public class SearchTestsWithoutSetup {
        private SearchPage searchPage;

        @BeforeEach
        public void start() {
            searchPage = new SearchPage();
        }

        @AfterAll
        public static void quitDriver() {
            Driver.quit();
        }

        @ParameterizedTest
        @MethodSource("testdata.urls.PagesUrlProvider#providePagesUrl")
        public void testSearchFieldAndSearchButtonArePresentOnDifferentPages(String page) {
            searchPage.open(page);
            assertAll("Search field and Search button on the page",
                    () -> assertTrue(searchPage.isSearchFieldDisplayed(),
                            "The Search field is not displayed on the page"),
                    () -> assertTrue(searchPage.isSearchButtonDisplayed(),
                            "The Search button is not displayed on the page")
            );
        }

    }

    @Nested
    public class SearchTestsWithSetup {
        private HomePage homePage;
        private SearchPage searchPage;

        @BeforeEach
        public void setupDriverAndOpenHomePageAndCloseCookieAlertAndClickLoginButton() {
            homePage = new HomePage();
            searchPage = new SearchPage();
            homePage.open();
            homePage.clickCookieAlertClose();
        }

        @AfterEach
        public void quitDriver() {
            Driver.quit();
        }

        @Test
        public void testSearchWithoutKeyword() {
            searchPage.clickSearchButton();
            assertAll("Search result for search without keyword",
                    () -> assertEquals(searchPage.SEARCH_RESULTS_PAGE_URL, searchPage.getCurrentUrl()),
                    () -> assertEquals(searchPage.SEARCH_RESULTS_PAGE_TITLE, searchPage.getPageTitleText()),
                    () -> assertTrue(searchPage.getNumberOfFoundItemsText().contains("товаров"))
            );
        }

        @Test
        public void testSmartSearchPopupAppearsWhenKeywordIsEntered() {
            searchPage.putKeywordInSearchField(Search.VALID_SEARCH);
            assertTrue(searchPage.isSmartSearchPopupDisplayed(),
                    "The Smart Search popup is not displayed");
        }
    }
}
