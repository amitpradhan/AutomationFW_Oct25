package stepDefinitions;

import com.automation.megamind.base.WebDriverContext; // <-- Import the shared context object
import com.automation.megamind.pages.HomePage;
import com.automation.megamind.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

/**
 * Step definitions for Login Page functionality.
 * Uses Constructor Dependency Injection (DI) to safely access the WebDriver
 * initialized in the Hooks class via the shared WebDriverContext.
 */
public class LoginPageSteps { // Removed 'extends Hooks'

    private LoginPage loginPage;
    private HomePage homePage;

    /**
     * Constructor Injection: Cucumber automatically instantiates this class once per scenario
     * and provides the shared WebDriverContext object (initialized by Hooks).
     * This is the safest place to instantiate Page Objects.
     */
    public LoginPageSteps(WebDriverContext context) {
        // Initialize the Page Objects using the driver instance from the shared context.
        this.loginPage = new LoginPage(context.driver);
        this.homePage = new HomePage(context.driver);
    }

    @Given("I am logged into application with user {string}")
    public void i_am_logged_into_application_with_user(String user) {
        // Page Objects are guaranteed to be initialized here.
        loginPage.login(user);

        String loggedInText = homePage.getUserLoggedInText();
        String expectedText = "Logged in as " + user;

        Assert.assertEquals("Login not successful. Expected: '" + expectedText + "', but found: '" + loggedInText + "'", expectedText, loggedInText);
    }

    @Then("I should be logged in successfully.")
    public void i_should_be_logged_in_successfully() {
        String actualLogoutText = homePage.getUserLoggedOutText();
        String expectedLogoutText = "Logout";

        Assert.assertEquals("Login not successful. Expected: '" + expectedLogoutText + "', but found: '" +
                actualLogoutText + "'", expectedLogoutText, actualLogoutText);

    }
}
