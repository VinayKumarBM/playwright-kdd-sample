package com.smartstore.pages;

import com.microsoft.playwright.Page;
import com.smartstore.configs.SessionDataManager;

public class LoginPage {

	private Page page; 
	
	public LoginPage() {
		this.page = (Page) SessionDataManager.getInstance().getSessionData("page");
	}
	
	// Element selector for Login Page 
	private String userNameTextbox = "#UsernameOrEmail"; 
	private String passwordTextbox = "#Password"; 
	private String loginButton = ".btn-login"; 
	private String registerButton = ".register-button"; 
			
	public void enterUserName(String userName) {
		page.fill(userNameTextbox, userName);
	}
	
	public void enterPassword(String password) {
		page.fill(passwordTextbox, password);
	}
	
	public void clickLoginButton() {
		page.click(loginButton);
	}
	
	public void clickRegisterButton() {
		page.click(registerButton);
	}
}
