package com.smartstore.test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.smartstore.configs.GlobalVariables;
import com.smartstore.configs.SessionDataManager;
import com.smartstore.listeners.TestListener;
import com.smartstore.utils.ExcelReader;

@Listeners({TestListener.class})
public class TestBase implements GlobalVariables{

	@BeforeSuite
	public void configurations(ITestContext context) {
		ExcelReader.setExcelFile(testDataPath);
		ExcelReader.clearColumnData(testDataSheet, resultColumn, testDataPath);
		ExcelReader.clearColumnData(testDataSheet, errorColumn, testDataPath);
	}
	
	@BeforeMethod (alwaysRun = true)
	public void createContextAndPage() {
		List<String> args = new ArrayList<String>();
		args.add("--start-maximized");
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new BrowserType.LaunchOptions()
				.setHeadless(false).setChannel("chrome")
				.setArgs(args)
				//.setDownloadsPath(Path.of(new File(DOWNLOADS_FOLDER).getAbsolutePath()))
				);
		BrowserContext context = browser.newContext(
				new Browser.NewContextOptions()
				.setViewportSize(null)
				.setRecordVideoDir(Paths.get(VIDEOS_FOLDER))
				//.setRecordVideoSize(640, 480)
				);
		Page page = context.newPage();
		page.setDefaultNavigationTimeout(TIME_OUT*1000);
		page.navigate(BASE_URL);
		SessionDataManager.getInstance().setSessionData("page", page);
		SessionDataManager.getInstance().setSessionData("context", context);
	}		
}
