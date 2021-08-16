package com.smartstore.configs;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smartstore.actions.ActionsClass;
import com.smartstore.exceptions.InvalidKeywordException;
import com.smartstore.utils.ExcelReader;
import com.smartstore.utils.Log;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;

public class Executor implements GlobalVariables{
	private static final Logger LOG = LogManager.getLogger(Executor.class.getName());

	public List<String[]> getListOfTestCasesToExecute(String sheetName) {
		//LOG.info("Getting List of Test Cases to execute from "+sheetName);
		return new ExcelReader().getTestCasesToRun(sheetName, runModeColumn, testCaseColumn, testCaseDescriptionColumn);
	}
	
	// Original set of method
	public List<String[]> getListOfTestCasesToExecute() {
		LOG.info("Getting List of Test Cases to execute.");
		return new ExcelReader().getTestCasesToRun(testDataSheet, runModeColumn, testCaseColumn, testCaseDescriptionColumn);
	}

	public void executeTestCase(int testRow, String testCase, String testDescription) throws Exception {
		AllureLifecycle lifecycle = Allure.getLifecycle();
	    lifecycle.updateTestCase(testResult -> testResult.setName(testCase).setDescription("Test Description: "+testDescription));
		ExcelReader reader = new ExcelReader();
		ExcelManager.getInstance().setExcelReader(reader);			
		SessionDataManager.getInstance().setSessionData("testCaseName", testCase);
		SessionDataManager.getInstance().setSessionData("testCaseRow", testRow);
		Log.message(testCase+" "+START);
		String keyword;
		String value;
		String stepDesc;
		int numberOfRows = ExcelReader.getNumberOfRows(testCase);
		ExcelReader excel = ExcelManager.getInstance().getExcelReader();
		for (int iRow = 1; iRow < numberOfRows; iRow++){
			keyword = excel.getCellData(iRow, keywordColumn, testCase);
			value = excel.getCellData(iRow, dataColumn, testCase);
			stepDesc = excel.getCellData(iRow, testStepDescriptionColumn, testCase);			
			LOG.info("Keyword: "+keyword+" Value: "+ value);			
			executeAction(keyword, value, stepDesc);			
		}
	}

	@Step("Step: {2}")
	private void executeAction(String keyword, String value, String stepDesc) throws Exception {		
		ActionsClass keywordActions = new ActionsClass();
		Method method[] = keywordActions.getClass().getMethods();
		boolean keywordFound = false;
		for(int i = 0;i < method.length;i++){
			if(method[i].getName().equals(keyword)){
				method[i].invoke(keywordActions, value);
				keywordFound = true;
				break;
			}
		}
		if(!keywordFound) {
			throw new InvalidKeywordException("Invalid Keyword "+keyword);
		}
	}
}
