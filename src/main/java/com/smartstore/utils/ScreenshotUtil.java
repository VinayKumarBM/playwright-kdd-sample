package com.smartstore.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.smartstore.configs.GlobalVariables;
import com.smartstore.configs.SessionDataManager;

import io.qameta.allure.Attachment;

public class ScreenshotUtil implements GlobalVariables{

	public static void takeScreenshot(String fileName) {
		Page page = (Page) SessionDataManager.getInstance().getSessionData("page");
		page.screenshot(new Page.ScreenshotOptions()
				  .setPath(Paths.get(SCREENSHOT_FOLDER+fileName+"_"+System.currentTimeMillis()+FILE_FORMAT))
				  .setFullPage(true));
	}
	
	@Attachment(value = "{0} Screenshot", type = "image/png")
	public static byte[] attachImageToAllure(String testName) {
		Page page = (Page) SessionDataManager.getInstance().getSessionData("page");
		return page.screenshot();
	}
	
	@Attachment(value = "{0} Video", type = "video/webm")
	public static byte[] attachVideoToAllure(String testName) {
		Page page = (Page) SessionDataManager.getInstance().getSessionData("page");
		try {
			return Files.readAllBytes(page.video().path().toFile().toPath());
		} catch (IOException e) {
			Log.captureException("Error while attaching the video", e);
		}
		return null;
	}
}
