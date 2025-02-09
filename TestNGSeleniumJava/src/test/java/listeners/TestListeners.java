package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListeners implements ITestListener {

	private static final Logger logger = LogManager.getLogger(TestListeners.class);
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	private static WebDriver driver; // WebDriver for screenshots
	public static void setDriver(WebDriver webDriver) {
		driver = webDriver;
	}
	public void onStart(ITestContext context) {
		logger.info("*************************************************************");
		logger.info("ON START " + context.getName());
	    String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	    String reportPath = "ExtentReports/ExtentReport_" + timestamp + ".html";
	    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	public void onTestStart(ITestResult result) {
		logger.info("*************************************************************");
		logger.info("On Test Start " + result.getName());
		// Create Extent Test
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testThread.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("*************************************************************");
		logger.info("On Test Success " + result.getName());
		ExtentTest test = testThread.get();
		test.log(Status.PASS, "Test Passed: " + result.getName());
		if (driver != null) {
			String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
			// Attach screenshot within the node using MediaEntityBuilder
			test.pass("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
	}

	public void onTestFailure(ITestResult result) {
		logger.info("*************************************************************");
		logger.info("On Test Failure " + result.getName());
		ExtentTest test = testThread.get();
		test.log(Status.FAIL, "Test Failed: " + result.getName());
		test.log(Status.FAIL, result.getThrowable());
		if (driver != null) {
			String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
			// Attach screenshot within the node using MediaEntityBuilder
			test.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("*************************************************************");
		logger.info("On Test Skipped " + result.getName());
		// Log skip in Extent Report
		ExtentTest test = testThread.get();
		test.log(Status.SKIP, "Test Skipped: " + result.getName());
	}

	public void onFinish(ITestContext context) {
		logger.info("ON FINISH " + context.getName());
		// Flush Extent Reports
		if (extent != null) {
			extent.flush();
		}
	}

	private String takeScreenshot(String testName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Ensure screenshots directory exists
		File destDir = new File("ExtentReports/Screenshots/");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		// Define screenshot path
		String destPath = "ExtentReports/Screenshots/" + testName + ".png";
		File destFile = new File(destPath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return relative path for Extent Report
		return "Screenshots/" + testName + ".png"; // Correct relative path
	}
}
