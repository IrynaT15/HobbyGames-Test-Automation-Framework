package by.hobbygames.testdata.search;

import java.util.stream.*;

public class UiSearchTestData {
    public static final String EXISTING_SEARCH_ITEM = "fallout";
    public static final String NOT_EXISTING_ITEM = "NotExistingItem";

    public static Stream<String> provideParameterForExistingItems() {
        return Stream.of(
                "Fallout",
                "манчкин",
                "игры для детей",
                "Tom & Jerry",
                "1000",
                "пазл   ",
                "тетрис!"
        );
    }
}
