package by.hobbygames.pages;

import by.hobbygames.driver.*;
import by.hobbygames.utils.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;

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

    public void open(String url) {
        logger.info("Opening the Search Page");
        driver.get(url);
    }

    public boolean isElementDisplayed(By locator, String elementTitle) {
        logger.info("Ensuring whether the {} is/isn't displayed", elementTitle);
        return Waits.wait(locator).isDisplayed();
    }

    public void clickSearchButton() {
        logger.info("Clicking the Search Button");
        Waits.waitAndClick(SEARCH_BUTTON);
    }

    public void putSearchParameter(String searchParameter) {
        Waits.waitAndInput(SEARCH_FIELD, searchParameter);
    }

    public void putSearchParameterAndClickSearchButton(String searchParameter) {
        logger.info("Putting a \"{}\" in the Search Field and submitting login", searchParameter);
        putSearchParameter(searchParameter);
        clickSearchButton();
    }

    public String getCurrentUrl() {
        logger.info("Getting the current URL");
        return driver.getCurrentUrl();
    }

    public String getElementText(By locator) {
        logger.info("Getting the title of the element: {}", locator);
        return Waits.waitAndGetText(locator);
    }

    public List<WebElement> getProductCards() {
        logger.info("Getting Product Cards in the Search Results page");
        return driver.findElements(PRODUCT_CARD);
    }

    public int getNumberOfFoundItemsFromText() {
        logger.info("Getting the total number of found items displayed in the page title");
        return Integer.parseInt(getElementText(NUMBER_OF_FOUND_ITEMS_TEXT).replaceAll("\\D+", ""));
    }

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
