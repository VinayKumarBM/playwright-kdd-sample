package com.smartstore.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private static XSSFWorkbook excelWorkbook;
	private static final Logger LOG = LogManager.getLogger(ExcelReader.class.getName());
	private static final String RUN_MODE_YES = "YES";

	public synchronized static void setExcelFile(String sheetPath) {
		try{
			FileInputStream excelFile = new FileInputStream(sheetPath);
			excelWorkbook = new XSSFWorkbook(excelFile);			
		} catch(Exception exp){
			LOG.error("Exception occured in setExcelFile: ", exp);
		}		
	}

	public synchronized static int getNumberOfRows(String sheetName) {
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		int numberOfRows = excelSheet.getPhysicalNumberOfRows();
		//LOG.info("Number Of Rows: "+numberOfRows);
		return numberOfRows;
	}

	public synchronized String getCellData(int rowNumb, int colNumb, String sheetName) throws Exception{
		try{
			XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
			XSSFCell cell = excelSheet.getRow(rowNumb).getCell(colNumb);
			//LOG.debug("Getting cell data.");
			if(cell.getCellType() == CellType.NUMERIC) {
				cell.setCellType(CellType.STRING);
			}
			String cellData = cell.getStringCellValue();
			return cellData;
		}
		catch(Exception exp){
			return "";
		}
	}

	public synchronized static void clearColumnData(String sheetName, int colNumb, String excelFilePath) {
		int rowCount = getNumberOfRows(sheetName);
		XSSFRow row;
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		for(int i=1; i< rowCount; i++) {
			XSSFCell cell = excelSheet.getRow(i).getCell(colNumb);
			if(cell==null){
				row = excelSheet.getRow(i);
				cell = row.createCell(colNumb);
			}
			cell.setCellValue("");			
		}
		//LOG.info("Clearing column "+colNumb+" of Sheet: "+sheetName);
		writingDataIntoFile(excelFilePath);
	}

	public synchronized void setCellData(String result, int rowNumb, int colNumb, String excelFilePath, String sheetName) {	
		XSSFSheet excelSheet = excelWorkbook.getSheet(sheetName);
		XSSFRow row = excelSheet.getRow(rowNumb);
		XSSFCell cell = row.getCell(colNumb);
		//LOG.info("Setting results into the excel sheet.");
		if(cell==null){
			cell = row.createCell(colNumb);
		}
		cell.setCellValue(result);
		//LOG.info("Setting value into cell["+rowNumb+"]["+colNumb+"]: "+result);
		writingDataIntoFile(excelFilePath);		
	}

	private synchronized static void writingDataIntoFile(String excelFilePath) {
		try{
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWorkbook.write(fileOut);
			fileOut.flush();
		//	fileOut.close();
		}catch(Exception exp){
			LOG.error("Exception occured in setCellData: ",exp);
		}
	}

	public synchronized List<String[]> getTestCasesToRun(String sheetName, int runModeColumn, int testCaseColumn, int testCaseDescriptionColumn) {
		int rowCount = getNumberOfRows(sheetName);
		//LOG.info("Row Count: "+rowCount);
		List<String[]> testList = new ArrayList<String[]>();
		try {			
			String[] testCase;
			for(int i=1; i< rowCount; i++) {
				testCase = getTestCaseToRun(i, runModeColumn, testCaseColumn, testCaseDescriptionColumn, sheetName);
				if(testCase != null) {
					testList.add(testCase);
				}
			}
		}catch (Exception e) {
			LOG.error("Exeception Occured while adding data to List:\n", e);
		}
		return testList;
	}

	private synchronized String[] getTestCaseToRun(int row, int runModeColumn, int testCaseColumn, int testCaseDescriptionColumn, String sheetName) {
		String[] testDetails = new String[3];
		try{
			if(getCellData(row, runModeColumn, sheetName).equalsIgnoreCase(RUN_MODE_YES)){
				testDetails[0] = String.valueOf(row);
				testDetails[1] = getCellData(row, testCaseColumn, sheetName).trim();
				testDetails[2] = getCellData(row, testCaseDescriptionColumn, sheetName).trim();
				//LOG.info("Test Case to Run: "+Arrays.deepToString(testDetails));
				return testDetails;
			} 
		} catch(Exception exp){
			LOG.error("Exception occured in getTestCaseRow: ", exp);
		}
		return null;
	}
}