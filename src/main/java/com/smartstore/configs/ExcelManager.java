package com.smartstore.configs;

import java.util.HashMap;
import java.util.Map;

import com.smartstore.utils.ExcelReader;

public class ExcelManager {
	private static ExcelManager excelReader;
	static Map<Integer, ExcelReader> readerMap = new HashMap<Integer, ExcelReader>();
	
	private ExcelManager() {		
	}
	
	public static ExcelManager getInstance() {
		if(excelReader == null) {
			excelReader = new ExcelManager();
		}
		return excelReader;
	}
	
	public void setExcelReader (ExcelReader reader) {
		readerMap.put((int) (long) (Thread.currentThread().getId()), reader);
	}

	public ExcelReader getExcelReader () {		
		return readerMap.get((int) (long) (Thread.currentThread().getId()));
	}
}
