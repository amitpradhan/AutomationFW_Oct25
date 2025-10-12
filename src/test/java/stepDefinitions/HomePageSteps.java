package stepDefinitions;

import com.automation.megamind.base.WebDriverContext;
import com.automation.megamind.pages.HomePage;
import com.automation.megamind.pages.LoginPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;

public class HomePageSteps {
    private LoginPage loginPage;
    private HomePage homePage;

    /**
     * Constructor Injection: Cucumber automatically instantiates this class once per scenario
     * and provides the shared WebDriverContext object (initialized by Hooks).
     * This is the safest place to instantiate Page Objects.
     */
    public HomePageSteps(WebDriverContext context) {
        // Initialize the Page Objects using the driver instance from the shared context.
        this.loginPage = new LoginPage(context.driver);
        this.homePage = new HomePage(context.driver);
    }

    @Then("I add first item to the cart")
    public void i_add_first_item_to_the_cart() {
        homePage.addFirstItemToCart();
    }

}
