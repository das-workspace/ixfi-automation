package com.ixfi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.ForgotPasswordPage;
import com.ixfi.pageobjects.GmailPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.ResetPasswordPage;
import com.ixfi.utility.Log;

public class ForgotPasswordPageTest extends BaseClass {

	IndexPage index;
	LoginPage login;
	ForgotPasswordPage forgotPass;
	GmailPage gmail;
	ResetPasswordPage reset;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		gmail = new GmailPage(driver);
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyUserIsAbleToClickOnForgotPasswordLink() throws InterruptedException {
		Log.startTestCase("verifyUserIsAbleToClickOnForgotPasswordLinkTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		Thread.sleep(1000);
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		String actualTitle = forgotPass.getforgotPasswordPageTitle();
		Log.info("Retrieved the actual title: " + actualTitle);
		Assert.assertEquals(actualTitle, prop.getProperty("forgotPasswordPageTitle"),
				"Titles are not matched, please check again");
		Log.info("Verified forgot password page title");
		Log.endTestCase("verifyUserIsAbleToClickOnForgotPasswordLinkTest");
	}

	@Test
	public void verifyPasswordResetLinkIsSentOnTheRegisteredEmailID() throws InterruptedException {
		Log.startTestCase("verifyPasswordResetLinkIsSentOnTheRegisteredEmailIDTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		Thread.sleep(2000);
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		forgotPass.enterEmail();
		Log.info("Enter valid email ID");
		forgotPass.clickOnSubmitBtn();
		Log.info("Clicked On Submit button");
		Log.info("Validate captcha slider Manually");
		Thread.sleep(5000);
		gmail.navigateToGmail();
		Log.info("navigated to the gmail in new tab");
		Thread.sleep(2000);
		Action.switchToNewTab();
		Log.info("Driver control passed to the gmail tab");
		gmail.loginToTheGmail();
		Log.info("Entered Valid username and password for login");
		Thread.sleep(2000);
		gmail.waitForGmailToLoadProperly();
		Log.info("Successfully Logged into the gmail");
		gmail.clickOnResetPasswordMail();
		Log.info("Clicked on Reset password mail");
		Thread.sleep(2000);
		if (gmail.isHideExpandedContentInMailVisible()) {
			gmail.clickOnHideExpandedContent();
			Log.info("Expanded the mail to see the reset link");
		}
		Thread.sleep(1000);
		reset = gmail.clickOnResetPasswordLink();
		Log.info("Clicked on reset password Link");
		Thread.sleep(2000);
		Action.switchToNewTab();
		Log.info("Driver Switched to reset password page");
		reset.enterNewPassword();
		Log.info("Entered New Password");
		reset.enterNewConfirmPassword();
		Log.info("Entered Confirm New Password");
		Thread.sleep(2000);
		reset.clickOnUpdatePasswordButton();
		Log.info("Clicked on update password button");
		Thread.sleep(2000);
		reset.enterIXFIPin();
		Log.info("Entered IXFI Pin");
		login = reset.clickOnNextButton();
		Thread.sleep(1000);
		Assert.assertEquals(login.getLoginPageTitle(), prop.getProperty("loginPageTitle"),
				"LoginPage title are not matched");
		Log.info("Verified login page title");
		Log.endTestCase("verifyPasswordResetLinkIsSentOnTheRegisteredEmailIDTest");
	}

	@Test
	public void verifyWhenUserClickOnLoginLinkItWillNavigatedToTheLoginPage() throws InterruptedException {
		Log.startTestCase("verifyWhenUserClickOnLoginLinkItWillNavigatedToTheLoginPageTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		Thread.sleep(1000);
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		Thread.sleep(1000);
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		login = forgotPass.clickOnLoginLink();
		Log.info("Clicked on login link");
		Log.info("Verifying the page title of login page: " + login.getLoginPageTitle());
		Assert.assertEquals(login.getLoginPageTitle(), prop.getProperty("loginPageTitle"));
		Thread.sleep(1000);
		Log.info("Verified the page titles");
		Log.endTestCase("verifyWhenUserClickOnLoginLinkItWillNavigatedToTheLoginPageTest");
	}

	@Test
	public void verifyThatUserIsAbleToSendTheResetPasswordLinkOnRegisteredPhoneNumber() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToSendTheResetPasswordLinkOnRegisteredPhoneNumberTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		Thread.sleep(1000);
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		Thread.sleep(1000);
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		Thread.sleep(1000);
		forgotPass.clickOnClickHereLink();
		Log.info("Clicked on click here link to send the reset link on phone number");
		forgotPass.enterPhone();
		Log.info("Entered registered phone number");
		forgotPass.clickOnSubmitBtn();
		Log.info("Clicked On Submit button");
		Log.info("Slide the captcha manually");
		Thread.sleep(5000);
		Log.info("Reset password link sent you the registered phone number successfully");
		Log.endTestCase("verifyThatUserIsAbleToSendTheResetPasswordLinkOnRegisteredPhoneNumberTest");
	}

	@Test
	public void verifyThatUserIsAbleToSendTheResetLinkByClickingOnClickHereLinkOnPhoneTab()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToSendTheResetLinkByClickingOnClickHereLinkOnPhoneTabTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		Thread.sleep(1000);
		login.clickOnPhoneButton();
		Log.info("Clicked on phone button section");
		Thread.sleep(1000);
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		forgotPass.clickOnClickHereLink(); // navigated to the send link on phone
		Log.info("Clicked on click here link to send the reset link on phone number");

		forgotPass.clickOnClickHereLink(); // navigated to the send link on email
		Log.info("Clicked on click here link to send the reset link on Email");
		Thread.sleep(3000);

		Log.endTestCase("verifyThatUserIsAbleToSendTheResetLinkByClickingOnClickHereLinkOnPhoneTabTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnLoginLinkAndNavigatedToLoginPageFromPhoneForgotPasswordPage()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToClickOnLoginLinkAndNavigatedToLoginPageFromPhoneForgotPasswordPageTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		forgotPass.clickOnClickHereLink(); // navigated to the send link on phone
		Log.info("Clicked on click here link to send the reset link on phone number");
		login = forgotPass.clickOnLoginLink();
		Log.info("Clicked on login link");
		Log.info("Verifying the page title of login page: " + login.getLoginPageTitle());
		Assert.assertEquals(login.getLoginPageTitle(), prop.getProperty("loginPageTitle"));
		Thread.sleep(4000);
		Log.info("Verified the page titles");

		Log.endTestCase("verifyThatUserIsAbleToClickOnLoginLinkAndNavigatedToLoginPageFromPhoneForgotPasswordPageTest");
	}

	@Test
	public void verifyUserIsAbleToNavigateBackToTheIndexPageFromForgotPasswordPage() {
		Log.startTestCase("verifyUserIsAbleToNavigateBackToTheIndexPageFromForgotPasswordPageTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		login.clickOnemailButton();
		Log.info("Clicked on email button section");
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked on ForgotPassword Link");
		index = forgotPass.clickOnIxfiLogo();
		Log.info("Clicked on IXFI Logo");
		Log.info("Captured Index page title: " + index.getIXFITitle());
		Assert.assertEquals(index.getIXFITitle(), prop.getProperty("title"),
				"Titles are not matched, please verify again");
		Log.info("Verified the page titles");
		Log.endTestCase("verifyUserIsAbleToNavigateBackToTheIndexPageFromForgotPasswordPageTest");
	}

}
