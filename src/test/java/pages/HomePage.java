package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseTest;
import helpers.SeleniumHelper;
import io.qameta.allure.Step;

/*
 * This class contains all Home page web elements & action methods
 */
public class HomePage extends BaseTest{
	public final static String HOMEPAGEURL = "https://www.saucedemo.com/inventory.html";
	public static SeleniumHelper selHelper;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		selHelper = new SeleniumHelper(this.driver);
//		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}
	
	@FindBy(id = "react-burger-menu-btn")
	WebElement menuIcon;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	WebElement logoutBtn;
	
	@Step(value = "Verify user login successful step")
	public void verifyLoginSuccess() {
		selHelper.wait.until(ExpectedConditions.urlContains(HOMEPAGEURL));
		assertEquals(selHelper.getCurrentURL(), HOMEPAGEURL);
	}
	
	@Step(value = "Verify user logout successful step")
	public void logout() {
		//using selenium helper
		selHelper.click(menuIcon);
		selHelper.click(logoutBtn);
		selHelper.wait.until(ExpectedConditions.urlContains(LoginPage.LOGINPAGEURL));
		assertEquals(selHelper.getCurrentURL(), LoginPage.LOGINPAGEURL);
		
	}
	
	
}
