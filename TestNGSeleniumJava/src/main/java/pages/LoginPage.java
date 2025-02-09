package pages;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.AsyncSeleniumHelper;
import utils.LocatorsReader;
import utils.UiActions;

public class LoginPage {
	private WebDriver driver;
	private UiActions actions;
	private AsyncSeleniumHelper asyncSeleniumHelper;

	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new UiActions(driver);
		this.asyncSeleniumHelper = new AsyncSeleniumHelper(driver);
	}

	LocatorsReader locatorsReader = new LocatorsReader();
	Properties loginProperties = locatorsReader.getLocatorsFrom("LoginPage");

	By username = By.xpath(loginProperties.getProperty("login.username"));
	By password = By.xpath(loginProperties.getProperty("login.password"));
	By loginButton = By.xpath(loginProperties.getProperty("login.loginButton"));
	By productTitle = By.xpath(loginProperties.getProperty("homePage.productLabel"));
	By errorMessage = By.xpath(loginProperties.getProperty("login.errorMessage"));

	public void loginPageUsingCredentials(String userName, String pass) {
		logger.info("Enter username ..............");
		actions.sendKeys(username, userName);
		logger.info("Enter password ..............");
		actions.sendKeys(password, pass);
		logger.info("Click button ..............");
		actions.click(loginButton);
	}

	public String validateLogin() {
		if (asyncSeleniumHelper.waitForVisibilityTrue(productTitle)) {
			logger.info("Login Successful");
			return "Login Successful";
		} else if (asyncSeleniumHelper.waitForVisibilityTrue(errorMessage)) {
			logger.info("Login Failed");
			return "Login Failed: " + actions.getText(errorMessage);
		} else {
			logger.info("Login Status Unknown");
			return "Login Status Unknown";
		}
	}
}