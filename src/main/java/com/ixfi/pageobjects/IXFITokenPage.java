package com.ixfi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class IXFITokenPage extends BaseClass{
	
	//WebElement
	@FindBy(xpath="//div[@class='elementor-element elementor-element-cad95e3 grd elementor-widget elementor-widget-text-editor']//div[@class='elementor-widget-container']")
	WebElement ixfiTitleText;
	
	@FindBy(xpath="//div[@class='elementor-element elementor-element-0127933 elementor-align-left elementor-mobile-align-center elementor-widget elementor-widget-button']//span[@class='elementor-button-text'][normalize-space()='Secure your spot']")
	WebElement secureYourSpotTopButton;
	
	@FindBy(xpath="//div[@class='elementor-element elementor-element-bb92534 elementor-align-left elementor-mobile-align-center elementor-widget elementor-widget-button']//span[@class='elementor-button-text'][normalize-space()='Secure your spot']")
	WebElement secureYourSpotMiddleButton;
	
	@FindBy(xpath="//p[normalize-space()='Be Part of the Revolution.']")
	WebElement middleButtonScroller;
	
	@FindBy(xpath="//div[@class='elementor-element elementor-element-743d75a elementor-align-center elementor-mobile-align-center elementor-widget elementor-widget-button']//span[@class='elementor-button-text'][normalize-space()='Secure your spot']")
	WebElement secureYourSpotBottomButton;
	
	@FindBy(xpath="//p[contains(text(),'Discover a fully gamified platform that offers use')]")
	WebElement bottomButtonScroller;
	
	@FindBy(xpath="//div[@class='elementor-element elementor-element-cda3791 e-con-full e-flex e-con e-child']")
	WebElement emailSection;
	
	@FindBy(xpath="//input[@id='wpforms-7032-field_2' and @type='email']")
	WebElement emailField;
	
	@FindBy(xpath="//button[@id='wpforms-submit-7032' and @type='submit']")
	WebElement joinWaitlist;
	
	@FindBy(xpath="//div[@id='wpforms-confirmation-7032']/p")
	WebElement alertMessage;
	
	
	By readArticlebuttons=By.xpath("//span[@class='elementor-button-text'][normalize-space()='Read article']");
	
	//Constructor 
	public IXFITokenPage(WebDriver driver)
	{
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	//Action methods 
	public String getIXFITokenPageTitle()
	{
		return Action.getTitle(driver);
	}
	
	public String getIXFITokenTitleText()
	{
		Action.waitForElementToBeVisible(ixfiTitleText, 20);
		return Action.getText(ixfiTitleText);
	}
	
	public void clickOnSecureYourSpotButtonPresentOnTop()
	{
		Action.scrollByVisibilityOfElement(secureYourSpotTopButton);
		Action.click(secureYourSpotTopButton);
	}
	
	public void clickOnSecureYourSpotButtonPresentOnMiddle()
	{
		Action.scrollToElement(driver,middleButtonScroller);
		Action.click(secureYourSpotMiddleButton);
	}
	
	public void clickOnSecureYourSpotButtonPresentOnBottom() throws InterruptedException
	{
		//bottomButtonScroller
		Action.scrollToElement(driver,bottomButtonScroller);
		Thread.sleep(1000);
		Action.scrollToElement(driver,secureYourSpotBottomButton);
		Action.click(secureYourSpotBottomButton);
	}
	
	public void clickOnReadArticleAndOpenInNewTab() throws InterruptedException
	{
		List<WebElement>articles=Action.findElements(driver, readArticlebuttons);
		for(WebElement art:articles)
		{
			Action.openInNewTab(art);
		}
		
	}
	
	public boolean isEmail_JoinWaitListSectionPresent()
	{
	
		return Action.isElementDisplayed(emailSection);
	}
	
	public String getTextOfJoinWaitListButton()
	{
		return Action.getText(joinWaitlist);
	}
	
	public void clickOnJoinWaitlistButton()
	{
		Action.click(joinWaitlist);
	}
	
	public void enterEmail(String emailId)
	{
		Action.waitForElementToBeVisible(emailField, 20);
		Action.enterText(emailField, emailId);
	}
	
	public String getAlertMessage()
	{
		Action.waitForElementToBeVisible(alertMessage, 20);
		return Action.getText(alertMessage);
	}
}
