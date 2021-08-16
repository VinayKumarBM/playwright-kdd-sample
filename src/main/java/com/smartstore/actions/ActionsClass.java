package com.smartstore.actions;

import com.smartstore.steps.HomePageSteps;
import com.smartstore.steps.LoginPageSteps;
import com.smartstore.steps.RegisterPageSteps;

public class ActionsClass {

	private HomePageSteps homePageSteps = new HomePageSteps(); 
	private LoginPageSteps loginPageSteps = new LoginPageSteps();
	private RegisterPageSteps registerPageSteps = new RegisterPageSteps();
	
	public void login(String data) {
		String[] dataArray = data.split("\\|");
		homePageSteps.navigateToLogin();
		loginPageSteps.enterLoginDetails(dataArray[0], dataArray[1]);
		homePageSteps.validateLoggedInUser(dataArray[0]);
	}
	
	public void logout(String data) {
		homePageSteps.logout();
	}
	
	public void registerUser(String data) {
		String[] dataArray = data.split("\\|");
		homePageSteps.navigateToLogin();
		loginPageSteps.navigateToRegistration();		
		String userName = registerPageSteps.enterRegistrationDetails(dataArray[0], dataArray[1], dataArray[2], dataArray[3], 
				dataArray[4], dataArray[5], dataArray[6], dataArray[7], dataArray[8], dataArray[9], dataArray[10]);
		registerPageSteps.saveRegistrationDetails();
		homePageSteps.validateLoggedInUser(userName);
	}
	
	public void subscribeToNewsletter(String data) {
		String[] dataArray = data.split("\\|");
		homePageSteps.enterEmailToSubscribe(dataArray[0]);
		homePageSteps.validateSubscriptionMessage(dataArray[1]);
	}	
	
	public void unsubscribeToNewsletter(String data) {
		String[] dataArray = data.split("\\|");
		homePageSteps.enterEmailToUnsubscribe(dataArray[0]);
		homePageSteps.validateSubscriptionMessage(dataArray[1]);
	}
}
