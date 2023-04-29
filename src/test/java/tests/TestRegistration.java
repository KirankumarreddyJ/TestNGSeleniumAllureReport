package tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.Constants;
import dataproviders.MyDataProvider;
import helpers.ConfigHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import pages.RegPage;

public class TestRegistration {
	protected BaseTest baseTest;
	protected WebDriver driver;
	protected Properties prop;
	protected RegPage regPage;

	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	public void setUp(@Optional("chrome") String browser) {
		baseTest = new BaseTest();
		prop = ConfigHelper.initialize_Properties();
		driver = baseTest.initialize_driver(browser);
		driver.get("https://demo.automationtesting.in/Register.html");
		regPage = new RegPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
//		baseTest.tearDown();
	}
	
	@Test(dataProvider = "getRegDetails", dataProviderClass = MyDataProvider.class,enabled = true)
	@Description(value = "Verify valid Login")
	@Severity(SeverityLevel.BLOCKER)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyRegistration(
			String fName, String lName, String address, String email, String phone, 
			String gender, String hobbies, String languages, String skills, String country, 
			String year, String month, String day, String pwd, String cPwd) {
		
		regPage.enterFristName(fName);
		regPage.enterlastName(lName);
		regPage.enterAddress(address);
		regPage.enterEmail(email);
		regPage.enterPhoneNum(phone);
		regPage.selectGender(gender);
		regPage.selectHobbies(hobbies);
		regPage.selectLanguages(languages);
		regPage.selectSkills(skills);
		regPage.selectCountry(country);
		regPage.selectYear(year);
		regPage.selectMonth(month);
		regPage.selectDay(day);
		regPage.enterPassword(pwd);
		regPage.enterConfPassword(cPwd);
		regPage.uploadPhoto(Constants.TESTDATA_IMAGE_PATH);
		regPage.clickOnSubmit();
//		regPage.clickOnRefresh();
		
	}
}
