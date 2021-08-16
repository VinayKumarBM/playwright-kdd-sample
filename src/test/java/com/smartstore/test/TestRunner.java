package com.smartstore.test;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smartstore.configs.Executor;

public class TestRunner extends TestBase {	

	private Executor executor = new Executor();

	@Test (dataProvider = "testCasesList")
	public void testCasesExecutor(String testRow, String testCase, String testDescription) throws Throwable {	
		try {	
			executor.executeTestCase(Integer.valueOf(testRow), testCase, testDescription);
		} catch (Exception e) {
			throw new AssertionError(e.getCause());
		}
	}

	@DataProvider (name = "testCasesList", parallel = true)
	public Object[][] getTestCaseList(){
		List<String[]> testList = executor.getListOfTestCasesToExecute();
		Object[][] testCaseList = new Object[testList.size()][3];
		int i=0;
		for (String[] test: testList) {
			int j=0;
			for (String details : test) {
				testCaseList[i][j] = details;
				j++;
			}
			i++;
		}
		return testCaseList;
	}
}
