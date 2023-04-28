package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import io.qameta.allure.Attachment;

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
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
			// Save a log on allure.
			saveTextLog(getTestMethodName(iTestResult) + " passed and screenshot taken!");
		} else {
			// Save a log on allure.
			saveTextLog(getTestMethodName(iTestResult) + " passed!");
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
//		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver && !driver.toString().contains("null")) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
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
