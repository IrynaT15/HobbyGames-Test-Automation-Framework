package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.openqa.selenium.*;

public class SearchPage {
    private final String BASE_URL = "https://hobbygames.by/";

    private final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath(
            "//a[@class='input--search__btn btn search-block__submit search-btn']");

    private WebDriver driver;

    public SearchPage() {
        this.driver = Driver.getDriver();
    }

    public void open(String url) {
        driver.get(url);
    }

    public Boolean isSearchFieldDisplayed() {
        return Waits.wait(SEARCH_FIELD).isDisplayed();
    }

    public Boolean isSearchButtonDisplayed() {
        return Waits.wait(SEARCH_BUTTON).isDisplayed();
    }
}
