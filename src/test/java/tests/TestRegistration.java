package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.Constants;
import dataproviders.MyDataProvider;
import helpers.SeleniumHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestAllureListener;
import pages.RegPage;

@Listeners(value = TestAllureListener.class)
public class TestRegistration extends BaseTest {
	protected RegPage regPage;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		SeleniumHelper.navigateToURL(getDriver(), Constants.AT_REG_URL);
		regPage = new RegPage(getDriver());
	}

	@Test(dataProvider = "getRegDetails", dataProviderClass = MyDataProvider.class, groups = { "Sanity",
			"Regression" }, enabled = true)
	@Description(value = "Verify valid Login")
	@Severity(SeverityLevel.BLOCKER)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyRegistration(String fName, String lName, String address, String email, String phone,
			String gender, String hobbies, String languages, String skills, String country, String year, String month,
			String day, String pwd, String cPwd) {

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
