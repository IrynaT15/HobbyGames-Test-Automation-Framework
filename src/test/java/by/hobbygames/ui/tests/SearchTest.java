package by.hobbygames.ui.tests;

import by.hobbygames.testdata.search.*;
import by.hobbygames.ui.assertions.*;
import by.hobbygames.driver.*;
import by.hobbygames.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

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

        @DisplayName("Search field and button on different pages")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.urls.PagesUrlProvider#providePagesUrl")
        public void testSearchFieldAndSearchButtonArePresentOnDifferentPages(String page) {
            searchPage.open(page);
            assertAll("Search field and Search button on the page",
                    () -> assertTrue(searchPage.isElementDisplayed(searchPage.SEARCH_FIELD),
                            "The Search field is not displayed on the page"),
                    () -> assertTrue(searchPage.isElementDisplayed(searchPage.SEARCH_BUTTON),
                            "The Search button is not displayed on the page")
            );
        }
    }

    @Nested
    public class SearchTestsWithSetup {
        private HomePage homePage;
        private SearchPage searchPage;

        @BeforeEach
        public void setupDriverAndOpenHomePageAndCloseCookieAlert() {
            homePage = new HomePage();
            searchPage = new SearchPage();
            homePage.open();
            homePage.clickCookieAlertClose();
        }

        @AfterEach
        public void quitDriver() {
            Driver.quit();
        }

        @DisplayName("Search with empty search parameter")
        @Test
        public void testSearchWithEmptySearchParameter() {
            searchPage.clickSearchButton();
            SearchAssertions.assertUrlAndPageTitleAndNotEmptyProductsList(searchPage, searchPage.BASE_URL);
        }

        @DisplayName("Smart Search popup appears when search parameter is entered")
        @Test
        public void testSmartSearchPopupAppearsWhenSearchParameterIsEntered() {
            searchPage.putSearchParameter(UiSearchTestData.EXISTING_SEARCH_ITEM);
            assertTrue(searchPage.isElementDisplayed(searchPage.SMART_SEARCH_POPUP),
                    "The Smart Search popup is not displayed");
        }

        @DisplayName("Search results for existing items")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.search.UiSearchTestData#provideParameterForExistingItems")
        public void testSearchResultPageWithItems(String searchParameter) {
            searchPage.putSearchParameterAndClickSearchButton(searchParameter);
            SearchAssertions.assertUrlAndPageTitleAndNotEmptyProductsList(searchPage,
                    searchPage.RESULTS_URL + searchParameter);
        }

        @DisplayName("Total count of found items")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.search.UiSearchTestData#provideParameterForExistingItems")
        public void testCountOfItemsInSearchResult(String searchParameter) {
            searchPage.putSearchParameterAndClickSearchButton(searchParameter);
            Assertions.assertEquals(
                    searchPage.getNumberOfFoundItemsInAllPages(),
                    searchPage.getNumberOfFoundItemsFromText());
        }

        @DisplayName("Search results for not existing items")
        @Test
        public void testSearchForNotExistingItems() {
            searchPage.putSearchParameterAndClickSearchButton(UiSearchTestData.NOT_EXISTING_ITEM);
            SearchAssertions.assertUrlAndPageTitleAndZeroItemsAndNoResultsText(searchPage,
                    searchPage.RESULTS_URL + UiSearchTestData.NOT_EXISTING_ITEM);
        }
    }
}
