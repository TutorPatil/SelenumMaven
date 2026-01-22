package com.selenium.tests;

import com.selenium.base.BaseClass;
import com.selenium.utils.CommonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseClass {

    @Test(priority = 1)
    public static void login_001() throws Exception {
        writeLogsToFile("adding one line directly from git hub");
        writeLogsToFile("*****  starting the test case Login_001*****");
        writeLogsToFile("*****  added from git hub starting the test case Login_001*****");
        boolean result = CommonUtils.loginToSaucelabApp();
        writeLogsToFile("**** launched the app now trying to login*****");

        //Assert.assertTrue(result);
        Assert.assertTrue(result,"Could not login to saucelab app!!");
        writeLogsToFile("**** successfully logged in to the app*****");
        writeResultsToFile("login_001", "Pass");
        captureScreenShot("login_001");
        writeLogsToFile("*****  ending the test case Login_001*****");
        readDataFromExcelFile();
    }

    //@Test(priority = 2)
    @Test(enabled = false) // This method will not be picked by TestNG
    public static void login_002() throws Exception {

        writeLogsToFile("*****  starting the test case login_002*****");
        boolean result = CommonUtils.loginToSaucelabApp();
        writeLogsToFile("**** launched the app now trying to login*****");

        //Assert.assertTrue(result);
        Assert.assertTrue(result,"Could not login to saucelab app!!");
        writeLogsToFile("**** successfully logged in to the app*****");
        writeResultsToFile("login_002", "Pass");
        captureScreenShot("login_002");
        writeLogsToFile("*****  ending the test case login_002*****");
    }
}
