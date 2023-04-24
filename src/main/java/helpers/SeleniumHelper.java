package helpers;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import qa.base.BaseTest;

public class SeleniumHelper {
	WebDriver driver;
	static Properties prop;
	JavascriptExecutor executor;
	public WebDriverWait wait;
	
	public static WebDriverWait getWebDriverWait(WebDriver driver, int seconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(seconds));
	}
	public SeleniumHelper(WebDriver driver) {
		this.driver = driver;
		prop = BaseTest.prop;
		wait = getWebDriverWait(driver, Integer.parseInt(prop.getProperty("DEFAULT_ELEMENT_WAIT_TIME")));
	}
	
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	
	public Boolean click(WebElement element){
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			return true;
		} catch (Exception e) {
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			return true;
		}
	}
	
	
	public Boolean enterVal(WebElement element, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(value);
		return true;
	}
	
}
