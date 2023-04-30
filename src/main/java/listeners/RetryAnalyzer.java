package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import base.BaseTest;
import helpers.ConfigHelper;


/* #########################################################################
Class Name   : RetryAnalyzer
Purpose      : This class handles to re-execute failed tests based on maxRetryLimit

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 29/04/2023 
############################################################################# */
public class RetryAnalyzer implements IRetryAnalyzer {

	// Counter to keep track of retry attempts
	int retryAttemptsCounter = 0;

//	The max limit to retry running of failed test cases
//	Set the value to the number of times we want to retry in config file
//	int maxRetryLimit = 3;
	int maxRetryLimit = Integer.parseInt(ConfigHelper.initialize_Properties().getProperty("RE_EXECUTE_FAILED_TCS_COUNT"));

	// Method to attempt retries for failure tests
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (retryAttemptsCounter < maxRetryLimit) {
				retryAttemptsCounter++;
				return true;
			}
		}
		return false;
	}
}
