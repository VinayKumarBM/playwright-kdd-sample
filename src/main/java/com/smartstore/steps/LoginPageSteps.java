package com.smartstore.steps;

import com.smartstore.pages.LoginPage;

public class LoginPageSteps {
	
	private LoginPage loginPage = new LoginPage();
	
	public void enterLoginDetails(String userName, String password) {
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
	}
	
	public void navigateToRegistration() {
		loginPage.clickRegisterButton();
	}

}
