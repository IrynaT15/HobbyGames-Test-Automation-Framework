package api;

import by.hobbygames.api.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

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

    @Test
    public void testLoginWithValidNotLoggedInEmail() {
        userAuthService.doRequest("irina.test@gmail.com", "Password123", "email");
        userAuthService.printResponse();

        assertAll("Login test",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Такой E-Mail адрес не зарегистрирован",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Неверный телефон/e-mail",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @Test
    public void testLoginWithValidNotLoggedInPhoneNumber() {
        userAuthService.doRequest("+375 44 760 3500", "Password123", "email");
        userAuthService.printResponse();

        assertAll("Login test",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Такой телефон не зарегистрирован",
                        userAuthService.getMessage("errors.phone")),
                () -> assertEquals("Неверный телефон/e-mail",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.email")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @ParameterizedTest
    @MethodSource("testdata.InvalidEmailsProvider#provideEmailsWithMissingPart")
    public void testLoginWithEmailsWithMissingPart(String email) {
        userAuthService.doRequest(email, "70Ef0HOP", "email");

        assertAll("Login test with emails with missing part",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Неверный формат Электронной почты",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Введённые данные некорректны",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @ParameterizedTest
    @MethodSource("testdata.InvalidEmailsProvider#provideEmailsWithIllegalStructure")
    public void testLoginWithEmailsWithIllegalStructure(String email) {
        userAuthService.doRequest(email, "70Ef0HOP", "email");

        assertAll("Login test with emails with illegal structure",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Неверный формат Электронной почты",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Введённые данные некорректны",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @ParameterizedTest
    @MethodSource("testdata.InvalidEmailsProvider#provideEmailsWithIllegalCharacters")
    public void testLoginWithEmailsWithIllegalCharacters(String email) {
        userAuthService.doRequest(email, "70Ef0HOP", "email");

        assertAll("Login test with emails with illegal characters",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Неверный формат Электронной почты",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Введённые данные некорректны",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @ParameterizedTest
    @MethodSource("testdata.InvalidEmailsProvider#provideEmailsWithMultipleAt")
    public void testLoginWithEmailsWithMultipleAt(String email) {
        userAuthService.doRequest(email, "70Ef0HOP", "email");

        assertAll("Login test with emails with multiple at",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Неверный формат Электронной почты",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Введённые данные некорректны",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }

    @ParameterizedTest
    @MethodSource("testdata.InvalidEmailsProvider#provideEmailsWithWhitespaces")
    public void testLoginWithEmailsWithWhitespaces(String email) {
        userAuthService.doRequest(email, "70Ef0HOP", "email");

        assertAll("Login test with emails with whitespaces",
                () -> assertEquals(200, userAuthService.getStatusCode()),
                () -> assertEquals("Неверный формат Электронной почты",
                        userAuthService.getMessage("errors.email")),
                () -> assertEquals("Введённые данные некорректны",
                        userAuthService.getMessage("errors.login")),
                () -> assertEquals("Неверный пароль",
                        userAuthService.getMessage("errors.password")),
                () -> assertNull(userAuthService.getMessage("errors.phone")),
                () -> assertEquals("false",
                        userAuthService.getMessage("success"))
        );
    }
}

