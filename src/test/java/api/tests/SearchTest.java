package api.tests;

import api.assertions.*;
import by.hobbygames.api.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        searchService = new SearchService();
    }

    @DisplayName("Search with empty search parameter")
    @Test
    public void testSearchWithEmptySearchParameter() {
        searchService.doRequest("");

        assertAll("Empty search parameter",
                () -> SearchAssertions.assertSearchResponseStatusCodeAndContentTypeAndH1Text(searchService),
                () -> assertTrue(searchService.getProductCardsCount() >= 1,
                        "No product cards in the list.")
        );
    }

    @DisplayName("Search with a space as search parameter")
    @Test
    public void testSearchWithSpaceAsSearchParameter() {
        searchService.doRequest(" ");

        assertAll("Space as search parameter",
                () -> SearchAssertions.assertSearchResponseStatusCodeAndContentTypeAndH1Text(searchService),
                () -> assertTrue(searchService.getProductCardsCount() >= 1,
                        "No product cards in the list.")
        );
    }

    @DisplayName("Search with search results:")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("testdata.search.ApiSearchParamenetersProvider#provideSearchParameters")
    public void testSearchWithSearchParametersForExistingItems(String searchParameter) {
        searchService.doRequest(searchParameter);

        assertAll("Search parameters for existing items",
                () -> SearchAssertions.assertSearchResponseStatusCodeAndContentTypeAndH1Text(searchService),
                () -> assertTrue(searchService.getProductCardsCount() >= 1,
                        "No product cards in the list.")
        );
    }

    @DisplayName("Search with no results")
    @Test
    public void testSearchWithNoResults() {
        searchService.doRequest("abracadabra");

        assertAll("Search parameters for not existing items",
                () -> SearchAssertions.assertSearchResponseStatusCodeAndContentTypeAndH1Text(searchService),
                () -> assertEquals("Ничего не найдено.", searchService.getResultsText()),
                () -> assertTrue(searchService.isProductCardsListEmpty(), "Product Cards list is not empty.")
        );
    }

    @DisplayName("Search Response Time for different parameters:")
    @ParameterizedTest
    @MethodSource("testdata.search.ApiSearchParamenetersProvider#provideParametersForSearchResponseTime")
    public void testSearchResponseTime(String searchParameter) {
        searchService.doRequest(searchParameter);
        long responseTime = searchService.getResponseTime();

        Assertions.assertTrue(responseTime <= 2000L,
                "Response time for \"" + searchParameter + "\" is " + responseTime);
    }
}