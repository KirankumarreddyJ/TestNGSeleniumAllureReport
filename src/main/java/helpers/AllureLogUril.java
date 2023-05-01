package helpers;

import io.qameta.allure.Step;

/* #########################################################################
Class Name   : LogAllureUril
Purpose      : This class contains method to log steps into Allure report.
			   Class members can access statically.

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 02/05/2023 
############################################################################# */
public final class AllureLogUril {
	
	
	/*   ###############################################################
	Method Name  : logALStep
	Purpose      : To log step into the allure report
	Input        : String stepDescription
	Output       : None
	Note         : Use this method to log detailed steps into Allure Report
	 
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 02/05/2023 
	##################################################################### */
	@Step("{0}")
	public static void logALStep(final String stepDescription) {
		// None
	}

}
