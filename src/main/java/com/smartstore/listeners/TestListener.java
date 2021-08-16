package com.smartstore.listeners;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.microsoft.playwright.BrowserContext;
import com.smartstore.configs.ExcelManager;
import com.smartstore.configs.GlobalVariables;
import com.smartstore.configs.SessionDataManager;
import com.smartstore.utils.Log;
import com.smartstore.utils.ScreenshotUtil;

public class TestListener implements ITestListener, GlobalVariables {

	private String getTestName() {
		return (String) SessionDataManager.getInstance().getSessionData("testCaseName");
	}   
	
	private void updateExcelStatus(String status) {
		int testCaseRow = (Integer) SessionDataManager.getInstance().getSessionData("testCaseRow");
		ExcelManager.getInstance().getExcelReader().setCellData(status, testCaseRow, resultColumn, testDataPath, testDataSheet);		
		closeContext();
	}

	private void closeContext() {
		((BrowserContext)SessionDataManager.getInstance().getSessionData("context")).close();		
	}
	
	public void onTestSuccess(ITestResult iTestResult) {
		Log.message(getTestName()+" "+PASS); 
		updateExcelStatus(PASS);
	}	  

	public void onTestFailure(ITestResult iTestResult) {
		logErrorOnFailure(getTestName(), iTestResult.getThrowable());
		ScreenshotUtil.takeScreenshot(getTestName()+"_"+FAIL);
		ScreenshotUtil.attachImageToAllure(getTestName());		
		updateExcelStatus(FAIL);
		ScreenshotUtil.attachVideoToAllure(getTestName());
	}

	private void logErrorOnFailure(String testName, Throwable error) {
		Log.captureException(testName+" "+FAIL,  error);
		Log.message(testName+" "+FAIL);
		int testCaseRow = (Integer) SessionDataManager.getInstance().getSessionData("testCaseRow");
		ExcelManager.getInstance().getExcelReader().setCellData(ExceptionUtils.getStackTrace(error.getCause()), 
				testCaseRow, errorColumn, testDataPath, testDataSheet);
	}	

	public void onTestSkipped(ITestResult iTestResult) {
		Log.message(getTestName()+" "+SKIP); 
		updateExcelStatus(SKIP);
	}
}
