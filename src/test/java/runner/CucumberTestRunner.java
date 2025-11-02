package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Feature Files Location
        features = "src/test/resources/features",

        // Glue Paths: CRITICAL for DI. Tells Cucumber where to find Hooks AND Step Definitions.
        // Ensure both packages are listed so PicoContainer can find all classes.
        glue = {"stepDefinitions", "com.automation.megamind.base"},

        // Plugins: Removed the explicit "io.cucumber.picocontainer.Picocontainer" entry.
        // Cucumber will now automatically detect the DI dependency from the classpath.
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"},

        // Tags: Run only the scenarios matching the tags defined (e.g., "@cart_page and @validateTotalAmount").
        tags = "@smoke",

        monochrome = true,
        dryRun = false
)
public class CucumberTestRunner {
    // This class runs the features defined above using JUnit 4.
}

