package by.hobbygames.utils;

import by.hobbygames.driver.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.*;

public class Waits {
    private static final Logger logger = LogManager.getLogger();

    public static WebElement wait(By xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public static void waitAndClick(By xpath) {
       wait(xpath).click();
    }

    public static boolean waitAndCheckElementIsDisplayed(By locator, String elementStr) {
        try {
            if (wait(locator).isDisplayed()) {
                logger.info(String.format("%s is displayed.", elementStr));
            }
            return wait(locator).isDisplayed();
        } catch (Exception e) {
            logger.info(String.format("%s is NOT found.", elementStr));
            return false;
        }
    }
}
