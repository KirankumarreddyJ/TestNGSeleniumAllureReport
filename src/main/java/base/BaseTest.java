package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import helpers.ConfigHelper;
import helpers.SeleniumHelper;

/* #########################################################################
Class Name   : BaseTest
Purpose      : This is a base class to all test classes
               This class will handle webdriver & browser setup and teardown
Note         : This class can be used to define Before & After setup for 
               module & test level execution

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 25/04/2023 
############################################################################# */
public class BaseTest {
	
	public WebDriver driver;
	public static Properties prop;
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
	
	@BeforeSuite(alwaysRun = true)
	public void setUpSuite() {
		//Loads the properties file
		prop = ConfigHelper.initialize_Properties();
	}
	
//	@AfterSuite(alwaysRun = true)
//	public void tearDownSuite() {
//		// Write code to execute after suite execution
//	}
//	
//	@BeforeTest(alwaysRun = true)
//	public void setUpTest() {
//		// Write code to execute before test execution
//	}
//	
//	@AfterTest(alwaysRun = true)
//	public void tearDownTest() {
//		// Write code to execute after test execution
//	}
	
	/*   ###############################################################
	Method Name  : setupDriver
	Purpose      : Open browser based on browser name and set driver to thread local
	Input        : String browserName
	Output       : WebDriver driver
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 25/04/2023 
	##################################################################### */
	public WebDriver setupDriver(String browserName) {
		if(browserName.equals(null)) {
			throw new IllegalArgumentException("Browser value can't be null");
		}
		driver = SeleniumHelper.launchBrowser(browserName);
		tdriver.set(driver);
		return getDriver();
	}
	
	/*   ###############################################################
	Method Name  : getDriver
	Purpose      : To get driver object from thread local
	Input        : None
	Output       : WebDriver driver
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 25/04/2023 
	##################################################################### */
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

}
