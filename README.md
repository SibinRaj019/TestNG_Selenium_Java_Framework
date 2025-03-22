# TestNG Selenium Testing Framework

## Project Overview
This is a **TestNG-based test automation framework** designed for testing web applications. The framework supports **UI automation using Selenium**, along with **Log4j for logging** and **Extent Reports for reporting**.

## Repository Link
[GitHub - TestNG Selenium Java Framework](https://github.com/SibinRaj019/TestNG_Selenium_Java_Framework)

## Tech Stack
- **Programming Language:** Java
- **Testing Framework:** TestNG
- **UI Automation:** Selenium WebDriver
- **Logging:** Log4j
- **Reporting:** Extent Reports
- **Build Tool:** Maven
- **CI/CD:**GitHub Actions

## Project Structure
```
TestNGFramework/
│── src/
│   ├── main
│   │   ├── java/
│   │   │   ├── com.projectname/
│   │   │   │   ├── pages/             # Page Object Model (POM) for UI
│   │   │   │   ├── utilities/         # Utility classes (Config, Reporting, Logging)
│   ├── test/
│   │   ├── java/
│   │   │   ├── com.projectname/
│   │   │   │   ├── testcases/         # TestNG test cases
│   │   │   │   ├── listeners/         # TestNG listeners for reporting
│   │   ├── resources/
│   │   │   ├── config/                # Configuration files (config.properties, log4j.xml)
│   │   │   ├── reports/               # Extent Reports output
│── pom.xml                             # Maven dependencies
│── testng.xml                          # TestNG suite configuration
│── README.md                           # Documentation
```

## Setup & Execution

### Prerequisites
- **Java JDK 11+** installed and configured
- **Maven** installed
- **ChromeDriver** installed

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/SibinRaj019/TestNG_Selenium_Java_Framework.git
   cd TestNG_Selenium_Java_Framework
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```

### Running Tests
- **Run UI Tests:**
  ```sh
  mvn test -Dgroups=UITests
  ```
- **Run all tests using TestNG suite:**
  ```sh
  mvn test -DsuiteXmlFile=testng.xml
  ```

## Reporting
After execution, Extent Reports are generated in:
```
/target/reports/extent-report.html
```
Open `extent-report.html` in a browser to view test execution results.

## Logging
Log files are generated using Log4j and stored in:
```
/logs/app.log
```

## Contributing
Feel free to contribute by creating a pull request or reporting issues.

---

### **Author**
**Sibin Raj** - [GitHub](https://github.com/SibinRaj019)
