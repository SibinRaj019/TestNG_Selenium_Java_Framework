package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import dataProvider.LoginDataProvider;
import pages.LoginPage;


@Listeners(listeners.TestListeners.class)
public class LoginTest extends BaseTest {
	LoginPage loginPage;

	private static final Logger logger = LogManager.getLogger(LoginTest.class);

	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage(driver);
	}

	@Test(dataProvider="InValidCredentials", dataProviderClass = LoginDataProvider.class,enabled=true)
	public void verifyInvalidCredentialsIsNotAllowed(String userName, String password) {
		logger.info("Starting test: verifyInvalidCredentialsIsNotAllowed");
		// Attempt to login
		loginPage.loginPageUsingCredentials(userName,password);
		logger.info("Entered invalid credentials: Username="+userName+", Password="+password+"");
		// Validate login result
		String loginStatus = loginPage.validateLogin();
		logger.info("Login validation result: " + loginStatus);
		// Assertion with logging
		try {
			Assert.assertEquals(loginStatus,
					"Login Failed: Epic sadface: Username and password do not match any user in this service",
					"Error: Expected login to fail with incorrect credentials");
			logger.info("Test Passed: Invalid credentials were correctly rejected.");
		} catch (AssertionError e) {
			logger.error("Test Failed: " + e.getMessage());
			throw e; // Re-throw to ensure test failure is captured by TestNG
		}
	}

	@Test(dataProvider="ValidCredentials", dataProviderClass = LoginDataProvider.class,enabled=true)
	public void verifyValidCredentialsIsAllowed(String username, String password) {
		logger.info("Starting test: verifyValidCredentialsIsAllowed");

		// Attempt to login with valid credentials
		loginPage.loginPageUsingCredentials(username,password);
		logger.info("Entered valid credentials: Username=standard_user, Password=****");

		// Validate login result
		String loginStatus = loginPage.validateLogin();
		logger.info("Login validation result: " + loginStatus);

		// Assertion with logging
		try {
			Assert.assertEquals(loginStatus, "Login Successful", "Error: Expected successful login");
			logger.info("Test Passed: Valid credentials allowed login.");
		} catch (AssertionError e) {
			logger.error("Test Failed: " + e.getMessage());
			throw e; // Re-throw to ensure TestNG captures the failure
		}
	}
}
