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

    @Step("Open the Home page")
    public void open() {
        logger.info("Opening Home Page: {}", BASE_URL);
        driver.get(BASE_URL);
    }

    @Step("Accept cookies")
    public void acceptCookie() {
        logger.info("Accepting cookies");
        Waits.waitAndClick(COOKIE_ALERT_CLOSE);
    }

    @Step("Click on Login button")
    public void clickLoginButton() {
        logger.info("Clicking on Login button");
        Waits.waitAndClick(LOGIN_BUTTON);
    }

    @Step("Check if Login button is displayed")
    public boolean isLoginButtonDisplayed() {
        boolean buttonPresence = driver.findElement(LOGIN_BUTTON).isDisplayed();
        logger.info("Login button displayed: {}", buttonPresence);
        return buttonPresence;
    }

    @Step("Wait and check if Login popup is displayed")
    public boolean isLoginPopupDisplayed() {
        boolean popupPresence = Waits.waitUntilIsDisplayed(LOGIN_POPUP);
        logger.info("Waiting. Login popup displayed: {}", popupPresence);
        return popupPresence;
    }

    @Step("Get Login button text")
    public String getLoginButtonText() {
        String loginButtonText = driver.findElement(LOGIN_BUTTON).getText();
        logger.info("Login button text: {}", loginButtonText);
        return loginButtonText;
    }
}
