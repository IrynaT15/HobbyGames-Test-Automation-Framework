package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

public class LoginPage {
    private final By TITLE = By.xpath("//div[@class='login-popup']/div[@class='h2']");
    private final By CLOSE_BUTTON = By.xpath("//div[@class='vex-close']");
    private final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");
    private final By FORGOT_LINK = By.xpath("//a[@class='js-openForgotModal']");
    private final By FORGOT_POPUP = By.xpath("//div[@class='login-popup forgot_popup']");
    private final By REGISTER_LINK = By.xpath("//a[@class='footer-btn js-openRegisterModal']");
    private final By REGISTER_POPUP = By.xpath("//div[@class='register_popup']");

    public final By LOGIN_FIELD = By.xpath("//input[@name='login']");
    public final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    public final By ERROR_LOGIN_INPUT = By.xpath("//label[@data-scenario='login']/div[@class='error']");
    public final By ERROR_PASSWORD_INPUT = By.xpath("//label[@class='password-group']/div[@class='error']");

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public void clickCloseButton() {
        Waits.waitAndClick(CLOSE_BUTTON);
        logger.info("[Close] button is clicked in the Login popup.");
    }

    public void clickForgotModalLink() {
        Waits.waitAndClick(FORGOT_LINK);
        logger.info("[Forgot] link is clicked in the Login popup.");
    }

    public void clickRegisterModalLink() {
        Waits.waitAndClick(REGISTER_LINK);
        logger.info("[Registration] link is clicked in the Login popup.");
    }

    public void clickSubmitButton() {
       Waits.waitAndClick(SUBMIT_BUTTON);
        logger.info("[Submit] button is clicked in the Login popup.");
    }

    public boolean isLoginPopupTitleDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(TITLE, "Title");
    }

    public boolean isSubmitButtonDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(SUBMIT_BUTTON, "[Submit] button");
    }

    public boolean isLoginFieldDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(LOGIN_FIELD, "[Login] field");
    }

    public boolean isPasswordFieldDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(PASSWORD_FIELD, "[Password] field");
    }

    public boolean isForgotModalLinkDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(FORGOT_LINK, "[Forgot] link");
    }

    public boolean isForgotPopupDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(FORGOT_POPUP, "Forgot popup");
    }

    public boolean isRegisterModalLinkDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(REGISTER_LINK, "[Register] link");
    }

    public boolean isRegisterPopupDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(REGISTER_POPUP, "Register popup");
    }

    public boolean isErrorForIncorrectLoginInputDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(ERROR_LOGIN_INPUT, "Error message for incorrect login");
    }

    public boolean isErrorForIncorrectPasswordInputDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(ERROR_PASSWORD_INPUT, "Error message for incorrect password");
    }

    public String getErrorMessage(By field) {
        return Waits.waitAndGetText(field);
    }

    public void putValueToField(By field, String value) {
        Waits.waitAndInput(field, value);
    }

    public void putValueAndSubmit(By field, String value) {
        putValueToField(field, value);
        clickSubmitButton();
    }
}
