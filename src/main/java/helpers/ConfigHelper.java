package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import constants.Constants;

import qa.base.BaseTest;

public class ConfigHelper extends BaseTest{
	
	public static Properties initialize_Properties() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Properties getPropertyObj() {
		return prop;
	}
	
	public static String getConfigStrVal(String key) {
		return prop.getProperty(key);
	}
	
	public static int getConfigIntVal(String key) {
		return Integer.parseInt(prop.getProperty(key));
	}
	
}
