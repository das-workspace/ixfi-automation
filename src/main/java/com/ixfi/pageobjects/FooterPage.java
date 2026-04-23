package com.ixfi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class FooterPage extends BaseClass {

	//WebElements
	@FindBy(xpath="//img[@alt='IXFI Logo']")
	WebElement ixfiLogo;
	
	By footerLinks = By.xpath("//div[@class='col-12 col-md-3 mb-2 footer-list ng-star-inserted']//ul//li[@class='ng-star-inserted']/a");
	
	@FindBy(xpath="//img[@alt='App store link']")
	WebElement appStoreLink;
	
	@FindBy(xpath="//img[@alt='Play store link']")
	WebElement playStoreLink;
	
	By socialMediaLinks=By.xpath("//li[@class='list-inline-item footer-icon ng-star-inserted']/a"); 
	
	//-------------------System Status--------------------------------------
	@FindBy(xpath="//div[@class='col-12 col-md-3 mb-2 footer-list ng-star-inserted']//a[@class='ng-star-inserted'][normalize-space()='System Status']")
	WebElement systemStatusBtn;
	
	
	
	
	
	//Constructors
	public FooterPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	//ActionMethods
	
	public void scrollToTheFooterSection()
	{
		Action.scrollByVisibilityOfElement(ixfiLogo);
	}
	
	public boolean isIXFILogoDisplayed()
	{
		return Action.isElementDisplayed(ixfiLogo);
	}
	
	public List<WebElement> getAllFooterLinks()
	{
		List<WebElement> links=Action.findElements(driver, footerLinks);
		return links;
	}
	
	public void clickOnAppStoreLink()
	{
		Action.scrollByVisibilityOfElement(appStoreLink);
		Action.click(appStoreLink);
	}
	
	public void clickOnPlayStoreLink()
	{
		Action.click(playStoreLink);
	}
	
	public List<WebElement> getAllSocialMediaLinks()
	{
		List<WebElement> links=Action.findElements(driver, socialMediaLinks);
		return links;
	}
	
	public StatusPage clickOnSystemStatusButton()
	{
		Action.scrollByVisibilityOfElement(systemStatusBtn);
		Action.click(systemStatusBtn);
		return new StatusPage(driver);
	}
	
	
	
}
