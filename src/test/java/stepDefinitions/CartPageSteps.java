package stepDefinitions;

import com.automation.megamind.base.WebDriverContext;
import com.automation.megamind.pages.CartPage;
import com.automation.megamind.pages.HomePage;
import com.automation.megamind.pages.LoginPage;
import com.automation.megamind.pages.ProductPage;
import io.cucumber.java.en.Then;

public class CartPageSteps {

    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    /**
     * Constructor Injection: Cucumber automatically instantiates this class once per scenario
     * and provides the shared WebDriverContext object (initialized by Hooks).
     * This is the safest place to instantiate Page Objects.
     */
    public CartPageSteps(WebDriverContext context) {
        // Initialize the Page Objects using the driver instance from the shared context.
        this.homePage = new HomePage(context.driver);
        this.productPage = new ProductPage(context.driver);
        this.cartPage = new CartPage(context.driver);
    }


    @Then("check Price and Quantity in Cart page")
    public void check_price_and_quantity_in_cart_page() throws InterruptedException {
        cartPage.clickOnViewCart();
        System.out.println("AMIT....");
        System.out.println(cartPage.getProductPriceBasedOnProductDescription("Blue Top"));
        Thread.sleep(5000);
    }

    @Then("validate the total amount")
    public void validate_the_total_amount() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
