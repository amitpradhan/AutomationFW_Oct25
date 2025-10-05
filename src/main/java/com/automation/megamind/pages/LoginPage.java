package com.automation.megamind.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;
import static com.automation.megamind.utils.PropertyReader.getProperty;

public class LoginPage  {
    //Page Object Model Design Pattern
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(xpath = "//a[text()=' Signup / Login']")
    WebElement signUpLoginBtn;
    @FindBy(xpath = "//input[@name='email']")
    WebElement userEmailId;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = " //button[text()='Login']")
    WebElement loginBtn;

    public void login(){
        signUpLoginBtn.click();
        userEmailId.sendKeys(getProperty(CONFIG_PROPERTIES  ,"userName" ));
        password.sendKeys(getProperty(CONFIG_PROPERTIES , "pwd"));
        loginBtn.click();

    }








}
