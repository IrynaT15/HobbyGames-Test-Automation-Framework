package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;

import io.qameta.allure.Step;

public class SearchPage {
    public final String BASE_URL = "https://hobbygames.by/catalog/search";
    public final String RESULTS_URL = "https://hobbygames.by/catalog/search?keyword=";
    public final String SEARCH_RESULTS_PAGE_TITLE = "Результаты поиска";

    public final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    public final By SEARCH_BUTTON = By.xpath(
            "//a[@class='input--search__btn btn search-block__submit search-btn']");
    public final By PAGE_TITLE = By.xpath("//h1");
    public final By NUMBER_OF_FOUND_ITEMS_TEXT = By.xpath(
            "//div[@class='h1 catalog-info-count pe-25 px-md-0 text-nowrap']");
    public final By SMART_SEARCH_POPUP = By.xpath(
            "//table[@class='search__popup_head result active']");
    public final By PRODUCT_CONTENT_NO_RESULT = By.xpath(
            "//div[@class='product-content']//div[@class='result']");
    private final By PRODUCT_CARD = By.xpath("//div[normalize-space(@class)='product-card']");
    private final By NEXT_BUTTON = By.xpath("//a[@class='next']");

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public SearchPage() {
        this.driver = Driver.getDriver();
    }

    @Step("Open the Search Results Page")
    public void open(String url) {
        logger.info("Opening the Search Results Page");
        driver.get(url);
    }

    @Step("Ensure the test element: {elementTitle} is displayed or is not displayed")
    public boolean isElementDisplayed(By locator, String elementTitle) {
        logger.info("Ensuring whether the {} is/isn't displayed", elementTitle);
        return Waits.wait(locator).isDisplayed();
    }

    @Step("Click the Search Button")
    public void clickSearchButton() {
        logger.info("Clicking the Search Button");
        Waits.waitAndClick(SEARCH_BUTTON);
    }

    public void putSearchParameter(String searchParameter) {
        Waits.waitAndInput(SEARCH_FIELD, searchParameter);
    }

    @Step("Put a Search Parameter in the Search Field and submit login")
    public void putSearchParameterAndClickSearchButton(String searchParameter) {
        logger.info("Putting a \"{}\" in the Search Field and submitting login", searchParameter);
        putSearchParameter(searchParameter);
        clickSearchButton();
    }

    @Step("Get the current URL")
    public String getCurrentUrl() {
        logger.info("Getting the current URL");
        return driver.getCurrentUrl();
    }

    @Step("Get the element text")
    public String getElementText(By locator) {
        logger.info("Getting the title of the element: {}", locator);
        return Waits.waitAndGetText(locator);
    }

    @Step("Get Product Cards in the Search Results page")
    public List<WebElement> getProductCards() {
        logger.info("Getting Product Cards in the Search Results page");
        return driver.findElements(PRODUCT_CARD);
    }

    @Step("Get the total number of found items displayed in the page title")
    public int getNumberOfFoundItemsFromText() {
        logger.info("Getting the total number of found items displayed in the page title");
        return Integer.parseInt(getElementText(NUMBER_OF_FOUND_ITEMS_TEXT).replaceAll("\\D+", ""));
    }

    @Step("Get the total number of found items in all Search Results pages")
    public int getNumberOfFoundItemsInAllPages() {
        logger.info("Getting the total number of found items in all Search Results pages");
        int count = 0;

        while (true) {
            List<WebElement> cards = Waits.waitUntilAllArePresent(PRODUCT_CARD);
            count += cards.size();

            if (Waits.waitUntilIsDisplayed(NEXT_BUTTON)) {
                WebElement firstCard = cards.get(0);
                WebElement nextButton = Waits.waitUntilClickable(NEXT_BUTTON);
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", nextButton);
                nextButton.click();
                Waits.waitForStaleness(firstCard);
            } else {
                break;
            }
        }
        return count;
    }
}
