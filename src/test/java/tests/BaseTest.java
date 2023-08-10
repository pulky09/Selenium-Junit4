package tests;

import org.junit.Before;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.Collection;

/**
 * All tests class inherit from the base test
 */

public class BaseTest {
    protected WebDriver driver;

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters()
    public static Collection<Object[]> crossPlatformData() {
        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"firefox"}
        });
    }


    @Before
    public void setUp() {

        switch (browser) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalStateException("unexpected value" + browser);

        }
    }

    @Rule
    public
    TestName name = new TestName();

    @Rule
    public
    TestRule watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            if (driver != null) {
                System.out.println("Test passed");
                driver.quit();
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (driver != null) {
                System.out.println("Test failed due to" + e.getMessage());
                driver.quit();
            }
        }
    };

}
