package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class ReferralProgramPage extends BaseClass{
	
	//WebElements
	
	//Constructor
	public ReferralProgramPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	//Action Method
	public String getReferralProgramPageTitle()
	{
		String referralPageTitle=Action.getTitle(driver);
		return referralPageTitle;
	}
	

}
