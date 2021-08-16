package com.smartstore.steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smartstore.pages.HomePage;

public class HomePageSteps {
	
	private static final Logger LOG = LogManager.getLogger(HomePageSteps.class.getName());
	private HomePage homePage = new HomePage();
		
	public void navigateToLogin() {
		homePage.clickLoginLink();
	}

	public void validateLoggedInUser(String userName) {		
		String user = homePage.getLoggedinUser();
		assertEquals(user, userName, "Login failed");
		LOG.info("Logged in as "+user);
	}
	
	public void logout() {
		homePage.clickUserIcon();
		homePage.clickLogoutLink();		
		assertTrue(homePage.isLoginLinkDisplayed(), "User was not logged out");
		LOG.info("Logged out of the application");
	}
	
	public void enterEmailToSubscribe(String email) {
		homePage.enterEmailToSubscribe(email);
	}
	
	public void enterEmailToUnsubscribe(String email) {
		enterEmailToSubscribe(email);
		homePage.newsletterUnsubscribe();
	}
	
	public void validateSubscriptionMessage(String message) {
		homePage.clickSubscribeButton();
		assertEquals(homePage.getSubscriptionMessage(), message, "Unsubscription message did not match");
	}
}
