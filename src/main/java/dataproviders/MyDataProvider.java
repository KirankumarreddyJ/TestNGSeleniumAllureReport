package dataproviders;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;
import helpers.ExcelHelper;
import constants.Constants;

/* #########################################################################
Class Name   : MyDataProvider
Purpose      : This class contains data provider methods for test methods

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 25/04/2023 
############################################################################# */
public class MyDataProvider {
	private static final String excePath = Constants.TESTDATA_SHEET_PATH;
	private static final String loginSheet = Constants.TESTDATA_LOGIN_SHEET;
	private static final String regSheet = Constants.TESTDATA_REG_SHEET;
	
	@DataProvider(name = "getValidLoginData")
	public Object[][] getValidLoginData() {
		Object contactsData[][] = {{
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 1),
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 2),
			}};
		return contactsData;
	}
	
//	@DataProvider(name = "getInvalidLoginData")
//	public Object[][] getInvalidLoginData() {
////		Object contactsData[][] = TestUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
//		Object contactsData[][] = new Object[][] {{"standard_user", "secret_sauce123"}};
//		return contactsData;
//	}
	
	@DataProvider(name = "getInvalidLoginData")
	public Object[][] getInvalidLoginData() {
		Object[][] contactsData = {{
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 4),
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 5),
			}};
		return contactsData;
	}
	
	@DataProvider(name = "getRegDetails")
	public Object[][] getRegData(){
		List<String> cols = Arrays.asList(new String[] {
				"First Name", "Last Name", "Address", "Email", "Phone", "Gender", 
				"Hobbies", "Languages", "Skills", "Country", "DoB Year", "DoB Month",
				"DoB Day", "Password", "Confirm Password"});
		Object[][] regData = {
			ExcelHelper.getDataFromColNamesInRowNum(excePath, regSheet, 2, cols)
			};
		return regData;
	}
}

