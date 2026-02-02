package testdata.search;

public class SearchTestData {
    public static final String EXISTING_SEARCH_ITEM = "fallout";
    public static final String URL_FOR_EXISTING_SEARCH_RESULT =
            "https://hobbygames.by/catalog/search?keyword=" + EXISTING_SEARCH_ITEM;

    public static final String NOT_EXISTING_SEARCH_ITEM = "fakesearch";
    public static final String URL_FOR_NOT_EXISTING_SEARCH_RESULT =
            "https://hobbygames.by/catalog/search?keyword=" + NOT_EXISTING_SEARCH_ITEM;
}
