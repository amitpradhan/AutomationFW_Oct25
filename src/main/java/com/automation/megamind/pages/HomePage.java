package com.automation.megamind.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    public String getUserLoggedInText(){
        return loggedInUserText.getText();
    }

    public String getUserLoggedOutText(){
        return logoutText.getText();
    }


}
