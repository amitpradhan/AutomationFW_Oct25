package com.automation.megamind.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;
import static com.automation.megamind.utils.PropertyReader.getProperty;
import static com.automation.megamind.utils.StringEncryptor.getPwd;

/**
 * Page Object Model for the Login page.
 * NOTE: This class no longer extends 'Hooks' as that caused a compiler error
 * due to the change in the Hooks constructor for Dependency Injection.
 */
public class LoginPage { // Removed 'extends Hooks'

    // Declare the WebDriver instance within the Page Object
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        // This is where all @FindBy elements are initialized (proxies created)
        PageFactory.initElements(driver ,this);
    }

    // Web Elements
    @FindBy(xpath = "//a[text()=' Signup / Login']")
    WebElement signUpLoginBtn;

    @FindBy(xpath = "//input[@name='email']")
    WebElement userEmailId;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;

    // Actions
    public void login(){
        signUpLoginBtn.click();
        userEmailId.sendKeys(getProperty(CONFIG_PROPERTIES  ,"userName" ));
        password.sendKeys(getProperty(CONFIG_PROPERTIES , "pwd"));
        loginBtn.click();
    }

    public void login(String user){
        signUpLoginBtn.click();
        userEmailId.sendKeys(getProperty(CONFIG_PROPERTIES  ,"userName" ));
        // Assuming getPwd(user) retrieves the password based on the user string
        password.sendKeys(getPwd(user));
        loginBtn.click();
    }
}
