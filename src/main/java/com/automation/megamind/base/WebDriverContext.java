package com.automation.megamind.base;

import org.openqa.selenium.WebDriver;

/**
 * This class serves as the World (Context) class for PicoContainer.
 * It holds the shared WebDriver instance for Dependency Injection.
 * The public 'driver' field allows the Hooks class to directly initialize it.
 */
public class WebDriverContext {

    // IMPORTANT: Declared public to allow direct assignment by the Hooks class (context.driver = new ChromeDriver())
    public WebDriver driver;

    public WebDriverContext() {
        // Public constructor for PicoContainer DI
    }
}
