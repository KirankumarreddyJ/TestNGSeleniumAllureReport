package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import base.BaseTest;
import constants.Constants;

/* #########################################################################
Class Name   : ConfigHelper
Purpose      : This class handles the config file and retrieves data.
               This class will contain all common methods which will be 
               helpful to get data from config properties file

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 29/04/2023 
############################################################################# */
public class ConfigHelper extends BaseTest{
	
	/*   ###############################################################
	Method Name  : initialize_Properties
	Purpose      : To load the config properties file and return object
	Input        : None
	Output       : Properties object
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static Properties initialize_Properties() {
		prop = new Properties();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fi);
			
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to load Properties file: "+e.getMessage());
			e.printStackTrace();
		} finally {
			if (null != fi)
		    {
		        try
		        {
		            fi.close();
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		    }
		}
		return prop;
	}
	
	/*   ###############################################################
	Method Name  : getPropertyObj
	Purpose      : To get the value of give Key in property file
	Input        : None
	Output       : Properties object
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static Properties getPropertyObj() {
		return prop;
	}
	
	/*   ###############################################################
	Method Name  : getConfigStrVal
	Purpose      : To get the string value of give Key in property file
	Input        : String Key
	Output       : String value
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static String getConfigStrVal(String key) {
		return prop.getProperty(key).trim();
	}
	
	
	/*   ###############################################################
	Method Name  : getConfigStrVal
	Purpose      : To get the integer value of give Key in property file
	Input        : String Key
	Output       : int value
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static int getConfigIntVal(String key) {
		return Integer.parseInt(prop.getProperty(key).trim());
	}
	
}
