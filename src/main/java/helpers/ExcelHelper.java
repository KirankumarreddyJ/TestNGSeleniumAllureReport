package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.Constants;

/* #########################################################################
Class Name   : ExcelHelper
Purpose      : This class handles the excel files and retrieves data.
               This class will contain all common methods which will be 
               helpful to get or set data from excel file(.xlsx)
Note         : Excel path takes from Constant class(TESTDATA_SHEET_PATH) if
               'null' or empty passed to methods(refer getSheetObj).

Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
Created Date : 29/04/2023 
############################################################################# */

public class ExcelHelper {
	private static XSSFWorkbook wb;
	private static XSSFSheet ws;
	private static final DataFormatter df = new DataFormatter();
	
	/*   ###############################################################
	Method Name  : getSheetObj
	Purpose      : Get sheet object of give sheet name in excel file
	Input        : String xlPath, String xlSheetName
	Output       : XSSFSheet or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	##################################################################### */
	public static synchronized XSSFSheet getSheetObj(String xlPath, String xlSheetName) {
//		xlPath = Optional.ofNullable(xlPath).orElse(Constants.TESTDATA_SHEET_PATH);
		xlPath = (xlPath == null || xlPath.equals("")) ? Constants.TESTDATA_SHEET_PATH : xlPath;

		try {
			wb = new XSSFWorkbook(new FileInputStream(xlPath));
			ws = wb.getSheet(xlSheetName.trim());
			return ws;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println("Unable to get " + xlSheetName + " sheet object.");
		return null;
	}
	
	
	/* ################################################################################################
	Method Name  : getCellDataFromRowColIndexes
	Purpose      : It will get the Cell value of give row and column index numbers from the excel file
	Input        : String xlPath, String xlSheetName, int rowIndex, int colIndex
	Output       : String (Cell value) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized String getCellDataFromRowColIndexes(String xlPath, String xlSheetName, int rowIndex,
			int colIndex) {
		try {
			ws = getSheetObj(xlPath, xlSheetName);
			if (ws != null) {
				Cell cell = ws.getRow(rowIndex).getCell(colIndex);
				return df.formatCellValue(cell).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println(
				"Unable to get data from " + rowIndex + " row index & " + colIndex + "column index in " + xlSheetName + " sheet.");
		return null;
	}
	
	
	/* ################################################################################################
	Method Name  : getCellDataFromRowColNumbers
	Purpose      : It will get the Cell value of give row and column numbers from the excel file
	Input        : String xlPath, String xlSheetName, int rowNum, int colNum
	Output       : String (Cell value) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized String getCellDataFromRowColNumbers(String xlPath, String xlSheetName, int rowNum,
			int colNum) {

		return getCellDataFromRowColIndexes(xlPath, xlSheetName, rowNum - 1, colNum - 1);
	}
	
	
	/* ################################################################################################
	Method Name  : getCellDataFromRowColNames
	Purpose      : It will get the Cell value of give row and column names from the excel file
	Input        : String xlPath, String xlSheetName, int rowName, int colName
	Output       : String (Cell value) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized String getCellDataFromRowColNames(String xlPath, String xlSheetName, String rowName,
			String colName) {
		String cellData = null;
		try {
			ws = getSheetObj(xlPath, xlSheetName);
			if (ws == null) {
				return null;
			}
			for(Row row : ws) {
				if(df.formatCellValue(row.getCell(0)).trim().equalsIgnoreCase(rowName)) {
					for(int i=0;i<row.getLastCellNum();i++) {
						if(df.formatCellValue(ws.getRow(0).getCell(i)).trim().equalsIgnoreCase(colName)) {
							return df.formatCellValue(row.getCell(i)).trim();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println(
				"Unable to get data from " + rowName + " row & " + colName + "column in " + xlSheetName + " sheet.");
		return null;
	}
	
	
	/* ################################################################################################
	Method Name  : getDataFromSheet
	Purpose      : Get the excel data from given sheet in excel file
	Input        : String xlPath, String xlSheetName
	Output       : Object[][](Sheet data) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized Object[][] getDataFromSheet(String xlPath, String xlSheetName){
		Object[][] arrExcelData = null;
		
		try {
			ws = getSheetObj(xlPath, xlSheetName);
			if(ws == null) {
				return null;
			}
			int lastRowNum = ws.getLastRowNum();
			int lastColNum = ws.getRow(0).getLastCellNum();
			arrExcelData = new Object[lastRowNum][lastColNum];
			
			int i=0;
			for(Row row:ws) {
				if(row.getRowNum() == 0) {
					continue;
				}
				i++;
				String cellData=null;
				for(int j=0; j < lastColNum; j++) {
					Cell cell = row.getCell(j);
					cellData = df.formatCellValue(cell).trim();
					System.out.println(i + " "+ j);
					arrExcelData[i-1][j] = cellData;
				}
			}
			return arrExcelData;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println("Unable to get data from " + xlSheetName + " sheet.");
		return null;	
	}
	
	
	/* ################################################################################################
	Method Name  : getAllDataFromColNames
	Purpose      : Get the list of excel data from give all column names in sheet
	Input        : String xlPath, String xlSheetName, List<String> colNames
	Output       : Object[][](Columns data) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized Object[][] getAllDataFromColNames(String xlPath, String xlSheetName, List<String> colNames){
		Object[][] arrExcelData = null;
		
		try {
			ws = getSheetObj(xlPath, xlSheetName);
			if(ws == null) {
				return null;
			}
			//Find Column numbers
			List<Integer> colNumList = new ArrayList<Integer>();
			for(String colName: colNames) {
				String colNum = getColNumByColName(ws, colName);
				if(colNum != null) {
					colNumList.add(Integer.parseInt(colNum));
				}else {
					System.out.println("Unable to get data from " + xlSheetName + " sheet. Please verify given sheet name & column names");
					return null;
				}
			}
			//get the data from row of give all columns
			arrExcelData = new Object[ws.getLastRowNum()][colNumList.size()];
			for(Row row : ws) {
				if(row.getRowNum() == 0) {
					continue;
				}
				for(int j=0;j<colNumList.size();j++) {
					Cell cell = row.getCell(colNumList.get(j)-1);
					String cellData = df.formatCellValue(cell).trim();
					arrExcelData[row.getRowNum()-1][j] = cellData;
				}
			}	
			return arrExcelData;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println("Unable to get data from " + xlSheetName + " sheet.");
		return null;	
	}
	
	/* ################################################################################################
	Method Name  : getDataFromColNamesInRowNum
	Purpose      : Get the list of excel data from give all column names in given row number sheet
	Input        : String xlPath, String xlSheetName, int excelRowNum, List<String> colNames
	Output       : Object[](Columns data) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized Object[] getDataFromColNamesInRowNum(String xlPath, String xlSheetName, int excelRowNum, List<String> colNames){
		Object[] arrExcelData = null;
		
		try {
			ws = getSheetObj(xlPath, xlSheetName);
			if(ws == null) {
				return null;
			}
			//Find Column numbers
			List<Integer> colNumList = new ArrayList<Integer>();
			for(String colName: colNames) {
				String colNum = getColNumByColName(ws, colName);
				if(colNum != null) {
					colNumList.add(Integer.parseInt(colNum));
				}else {
					System.out.println("Unable to get data from " + xlSheetName + " sheet. Please verify given sheet name & column names");
					return null;
				}
			}
			
			//get the data from given row number of give all columns
			arrExcelData = new Object[colNumList.size()];
			Row row = ws.getRow(excelRowNum-1);
			for(int i=0;i<colNumList.size();i++) {
				Cell cell = row.getCell(colNumList.get(i)-1);
				String cellData = df.formatCellValue(cell).trim();
				arrExcelData[i] = cellData;
			}
			
//			
//			//get the data from row of give all columns
//			arrExcelData = new Object[ws.getLastRowNum()][colNumList.size()];
//			for(Row row : ws) {
//				if(row.getRowNum() == 0) {
//					continue;
//				}
//				for(int j=0;j<colNumList.size();j++) {
//					Cell cell = row.getCell(colNumList.get(j)-1);
//					String cellData = df.formatCellValue(cell).trim();
//					arrExcelData[row.getRowNum()-1][j] = cellData;
//				}
//			}	
			return arrExcelData;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				wb.close();
			} catch (IOException e) {
			}
		}
		System.out.println("Unable to get data from " + xlSheetName + " sheet.");
		return null;	
	}
	
	
	/* ################################################################################################
	Method Name  : getColNumByColName
	Purpose      : Get column number of given column name
	Input        : XSSFSheet workSheet, String colName
	Output       : String(column number) or null
	
	Created By   : Kirankumar Reddy Juturu(jkirankumarreddy9@gmail.com)
	Created Date : 29/04/2023 
	################################################################################################# */
	public static synchronized String getColNumByColName(XSSFSheet workSheet, String colName) {
		String ColumnNum = null;
		
		if(workSheet == null) {
			return ColumnNum;
		}
		
		int colCount = workSheet.getRow(0).getLastCellNum();
		XSSFRow columnRow = workSheet.getRow(0);
		for(int colNum = 1; colNum<= colCount;colNum++) {
			Cell cell = columnRow.getCell(colNum-1);
			String cellData = df.formatCellValue(cell).trim();
			if(colName.trim().equals(cellData)) {
				return ColumnNum =  String.valueOf(colNum);
			}
		}
		System.out.println("Unable to find column number of "+colName+" column");
		return ColumnNum;
	}
	
	
	public static void main(String[] args) {
		
//		run 1
//		System.out.println(getCellDataFromRowColNumbers(null, "Environments", 1, 1));
		
//		run 2
//		List<String> cols = Arrays.asList(new String[] {"Environments", "URL"});
//		Object[][] data = getDataFromColNames(Constants.TESTDATA_SHEET_PATH, "Environments",cols);
//		for(int i=0;i<data.length;i++) {
//			for(int j=0;j<data[0].length;j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		
//		run 3
//		Object[][] data = getDataFromSheet(Constants.TESTDATA_SHEET_PATH, "Environments");
//		for(int i=0;i<data.length;i++) {
//			for(int j=0;j<data[0].length;j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		
//		run 4
//		List<String> cols = Arrays.asList(new String[] {"First Name", "Last Name", "Address", "Email", "Phone", "Gender", "Skills"});
//		Object[][] data = getDataFromColNamesInRowNum(Constants.TESTDATA_SHEET_PATH, "Registration", 3, cols);
//		for(int i=0;i<data.length;i++) {
//			for(int j=0;j<data[0].length;j++) {
//				System.out.println(data[i][j]);
//			}
//		}
	}
}
