package com.selenium.base;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BaseClass {

    public static WebDriver driver = null;
   public static Logger log = LogManager.getLogger(BaseClass.class);



    public static void writeLog(String msg)
    {
        log.info(msg);
    }

    public static void writeErrorLof(Throwable t)
    {
        log.error(t.getMessage());
    }

    /**
     * @throws IOException
     * @Author Patil
     * @Description This method is to launch the application using the required browser
     */

    @BeforeMethod(alwaysRun = true)
    public static void launchApplication() throws IOException {

        String browser = getConfigData("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get(getConfigData("url"));
        // Adding implicit wait
        int waittime = Integer.parseInt(getConfigData("waittime"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waittime));

    }


    public static String getConfigData(String key) throws IOException {
        String propVal = "";

        File f = new File("./src/test/java/data/config.properties");
        FileInputStream fio = new FileInputStream(f);

        // Creating the object of properties file
        Properties prop = new Properties();

        // using the load method of properties class to load the input stream
        prop.load(fio);

        // to get  the property value based on key
        propVal = prop.getProperty(key);
        return propVal;

    }

    public static String getLocatorData(String key) throws IOException {
        String propVal = "";

        File f = new File("./src/test/java/data/locatordata.properties");
        FileInputStream fio = new FileInputStream(f);

        // Creating the object of properties file
        Properties prop = new Properties();

        // using the load method of properties class to load the input stream
        prop.load(fio);

        // to get  the property value based on key
        propVal = prop.getProperty(key);
        return propVal;

    }

    public static String getTestData(String key) throws IOException {
        String propVal = "";

        File f = new File("./src/test/java/data/testdata.properties");
        FileInputStream fio = new FileInputStream(f);

        // Creating the object of properties file
        Properties prop = new Properties();

        // using the load method of properties class to load the input stream
        prop.load(fio);

        // to get  the property value based on key
        propVal = prop.getProperty(key);
        return propVal;

    }

    @AfterMethod(alwaysRun = true)
    public static  void closeBrowser()
    {
        driver.quit();
    }


    public static void writeResultsToFile(String testCaseName, String testCaseStatus) throws IOException {

        //File f = new File("C:\\Users\\Sudhanva\\Desktop\\SaagTrainings\\SauceDemoAppAutomation\\src\\results\\results.txt");
        File f = new File("./src/test/java/results/results.txt");
        FileWriter fw = new FileWriter(f,true);

        fw.write(testCaseName +"-----"+testCaseStatus+"\n");

        fw.flush();
        fw.close();


    }

    public static void writeLogsToFile(String logMsg) throws IOException {
        writeLog(logMsg);

        //File f = new File("C:\\Users\\Sudhanva\\Desktop\\SaagTrainings\\SauceDemoAppAutomation\\src\\results\\logs.txt");
        File f = new File("./src/test/java/results/logs.txt");
        FileWriter fw = new FileWriter(f,true);

        fw.write(logMsg +"\n");
        System.out.println(logMsg);

        fw.flush();
        fw.close();


    }

    public static void captureScreenShot(String fileName) throws IOException {


        // Down casting driver to Take Screenshot level and capturng screen shot as file
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Creating a file class object to save the screen shot as a file
        File dest = new File("./src/test/java/results/screenshots/"+fileName+".png");

        // Copying src to destination ,, this will create the physical file in the destination location
        FileHandler.copy(source, dest);


    }

    @BeforeSuite(alwaysRun = true)
    public static void clearResultsAndLogs() throws IOException {

        // Clearing the previous run logs from the logfile
        File f = new File("./src/test/java/results/logs.txt");
        FileWriter fw = new FileWriter(f);
        fw.write("Capturing the logs for the current run \n");
        fw.flush();
        fw.close();

        // Clearing the previous run results from the logfile
        File f1 = new File("./src/test/java/results/results.txt");
        FileWriter fw1 = new FileWriter(f1);
        fw1.write("Capturing the Results for the current run \n");
        fw1.flush();
        fw1.close();


    }


    @AfterSuite(alwaysRun = true)
    public static void afterSuiteMethod()
    {
        System.out.println(" This method will run after the complete execution");

    }

    @BeforeClass(alwaysRun = true)
    public static  void beforeClassMethod()
    {
        System.out.println("This method will run before every class");
    }

    @AfterClass(alwaysRun = true)
    public static  void afterClassMethod()
    {
        System.out.println("This method will run after every class");
    }


    public static void readDataFromExcelFile() throws Exception {
    {
        File f = new File("./src/test/java/data/locatordata.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getRow(1).getCell(1).toString());
    }

    }





}
