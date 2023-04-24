package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import helpers.SeleniumHelper;
import io.qameta.allure.Step;
import qa.base.BaseTest;

public class LoginPage extends BaseTest{
	public final static String LOGINPAGEURL = "https://www.saucedemo.com/";
	public static SeleniumHelper selHelper;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		selHelper = new SeleniumHelper(driver);
//		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
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
		
//		userId.sendKeys(username);
//		password.sendKeys(pwd);
//		loginBtn.click();
//		assertEquals(driver.getCurrentUrl(), HomePage.HOMEPAGEURL);
		return  new HomePage(driver);
	}
	
}
