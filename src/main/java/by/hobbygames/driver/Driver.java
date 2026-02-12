package by.hobbygames.driver;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Driver {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.info("Creating a new ChromeDriver instance");
            try {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                logger.info("ChromeDriver created and browser window maximized");
            } catch (Exception e) {
                logger.error("Failed to create ChromeDriver", e);
            }
        } else {
            logger.info("Using existing driver");
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            logger.info("Closing browser and releasing WebDriver");
            driver.quit();
            driver = null;
        } else {
            logger.warn("Attempt to close WebDriver that was not initialized");
        }
    }
}
