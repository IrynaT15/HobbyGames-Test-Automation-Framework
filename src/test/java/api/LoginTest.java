package api;

import by.hobbygames.api.*;
import org.apache.commons.logging.*;
import org.junit.jupiter.api.*;
import testdata.*;

public class LoginTest {
    private static final Log log = LogFactory.getLog(LoginTest.class);
    UserAuthService userAuthService;

    @BeforeEach
    public void setUp() {
        userAuthService = new UserAuthService();
    }

    @Test
    public void testLoginWithMissingCredentials() {
        userAuthService.doRequest("", "");
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ErrorMessages.PHONE_IS_NOT_PROVIDED,
                null,
                ErrorMessages.PASSWORD_IS_NOT_PROVIDED,
                ErrorMessages.LOGIN_MISSING_CREDENTIALS
        );
    }

    @Test
    public void testLoginWithMissingLogin() {
        userAuthService.doRequest("", LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ErrorMessages.PHONE_IS_NOT_PROVIDED,
                null,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_MISSING_CREDENTIALS
        );
    }

    @Test
    public void testLoginWithTooShortPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.SHORT_PHONE, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ErrorMessages.PHONE_IS_TOO_SHORT,
                null,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_INVALID_DATA
        );
    }

    @Test
    public void testLoginWithTooLongPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.LONG_PHONE, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ErrorMessages.PHONE_IS_TOO_LONG,
                null,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_INVALID_DATA
        );
    }

    @Test
    public void testLoginWithNotRegisteredPhoneNumber() {
        userAuthService.doRequest(LoginCredentials.NOT_REGISTERED_PHONE, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                ErrorMessages.PHONE_IS_NOT_REGISTERED,
                null,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @Test
    public void testLoginWithMissingPassword() {
        userAuthService.doRequest(LoginCredentials.REGISTERED_EMAIL, null);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,null,
                ErrorMessages.PASSWORD_IS_NOT_PROVIDED,
                null
        );
    }

    @Test
    public void testLoginWithWrongPassword() {
        userAuthService.doRequest(LoginCredentials.REGISTERED_EMAIL, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null,
                ErrorMessages.PASSWORD_IS_WRONG,
                null
        );
    }

    @Test
    public void testLoginWithInvalidEmail() {
        userAuthService.doRequest(LoginCredentials.INVALID_EMAIL, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ErrorMessages.EMAIL_IS_INVALID,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_INVALID_DATA
        );
    }

    @Test
    public void testLoginWithNotRegisteredEmail() {
        userAuthService.doRequest(LoginCredentials.NOT_REGISTERED_EMAIL, LoginCredentials.PASSWORD);
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null,
                ErrorMessages.EMAIL_IS_NOT_REGISTERED,
                ErrorMessages.PASSWORD_IS_WRONG,
                ErrorMessages.LOGIN_WRONG_PHONE_OR_EMAIL
        );
    }

    @Test
    public void testLoginResponseAfterThreeFailedLoginAttempts() {
        int attemptsCount = 0;
        while (attemptsCount < 4) {
            userAuthService.doRequest(LoginCredentials.INVALID_EMAIL, LoginCredentials.PASSWORD);
            attemptsCount ++;
        }
        LoginAssertions.assertFailedLogin(
                userAuthService,
                null, null, null,
                ErrorMessages.LOGIN_INVALID_DATA
        );
    }
}
