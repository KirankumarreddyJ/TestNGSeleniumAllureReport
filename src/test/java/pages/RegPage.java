package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*
 * This class contains all Automation registration page web elements & action methods
 */
public class RegPage extends BasePage{
	
	public RegPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@ng-model='FirstName']")
	WebElement fName_Box;
	
	@FindBy(xpath = "//input[@ng-model='LastName']")
	WebElement lName_tBox;
	
	@FindBy(xpath = "//label[text()='Address']/following-sibling::div//textarea[@ng-model='Adress']")
	WebElement address_tBox;
	
	@FindBy(xpath = "//input[@type='email']")
	WebElement email_tBox;
	
	@FindBy(xpath = "//input[@type='tel']")
	WebElement phoneNum_tBox;
	
	@FindBy(xpath = "//input[@value='Male']")
	WebElement male_rBtn;
	
	@FindBy(xpath = "//input[@value='FeMale']")
	WebElement female_rBtn;
	
	@FindBy(id = "msdd")
	WebElement language_rBtn;
	
	@FindBy(xpath = "//select[@id='Skills']")
	WebElement skills_drp;
	
	@FindBy(xpath = "//span[@id='select2-country-container']/parent::span[@role='combobox']")
	WebElement country_cBox;
	
	@FindBy(id = "yearbox")
	WebElement year_drp;
	
	@FindBy(xpath = "//select[@placeholder='Month']")
	WebElement month_drp;
	
	@FindBy(id = "daybox")
	WebElement day_drp;
	
	@FindBy(id = "firstpassword")
	WebElement password_tBox;
	
	@FindBy(id = "secondpassword")
	WebElement confPassword_tBox;
	
	@FindBy(id = "imagesrc")
	WebElement chooseFile_btn;
	
	@FindBy(id = "submitbtn")
	WebElement submit_btn;
	
	@FindBy(id = "Button1")
	WebElement refresh_btn;
	
	@Step("Enter first name '{0}' step")
	public void enterFristName(String fName) {
		assertTrue(selHelper.enterVal(fName_Box, fName), "Failed to enter '"+fName+"' into First Name textbox");
	}
	
	@Step("Enter last name '{0}' step")
	public void enterlastName(String lName) {
		assertTrue(selHelper.enterVal(lName_tBox, lName), "Failed to enter '"+lName+"' into Last Name textbox");
	}
	
	@Step("Enter address '{0}' step")
	public void enterAddress(String address) {
		assertTrue(selHelper.enterVal(address_tBox, address), "Failed to enter '"+address+"' into Address textbox");
	}
	
	@Step("Enter email address '{0}' step")
	public void enterEmail(String email) {
		assertTrue(selHelper.enterVal(email_tBox, email), "Failed to enter '"+email+"' into Email Address textbox");
	}
	
	@Step("Enter Phone number '{0}' step")
	public void enterPhoneNum(String pNum) {
		assertTrue(selHelper.enterVal(phoneNum_tBox, pNum), "Failed to enter '"+pNum+"' into Phone Number textbox");
	}
	
	@Step("Select Gender '{0}' step")
	public void selectGender(String gender) {
		if(gender.trim().equalsIgnoreCase("male")) {
			assertTrue(selHelper.click(male_rBtn), "Failed to click on 'Male' radio button");
		}else if(gender.trim().equalsIgnoreCase("female")){
			assertTrue(selHelper.click(female_rBtn), "Failed to click on 'Female' radio button");
		}else {
			assertFalse(true, "Invalid Gender value passed. Expected ['male', 'female'] but found '"+gender+"'");
		}
	}
	
	@Step("Select Hobbies '{0}' step")
	public void selectHobbies(String hobbies) {
		for(String hobby: hobbies.split(";")) {
			String xpath = "//input[@type='checkbox' and @value='"+hobby.trim()+"']";
			WebElement hobby_cBox = selHelper.getWebElement(By.xpath(xpath));
			assertTrue(selHelper.click(hobby_cBox), "Failed to click on '"+hobby.trim()+"' hobbies checkbox");
		}
	}
	
	@Step("Select Languages '{0}' step")
	public void selectLanguages(String languages) {
		assertTrue(selHelper.click(language_rBtn), "Failed to click on languages dropdown");
		for(String language: languages.split(";")) {
			String xpath = "//div[@id='msdd']/following-sibling::*//li//a[contains(text(),'"+language.trim()+"')]";
			WebElement language_opt = selHelper.getWebElement(By.xpath(xpath));
			assertTrue(selHelper.click(language_opt), "Failed to click on '"+language.trim()+"' language in languages checkbox");
		}
	}
	
	@Step("Select skills '{0}' step")
	public void selectSkills(String skillName) {
		assertTrue(selHelper.selectDropdownByName(skills_drp, skillName.trim()), "Failed to select '"+skillName+"' in Skills dropdown");
	}
	
	@Step("Select Country '{0}' step")
	public void selectCountry(String country) {
		assertTrue(selHelper.click(country_cBox), "Failed to click on Country dropdown");
		String xpath = "//ul[@id='select2-country-results']//li[contains(text(),'"+country.trim()+"')]";
		WebElement country_opt = selHelper.getWebElement(By.xpath(xpath));
		assertTrue(selHelper.click(country_opt), "Failed to click on '"+country.trim()+"' country in Country dropdown");
	}
	
	@Step("Select Year '{0}' step")
	public void selectYear(String year) {
		assertTrue(selHelper.selectDropdownByName(year_drp, year.trim()), "Failed to select '"+year+"' year in Year dropdown");
	}

	@Step("Select Month '{0}' step")
	public void selectMonth(String month) {
		assertTrue(selHelper.selectDropdownByName(month_drp, month.trim()), "Failed to select '"+month+"' month in Month dropdown");
	}

	@Step("Select Day '{0}' step")
	public void selectDay(String day) {
		assertTrue(selHelper.selectDropdownByName(day_drp, day.trim()), "Failed to select '"+day+"' day in Day dropdown");
	}

	@Step("Enter Password name '{0}' step")
	public void enterPassword(String pwd) {
		assertTrue(selHelper.enterVal(password_tBox, pwd), "Failed to enter '"+pwd+"' into Password textbox");
	}

	@Step("Enter Confirm Password name '{0}' step")
	public void enterConfPassword(String pwd) {
		assertTrue(selHelper.enterVal(confPassword_tBox, pwd), "Failed to enter '"+pwd+"' into Confirm Password textbox");
	}
	
	@Step("Upload Photo step")
	public void uploadPhoto(String path) {
		assertTrue(selHelper.uploadFile(chooseFile_btn, path), "Failed to upload photo");
	}
	
	@Step("Click on Submit button step")
	public void clickOnSubmit() {
		assertTrue(selHelper.click(submit_btn), "Failed to click on submit button");
	}
	
	@Step("Click on Refresh button step")
	public void clickOnRefresh() {
		assertTrue(selHelper.click(refresh_btn), "Failed to click on Refresh button");
	}
	
}
