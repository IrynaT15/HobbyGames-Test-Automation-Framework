package by.hobbygames.api.assertions;

import by.hobbygames.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAssertions {
    public static void assertSearchResponseStatusCodeAndContentTypeAndH1Text(SearchService searchService) {
        assertAll("Search Response StatusCode, ContentType, H1 Title",
                () -> assertEquals(200, searchService.getStatusCode()),
                () -> assertEquals("text/html; charset=UTF-8", searchService.getContentType()),
                () -> assertEquals("Результаты поиска", searchService.getH1Text())
        );
    }
}
