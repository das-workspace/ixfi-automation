package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class ForgotPasswordPage extends BaseClass {

	// WebElements
	@FindBy(xpath = "//input[@placeholder='Email Address']")
	WebElement emailField;

	@FindBy(xpath = "//div[@class='form-area']/button")
	WebElement submitBtn;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginLink;

	@FindBy(xpath = "//a[normalize-space()='Click Here']")
	WebElement clickHereLink;

	@FindBy(xpath = "//input[@id='mobile_no']")
	WebElement phoneNoField;

	@FindBy(xpath = "//span[@class='main-title']")
	WebElement forgotPasswordText;

	@FindBy(xpath = "//h3[contains(text(),' Enter your registered phone number to receive a reset link. ')]")
	WebElement phoneResetLinkTextPara;

	@FindBy(xpath = "//h3[contains(text(),'Enter your registered email to receive a reset link.')]")
	WebElement emailResetLinkTextPara;

	@FindBy(xpath="//div[@class='logo ng-star-inserted']//img[@alt='IXFI logo']")
	WebElement ixfiLogo;
	
	
	// Constructor
	public ForgotPasswordPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// Action Method
	public String getforgotPasswordPageTitle() {
		String forgotPasswordPageTitle = Action.getTitle(driver);
		return forgotPasswordPageTitle;
	}

	public void enterEmail() {
		Action.enterText(emailField, prop.getProperty("email"));
	}

	public void enterPhone() {
		Action.enterText(phoneNoField, prop.getProperty("phone"));
	}

	public void clickOnSubmitBtn() {
		Action.click(submitBtn);
	}

	public LoginPage clickOnLoginLink() {
		Action.scrollByVisibilityOfElement(loginLink);
		Action.click(loginLink);
		return new LoginPage(driver);
	}

	public void clickOnClickHereLink() {
		Action.scrollByVisibilityOfElement(clickHereLink);
		Action.click(clickHereLink);

	}

	public String getForgotPasswordMainTextTitle() {
		return forgotPasswordText.getText();
	}

	public String getPhoneResetLinkTextParagraph() {
		return phoneResetLinkTextPara.getText();
	}

	public String getEmailResetLinkTextParagraph() {
		return emailResetLinkTextPara.getText();
	}
	
	public IndexPage clickOnIxfiLogo()
	{
		Action.waitForElementToBeVisible(ixfiLogo, 10);
		Action.click(ixfiLogo);
		return new IndexPage(driver);
	}

	

}
