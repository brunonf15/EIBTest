// Location: src/test/java/tests/BaseTest.java
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        // Configure Chrome to run in headless mode for CI environments.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");         // Essential for CI: runs the browser without a GUI.
        options.addArguments("--disable-gpu");      // Avoids potential issues on some server environments.
        options.addArguments("--no-sandbox");       // Required for running as root in Linux/Docker environments.
        options.addArguments("--window-size=1920,1080"); // Sets a standard window size to prevent responsive layout issues.

        // Initialize the driver with the configured options.
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
