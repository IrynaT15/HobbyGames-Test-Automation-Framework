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

    @Step("Click the element: {elementTitle}")
    public void clickElement(By locator, String elementTitle) {
        logger.info("Clicking element '{}'", elementTitle);
        Waits.waitAndClick(locator);
    }

    @Step("Check if the element: {elementTitle} is displayed")
    public boolean isElementDisplayed(By locator, String elementTitle) {
        boolean elementDisplayed = Waits.waitUntilIsDisplayed(locator);
        logger.info("Element '{}' is displayed: {}", elementTitle, elementDisplayed);
        return elementDisplayed;
    }

    @Step("Get the error message for {field}")
    public String getErrorMessage(By field) {
        String message = Waits.waitAndGetText(field);
        logger.info("Error message for {}: {}", field, message);
        return message;
    }

    @Step("Enter value '{value}' into field and submit login")
    public void putValueAndSubmit(By field, String value) {
        logger.info("Entering value '{}' into field '{}' and submitting login", value, field);
        Waits.waitAndInput(field, value);
        clickElement(SUBMIT_BUTTON, "Submit Button");
    }
}
