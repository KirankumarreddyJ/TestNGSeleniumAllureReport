package listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import constants.Constants;
import helpers.SeleniumHelper;
import io.qameta.allure.Attachment;

/* #########################################################################
Class Name   : TestAllureListener
Purpose      : This class handles to perform steps based on test status

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 29/04/2023 
############################################################################# */
public class TestAllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onSuccess method " + getTestMethodName(iTestResult) + " passed");
//		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		
		if (driver instanceof WebDriver && !driver.toString().contains("null")) {
			saveScreenshotPNG(driver);
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			// Save a log on allure.
			saveTextLog(getTestMethodName(iTestResult) + " passed and screenshot taken!");
		} else {
			// Save a log on allure.
			saveTextLog(SeleniumHelper.getBrowserName(driver)+": "+getTestMethodName(iTestResult) + " passed!");
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
//		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver && !driver.toString().contains("null")) {
			saveScreenshotPNG(driver);
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
		}
		// Save a log on allure.
		saveTextLog(SeleniumHelper.getBrowserName(driver)+": "+getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	@Attachment(value = "{0}", type = "text/plain")
	private String saveTextLog(String message) {
		return message;
	}

	@Attachment(value = "Page Screenshot", type = "image/png")
	private byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
