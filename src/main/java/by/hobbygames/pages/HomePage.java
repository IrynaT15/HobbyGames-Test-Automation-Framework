package by.hobbygames.pages;

import by.hobbygames.driver.*;
import org.openqa.selenium.*;

public class HomePage {
    private final String BASE_URL = "https://hobbygames.by/";

    public final String LOGIN_BUTTON_TITLE_TEXT = "Войти";

    private final By COOKIE_ALERT_CLOSE = By.xpath("//div[@class='cookie-banner__button']/button");
    private final By LOGIN_BUTTON = By.xpath("//div[@class='user-profile']/a/span[@class='cart-text']");
    private final By LOGIN_POPUP = By.xpath("//div[@class='login-popup']");
    private final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath("//span[@class='icon icon-ic_search_black search-btn']");

    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void clickCookieAlertClose() {
        driver.findElement(COOKIE_ALERT_CLOSE).click();
    }

    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    public boolean isLoginPopupDisplayed() {
        return !driver.findElements(LOGIN_POPUP).isEmpty()
                && driver.findElements(LOGIN_POPUP).get(0).isDisplayed();
    }

    public String getLoginButtonTitle() {
        return driver.findElement(LOGIN_BUTTON).getText();
    }
}
