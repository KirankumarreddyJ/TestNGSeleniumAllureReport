package dataproviders;

import org.testng.annotations.DataProvider;

import helpers.ExcelHelper;
import constants.Constants;

public class MyDataProvider {
	private static final String excePath = Constants.TESTDATA_SHEET_PATH;
	private static final String loginSheet = Constants.TESTDATA_LOGIN_SHEET;
	
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
		Object contactsData[][] = {{
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 4),
			ExcelHelper.getCellDataFromRowColNumbers(excePath, loginSheet, 2, 5),
			}};
		return contactsData;
	}
}

