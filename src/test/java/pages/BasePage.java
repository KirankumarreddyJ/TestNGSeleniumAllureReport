package pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;

import base.BaseTest;
import constants.Constants;
import helpers.SeleniumHelper;

/* #########################################################################
Class Name   : BasePage
Purpose      : This is a base class to all page classes
               This class will handle to initialize web elements using page factory

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 01/05/2023 
############################################################################# */
public class BasePage{
	
	protected WebDriver driver;
	protected SeleniumHelper selHelper;
	
	/*
	 *  To initialize the normal web elements 
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		selHelper = new SeleniumHelper(driver);
	}
	
	/*
	 *  To initialize the Ajax web elements 
	 */
	public BasePage(WebDriver driver, boolean isAjaxElementsInPage) {
		this.driver = driver;
		if(isAjaxElementsInPage) {
			PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.DEFAULT_ELEMENT_WAIT_TIME), this);
		}else {
			PageFactory.initElements(driver, this);
		}
		selHelper = new SeleniumHelper(driver);
	}
	
}
