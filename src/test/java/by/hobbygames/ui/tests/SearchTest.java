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

        @DisplayName("Search field and button should be displayed on each page")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.urls.PagesUrlProvider#providePagesUrl")
        public void testSearchFieldAndButtonAreDisplayedOnEachPage(String page) {
            searchPage.open(page);
            assertAll("Search elements visibility check",
                    () -> assertTrue(searchPage.isElementDisplayed(searchPage.SEARCH_FIELD, "Search Field"),
                            "The Search Field is not displayed on page: " + page),
                    () -> assertTrue(searchPage.isElementDisplayed(searchPage.SEARCH_BUTTON, "Search Button"),
                            "The Search Button is not displayed on page: " + page)
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
            homePage.acceptCookie();
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

        @DisplayName("Smart Search popup appears when entering search parameter")
        @Test
        public void testSmartSearchPopupAppearsWhenSearchParameterIsEntered() {
            searchPage.putSearchParameter(UiSearchTestData.EXISTING_SEARCH_ITEM);
            assertTrue(searchPage.isElementDisplayed(searchPage.SMART_SEARCH_POPUP, "Smart Search Popup"),
                    "Smart Search popup is not displayed");
        }

        @DisplayName("SSearch results for existing items are displayed correctly")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.search.UiSearchTestData#provideParameterForExistingItems")
        public void testSearchResultForExistingItems(String searchParameter) {
            searchPage.putSearchParameterAndClickSearchButton(searchParameter);
            SearchAssertions.assertUrlAndPageTitleAndNotEmptyProductsList(searchPage,
                    searchPage.RESULTS_URL + searchParameter);
        }

        @DisplayName("Total count of found items should match displayed count")
        @ParameterizedTest
        @MethodSource("by.hobbygames.testdata.search.UiSearchTestData#provideParameterForExistingItems")
        public void testTotalCountMatches(String searchParameter) {
            searchPage.putSearchParameterAndClickSearchButton(searchParameter);
            assertEquals(
                    searchPage.getNumberOfFoundItemsInAllPages(),
                    searchPage.getNumberOfFoundItemsFromText(),
                    "Total number of found items does not match the displayed count");
        }

        @DisplayName("Search results page shows no items for non-existing search")
        @Test
        public void testSearchResultForNonExistingItems() {
            searchPage.putSearchParameterAndClickSearchButton(UiSearchTestData.NOT_EXISTING_ITEM);
            SearchAssertions.assertUrlAndPageTitleAndZeroItemsAndNoResultsText(searchPage,
                    searchPage.RESULTS_URL + UiSearchTestData.NOT_EXISTING_ITEM);
        }
    }
}
