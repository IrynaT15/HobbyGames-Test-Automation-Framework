package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import io.qameta.allure.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

public class LoginPage {
    public final By TITLE = By.xpath("//div[@class='login-popup']/div[@class='h2']");
    public final By CLOSE_BUTTON = By.xpath("//div[@class='vex-close']");
    public final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");
    public final By FORGOT_LINK = By.xpath("//a[@class='js-openForgotModal']");
    public final By FORGOT_POPUP = By.xpath("//div[@class='login-popup forgot_popup']");
    public final By REGISTER_LINK = By.xpath("//a[@class='footer-btn js-openRegisterModal']");
    public final By REGISTER_POPUP = By.xpath("//div[@class='register_popup']");

    public final By LOGIN_FIELD = By.xpath("//input[@name='login']");
    public final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    public final By ERROR_LOGIN_INPUT = By.xpath("//label[@data-scenario='login']/div[@class='error']");
    public final By ERROR_PASSWORD_INPUT = By.xpath("//label[@class='password-group']/div[@class='error']");

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    @Step("Click the test element: {elementTitle}")
    public void clickElement(By locator, String elementTitle) {
        logger.info("Clicking the {} on the Login Popup", elementTitle);
        Waits.waitAndClick(locator);
    }

    @Step("Ensure the test element: {elementTitle} is displayed or is not displayed")
    public boolean isElementDisplayed(By locator, String elementTitle) {
        logger.info("Ensuring whether the {} is/isn't displayed on the Login Popup", elementTitle);
        return Waits.waitUntilIsDisplayed(locator);
    }

    @Step("Get the Error Message")
    public String getErrorMessage(By field) {
        logger.info("Getting the Error Message text for the {}", field);
        return Waits.waitAndGetText(field);
    }

    @Step("Put a test value and submit login")
    public void putValueAndSubmit(By field, String value) {
        logger.info("Putting {} into the {} field and submitting login", value, field);
        Waits.waitAndInput(field, value);
        clickElement(SUBMIT_BUTTON, "Submit Button");
    }
}
