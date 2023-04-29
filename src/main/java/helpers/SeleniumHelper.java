package helpers;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import constants.Constants;
import io.qameta.allure.Step;

/* #########################################################################
Class Name   : SeleniumHelper
Purpose      : This class handles to perform actions on web page.
               This class will contain all common methods which will be 
               helpful to perform actions on web page.
Note         : Define default wait time in Config property file

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 29/04/2023 
############################################################################# */

public class SeleniumHelper {
	WebDriver driver;
	static Properties prop;
	JavascriptExecutor executor;
	public WebDriverWait wait;
	private static final int DEFAULT_WAIT = ConfigHelper.getConfigIntVal("DEFAULT_ELEMENT_WAIT_TIME");
	
	public SeleniumHelper(WebDriver driver) {
		this.driver = driver;
		prop = BaseTest.prop;
		wait = getWebDriverWait(driver, DEFAULT_WAIT);
	}
	
	
	/***************** Webpage navigation Methods ********************/
	/*   ###############################################################
	Method Name  : navigateToURL
	Purpose      : Navigate to URL and wait until URL matches
	Input        : WebDriver driver, String url
	Output       : Boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static boolean navigateToURL(WebDriver driver, String url) {
		try {
			driver.get(url);
			getWebDriverWait(driver, DEFAULT_WAIT).until(ExpectedConditions.urlContains(url));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : navigateBack
	Purpose      : To navigate to back on webpage
	Input        : None
	Output       : Boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean navigateBack() {
		try {
			driver.navigate().back();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : navigateForward
	Purpose      : To navigate to forward on webpage
	Input        : None
	Output       : Boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean navigateFarward() {
		try {
			driver.navigate().forward();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : refreshPage
	Purpose      : To refresh webpage
	Input        : None
	Output       : Boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean refreshPage() {
		try {
			driver.navigate().refresh();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/***************** Get Methods from Webpage  ********************/
	/*   ###############################################################
	Method Name  : getWebDriverWait
	Purpose      : To create WebDriverWait object
	Input        : WebDriver driver, int seconds
	Output       : WebDriverWait object
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriverWait getWebDriverWait(WebDriver driver, int seconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(seconds));
	}
	
	/*   ###############################################################
	Method Name  : getTitle
	Purpose      : To get the title of driver focused web page
	Input        : None
	Output       : String browserTitle or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getCurrentURL
	Purpose      : To get the current URL of driver focused web page
	Input        : None
	Output       : String browserURL or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getWebElement
	Purpose      : To get the WebElement from the web page
	Input        : By byLocater
	Output       : WebElement element or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public WebElement getWebElement(By byLocater) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(byLocater));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getElementText
	Purpose      : To get the text from the web page
	Input        : None
	Output       : String text
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public String getElementText(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/************* Web element status verification Methods  *************/
	/*   ###############################################################
	Method Name  : verifyDisplayed
	Purpose      : To wait and check element is displayed
	Input        : By byLocater
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyDisplayed(By byLocater) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(byLocater)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifyDisplayed
	Purpose      : To wait and check element is displayed
	Input        : WebElement element
	Output       : WebElement element
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyDisplayed(WebElement element) {
		try {
			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifyDisplayed
	Purpose      : To wait and check element is displayed in given time.
	Input        : WebElement element, int waitTime
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyDisplayed(WebElement element, int waitTime) {
		try {
			return getWebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifyEnabled
	Purpose      : To wait and check element is enabled
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyEnabled(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return element.isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifyEnabled
	Purpose      : To wait and check element is enabled in given time.
	Input        : WebElement element, int waitTime
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyEnabled(WebElement element, int waitTime) {
		try {
			getWebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(element));
			return element.isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifyDisabled
	Purpose      : To wait and check element is disabled
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyDisabled(WebElement element) {
		try {
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(2))
					.withTimeout(Duration.ofSeconds(DEFAULT_WAIT))
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			Function<WebDriver, Boolean> disabled = new Function<WebDriver, Boolean>() {
			    public Boolean apply(WebDriver driver) {
			    return !element.isEnabled();
			    }
			};		
			fWait.until(disabled);
			return !element.isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*   ###############################################################
	Method Name  : verifyDisabled
	Purpose      : To wait and check element is disabled in given time
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifyDisabled(WebElement element, int waitTime) {
		try {
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(2))
					.withTimeout(Duration.ofSeconds(waitTime))
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			Function<WebDriver, Boolean> disabled = new Function<WebDriver, Boolean>() {
			    public Boolean apply(WebDriver driver) {
			    return !element.isEnabled();
			    }
			};		
			fWait.until(disabled);
			return !element.isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifySelected
	Purpose      : To wait and check element is selected
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifySelected(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeSelected(element));
			return element.isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : verifySelected
	Purpose      : To wait and check element is selected in given time
	Input        : WebElement element, int waitTime
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean verifySelected(WebElement element, int waitTime) {
		try {
			getWebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeSelected(element));
			return element.isSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/***********  WebPage clickable elements methods **********************/
	/*   ###############################################################
	Method Name  : click
	Purpose      : To click on the web page
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean click(WebElement element){
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			return true;
		} catch (Exception e) {
			try {
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : submit
	Purpose      : To submit the form or application
	Input        : WebElement element
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean submit(WebElement element){
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.submit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*******************  WebPage text box Method  ************************/
	/*   ###############################################################
	Method Name  : enterVal
	Purpose      : To enter the give value into field in web page
	Input        : WebElement element, String value
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean enterVal(WebElement element, String value) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/********************  WebPage Dropdown Methods  **********************/
	/*   ###############################################################
	Method Name  : selectDropdownByName
	Purpose      : To select the dropdown option based on given option name
	Input        : WebElement element, String optName
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean selectDropdownByName(WebElement element, String optName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(optName.trim());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : selectDropdownByIndex
	Purpose      : To select the dropdown option based on given option index
	Input        : WebElement dropdown, int optIndex
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean selectDropdownByIndex(WebElement element, int optIndex) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			dropdown.selectByIndex(optIndex);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : selectDropdownByValue
	Purpose      : To select the dropdown option based on given option value
	Input        : WebElement dropdown, String optValue
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean selectDropdownByValue(WebElement element, String optValue) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			dropdown.selectByValue(optValue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*   ###############################################################
	Method Name  : getDropdownOpts
	Purpose      : To get list of all options from dropdown
	Input        : WebElement dropdown element
	Output       : List<WebElement> options or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public List<WebElement> getDropdownOpts(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			List<WebElement> options = dropdown.getOptions();
			return options;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getSelectedDropdownOpt
	Purpose      : To get selected dropdown option
	Input        : WebElement dropdown element
	Output       : String selected option name or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public String getSelectedDropdownOpt(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			 String option = dropdown.getFirstSelectedOption().getText();
			return option;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getListOfSelectedDropdownOpts
	Purpose      : To get list of all selected options from dropdown
	Input        : WebElement dropdown element
	Output       : List<WebElement> selected option names or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public List<WebElement> getListOfSelectedDropdownOpts(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select dropdown = new Select(element);
			 List<WebElement> options = dropdown.getAllSelectedOptions();
			return options;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/************************  WebPage Alert Methods  *********************/
	/*   ###############################################################
	Method Name  : switchToAlert
	Purpose      : To switch to web alert
	Input        : WebDriver driver
	Output       : Alert web page alert
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static Alert switchToAlert(WebDriver driver) {
		try {
			getWebDriverWait(driver, DEFAULT_WAIT).until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : acceptAlert
	Purpose      : To click on OK or Accept button on web alert
	Input        : WebDriver driver
	Output       : WebDriver driver
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver acceptAlert(WebDriver driver) {
		try {
			switchToAlert(driver).accept();
			driver.switchTo().defaultContent();
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : dismissAlert
	Purpose      : To click on OK or Accept button on web alert
	Input        : WebDriver driver
	Output       : WebDriver driver
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver dismissAlert(WebDriver driver) {
		try {
			switchToAlert(driver).dismiss();
			driver.switchTo().defaultContent();
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : getTextFromAlert
	Purpose      : To get text on web alert
	Input        : WebDriver driver
	Output       : String text
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static String getTextFromAlert(WebDriver driver) {
		try {
			String text = switchToAlert(driver).getText();
			driver.switchTo().defaultContent();
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : enterTextIntoAlert
	Purpose      : To enter text on web alert
	Input        : WebDriver driver
	Output       : WebDriver driver
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver enterTextIntoAlert(WebDriver driver, String text) {
		try {
			switchToAlert(driver).sendKeys(text);
			driver.switchTo().defaultContent();
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/************************  WebPage windows Methods  *********************/
	/*   ###############################################################
	Method Name  : switchToWindowByTitle
	Purpose      : To switch to new browser window based on title
	Input        : WebDriver driver, String newWindowTitle
	Output       : WebDriver driver or null
	Note         : Returns null value if new window with given title not found 
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver switchToWindowByTitle(WebDriver driver, String newWindowTitle) {
		try {
			String orgWindow = driver.getWindowHandle();
			 Set<String> windows = driver.getWindowHandles();
			 Iterator<String> itr = windows.iterator();
			 while(itr.hasNext()) {
				 driver = driver.switchTo().window(itr.next());
				 if(driver.getTitle().trim().equalsIgnoreCase(newWindowTitle.toLowerCase().trim())) {
					 return driver;
				 }
			 }
			 driver.switchTo().window(orgWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : createNewWindowAndSwitch
	Purpose      : To create new window switch to it
	Input        : WebDriver driver
	Output       : WebDriver driver or null
	Note         : Returns null value if new window not created 
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver createNewWindowAndSwitch(WebDriver driver) {
		try {
			return driver.switchTo().newWindow(WindowType.WINDOW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*   ###############################################################
	Method Name  : createNewTabAndSwitch
	Purpose      : To create new browser tab switch to it
	Input        : WebDriver driver
	Output       : WebDriver driver or null
	Note         : Returns null value if new browser tab not created 
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static WebDriver createNewTabAndSwitch(WebDriver driver) {
		try {
			return driver.switchTo().newWindow(WindowType.TAB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*****************  WebPage upload files Method  *******************/
	/*   ###############################################################
	Method Name  : uploadFile
	Purpose      : To upload file in web page
	Input        : WebElement element, String fileAbsolutePath
	Output       : boolean true/false
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public boolean uploadFile(WebElement element, String fileAbsolutePath) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(fileAbsolutePath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
