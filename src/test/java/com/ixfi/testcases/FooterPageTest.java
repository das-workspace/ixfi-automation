package com.ixfi.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.FooterPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.utility.Log;

public class FooterPageTest extends BaseClass{
	
	IndexPage index;
	SoftAssert softAssert;
	FooterPage footer;
	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert = new SoftAssert();
		// login=new LoginPage(driver);
	}
	
	@Test
	public void verifyThePresenceOfIXFILogo() throws InterruptedException
	{
		Log.startTestCase("verifyThePresenceOfIXFILogoTest");
		footer=index.navigateToFooterSection();
		Thread.sleep(5000);
		Log.info("Navigated to footer section");
		footer.scrollToTheFooterSection();
		Log.info("Scrolled to the IXFI Logo");
		Assert.assertTrue(footer.isIXFILogoDisplayed(), "IXFI Logo at footer section is not present");
		Log.info("Verified the presence of IXFI Logo");
		Log.endTestCase("verifyThePresenceOfIXFILogoTest");
	}
	
	@Test
	public void verifyTheAppleStoreLinkIsPresentAndClickable() throws InterruptedException
	{
		Log.startTestCase("verifyTheAppleStoreLinkIsPresentAndClickableTest");
		footer=index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		//footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		footer.clickOnAppStoreLink();
		Log.info("Clicked on App store link");
		List<String> tabs=Action.getAllOpenTabs();
		
		Log.info("Total Opened Tabs: " + tabs.size());

		// Iterate through all open tabs and verify titles
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("Switched to tab " + i);

			// Wait until the title is available
			String actualTitle = Action.waitForTitle(driver, 10); // Custom wait
			Log.info("Title of Tab " + i + ": " + actualTitle);
			Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
		}
		
		
		Log.endTestCase("verifyTheAppleStoreLinkIsPresentAndClickableTest");
	}
	
	@Test
	public void verifyThePlayStoreLinkIsPresentAndClickable() throws InterruptedException
	{
		Log.startTestCase("verifyThePlayStoreLinkIsPresentAndClickableTest");
		footer=index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		//footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		footer.clickOnPlayStoreLink();
		
		Log.info("Clicked on play store link");
		List<String> tabs=Action.getAllOpenTabs();
		
		Log.info("Total Opened Tabs: " + tabs.size());

		// Iterate through all open tabs and verify titles
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("Switched to tab " + i);

			// Wait until the title is available
			String actualTitle = Action.waitForTitle(driver, 10); // Custom wait
			Log.info("Title of Tab " + i + ": " + actualTitle);
			Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
		}
		
		
		Log.endTestCase("verifyThePlayStoreLinkIsPresentAndClickableTest");
	}
	
	@Test
	public void verifyTheFooterLinksPresentAndAbleToOpenInNewTab() throws InterruptedException
	{
		Log.startTestCase("verifyTheFooterLinksPresentAndAbleToOpenInNewTabTest");
		footer=index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		//footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		List<WebElement>links=footer.getAllFooterLinks();
		for(WebElement link:links)
		{
			Action.openInNewTab(link);
			Thread.sleep(500);
		}
		List<String> tabs=Action.getAllOpenTabs();
		Log.info("Total Opened Tabs: " + tabs.size());

		// Iterate through all open tabs and verify titles
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("Switched to tab " + i);

			// Wait until the title is available
			String actualTitle = Action.waitForTitle(driver, 10); // Custom wait
			Log.info("Title of Tab " + i + ": " + actualTitle);
			Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
		}
		
		Log.endTestCase("verifyTheFooterLinksPresentAndAbleToOpenInNewTabTest");
	}
	
	@Test
	public void verifyTheSocialMediaFooterLinksPresentAndAbleToOpenInNewTab() throws InterruptedException
	{
		Log.startTestCase("verifyTheSocialMediaFooterLinksPresentAndAbleToOpenInNewTabTest");
		footer=index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		//footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		List<WebElement>links=footer.getAllSocialMediaLinks();
		for(WebElement link:links)
		{
			Action.openInNewTab(link);
			Thread.sleep(500);
		}
		List<String> tabs=Action.getAllOpenTabs();
		Log.info("Total Opened Tabs: " + tabs.size());

		// Iterate through all open tabs and verify titles
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("Switched to tab " + i);

			// Wait until the title is available
			String actualTitle = Action.waitForTitle(driver, 10); // Custom wait
			Log.info("Title of Tab " + i + ": " + actualTitle);
			Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
		}
		
		Log.endTestCase("verifyTheSocialMediaFooterLinksPresentAndAbleToOpenInNewTabTest");
	}

	
	
	
	
	
	
}
