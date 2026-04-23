package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class ConvertPage extends BaseClass{
	
	//WebElements
	
		//Constructor
		public ConvertPage(WebDriver driver) {
			BaseClass.driver = driver;

			PageFactory.initElements(driver, this);
		}
		
		//Action Method
		public String getConvertPageTitle()
		{
			String convertPageTitle=Action.getTitle(driver);
			return convertPageTitle;
		}

}
