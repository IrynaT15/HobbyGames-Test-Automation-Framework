package by.hobbygames.pages;

import by.hobbygames.driver.*;
import org.openqa.selenium.*;

import java.util.logging.*;

public class LoginPage {
    public final String LOGIN_POPUP_TITLE_TEXT = "Вход";
    public final String LOGIN_POPUP_SUBMIT_BUTTON_TEXT = "Вход";
    public final String LOGIN_POPUP_LOGIN_FIELD_PLACEHOLDER = "Email или телефон";
    public final String LOGIN_POPUP_PASSWORD_FIELD_PLACEHOLDER = "Пароль";
    public final String LOGIN_POPUP_FORGOT_MODAL_LINK_TEXT = "Не помните?";
    public final String LOGIN_POPUP_REGISTER_MODAL_LINK_TEXT = "Регистрация нового пользователя";
    public final String ERROR_TEXT_FOR_EMPTY_LOGIN= "Введите телефон или электронную почту";
    public final String ERROR_TEXT_FOR_EMPTY_PASSWORD = "Введите пароль";
    public final String ERROR_TEXT_FOR_NOT_LOGGED_IN_EMAIL = "Неверный телефон/e-mail";
    public final String NOT_LOGGED_IN_EMAIL = "123qweASD@gmail.com";

    private final By LOGIN_POPUP_TITLE = By.xpath("//div[@class='login-popup']/div[@class='h2']");
    private final By LOGIN_POPUP_CLOSE_BUTTON = By.xpath("//div[@class='vex-close']");
    private final By LOGIN_POPUP_SUBMIT_BUTTON = By.xpath("//input[@class='btn send-login btn-s']");
    private final By LOGIN_POPUP_LOGIN_FIELD = By.xpath("//input[@name='login']");
    private final By LOGIN_POPUP_PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final By LOGIN_POPUP_FORGOT_MODAL_LINK = By.xpath("//a[@class='js-openForgotModal']");
    private final By FORGOT_POPUP = By.xpath("//div[@class='login-popup forgot_popup']");
    private final By LOGIN_POPUP_REGISTER_MODAL_LINK = By.xpath("//a[@class='footer-btn js-openRegisterModal']");
    private final By REGISTER_POPUP = By.xpath("//div[@class='register_popup']");
    private final By GLOBAL_LOADER = By.xpath("//div[@class='global-loader']");
    private final By ERROR_LOGIN_INPUT = By.xpath("//label[@data-scenario='login']/div[@class='error']");
    private final By ERROR_PASSWORD_INPUT = By.xpath("//label[@class='password-group']/div[@class='error']");

    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public void clickCloseButtonInLoginPopup() {
        driver.findElement(LOGIN_POPUP_CLOSE_BUTTON).click();
    }

    public void clickForgotModalLinkInLoginPopup() {
        driver.findElement(LOGIN_POPUP_FORGOT_MODAL_LINK).click();
    }

    public void clickRegisterModalLinkInLoginPopup() {
        driver.findElement(LOGIN_POPUP_REGISTER_MODAL_LINK).click();
    }

    public void clickSubmitButtonInLoginPopup() {
        driver.findElement(LOGIN_POPUP_SUBMIT_BUTTON).click();
    }

    public boolean isLoginPopupTitleDisplayed() {
        return driver.findElement(LOGIN_POPUP_TITLE).isDisplayed();
    }

    public boolean isCloseButtonDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_CLOSE_BUTTON).isDisplayed();
    }

    public boolean isSubmitButtonDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_SUBMIT_BUTTON).isDisplayed();
    }

    public boolean isLoginFieldDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_LOGIN_FIELD).isDisplayed();
    }

    public boolean isPasswordFieldDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_PASSWORD_FIELD).isDisplayed();
    }

    public boolean isForgotModalLinkDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_FORGOT_MODAL_LINK).isDisplayed();
    }

    public boolean isForgotPopupDisplayed() {
        return !driver.findElements(FORGOT_POPUP).isEmpty()
                && driver.findElements(FORGOT_POPUP).get(0).isDisplayed();
    }

    public boolean isRegisterModalLinkDisplayedInLoginPopup() {
        return driver.findElement(LOGIN_POPUP_REGISTER_MODAL_LINK).isDisplayed();
    }

    public boolean isRegisterPopupDisplayed() {
        return driver.findElement(REGISTER_POPUP).isDisplayed();
    }

    public boolean isGlobalLoaderDisplayed() {
        return driver.findElements(GLOBAL_LOADER).get(0).isDisplayed();
    }

    public boolean isErrorForIncorrectLoginInputDisplayed() {
        return driver.findElement(ERROR_LOGIN_INPUT).isDisplayed();
    }

    public boolean isErrorForIncorrectPasswordInputDisplayed() {
        return driver.findElement(ERROR_PASSWORD_INPUT).isDisplayed();
    }

    public String getLoginPopupTitle() {
        return driver.findElement(LOGIN_POPUP_TITLE).getText();
    }

    public String getLoginPopupSubmitButtonTitle() {
        return driver.findElement(LOGIN_POPUP_SUBMIT_BUTTON).getAttribute("value");
    }

    public String getLoginPopupLoginFieldPlaceholderText() {
        return driver.findElement(LOGIN_POPUP_LOGIN_FIELD).getAttribute("placeholder");
    }

    public String getLoginPopupPasswordFieldPlaceholderText() {
        return driver.findElement(LOGIN_POPUP_PASSWORD_FIELD).getAttribute("placeholder");
    }

    public String getLoginPopupForgotModalLinkText() {
        return driver.findElement(LOGIN_POPUP_FORGOT_MODAL_LINK).getText();
    }

    public String getLoginPopupRegisterModalLinkText() {
        return driver.findElement(LOGIN_POPUP_REGISTER_MODAL_LINK).getText();
    }

    public String getErrorTextForLoginField() {
        return driver.findElement(ERROR_LOGIN_INPUT).getText();
    }

    public String getErrorTextForPasswordField() {
        return driver.findElement(ERROR_PASSWORD_INPUT).getText();
    }

    public void putNotLoggedInEmailToLoginField() {
        driver.findElement(LOGIN_POPUP_LOGIN_FIELD).sendKeys(NOT_LOGGED_IN_EMAIL);
    }
}
