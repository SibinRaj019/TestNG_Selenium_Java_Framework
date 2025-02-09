package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {
	// ThreadLocal for thread-safe WebDriver instances
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	/**
	 * Initializes the WebDriver based on the browser type.
	 *
	 * @param browser Browser name ("chrome", "firefox", "edge").
	 */
	public static void initializeDriver(String browser) {
		WebDriver driver = null;

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless"); // Run in CI mode
//			options.addArguments("--disable-dev-shm-usage"); // Fix for /dev/shm error
//			options.addArguments("--no-sandbox"); // Run without sandbox
//			options.addArguments("--remote-allow-origins=*");
//			options.addArguments("--user-data-dir=/tmp/chrome-profile"); // Fix for session error
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}

		// Set default timeouts and maximize the browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// Store the WebDriver instance in ThreadLocal
		driverThread.set(driver);
	}

	/**
	 * Get the WebDriver instance for the current thread.
	 *
	 * @return WebDriver instance.
	 */
	public static WebDriver getDriver() {
		return driverThread.get();
	}

	/**
	 * Quit and remove the WebDriver instance for the current thread.
	 */
	public static void quitDriver() {
		if (driverThread.get() != null) {
			driverThread.get().quit();
			driverThread.remove();
		}
	}
}
