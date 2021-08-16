package com.smartstore.pages;

import com.microsoft.playwright.Page;
import com.smartstore.configs.SessionDataManager;

public class HomePage {

	private Page page; 
	
	public HomePage() {
		this.page = (Page) SessionDataManager.getInstance().getSessionData("page");
	}
	
	// Element selector for Home Page 
	private String loginLink = "text=Log in";	
	private String userIcon = "#menubar-my-account .fa-user-circle";
	private String loggedInUserText = "xpath=(//*[@id='menubar-my-account']//span)[1]";
	private String logoutLink = "text=Log out";
	private String newsletterSubscribeTextbox = "#newsletter-email";
	private String newsletterSubscribeButton = "#newsletter-subscribe-button";
	private String newsletterSuccessText = "#newsletter-result-block";
	private String newsletterUnsubscribeRadiobutton = "#newsletter-unsubscribe";
		
	public void clickLoginLink() {
		page.click(loginLink);
	}
	
	public void clickUserIcon() {
		page.click(userIcon);
	}
	
	public void clickLogoutLink() {
		page.click(logoutLink);
	}
	
	public boolean isLoginLinkDisplayed() {
		return page.waitForSelector(loginLink).isVisible();
	}
	
	public String getLoggedinUser() {
		return page.textContent(loggedInUserText).trim();
	}
	
	public void enterEmailToSubscribe(String email) {
		page.fill(newsletterSubscribeTextbox, email);
	}
	
	public void clickSubscribeButton() {
		page.click(newsletterSubscribeButton);
	}
	
	public void newsletterUnsubscribe() {
		page.check(newsletterUnsubscribeRadiobutton);
	}
	
	public String getSubscriptionMessage() {
		return page.waitForSelector(newsletterSuccessText).textContent().trim();
	}
}
