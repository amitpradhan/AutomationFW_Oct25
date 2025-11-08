package stepDefinitions;

import com.automation.megamind.base.WebDriverContext;
import com.automation.megamind.pages.CartPage;
import com.automation.megamind.pages.HomePage;
import com.automation.megamind.pages.LoginPage;
import com.automation.megamind.pages.ProductPage;
import com.automation.megamind.utils.Log;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CartPageSteps {

    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    public int price ;
    public int quantity;
    public int total;

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


    @Then("check Price and Quantity in Cart page for {string}")
    public void check_price_and_quantity_in_cart_page(String productDescription) throws InterruptedException {
        Log.info("Clicking on view cart button..");

        cartPage.clickOnViewCart();
        Log.info("Getting product price..");
        price = cartPage.getProductPriceBasedOnProductDescription(productDescription);

        Log.info("Getting product quantity..");
        quantity = cartPage.getProductQuantityBasedOnProductDescription(productDescription);

        Log.info("Getting Total ..");
        total = cartPage.getCartTotal(productDescription);



        Log.info("Product Price: "+price);
        Log.info("No of Product added in the cart: "+quantity);
        Log.info("Total Price: "+total);
//
//        System.out.println("Product Price: "+price);
//        System.out.println("No of Product added in the cart: "+quantity);
//        System.out.println("Total Price: "+total);

    }

    @Then("validate the total amount")
    public void validate_the_total_amount() {
        int totalPrice = price * quantity;
        Log.endTestCase();
        Assert.assertEquals("Total price doesn't match",totalPrice , total );
    }
}
