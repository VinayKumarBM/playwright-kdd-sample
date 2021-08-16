package com.smartstore.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.smartstore.configs.SessionDataManager;

public class RegisterPage {

	private Page page; 
	
	public RegisterPage() {
		this.page = (Page) SessionDataManager.getInstance().getSessionData("page");
	}
	
	// Element selector for Home Page 
	private String genderRadioButton = "#gender-%s";	
	private String firstNameTextbox = "#FirstName";
	private String lastNameTextbox = "#LastName";
	private String birthDayDropDown = "[name='DateOfBirthDay']";
	private String birthMonthDropDown = "[name='DateOfBirthMonth']";
	private String birthYearDropDown = "[name='DateOfBirthYear']";
	private String emailTextbox = "#Email";
	private String userNameTextbox = "#Username";
	private String passwordTextbox = "#Password";
	private String confirmPasswordTextbox = "#ConfirmPassword";
	private String companyTextbox = "#Company";
	private String newsletterCheckbox = "#Newsletter";
	private String registerButton = "[name='register-button']";
	private String registerSuccessMessage = ".registration-result-page p";
	
	public void selectGender(String gender) {
		page.click(String.format(genderRadioButton, gender.toLowerCase()));
	}
	
	public void enterFirstName(String firstName) {
		page.fill(firstNameTextbox, firstName);
	}
	
	public void enterLastName(String lastName) {
		page.fill(lastNameTextbox, lastName);
	}
	
	public void selectBirthDay(String birthDay) {
		page.selectOption(birthDayDropDown, new SelectOption().setLabel(birthDay));
	}
	
	public void selectBirthMonth(String birthMonth) {
		page.selectOption(birthMonthDropDown, new SelectOption().setLabel(birthMonth));
	}
	
	public void selectBirthYear(String birthYear) {
		page.selectOption(birthYearDropDown, new SelectOption().setLabel(birthYear));
	}
	
	public void enterEmail(String email) {
		page.fill(emailTextbox, email);
	}
	
	public void enterUserName(String userName) {
		page.fill(userNameTextbox, userName);
	}
	
	public void enterPassword(String password) {
		page.fill(passwordTextbox, password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		page.fill(confirmPasswordTextbox, confirmPassword);
	}
	
	public void enterCompany(String company) {
		page.fill(companyTextbox, company);
	}
	
	public void subscribeToNewsLetter() {
		page.click(newsletterCheckbox);
	}
	
	public void clickRegisterButton() {
		page.click(registerButton);
	}
	
	public String getRegisterationSuccessMessage() {
		return page.waitForSelector(registerSuccessMessage).textContent().trim();
	}
}
