package api;

import by.hobbygames.api.*;
import org.apache.commons.logging.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final Log log = LogFactory.getLog(LoginTest.class);
    UserAuthService userAuthService;
    LoginAssertions loginAssertions;

    @BeforeEach
    public void setUp() {
        userAuthService = new UserAuthService();
        loginAssertions = new LoginAssertions();
    }

    @Test
    public void testLoginWithMissingCredentials() {
        userAuthService.doRequest("", "", "email");
        loginAssertions.assertResponseForFailedLoginWithPhoneAndPasswordAndLogin(
                userAuthService,
                "Данные не введены",
                "Введите пароль",
                "Введите телефон или электронную почту"
        );
    }

    @Test
    public void testLoginWithMissingLogin() {
        userAuthService.doRequest("", "Password123", "email");
        loginAssertions.assertResponseForFailedLoginWithPhoneAndPasswordAndLogin(
                userAuthService,
                "Данные не введены",
                "Неверный пароль",
                "Введите телефон или электронную почту"
        );
    }

    @Test
    public void testLoginWithTooShortPhoneNumber() {
        userAuthService.doRequest("4476522", "Password123", "email");
        loginAssertions.assertResponseForFailedLoginWithPhoneAndPasswordAndLogin(
                userAuthService,
                "Слишком мало символов",
                "Неверный пароль",
                "Введённые данные некорректны"
        );
    }

    @Test
    public void testLoginWithTooLongPhoneNumber() {
        userAuthService.doRequest("+%2B3754476766554", "Password123", "email");
        loginAssertions.assertResponseForFailedLoginWithPhoneAndPasswordAndLogin(
                userAuthService,
                "Слишком много символов",
                "Неверный пароль",
                "Введённые данные некорректны"
        );
    }

    @Test
    public void testLoginWithNotRegisteredPhoneNumber() {
        userAuthService.doRequest("%2B375447676655", "Password123", "email");
        loginAssertions.assertResponseForFailedLoginWithPhoneAndPasswordAndLogin(
                userAuthService,
                "Такой телефон не зарегистрирован",
                "Неверный пароль",
                "Неверный телефон/e-mail"
        );
    }

    @Test
    public void testLoginWithMissingPassword() {
        userAuthService.doRequest("it.19012026@gmail.com", "", "email");
        loginAssertions.assertResponseForFailedLoginWithPassword(
                userAuthService,
                "Введите пароль"
        );
    }

    @Test
    public void testLoginWithWrongPassword() {
        userAuthService.doRequest("it.19012026@gmail.com", "Password123!", "email");
        loginAssertions.assertResponseForFailedLoginWithPassword(
                userAuthService,
                "Неверный пароль"
        );
    }

    @Test
    public void testLoginWithInvalidEmail() {
        userAuthService.doRequest("it..19012026@gmail.com", "Password123!", "email");
        loginAssertions.assertResponseForFailedLoginWithEmailAndPasswordAndLogin(
                userAuthService,
                "Неверный формат Электронной почты",
                "Неверный пароль",
                "Введённые данные некорректны"
        );
    }

    @Test
    public void testLoginWithNotRegisteredEmail() {
        userAuthService.doRequest("irina.test@gmail.com", "Password123!", "email");
        loginAssertions.assertResponseForFailedLoginWithEmailAndPasswordAndLogin(
                userAuthService,
                "Такой E-Mail адрес не зарегистрирован",
                "Неверный пароль",
                "Неверный телефон/e-mail"
        );
    }

    @Test
    public void testLoginResponseAfterThreeFailedLoginAttempts() {
        userAuthService.doRequest("it.19012026@ gmail.com", "Password123!", "email");
        userAuthService.doRequest("it.19012026@ gmail.com", "Password123!", "email");
        userAuthService.doRequest("it.19012026@ gmail.com", "Password123!", "email");
        userAuthService.doRequest("it.19012026@ gmail.com", "Password123!", "email");
        loginAssertions.assertResponseForFailedLoginWithLogin(
                userAuthService,
                "Введённые данные некорректны"
        );
    }
}

