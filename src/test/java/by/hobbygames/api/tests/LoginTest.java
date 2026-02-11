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

    @DisplayName("Login attempt with missing credentials")
    @Test
    public void testLoginWithMissingCredentialsFails() {
        userAuthService.doRequest("", "");
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_PROVIDED,
                null,
                ApiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                ApiLoginErrors.LOGIN_MISSING_CREDENTIALS
        );
    }

    @DisplayName("Login attempt with missing email/phobe number")
    @Test
    public void testLoginWithMissingEmailOrPhoneNumberFails() {
        userAuthService.doRequest("", TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_PROVIDED,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_MISSING_CREDENTIALS
        );
    }

    @DisplayName("Login attempt with too short phone number")
    @Test
    public void testLoginWithTooShortPhoneNumberFails() {
        userAuthService.doRequest(TestLoginData.generateShortPhoneNumber(), TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_TOO_SHORT,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login attempt with too long phone number")
    @Test
    public void testLoginWithTooLongPhoneNumberFails() {
        userAuthService.doRequest(TestLoginData.generateLongPhoneNumber(), TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_TOO_LONG,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login attempt with unregistered phone number")
    @Test
    public void testLoginWithUnregisteredPhoneNumberFails() {
        userAuthService.doRequest(TestLoginData.generateUnregisteredPhoneNumber(), TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ApiLoginErrors.PHONE_IS_NOT_REGISTERED,
                null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @DisplayName("Login attempt with missing password")
    @Test
    public void testLoginWithMissingPasswordFails() {
        userAuthService.doRequest(TestLoginData.REGISTERED_EMAIL, null);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,null,
                ApiLoginErrors.PASSWORD_IS_NOT_PROVIDED,
                null
        );
    }

    @DisplayName("Login attempt with wrong password")
    @Test
    public void testLoginWithWrongPasswordFails() {
        userAuthService.doRequest(TestLoginData.REGISTERED_EMAIL, TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                null
        );
    }

    @DisplayName("Login attempt with invalid email")
    @Test
    public void testLoginWithInvalidEmailFails() {
        userAuthService.doRequest(TestLoginData.generateInvalidEmail(), TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ApiLoginErrors.EMAIL_IS_INVALID,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }

    @DisplayName("Login attempt with unregistered email")
    @Test
    public void testLoginWithUnregisteredEmailFails() {
        userAuthService.doRequest(TestLoginData.generateUnregisteredEmail(), TestLoginData.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ApiLoginErrors.EMAIL_IS_NOT_REGISTERED,
                ApiLoginErrors.PASSWORD_IS_WRONG,
                ApiLoginErrors.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @DisplayName("Specific error should be returned after the fourth failed login attempt")
    @Test
    public void testDifferentErrorIsReturnedAfterFourthFailedLoginAttempt() {
        for (int i = 0; i < 4; i++) {
            userAuthService.doRequest(
                    TestLoginData.generateInvalidEmail(),
                    TestLoginData.PASSWORD
            );
        }

        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null, null,
                ApiLoginErrors.LOGIN_INVALID_DATA
        );
    }
}
