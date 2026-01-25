package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

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
    public final String NOT_LOGGED_IN_EMAIL = "123qweASD@gmail.com";

    private final By TITLE = By.xpath("//div[@class='login-popup']/div[@class='h2']");
    private final By CLOSE_BUTTON = By.xpath("//div[@class='vex-close']");
    private final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");
    private final By LOGIN_FIELD = By.xpath("//input[@name='login']");
    private final By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final By FORGOT_LINK = By.xpath("//a[@class='js-openForgotModal']");
    private final By FORGOT_POPUP = By.xpath("//div[@class='login-popup forgot_popup']");
    private final By REGISTER_LINK = By.xpath("//a[@class='footer-btn js-openRegisterModal']");
    private final By REGISTER_POPUP = By.xpath("//div[@class='register_popup']");
    private final By GLOBAL_LOADER = By.xpath("//div[@class='global-loader']");
    private final By ERROR_LOGIN_INPUT = By.xpath("//label[@data-scenario='login']/div[@class='error']");
    private final By ERROR_PASSWORD_INPUT = By.xpath("//label[@class='password-group']/div[@class='error']");

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

    public String getLoginPopupTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getLoginPopupSubmitButtonTitle() {
        return driver.findElement(SUBMIT_BUTTON).getAttribute("value");
    }

    public String getLoginPopupLoginFieldPlaceholderText() {
        return driver.findElement(LOGIN_FIELD).getAttribute("placeholder");
    }

    public String getLoginPopupPasswordFieldPlaceholderText() {
        return driver.findElement(PASSWORD_FIELD).getAttribute("placeholder");
    }

    public String getLoginPopupForgotModalLinkText() {
        return driver.findElement(FORGOT_LINK).getText();
    }

    public String getLoginPopupRegisterModalLinkText() {
        return driver.findElement(REGISTER_LINK).getText();
    }

    public String getErrorTextForLoginField() {
        return Waits.waitAndGetText(ERROR_LOGIN_INPUT);
    }

    public String getErrorTextForPasswordField() {
        return Waits.waitAndGetText(ERROR_PASSWORD_INPUT);
    }

    public void putNotLoggedInEmailToLoginField() {
        Waits.waitAndInput(LOGIN_FIELD, NOT_LOGGED_IN_EMAIL);
    }
}
