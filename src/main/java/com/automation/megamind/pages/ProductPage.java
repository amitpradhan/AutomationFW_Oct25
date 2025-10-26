package com.automation.megamind.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(xpath = "//h4[text()='Added!']")
    WebElement productAddText;

    public String productAddedSuccessText(){
        return productAddText.getText();
    }




}
