package by.hobbygames.testdata.search.urls;

import java.util.stream.*;

public class PagesUrlProvider {


    public static Stream<String> providePagesUrl() {
        return Stream.of(
                "https://hobbygames.by/",
                "https://hobbygames.by/nastolnie-igri",
                "https://hobbygames.by/zakaz",
                "https://hobbygames.by/account/favorites",
                "https://hobbygames.by/zvjozdnie-imperii-nasledie"
        );
    }
}
