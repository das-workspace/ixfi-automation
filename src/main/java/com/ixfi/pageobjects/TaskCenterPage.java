package com.ixfi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class TaskCenterPage extends BaseClass{
	
	//WebElements
	
		//Constructor
		public TaskCenterPage(WebDriver driver) {
			BaseClass.driver = driver;

			PageFactory.initElements(driver, this);
		}
		
		//Action Method
		public String getTaskCenterPageTitle()
		{
			String taskCenterPageTitle=Action.getTitle(driver);
			return taskCenterPageTitle;
		}

}
