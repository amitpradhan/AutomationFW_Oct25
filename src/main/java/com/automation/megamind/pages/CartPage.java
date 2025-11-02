package com.automation.megamind.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
// Add imports for Explicit Wait
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    // Define a constant for the wait time
    private static final int TIMEOUT_SECONDS = 10;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }


    /**
     * ALl XPTAH
     * //table[@id='cart_info_table']/tbody//a[text()='Blue Top']
     * // table[@id='cart_info_table']/tbody//a[text()='Men Tshirt']
     * //table[@id='cart_info_table']/tbody/descendant::a[text()='Blue Top']
     * //table[@id='cart_info_table']/tbody/descendant::a[text()='Men Tshirt']
     *
     * // table[@id='cart_info_table']/tbody/descendant::a[text()='Men Tshirt']/ancestor::td/following-sibling::td[@class='cart_price']/p
     *
     * // table[@id='cart_info_table']/tbody/descendant::a[text()='Blue Top']/ancestor::td/following-sibling::td[@class='cart_quantity']/button
     *
     *
     * // table[@id='cart_info_table']/tbody/descendant::a[text()='Blue Top']/ancestor::td/following-sibling::td[@class='cart_total']/p
     *   *
     *
     *
     *
     */

    // --- XPATH Segments ---
    // The XPath is targeting the <p> element within the price <td>.
    private String cartProductDescription_xpath_start = "//table[@id='cart_info_table']/tbody/descendant::a[text()='";
    private String cartProductDescription_xpath_end = "']/ancestor::td/following-sibling::td[@class='cart_price']/p";

    //xpath for Quantity
    private String cartProductQuantity_xpath_start = "//table[@id='cart_info_table']/tbody/descendant::a[text()='";
    private String cartProductQuantity_xpath_end = "']/ancestor::td/following-sibling::td[@class='cart_quantity']/button";

    //xpath for Quantity
    private String cartTotal_xpath_start = "// table[@id='cart_info_table']/tbody/descendant::a[text()='";
    private String cartTotal_xpath_end = "']/ancestor::td/following-sibling::td[@class='cart_total']/p";



    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[3]/a")
    WebElement cartMenuLink;

    @FindBy(xpath = "//*[@id='cartModal']/div/div/div[2]/p[2]/a/u")
    WebElement viewCartLink;

    public int getProductPriceBasedOnProductDescription(String productDescription){
        // 1. Construct the full XPath
        String fullXPath = cartProductDescription_xpath_start + productDescription + cartProductDescription_xpath_end;

        // 2. Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        // 3. Wait until the element is VISIBLE before attempting to find it
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fullXPath)));

        // 4. Get the text from the now-found element
        String price = priceElement.getText();

        // Assuming price format is "Rs. 400"
        // rs. 400
//        System.out.println("AMIT    ->"+price);

        // 5. Extract the integer price value
        // Use trim() for robustness in case of extra spaces
        return Integer.parseInt(price.split(" ")[1].trim());
    }



    public int getProductQuantityBasedOnProductDescription(String productDescription){
        // 1. Construct the full XPath
        String fullXPath = cartProductQuantity_xpath_start + productDescription + cartProductQuantity_xpath_end;

        // 2. Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        // 3. Wait until the element is VISIBLE before attempting to find it
        WebElement quantityWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fullXPath)));

        // 4. Get the text from the now-found element
        String quantity = quantityWebElement.getText();

        // 5. Extract the integer price value
        return Integer.parseInt(quantity);
    }


    public int getCartTotal(String productDescription){
        // 1. Construct the full XPath
        String fullXPath = cartTotal_xpath_start + productDescription + cartTotal_xpath_end;

        // 2. Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        // 3. Wait until the element is VISIBLE before attempting to find it
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fullXPath)));

        // 4. Get the text from the now-found element
        String totalPrice = totalPriceElement.getText();

        // Assuming price format is "Rs. 400"
        // rs. 2000


        // 5. Extract the integer price value
        // Use trim() for robustness in case of extra spaces
        return Integer.parseInt(totalPrice.split(" ")[1].trim());
    }




    public void clickOnViewCart() {
        viewCartLink.click();
    }
    public void gotToCartPage() throws InterruptedException {
        Thread.sleep(5000);
        cartMenuLink.click();
    }
    }
