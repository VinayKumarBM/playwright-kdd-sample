package com.smartstore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Attachment;

public class Log {
	private static final Logger Log = LogManager.getLogger(Log.class.getName());

	public static void message(String message){
		Log.info("****************************************************************************************");
		Log.info("\t\t\t\t"+message.toUpperCase());
		Log.info("****************************************************************************************");
	}

	public static void captureException(String message, Throwable throwable){
		Log.error(message, throwable);
	}
	
	@Attachment(value = "Error Log", type = "text/plain")
	public static String attachLogToAllure(Throwable err) {
		return err.toString();
	}
}
