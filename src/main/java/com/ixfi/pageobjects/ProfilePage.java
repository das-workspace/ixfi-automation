package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.basepage.BaseClass;

public class ProfilePage extends BaseClass{
	
	//WebElement
	
	//constructor 
	public ProfilePage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	

}
