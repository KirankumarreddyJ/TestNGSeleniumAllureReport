package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import dataproviders.MyDataProvider;
import helpers.ConfigHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import listeners.RetryAnalyzer;
import listeners.TestAllureListener;
import pages.HomePage;
import pages.LoginPage;

@Listeners(value = TestAllureListener.class)
public class LoginTest{
	public BaseTest baseTest;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	public void setUp(@Optional("chrome") String browser) {
		baseTest = new BaseTest();
		prop = ConfigHelper.initialize_Properties();
		driver = baseTest.initialize_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		baseTest.tearDown();
	}
	
	@Test(dataProvider = "getValidLoginData", dataProviderClass = MyDataProvider.class,enabled = true)
	@Description(value = "Verify valid Login")
	@Severity(SeverityLevel.BLOCKER)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyValidLogin(String username, String pwd) {
		loginPage.login(username, pwd);
	}
	
	@Test(dataProvider = "getInvalidLoginData", dataProviderClass = MyDataProvider.class, 
			enabled=true, retryAnalyzer = RetryAnalyzer.class )
	@Description(value = "Verify invalid Login")
	@Severity(SeverityLevel.NORMAL)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyInValidLogin(String username, String pwd) {
		System.out.println("User name: "+username+" Password: "+pwd);
		loginPage.login(username, pwd);
	}
	
	@Test(dataProvider = "getValidLoginData", dataProviderClass = MyDataProvider.class, enabled = false)
	@Description(value = "Verify Login and Logout")
	@Severity(SeverityLevel.CRITICAL)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA002")
	public void verifyValidLoginLogout(String username, String pwd) {
		homePage = loginPage.login(username, pwd);
		homePage.logout();
	}
}
