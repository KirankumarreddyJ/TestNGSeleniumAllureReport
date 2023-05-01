package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import base.BaseTest;
import helpers.SeleniumHelper;
import io.qameta.allure.Step;

/*
 * This class contains all Login page web elements & action methods
 */
public class LoginPage extends BasePage{
	public final static String LOGINPAGEURL = "https://www.saucedemo.com/";
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "user-name")
	WebElement userId;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement loginBtn;
	
	@Step(value = "Login with username {0} and password {1} step")
	public HomePage login(String username, String pwd){
		selHelper.enterVal(userId, username);
		selHelper.enterVal(password, pwd);
		selHelper.click(loginBtn);
		assertEquals(selHelper.getCurrentURL(), HomePage.HOMEPAGEURL);
		return  new HomePage(driver);
	}
	
}
