package by.hobbygames.testdata.credentials;

import java.util.stream.*;

public class InvalidEmailsProvider {
    public static Stream<String> provideInvalidEmails() {
        return Stream.of(
                "it.19012026gmail.com",
                "it..19012026@gmail.com",
                "it.19012026 @gmail.com"
        );
    }

    public static Stream<String> provideEmailsWithMissingPart() {
        return Stream.of(
                "@gmail.com",
                "it.19012026@",
                "it.19012026@gmail"
        );
    }

    public static Stream<String> provideEmailsWithIllegalStructure() {
        return Stream.of(
                "it.19012026@gmail com",
                "it.19012026@gmail..com",
                "it.19012026.@gmail.com"
        );
    }

    public static Stream<String> provideEmailsWithIllegalCharacters() {
        return Stream.of(
                "it.19012026@gmail,com",
                "it.19012026@-gmail.com",
                "it.19012026@gmail-.com"
        );
    }

    public static Stream<String> provideEmailsWithMultipleAt() {
        return Stream.of(
                "it.19012026@@gmail.com",
                "it.190@12026@gmail.com"
        );
    }

    public static Stream<String> provideEmailsWithWhitespaces() {
        return Stream.of(
                "it.19012026 @gmail.com",
                "it.19012026@ gmail.com"
        );
    }
}
