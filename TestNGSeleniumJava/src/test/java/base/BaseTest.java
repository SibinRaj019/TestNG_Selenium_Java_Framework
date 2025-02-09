package base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import driverFactory.DriverManager;
import listeners.TestListeners;
import utils.ConfigReader;

@Listeners(listeners.TestListeners.class)
public class BaseTest {
	protected WebDriver driver;
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	ConfigReader configProp = new ConfigReader();
	Properties config = configProp.readProperties("config");
	
	@BeforeSuite
	public void launchBrowser()
	{
		logger.info("Initializing WebDriver...");
		DriverManager.initializeDriver(config.getProperty("browser"));
		driver = DriverManager.getDriver();
		logger.info("WebDriver initialized successfully.");
		TestListeners.setDriver(driver);
		driver.get(config.getProperty("url"));
	}
	@AfterSuite
	public void quitBrowser()
	{
		if (driver != null) {
            logger.info("Closing WebDriver...");
            driver.quit();
            logger.info("WebDriver closed.");
        }
	}
}
