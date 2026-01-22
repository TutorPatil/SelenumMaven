package com.selenium.utils;

import com.selenium.base.BaseClass;
import org.openqa.selenium.By;

public class CommonUtils extends BaseClass {

    public static boolean loginToSaucelabApp() throws Exception {

        boolean result = false;
        String userName = getTestData("username");

        driver.findElement(By.xpath(getLocatorData("usernameTextbox"))).sendKeys(userName);
        driver.findElement(By.xpath(getLocatorData("passwordTextbox"))).sendKeys(getTestData("password"));
        driver.findElement(By.xpath(getLocatorData("loginButton"))).click();
        Thread.sleep(4000);
       // driver.switchTo().alert().dismiss();

        result = driver.findElement(By.xpath(getLocatorData("cartIcon"))).isDisplayed();
        return result;
    }
}
