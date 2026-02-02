package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.openqa.selenium.*;

public class SearchPage {
    public final String SEARCH_RESULTS_PAGE_URL = "https://hobbygames.by/catalog/search";
    public final String SEARCH_RESULTS_PAGE_TITLE = "Результаты поиска";

    private final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath(
            "//a[@class='input--search__btn btn search-block__submit search-btn']");
    private final By PAGE_TITLE = By.xpath("//h1");
    private final By NUMBER_OF_FOUND_ITEMS_TEXT = By.xpath(
            "//div[@class='h1 catalog-info-count pe-25 px-md-0 text-nowrap']");
    private final By SMART_SEARCH_POPUP = By.xpath(
            "//table[@class='search__popup_head result active']");
    private final By PRODUCT_CONTENT_NO_RESULT = By.xpath("//div[@class='product-content']//div[@class='result']");

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

    public Boolean isSmartSearchPopupDisplayed() {
        return Waits.wait(SMART_SEARCH_POPUP).isDisplayed();
    }

    public void clickSearchButton() {
        Waits.waitAndClick(SEARCH_BUTTON);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitleText() {
        return Waits.waitAndGetText(PAGE_TITLE);
    }

    public String getNumberOfFoundItemsText() {
        return Waits.waitAndGetText(NUMBER_OF_FOUND_ITEMS_TEXT);
    }

    public String getProductContentNoResultText() {
        return Waits.waitAndGetText(PRODUCT_CONTENT_NO_RESULT);
    }

    public void putKeywordInSearchField(String keyword) {
        Waits.waitAndInput(SEARCH_FIELD, keyword);
    }
}
