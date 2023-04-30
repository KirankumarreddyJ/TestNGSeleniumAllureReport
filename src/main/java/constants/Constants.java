package constants;

import helpers.ConfigHelper;

/* #########################################################################
Class Name   : Constants
Purpose      : This class contains all constant values.
			   Class members can access statically.

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 25/04/2023 
############################################################################# */
public class Constants {

	// Project path
	public static final String PROJECT_PATH = System.getProperty("user.dir");

	// Configuration property file path
	public static final String CONFIG_FILE_PATH = PROJECT_PATH + "/src/main/resources/config/config.properties";

	// Test data excel path
	public static final String TESTDATA_SHEET_PATH = PROJECT_PATH + "/src/main/resources/testdata/testdata.xlsx";

	// Test data image path
	public static final String TESTDATA_IMAGE_PATH = PROJECT_PATH + "/src/main/resources/testdata/sampleUpload.jpg";

	// Login details sheet name
	public static final String TESTDATA_LOGIN_SHEET = "Login Details";

	// Registration details sheet name
	public static final String TESTDATA_REG_SHEET = "Registration";

	// Page load timeout
	public static final int PAGE_LOAD_TIMEOUT = Integer
			.parseInt(ConfigHelper.initialize_Properties().getProperty("PAGE_LOAD_TIMEOUT"));

	// Page load timeout
	public static final int DEFAULT_ELEMENT_WAIT_TIME = Integer
			.parseInt(ConfigHelper.initialize_Properties().getProperty("DEFAULT_ELEMENT_WAIT_TIME"));

	// Page SEUCEDEMO LOGIN URL timeout
	public static final String SEUCEDEMO_LOGIN_URL = ConfigHelper.initialize_Properties()
			.getProperty("SEUCEDEMO_LOGIN_URL");

	// Page Automation registration page URL timeout
	public static final String AT_REG_URL = ConfigHelper.initialize_Properties().getProperty("AT_REG_URL");

	// Default test browser name
	public static final String DEFAULT_BROWSER = ConfigHelper.initialize_Properties().getProperty("DEFAULT_BROWSER");

}
