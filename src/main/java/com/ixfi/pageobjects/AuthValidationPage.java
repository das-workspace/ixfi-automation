package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class AuthValidationPage extends BaseClass {

	IndexPage index;
	// WebElements

	@FindBy(xpath = "//button[text()=' Send OTP ']")
	WebElement sendOTPBtn;

	@FindBy(xpath = "//input[@placeholder='Enter Six Digit OTP']")
	WebElement emailOTPField;

	@FindBy(xpath = "//input[@class='form-control ng-pristine ng-invalid ng-touched']")
	WebElement phoneOTPField;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextBtn;

	@FindBy(xpath = "//button[text()=' Back ']")
	WebElement backBtn;

	@FindBy(xpath = "//img[@src='https://dfwt0d2sx0pdr.cloudfront.net/exchange/profile/left-arrow.svg']")
	WebElement backArrowOnBackButton;

	@FindBy(xpath = "//label[contains(text(),'Email Verification')]")
	WebElement emailVerificationText;

	@FindBy(xpath = "//span[@class='sub-label']")
	WebElement emailOTPVerificationNoticeText;

	// Constructor
	public AuthValidationPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// ActionMethods
	// email otp method --
	public KYCPopUpPage enterOTP() {
		Action.click(sendOTPBtn);
		// handle otp manually -- enter otp here Manually
		String otp = getOTP();
		Action.implicitWait(driver, 10);
		Action.enterText(emailOTPField, otp);
		Action.implicitWait(driver, 10);
		Action.click(nextBtn);
		return new KYCPopUpPage(driver); // now user can navigate to another page and add the actions accordingly

	}

	// this will navigate us to loginPage
	public LoginPage clickOnBackButton() {
		Action.click(backArrowOnBackButton);
		return new LoginPage(driver);
	}

	public String getEmailVerificationText() {
		String emailVefiText = emailVerificationText.getText(); // to validate if user is landed on authentication page
																// or not
		return emailVefiText;
	}

	public String getEmailVerificationNoticeText() {
		String noticeText = emailOTPVerificationNoticeText.getText();
		return noticeText;

	}

	// login -- mobile OTP -- mobile field send otp button

}
