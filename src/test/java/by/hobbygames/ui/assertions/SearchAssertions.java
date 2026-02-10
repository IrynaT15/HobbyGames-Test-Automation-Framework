package by.hobbygames.ui.assertions;

import by.hobbygames.pages.*;

import java.net.*;
import java.nio.charset.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAssertions {
    public static void assertUrlAndPageTitle(SearchPage searchPage, String expectedUrl) {
        assertAll("Search results page URL, Page Title",
                () -> assertEquals(expectedUrl, URLDecoder.decode(searchPage.getCurrentUrl(), StandardCharsets.UTF_8)),
                () -> assertEquals("Результаты поиска", searchPage.getElementText(searchPage.PAGE_TITLE))
        );
    }

    public static void assertUrlAndPageTitleAndNotEmptyProductsList(SearchPage searchPage, String expectedUrl) {
        assertAll("Search results page URL, Page Title, Not Empty products list",
                () -> assertUrlAndPageTitle(searchPage, expectedUrl),
                () -> assertFalse(searchPage.getProductCards().isEmpty(),
                        "Search results list is empty")
        );
    }

    public static void assertUrlAndPageTitleAndZeroItemsAndNoResultsText(SearchPage searchPage, String expectedUrl) {
        assertAll("Search results page URL, Page Title, Zero items, Content Text",
                () -> SearchAssertions.assertUrlAndPageTitle(searchPage, expectedUrl),
                () -> assertEquals(
                        "0", searchPage.getElementText(searchPage.NUMBER_OF_FOUND_ITEMS_TEXT)),
                () -> assertEquals(
                        "Ничего не найдено.", searchPage.getElementText(searchPage.PRODUCT_CONTENT_NO_RESULT))
        );
    }
}
