package tests;

import static helpers.AllureLogUril.logALStep;

import org.testng.annotations.BeforeMethod;
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
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import listeners.RetryAnalyzer;
import listeners.TestAllureListener;
import pages.HomePage;
import pages.LoginPage;

@Listeners(value = TestAllureListener.class)
public class LoginTest extends BaseTest {
	protected LoginPage loginPage;
	protected HomePage homePage;

	@BeforeMethod(alwaysRun = true)
	@Step(value = "Navigate to URL step")
	public void setUpMethod() {
		SeleniumHelper.navigateToURL(getDriver(), Constants.SEUCEDEMO_LOGIN_URL);
		logALStep("Navigated to URL: " + Constants.AT_REG_URL);
		loginPage = new LoginPage(getDriver());
	}

	@Test(dataProvider = "getValidLoginData", dataProviderClass = MyDataProvider.class, enabled = true)
	@Description(value = "Verify valid Login")
	@Severity(SeverityLevel.BLOCKER)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyValidLogin(String username, String pwd) {
		loginPage.login(username, pwd);
		logALStep("Completed this testcase execution");
	}

	@Test(dataProvider = "getInvalidLoginData", dataProviderClass = MyDataProvider.class, enabled = true, retryAnalyzer = RetryAnalyzer.class)
	@Description(value = "Verify invalid Login")
	@Severity(SeverityLevel.NORMAL)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA001")
	public void verifyInValidLogin(String username, String pwd) {
		System.out.println("User name: " + username + " Password: " + pwd);
		loginPage.login(username, pwd);
		logALStep("Completed this testcase execution");
	}

	@Test(dataProvider = "getValidLoginData", dataProviderClass = MyDataProvider.class, enabled = true)
	@Description(value = "Verify Login and Logout")
	@Severity(SeverityLevel.CRITICAL)
	@Feature(value = "Login Feature")
	@Epic(value = "Login Functionality")
	@Story(value = "QA002")
	public void verifyValidLoginLogout(String username, String pwd) {
		homePage = loginPage.login(username, pwd);
		homePage.logout();
		logALStep("Completed this testcase execution");
	}
}
