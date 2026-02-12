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

    @Step("Open the Search Results page: {url}")
    public void open(String url) {
        logger.info("Open page: {}", url);
        driver.get(url);
    }

    @Step("Check if element '{elementTitle}' is displayed")
    public boolean isElementDisplayed(By locator, String elementTitle) {
        boolean elementDisplayed = Waits.wait(locator).isDisplayed();
        logger.info("Element '{}' displayed: {}", elementTitle, elementDisplayed);
        return elementDisplayed;
    }

    @Step("Click on Search button")
    public void clickSearchButton() {
        logger.info("Clicking on Search button");
        Waits.waitAndClick(SEARCH_BUTTON);
    }

    public void putSearchParameter(String searchParameter) {
        Waits.waitAndInput(SEARCH_FIELD, searchParameter);
    }

    @Step("Enter search parameter '{searchParameter}' in the Search field and submit")
    public void putSearchParameterAndClickSearchButton(String searchParameter) {
        logger.info("Entering '{}' in the Search field and submitting search", searchParameter);
        putSearchParameter(searchParameter);
        clickSearchButton();
    }

    @Step("Get current URL")
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL: {}", currentUrl);
        return currentUrl;
    }

    @Step("Get text of element '{locator}'")
    public String getElementText(By locator) {
        String elementText = Waits.waitAndGetText(locator);
        logger.info("Title of the element '{}': {}", locator, elementText);
        return elementText;
    }

    @Step("Get Product Cards in Search Results page")
    public List<WebElement> getProductCards() {
        logger.info("Getting Product Cards in the Search Results page");
        return driver.findElements(PRODUCT_CARD);
    }

    @Step("Get total number of found items from the page title")
    public int getNumberOfFoundItemsFromText() {
        int totalNumber = Integer.parseInt(getElementText(NUMBER_OF_FOUND_ITEMS_TEXT).replaceAll("\\D+", ""));
        logger.info("Total number of found items from the page title: {}", totalNumber);
        return totalNumber;
    }

    @Step("Get total number of found items across all Search Results pages")
    public int getNumberOfFoundItemsInAllPages() {
        int count = 0;

        while (true) {
            List<WebElement> cards = Waits.waitUntilAllArePresent(PRODUCT_CARD);
            count += cards.size();

            if (Waits.waitUntilIsDisplayed(NEXT_BUTTON)) {
                WebElement firstCard = cards.get(0);
                WebElement nextButton = Waits.waitUntilClickable(NEXT_BUTTON);

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", nextButton
                );

                nextButton.click();
                Waits.waitForStaleness(firstCard);
            } else {
                break;
            }
        }
        logger.info("Total number of found items in all pages: {}", count);
        return count;
    }
}
