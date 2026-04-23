package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class KYCPopUpPage extends BaseClass{

	//KYC Popup will only open for the account who dont have KYC complete status -- So handling methods for KYC popup are declared in this class
	
	
	//WebElement
	
	@FindBy(xpath="//a[contains(text(),'I will do it later')]")
	WebElement iWillDoItLetterButton;
	
	@FindBy(xpath="//a[contains(text(),'Complete Now')]")
	WebElement completeNowBtn;
	
	@FindBy(xpath="//h5[@class='wallet-kyc-title']")
	WebElement kycPopUpHeadingText;
	
	@FindBy(xpath="//p[@class='device-detail-text text-start']")
	WebElement kycPopUpParagraphText;
	
	public KYCPopUpPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	//this method is for not accepting KYC popup -- that means KYC Can be done letter 
	public IndexPage clickOnIwillDoItLetterButton()
	{	
		Action.implicitWait(driver, 5);
		Action.click(iWillDoItLetterButton);
		return new IndexPage(driver);
	}
	
	//this method is to accept the KYC and complete the KYC 
	public ProfilePage clickOnCompleteNowBtnAndCompleteKYC()
	{
		Action.click(completeNowBtn);
		return new ProfilePage(driver); //this will navigate user to KYC Information under profile information page
	}
	
	public String getKYCPopUpTitle()
	{
		String title=kycPopUpHeadingText.getText();
		return title;
	}
	
	public String getKYCPopUpParagraphText()
	{
		String paraText=kycPopUpParagraphText.getText();
		return paraText;
	}
	
}
