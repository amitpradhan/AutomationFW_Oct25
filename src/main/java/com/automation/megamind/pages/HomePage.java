package com.automation.megamind.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.automation.megamind.utils.SeleniumUtils.clickUsingJavaScriptExecutor;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a")
    WebElement loggedInUserText;

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement logoutText;

     By viewProductBtn = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");



    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button")
    WebElement addToCartProductPage;


    public String getUserLoggedInText(){
        return loggedInUserText.getText();
    }
    public String getUserLoggedOutText(){
        return logoutText.getText();
    }
    //Add first to cart on the default HomePage
    public void addFirstItemToCart(){

       clickUsingJavaScriptExecutor(driver,driver.findElement(viewProductBtn));

        addToCartProductPage.click();
    }

    //click on POLO and add second item to Cart
    public void searchAndAddItemToCart(){

    }




}
