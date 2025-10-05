package com.automation.megamind.base;


import com.automation.megamind.constants.Constants;
import com.automation.megamind.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.awt.*;
import java.time.Duration;

import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;
import static com.automation.megamind.utils.PropertyReader.getProperty;

public class Base {

   public static WebDriver driver;


   public static WebDriver launchApp(){

       if( getProperty(CONFIG_PROPERTIES  ,"browserType" ).equals("chrome")){
           System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver/chromedriver.exe");
           driver = new ChromeDriver();
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(getProperty(CONFIG_PROPERTIES , "timeout"))));
           driver.get(getProperty(CONFIG_PROPERTIES , "url"));
       } else if (  getProperty(CONFIG_PROPERTIES  ,"browserType" ).equals("edge")) {
           System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/test/resources/edgedriver/edgedriver.exe");
           driver = new EdgeDriver();
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(getProperty(CONFIG_PROPERTIES , "timeout"))));
           driver.get(getProperty(CONFIG_PROPERTIES , "url"));

       }

       return driver;


   }


}
