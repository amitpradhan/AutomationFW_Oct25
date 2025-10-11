package com.automation.megamind.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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

    // Inject the shared context object. Cucumber/PicoContainer will create and share this.
    private WebDriverContext context;

    // Constructor Injection: Cucumber automatically instantiates this class and
    // provides the shared context object.
    public Hooks(WebDriverContext context) {
        this.context = context;
    }

    /**
     * @Before hook: Runs before every scenario. Initializes the WebDriver.
     */
    @Before
    public void launchApp(){
        String browserType = getProperty(CONFIG_PROPERTIES, "browserType");
        String url = getProperty(CONFIG_PROPERTIES, "url");
        long timeout = Long.parseLong(getProperty(CONFIG_PROPERTIES, "timeout"));

        if(browserType.equals("chrome")){
            // NOTE: Selenium Manager usually handles driver executable setup now,
            // but keeping System.setProperty for compatibility with older setups.
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver/chromedriver.exe");
            context.driver = new ChromeDriver();
        } else if (browserType.equals("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/test/resources/edgedriver/edgedriver.exe");
            context.driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        context.driver.manage().window().maximize();
        context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        context.driver.get(url);
    }

    /**
     * @After hook: Runs after every scenario. Closes the browser.
     */
    @After
    public void shutdown(){
        if (context.driver != null) {
            context.driver.quit();
        }
    }
}
