package com.ixfi.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.IXFITokenPage;
//import com.ixfi.pageobjects.FooterPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.utility.Log;
//import com.ixfi.pageobjects.StatusPage;

public class IXFITokenPageTest extends BaseClass {

	IndexPage index;
	SoftAssert softAssert;
	IXFITokenPage ixfiToken;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert = new SoftAssert();
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyThatUserIsAbleToClickOnIXFITokenHeaderMenu() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnIXFITokenHeaderMenuTest");
		ixfiToken = index.clickOnIXFITokenHeaderMenu();
		Log.info("Clicked on IXFI Token header menu");
		// Thread.sleep(1000);
		Action.switchToNewTab();
		Log.info("Switched to the IXFI Token Tab");
		Thread.sleep(4000);
		String actualTitle = ixfiToken.getIXFITokenPageTitle();
		Log.info("IXFI Token page title:" + actualTitle);
		String expectedTitle = prop.getProperty("ixfiTokenPageTitle");
		softAssert.assertEquals(actualTitle, expectedTitle,
				"IXFI Token page Titles are not matched, please verify it again");
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToClickOnIXFITokenHeaderMenuTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtTopAndAbleToNavigateToTheEmailField()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtTopAndAbleToNavigateToTheEmailFieldTest");
		ixfiToken = index.clickOnIXFITokenHeaderMenu();
		Log.info("Clicked on IXFI Token header menu");
		// Thread.sleep(1000);
		Action.switchToNewTab();

		Log.info("Switched to the IXFI Token Tab");
		Thread.sleep(4000);
		ixfiToken.clickOnSecureYourSpotButtonPresentOnTop();
		Log.info("Clicked On Secure Your spot button from top section");
		softAssert.assertTrue(ixfiToken.isEmail_JoinWaitListSectionPresent(),
				"Join wait list section is not present, please verify again");
		String actualButtonText=ixfiToken.getTextOfJoinWaitListButton();
		Log.info("Actual Button Text: "+actualButtonText);
		softAssert.assertEquals(actualButtonText, "Join Waitlist","Button Text are not matched, Email section is not present,please verify again");
		Thread.sleep(2000);
		
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonsPresentAtTopAndAbleToNavigateToTheEmailFieldTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtMiddleAndAbleToNavigateToTheEmailField()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtMiddleAndAbleToNavigateToTheEmailFieldTest");
		ixfiToken = index.clickOnIXFITokenHeaderMenu();
		Log.info("Clicked on IXFI Token header menu");
		// Thread.sleep(1000);
		Action.switchToNewTab();

		Log.info("Switched to the IXFI Token Tab");
		Thread.sleep(4000);
		ixfiToken.clickOnSecureYourSpotButtonPresentOnMiddle();
		Log.info("Clicked On Secure Your spot button from Middle section");
		softAssert.assertTrue(ixfiToken.isEmail_JoinWaitListSectionPresent(),
				"Join wait list section is not present, please verify again");
		Thread.sleep(2000);
		String actualButtonText=ixfiToken.getTextOfJoinWaitListButton();
		Log.info("Actual Button Text: "+actualButtonText);
		softAssert.assertEquals(actualButtonText, "Join Waitlist","Button Text are not matched, Email section is not present,please verify again");
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonsPresentAtMiddleAndAbleToNavigateToTheEmailFieldTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtBottomAndAbleToNavigateToTheEmailField()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonPresentAtBottomAndAbleToNavigateToTheEmailFieldTest");
		ixfiToken = index.clickOnIXFITokenHeaderMenu();
		Log.info("Clicked on IXFI Token header menu");
		// Thread.sleep(1000);
		Action.switchToNewTab();

		Log.info("Switched to the IXFI Token Tab");
		Thread.sleep(4000);

		ixfiToken.clickOnSecureYourSpotButtonPresentOnBottom();
		Log.info("Clicked On Secure Your spot button from Bottom section");
		softAssert.assertTrue(ixfiToken.isEmail_JoinWaitListSectionPresent(),
				"Join wait list section is not present, please verify again");
		String actualButtonText=ixfiToken.getTextOfJoinWaitListButton();
		Log.info("Actual Button Text: "+actualButtonText);
		softAssert.assertEquals(actualButtonText, "Join Waitlist","Button Text are not matched, Email section is not present,please verify again");
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToClickOnSecureYourSpotButtonsPresentAtBottomAndAbleToNavigateToTheEmailFieldTest");
	}
	
	//@Test //this testcase is blocked due to security reason 
	public void verifyThatUserIsAbleToSubscribeTheEmailAndJoinIxfiTokenWishList() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToSubscribeTheEmailAndJoinIxfiTokenWishListTest");
		ixfiToken = index.clickOnIXFITokenHeaderMenu();
		Log.info("Clicked on IXFI Token header menu");
		// Thread.sleep(1000);
		Action.switchToNewTab();

		Log.info("Switched to the IXFI Token Tab");
		Thread.sleep(4000);
		ixfiToken.clickOnSecureYourSpotButtonPresentOnTop();
		Log.info("Click on secure your spot button");
		Thread.sleep(2000);
		ixfiToken.enterEmail(prop.getProperty("email2"));
		Log.info("Entered the email id: "+prop.getProperty("email2"));
		ixfiToken.clickOnJoinWaitlistButton();
		Log.info("Clicked on join waitList button");
		Thread.sleep(1000);
		String actual_msg=ixfiToken.getAlertMessage();
		Log.info("Actual Alert Message: "+actual_msg);
		String expected_msg=prop.getProperty("ixfiTokenAlertMessage");
		Log.info("Expected alert message: "+expected_msg);
		softAssert.assertEquals(actual_msg, expected_msg,"Success message not correct, can u please verify if user has been subscribed or not");
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToSubscribeTheEmailAndJoinIxfiTokenWishListTest");
	}

}
