package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

public class LoginPage {
    public final String TITLE_TEXT = "Вход";
    public final String SUBMIT_BUTTON_TEXT = "Вход";
    public final String LOGIN_FIELD_PLACEHOLDER = "Email или телефон";
    public final String PASSWORD_FIELD_PLACEHOLDER = "Пароль";
    public final String FORGOT_LINK_TEXT = "Не помните?";
    public final String REGISTER_LINK_TEXT = "Регистрация нового пользователя";
    public final String ERROR_TEXT_FOR_EMPTY_LOGIN= "Введите телефон или электронную почту";
    public final String ERROR_TEXT_FOR_EMPTY_PASSWORD = "Введите пароль";
    public final String ERROR_TEXT_FOR_NOT_LOGGED_IN_EMAIL = "Неверный телефон/e-mail";
    public final String ERROR_TEXT_FOR_INVALID_EMAIL = "Введённые данные некорректны";
    public final String ERROR_TEXT_FOR_INVALID_PASSWORD = "Неверный пароль";
    public final String NOT_LOGGED_IN_EMAIL = "123qweASD@gmail.com";
    public final String INVALID_EMAIL = "123qwe";
    public final String INVALID_PASSWORD = "123qwe";

    private final By TITLE = By.xpath("//div[@class='login-popup']/div[@class='h2']");
    private final By CLOSE_BUTTON = By.xpath("//div[@class='vex-close']");
    private final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");
    public final By LOGIN_FIELD = By.xpath("//input[@name='login']");
    public final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final By FORGOT_LINK = By.xpath("//a[@class='js-openForgotModal']");
    private final By FORGOT_POPUP = By.xpath("//div[@class='login-popup forgot_popup']");
    private final By REGISTER_LINK = By.xpath("//a[@class='footer-btn js-openRegisterModal']");
    private final By REGISTER_POPUP = By.xpath("//div[@class='register_popup']");
    public final By ERROR_LOGIN_INPUT = By.xpath("//label[@data-scenario='login']/div[@class='error']");
    public final By ERROR_PASSWORD_INPUT = By.xpath("//label[@class='password-group']/div[@class='error']");

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public void clickCloseButtonInLoginPopup() {
        Waits.waitAndClick(CLOSE_BUTTON);
        logger.info("[Close] button is clicked in the Login popup.");
    }

    public void clickForgotModalLinkInLoginPopup() {
        Waits.waitAndClick(FORGOT_LINK);
        logger.info("[Forgot] link is clicked in the Login popup.");
    }

    public void clickRegisterModalLinkInLoginPopup() {
        Waits.waitAndClick(REGISTER_LINK);
        logger.info("[Registration] link is clicked in the Login popup.");
    }

    public void clickSubmitButtonInLoginPopup() {
       Waits.waitAndClick(SUBMIT_BUTTON);
        logger.info("[Submit] button is clicked in the Login popup.");
    }

    public boolean isLoginPopupTitleDisplayed() {
        return Waits.waitAndCheckElementIsDisplayed(TITLE, "Title");
    }

    public boolean isCloseButtonDisplayedInLoginPopup() {
        return Waits.waitAndCheckElementIsDisplayed(CLOSE_BUTTON, "[Close] button");
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

    public String getPageTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getSubmitButtonTitle() {
        return driver.findElement(SUBMIT_BUTTON).getAttribute("value");
    }

    public String getLoginFieldPlaceholderText() {
        return driver.findElement(LOGIN_FIELD).getAttribute("placeholder");
    }

    public String getPasswordFieldPlaceholderText() {
        return driver.findElement(PASSWORD_FIELD).getAttribute("placeholder");
    }

    public String getForgotModalLinkText() {
        return driver.findElement(FORGOT_LINK).getText();
    }

    public String getRegisterModalLinkText() {
        return driver.findElement(REGISTER_LINK).getText();
    }

    public String getErrorMessage(By field) {
        return Waits.waitAndGetText(field);
    }

    public void putValueToField(By field, String value) {
        Waits.waitAndInput(field, value);
    }
}
