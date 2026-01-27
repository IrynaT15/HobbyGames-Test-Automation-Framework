package api;

import by.hobbygames.api.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    public void testLoginWithEmptyLoginAndPassword() {
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.doRequest("dsgsvhc", "skdjnksvn", "email");
        userAuthService.printResponse();

        assertAll("Login test",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Данные не введены",
                        userAuthService.getMessage("errors.phone")),
                () -> assertEquals("Введите пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertEquals("Введите телефон или электронную почту",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @Test
    public void testLoginMessages() {
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.doRequest("", "", "email");
        userAuthService.printResponse();

        assertEquals("Данные не введены", userAuthService.getMessage("errors.phone"));
        assertEquals("Введите пароль", userAuthService.getMessage("errors.password"));
        assertEquals("Введите телефон или электронную почту", userAuthService.getMessage("errors.login"));
        assertEquals("false", userAuthService.getMessage("success"));
    }
}
