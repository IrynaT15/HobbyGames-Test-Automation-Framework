package by.hobbygames.api.tests;

import by.hobbygames.api.*;
import by.hobbygames.api.assertions.*;
import by.hobbygames.testdata.credentials.*;
import by.hobbygames.testdata.errors.*;
import org.apache.commons.logging.*;
import org.junit.jupiter.api.*;

public class LoginTest {
    private static final Log log = LogFactory.getLog(LoginTest.class);
    UserAuthService userAuthService;

    @BeforeEach
    public void setUp() {
        userAuthService = new UserAuthService();
    }

    @DisplayName("Login with missing credentials")
    @Test
    public void testResponseForLoginWithMissingCredentials() {
        userAuthService.doRequest("", "");
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_PROVIDED,
                null,
                ApiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                ApiLoginErrors.LOGIN_MISSING_CREDENTIALS
        );
    }

    @DisplayName("Login with missing Login and provided Password")
    @Test
    public void testResponseForLoginWithMissingLogin() {
        userAuthService.doRequest("", LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_PROVIDED,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_MISSING_CREDENTIALS
        );
    }

    @DisplayName("Login with too short phone number")
    @Test
    public void testResponseForLoginWithTooShortPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.generateShortPhoneNumber(), LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_TOO_SHORT,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login with too long phone number")
    @Test
    public void testResponseForLoginWithTooLongPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.generateLongPhoneNumber(), LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_TOO_LONG,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login with not registered phone number")
    @Test
    public void testResponseForLoginWithNotRegisteredPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.generateNotRegisteredPhoneNumber(), LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_REGISTERED,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @DisplayName("Login with missing Password and provided Login")
    @Test
    public void testResponseForLoginWithMissingPassword() {
        userAuthService.doRequest(LoginCredentials.REGISTERED_EMAIL, null);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,null,
                ApiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                null
        );
    }

    @DisplayName("Login with wrong Password")
    @Test
    public void testResponseForLoginWithWrongPassword() {
        userAuthService.doRequest(LoginCredentials.REGISTERED_EMAIL, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                null
        );
    }

    @DisplayName("Login with invalid Email")
    @Test
    public void testResponseForLoginWithInvalidEmail() {
        userAuthService.doRequest(LoginCredentials.generateInvalidEmail(), LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ApiLoginErrors.EMAIL_IS_INVALID,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login with not registered Password")
    @Test
    public void testResponseForLoginWithNotRegisteredEmail() {
        userAuthService.doRequest(LoginCredentials.generateNotRegisteredEmail(), LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ApiLoginErrors.EMAIL_IS_NOT_REGISTERED,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @DisplayName("Security testing: Login after three failed attempts")
    @Test
    public void testResponseAfterThreeFailedLoginAttempts() {
        int attemptsCount = 0;
        while (attemptsCount < 4) {
            userAuthService.doRequest(LoginCredentials.generateInvalidEmail(), LoginCredentials.PASSWORD);
            attemptsCount ++;
        }
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null, null,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }
}
