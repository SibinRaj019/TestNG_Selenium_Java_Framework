package utils;

public class FrameworkConstants {
    // 🔹 Prevents instantiation of this class
    private FrameworkConstants() {}

    // 🔹 General Constants
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    
    // 🔹 Project Paths
    private static final String BASE_PATH = System.getProperty("user.dir");
    public static final String CONFIG_PROPERTIES_PATH = BASE_PATH + "/src/test/resources/config.properties";
    public static final String LOCATORS_PATH = BASE_PATH + "/src/test/resources/page-locators/";    
    
    public static final String TEST_DATA_PATH = BASE_PATH + "/test-data/testdata.xlsx";
    public static final String LOCATORS_YAML_PATH = BASE_PATH + "/src/test/resources/locators.yaml";
    
    // 🔹 URL Constants
    public static final String BASE_URL = "https://example.com";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String HOME_URL = BASE_URL + "/home";

    // 🔹 Browser Constants (Used for Cross-Browser Testing)
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_EDGE = "edge";

    // 🔹 Reports and Logs
    public static final String REPORTS_PATH = BASE_PATH + "/reports/";
    public static final String LOGS_PATH = BASE_PATH + "/logs/";
    public static final String SCREENSHOTS_PATH = BASE_PATH + "/screenshots/";

    // 🔹 Timeouts
    public static final long PAGE_LOAD_TIMEOUT = 30;
    public static final long SCRIPT_TIMEOUT = 20;

    // 🔹 Extent Report Constants
    public static final String EXTENT_REPORT_FILE = REPORTS_PATH + "ExtentReport.html";

    // 🔹 Test Data Constants
    public static final String EXCEL_SHEET_NAME = "TestCases";
}
