package by.hobbygames.utils;

import by.hobbygames.driver.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.*;
import java.util.*;

public class Waits {
    private static final Logger logger = LogManager.getLogger();

    public static WebElement wait(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitAndClick(By locator) {
       wait(locator).click();
    }

    public static void waitAndInput(By locator, String value) {
        wait(locator).sendKeys(value);
    }

    public static String waitAndGetText(By locator) {
        return wait(locator).getText();
    }

    public static boolean waitUntilIsDisplayed(By locator) {
        try {
            return wait(locator).isDisplayed();
        } catch (TimeoutException e) {

            return false;
        }
    }

    public static WebElement waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(50));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForStaleness(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static List<WebElement> waitUntilAllArePresent(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(locator));
    }
}
