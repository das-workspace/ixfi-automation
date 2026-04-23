package com.ixfi.basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ixfi.actiondriver.Action;
import com.ixfi.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

//import org.apache.log4j.xml.DOMConfigurator;
public class BaseClass {

	// this base class is the parent class of all the classes

	public static Properties prop;
	public static WebDriver driver; // my general driver
	Scanner sc = new Scanner(System.in);

	@FindBy(xpath = "//button[@class='cookie-btn btn-default']")
	WebElement acceptCokiesButton;
	// public static Action action;

	// ThreadLocal driver declaration -- for parallel execution purpose
//	public static ThreadLocal<RemoteWebDriver> driver1 =new ThreadLocal(); // driver for parallel execution
//	
//	public static WebDriver getDriver()
//	{
//		return driver1.get(); //Returns the value in the current thread's copy of thisthread-local variable. -- parallel execution
//		//now instead of driver we will use getDriver() method
//	}

	@BeforeSuite
	public void loadConfiguration() throws IOException {
		// Load the log4j2.xml from a specific location
		ExtentManager.setExtent();
		Configurator.initialize(null, "log4j2.xml");
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Load configuration properties
//    @BeforeTest
//    public void loadConfig() {
//        try {
//            prop = new Properties();
//            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
//            prop.load(fis);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

	// Initialize WebDriver before each test class or method
	@BeforeMethod
	public void setup() throws InterruptedException {
		initializeDriver();
		launchApp();

	}

	// Initialize the WebDriver based on the config properties
	public static void initializeDriver() {
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			// driver.set(new InternetExplorerDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser " + browserName + " is not supported.");
		}
	}

	// Launch the application
	public void launchApp() throws InterruptedException {
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized.");
		}

		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Thread.sleep(4000);
		Action.waitForLoaderToDisappear(driver);
		// Apply timeouts
		Action.implicitWait(driver, 15);
		Action.pageLoadTimeOut(driver, 15);
		Action.acceptCookies(acceptCokiesButton);
		// Action.acceptCookies();
	}

	public String getOTP() {
		// here i have handled code manually, user can enter otp in console and fetch it
		// from there
		System.out.println("Enter otp");
		String otp = sc.nextLine();
		System.out.println("Otp is: " + otp);
		return otp;
		// add code to fetch otp from here
		// OR you can simply create on class under basePage which contains the OTP
		// fetching code -- API
	}

	// Close the driver after each test method
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
