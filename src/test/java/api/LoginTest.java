package api;

import by.hobbygames.api.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    UserAuthService userAuthService;

    @BeforeEach
    public void setUp() {
        userAuthService = new UserAuthService();
    }

    @Test
    public void testLoginWithEmptyLoginAndEmptyPassword() {
        userAuthService.doRequest("", "", "email");
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
    public void testLoginWithEmptyLoginAndProvidedPassword() {
        userAuthService.doRequest("", "Password123", "email");
        userAuthService.printResponse();

        assertAll("Login test",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Данные не введены",
                        userAuthService.getMessage("errors.phone")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertEquals("Введите телефон или электронную почту",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @Test
    public void testLoginWithEmptyPasswordAndProvidedEmail() {
        userAuthService.doRequest("it.19012026@gmail.com", "", "email");
        userAuthService.printResponse();

        assertAll("Login test",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Введите пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertNull(userAuthService.getMessage("errors.login")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }
}

