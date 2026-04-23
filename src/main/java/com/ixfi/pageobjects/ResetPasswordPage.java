package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class ResetPasswordPage extends BaseClass {

	//WebElement
	
	@FindBy(xpath="//input[@placeholder='New Password']")
	WebElement newPasswordField;
	

	@FindBy(xpath="//input[@placeholder='Confirm New Password']")
	WebElement confirmNewPasswordField;
	
	@FindBy(xpath="//button[normalize-space(text())='Update Password']")
	WebElement updatePasswordButton;
	
	@FindBy(xpath="//input[@placeholder='Enter Six digit IXFI PIN']")
	WebElement ixfiPinField;
	
	@FindBy(xpath="//button[contains(normalize-space(text()), 'Next')]")
	WebElement nextBtn;
	
	@FindBy(xpath="//button[contains(text(),'Back')]")
	WebElement backButton;
	
	
	public ResetPasswordPage(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
		// auth=new AuthValidationPage(driver);
	}
	
	public String getResetPasswordPageTitle()
	{
		return Action.getTitle(driver);
	}
	
	public void enterNewPassword()
	{
		Action.waitForElementToBeVisible(newPasswordField, 10);
		Action.enterText(newPasswordField, prop.getProperty("newPassword"));
	}
	
	public void enterNewConfirmPassword()
	{
		Action.enterText(confirmNewPasswordField, prop.getProperty("ConfirmNewPassword"));
	}
	
	public void clickOnUpdatePasswordButton()
	{
		Action.click(updatePasswordButton);
	}

	public void enterIXFIPin()
	{
		Action.waitForElementToBeVisible(ixfiPinField, 20);
		Action.enterText(ixfiPinField, prop.getProperty("ixfipin"));
	}
	
	public LoginPage clickOnNextButton()
	{
		Action.waitForElementToBeVisible(nextBtn, 20);
		Action.click(nextBtn);
		return new LoginPage(driver);
	}
	
	
	
}
