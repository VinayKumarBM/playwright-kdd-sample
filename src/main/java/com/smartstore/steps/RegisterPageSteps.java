package com.smartstore.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smartstore.pages.RegisterPage;

public class RegisterPageSteps {
	
	private static final Logger LOG = LogManager.getLogger(RegisterPageSteps.class.getName());
	private RegisterPage registerPage = new RegisterPage();
	
	public String enterRegistrationDetails(String gender, String firstName, String lastName, String day, String month, String year
			, String email, String userName, String password, String confirmPassword, String company) {
		registerPage.selectGender(gender);
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.selectBirthDay(day);
		registerPage.selectBirthMonth(month);
		registerPage.selectBirthYear(year);
		registerPage.enterEmail(String.format(email,System.currentTimeMillis()));
		userName = String.format(userName,System.currentTimeMillis());
		registerPage.enterUserName(userName);
		registerPage.enterPassword(password);
		registerPage.enterConfirmPassword(confirmPassword);
		registerPage.enterCompany(company);
		registerPage.subscribeToNewsLetter();
		return userName;
	}
	
	public void saveRegistrationDetails() {
		registerPage.clickRegisterButton();
		LOG.info(registerPage.getRegisterationSuccessMessage());
	}

}
