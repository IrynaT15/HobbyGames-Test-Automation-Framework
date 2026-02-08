package api.assertions;

import by.hobbygames.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAssertions {
    public static void assertFailedLogin(
            UserAuthService userAuthService,
            String phoneError,
            String emailError,
            String passwordError,
            String loginError
    ) {
        assertAll(
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals(phoneError, userAuthService.getMessage("errors.phone")),
                () -> assertEquals(emailError, userAuthService.getMessage("errors.email")),
                () -> assertEquals(passwordError, userAuthService.getMessage("errors.password")),
                () -> assertEquals(loginError, userAuthService.getMessage("errors.login")),
                () -> assertFalse(userAuthService.isSuccess())
        );
    }
}
