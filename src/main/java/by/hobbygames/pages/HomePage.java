package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import io.qameta.allure.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

public class HomePage {
    private final String BASE_URL = "https://hobbygames.by/";
    public final String LOGIN_BUTTON_TITLE_TEXT = "Войти";

    private final By COOKIE_ALERT_CLOSE = By.xpath("//div[@class='cookie-banner__button']/button");
    private final By LOGIN_BUTTON = By.xpath("//div[@class='user-profile']/a/span[@class='cart-text']");
    private final By LOGIN_POPUP = By.xpath("//div[@class='login-popup']");

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    @Step("Open the Home Page")
    public void open() {
        logger.info("Opening the Home Page: {}", BASE_URL);
        driver.get(BASE_URL);
    }

    @Step("Accept cookies")
    public void acceptCookie() {
        logger.info("Accepting cookies");
        Waits.waitAndClick(COOKIE_ALERT_CLOSE);
    }

    @Step("Click the Login Button")
    public void clickLoginButton() {
        logger.info("Clicking the Login Button");
        Waits.waitAndClick(LOGIN_BUTTON);
    }

    @Step("Ensure the Login Button is displayed")
    public boolean isLoginButtonDisplayed() {
        logger.info("Ensuring the Login Button is displayed");
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    @Step("Ensure the Login Popup is displayed")
    public boolean isLoginPopupDisplayed() {
        logger.info("Ensuring whether the Login Popup is displayed or not");
        return Waits.waitUntilIsDisplayed(LOGIN_POPUP);
    }

    @Step("Get the Login Button title")
    public String getLoginButtonTitle() {
        logger.info("Getting Text of the Login Button");
        return driver.findElement(LOGIN_BUTTON).getText();
    }
}
