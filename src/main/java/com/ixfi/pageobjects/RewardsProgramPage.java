package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class RewardsProgramPage extends BaseClass{
	
	//WebElements
	
	//Constructor
	public RewardsProgramPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	
	//ActionMethods
	
	public String getRewardsPageTitle()
	{
		String rewardsPageTitle=Action.getTitle(driver);
		return rewardsPageTitle;
	}
}
