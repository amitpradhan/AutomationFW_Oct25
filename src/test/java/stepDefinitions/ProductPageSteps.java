package stepDefinitions;

import com.automation.megamind.base.WebDriverContext;
import com.automation.megamind.pages.HomePage;
import com.automation.megamind.pages.LoginPage;
import com.automation.megamind.pages.ProductPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ProductPageSteps {
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;

    /**
     * Constructor Injection: Cucumber automatically instantiates this class once per scenario
     * and provides the shared WebDriverContext object (initialized by Hooks).
     * This is the safest place to instantiate Page Objects.
     */
    public ProductPageSteps(WebDriverContext context) {
        // Initialize the Page Objects using the driver instance from the shared context.
        this.loginPage = new LoginPage(context.driver);
        this.homePage = new HomePage(context.driver);
        this.productPage = new ProductPage(context.driver);
    }


    @Then("the item got added successfully")
    public void the_item_got_added_successfully() {
        Assert.assertEquals("Product did not get added successfully.", "Added!", productPage.productAddedSuccessText());

    }
}
