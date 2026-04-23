package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class GmailPage extends BaseClass{

	@FindBy(xpath="//input[@id='identifierId']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@name='Passwd']")
	WebElement passwordField;
	
	@FindBy(xpath="//span[normalize-space()='Next']")
	WebElement nextBtn;
	
	@FindBy(xpath="//img[@role='presentation']")
	WebElement gmailLogo;
	
	@FindBy(xpath="//img[@class='ajT']")
	WebElement hideExpandedContent;
	
	@FindBy(xpath="//span[@class='bog']//span[normalize-space(text())=\"IXFI: You forgot your password? No biggie!\"]")
	WebElement resetPasswordMail;
	
	@FindBy(xpath="//a[normalize-space()='Reset Password']")
	WebElement resetPasswordLink;
	
	public GmailPage(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
		// auth=new AuthValidationPage(driver);
	}
	
	public GmailPage navigateToGmail()
	{
		Action.openLinkInNewTab("https://www.gmail.com/");
		return new GmailPage(driver);
	}
	
	public void enterLoginEmail()
	{
		Action.waitForElementToBeVisible(emailField, 10);
		Action.enterText(emailField, prop.getProperty("myemail"));
	}
	
	public void clickOnNextButton()
	{
		Action.click(nextBtn);
	}
	

	public void enterLoginPassword()
	{
		Action.waitForElementToBeVisible(passwordField, 10);
		Action.enterText(passwordField, prop.getProperty("mypass"));
	}
	
	public void waitForGmailToLoadProperly()
	{
		Action.waitForElementToBeVisible(gmailLogo, 10);
	}
	
	public void clickOnHideExpandedContent()
	{
		Action.scrollByVisibilityOfElement(hideExpandedContent);
		Action.click(hideExpandedContent);
	}
	
	public boolean isHideExpandedContentInMailVisible()
	{
		return hideExpandedContent.isDisplayed();
	}
	
	public void clickOnResetPasswordMail()
	{
		Action.click(resetPasswordMail);
	}
	
	public ResetPasswordPage clickOnResetPasswordLink()
	{
		Action.scrollByVisibilityOfElement(resetPasswordLink);
		Action.waitForElementToBeVisible(resetPasswordLink, 10);
		Action.click(resetPasswordLink);
		return new ResetPasswordPage(driver);
	}
	
	public void loginToTheGmail()
	{
		//navigateToGmail();
		enterLoginEmail();
		clickOnNextButton();
		enterLoginPassword();
		clickOnNextButton();
		
	}
	
	
	

}
