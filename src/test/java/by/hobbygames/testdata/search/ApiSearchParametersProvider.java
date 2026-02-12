package by.hobbygames.testdata.search;

import java.util.stream.*;

public class ApiSearchParametersProvider {
    public static final String NOT_EXISTING_ITEM = "NotExistingItem";
    public static Stream<String> provideSearchParameters() {
        return Stream.of(
                "Zombie",
                "Зомби",
                "Zombie!",
                "Зомби:",
                "Zombie &",
                "Зомби ?",
                "Зомби-котята",
                "игра про зомби",
                "настолка зомби дети",
                "   Zombie",
                "Зомби   ",
                "Зо м би"
        );
    }

    public static Stream<String> provideParametersForSearchResponseTime() {
        return Stream.of(
                "Zombie",
                "Зомби-котята",
                "настолка зомби дети",
                "",
                " ",
                "NotExistingItems"
        );
    }
}
