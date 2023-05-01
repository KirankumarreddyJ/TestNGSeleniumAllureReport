package pages;

import static helpers.AllureLogUril.logALStep;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

/*
 * This class contains all Home page web elements & action methods
 */
public class HomePage extends BasePage{
	public final static String HOMEPAGEURL = "https://www.saucedemo.com/inventory.html";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "react-burger-menu-btn")
	WebElement menuIcon;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	WebElement logoutBtn;
	
	@Step(value = "Verify user login successful step")
	public void verifyLoginSuccess() {
		selHelper.waitWD.until(ExpectedConditions.urlContains(HOMEPAGEURL));
		assertEquals(selHelper.getCurrentURL(), HOMEPAGEURL);
		logALStep("Login Successfull!");
	}
	
	@Step(value = "Verify user logout successful step")
	public void logout() {
		//using selenium helper
		selHelper.click(menuIcon);
		logALStep("Clicked on Menu icon");
		selHelper.click(logoutBtn);
		logALStep("Clicked on Logout button");
		selHelper.waitWD.until(ExpectedConditions.urlContains(LoginPage.LOGINPAGEURL));
		assertEquals(selHelper.getCurrentURL(), LoginPage.LOGINPAGEURL);
		logALStep("Logout Successfull!");
	}
	
	
}
