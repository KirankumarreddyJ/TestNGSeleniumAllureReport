package pages;

import static helpers.AllureLogUril.logALStep;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		logALStep(String.format("Entered '%s' as Username",username));
		selHelper.enterVal(password, pwd);
		logALStep(String.format("Entered '%s' as Password",pwd));
		selHelper.click(loginBtn);
		logALStep("Clicked on Login button");
		assertEquals(selHelper.getCurrentURL(), HomePage.HOMEPAGEURL);
		logALStep("Login successfull!");
		return  new HomePage(driver);
	}
	
}
