package by.hobbygames.testdata.credentials;

import com.github.javafaker.*;

public class LoginCredentials {
    public static final String REGISTERED_EMAIL = "it.19012026@gmail.com";
    public static final String PASSWORD = "Password123";

    private static final Faker faker = new Faker();

    public static String generateNotRegisteredEmail() {
        return "autotest_" + faker.number().digits(8) + "@example.com";
    }

    public static String generateInvalidEmail() {
        return "autotest..." + faker.number().digits(8) + "@example.com";
    }

    public static String generateNotRegisteredPhoneNumber() {
        return "+37599" + faker.number().digits(7);
    }

    public static String generateShortPhoneNumber() {
        return "29" + faker.number().digits(5);
    }

    public static String generateLongPhoneNumber() {
        return "+37529" + faker.number().digits(8);
    }
}
