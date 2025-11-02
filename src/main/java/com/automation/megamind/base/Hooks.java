//package com.automation.megamind.base;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import java.time.Duration;
//
//// Assuming these static imports are correctly resolving for the user
//import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;
//import static com.automation.megamind.utils.PropertyReader.getProperty;
//
///**
// * Cucumber Hooks class for managing the WebDriver lifecycle.
// * Uses Dependency Injection (DI) to share the WebDriver instance via WebDriverContext.
// */
//public class Hooks {
//
//    // Inject the shared context object. Cucumber/PicoContainer will create and share this.
//    private WebDriverContext context;
//
//    // Constructor Injection: Cucumber automatically instantiates this class and
//    // provides the shared context object.
//    public Hooks(WebDriverContext context) {
//        this.context = context;
//    }
//
//    /**
//     * @Before hook: Runs before every scenario. Initializes the WebDriver.
//     */
//    @Before
//    public void launchApp(){
//        String browserType = getProperty(CONFIG_PROPERTIES, "browserType");
//        String url = getProperty(CONFIG_PROPERTIES, "url");
//        long timeout = Long.parseLong(getProperty(CONFIG_PROPERTIES, "timeout"));
//
//        if(browserType.equals("chrome")){
//            // NOTE: Selenium Manager usually handles driver executable setup now,
//            // but keeping System.setProperty for compatibility with older setups.
//            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver/chromedriver.exe");
//            context.driver = new ChromeDriver();
//        } else if (browserType.equals("edge")) {
//            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/test/resources/edgedriver/edgedriver.exe");
//            context.driver = new EdgeDriver();
//        } else {
//            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
//        }
//
//        context.driver.manage().window().maximize();
//        context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
//        context.driver.get(url);
//    }
//
//    /**
//     * @After hook: Runs after every scenario. Closes the browser.
//     */
//    @After
//    public void shutdown(){
//        if (context.driver != null) {
//            context.driver.quit();
//        }
//    }
//}



package com.automation.megamind.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

// Assuming these static imports are correctly resolving for the user
import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;
import static com.automation.megamind.utils.PropertyReader.getProperty;

/**
 * Cucumber Hooks class for managing the WebDriver lifecycle.
 * Uses Dependency Injection (DI) to share the WebDriver instance via WebDriverContext.
 */
public class Hooks {

    // The shared context object is injected by PicoContainer
    private WebDriverContext context;

    // Constructor Injection: Cucumber/PicoContainer will create and share this.
    public Hooks(WebDriverContext context) {
        this.context = context;
    }

    /**
     * @Before hook: Runs before every scenario. Initializes the WebDriver.
     * NOTE: Relies on Selenium Manager (Selenium 4.6+) to handle driver executables,
     * which is safer than using System.setProperty.
     */
    @Before(order = 1) // Using order=1 to ensure it runs before Page Object instantiation if needed
    public void launchApp(){
        // Safely retrieve properties
        String browserType = getProperty(CONFIG_PROPERTIES, "browserType");
        String url = getProperty(CONFIG_PROPERTIES, "url");
        long timeout = Long.parseLong(getProperty(CONFIG_PROPERTIES, "timeout"));

        WebDriver driver;

        // Initialize the appropriate WebDriver using Selenium Manager
        if(browserType.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            System.err.println("Unsupported browser type: " + browserType + ". Defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        // *** CRITICAL: Assign the newly initialized driver to the shared context ***
        this.context.driver = driver;

        this.context.driver.manage().window().maximize();
        this.context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        this.context.driver.get(url);
    }

    /**
     * @After hook: Runs after every scenario. Closes the browser and takes a screenshot on failure.
     */
    @After
    public void shutdown(Scenario scenario){
        if (context.driver != null) {
            // Take screenshot on failure for reporting
            if (scenario.isFailed() && context.driver instanceof TakesScreenshot) {
                try {
                    final byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                } catch (Exception e) {
                    System.err.println("Could not take screenshot: " + e.getMessage());
                }
            }

            // Quit the driver and clear the reference
            context.driver.quit();
            context.driver = null;
        }
    }
}
