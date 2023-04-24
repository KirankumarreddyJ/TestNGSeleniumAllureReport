package dataproviders;

import org.testng.annotations.DataProvider;

public class MyDataProvider {
	@DataProvider(name = "getValidLoginData")
	public Object[][] getValidLoginData() {
//		Object contactsData[][] = TestUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		Object contactsData[][] = new Object[][] {{"standard_user", "secret_sauce"}};
		return contactsData;
	}
	
	@DataProvider(name = "getInvalidLoginData")
	public Object[][] getInvalidLoginData() {
//		Object contactsData[][] = TestUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		Object contactsData[][] = new Object[][] {{"standard_user", "secret_sauce123"}};
		return contactsData;
	}
}

