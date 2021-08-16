package com.smartstore.configs;

public interface GlobalVariables {
	String BASE_DIR = System.getProperty("user.dir");
	String CONFIG_PATH = BASE_DIR+"/config.properties";

	// Application URL
	String BASE_URL = "https://services.smartbear.com/samples/TestComplete15/smartstore/";

	// Path to test data sheet with test cases to run and test case details
	String testDataPath = BASE_DIR+"/src/test/resources/data/TestDataSheet.xlsx";
	String testDataSheet = "TestCases";
//	String testDataPath = BASE_DIR+"/src/test/resources/data/TestSuiteSheet.xlsx";
//	String testDataSheet = "TestSuite";
	int testCaseColumn = 0;
	int testCaseDescriptionColumn = 1;
	int runModeColumn = 2;
	int resultColumn = 3;
	int errorColumn = 4;

	// Columns in Test Case sheet
	int testStepsColumn = 0;
	int testStepDescriptionColumn = 1;
	int keywordColumn = 2;
	int dataColumn = 3;	

	String START = "STARTED";
	String PASS = "PASSED";
	String FAIL = "FAILED";
	String SKIP = "SKIPED";
	String END = "ENDED";

	// Screenshot folder and file details
	String SCREENSHOT_FOLDER = "target/screenshots/";
	String FILE_FORMAT = ".png";
	String DOWNLOADS_FOLDER = "target/downloads/";
	String VIDEOS_FOLDER = "target/videos/";
	
	// Timeout
	double TIME_OUT = 60;
}
