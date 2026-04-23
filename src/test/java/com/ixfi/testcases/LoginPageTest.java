/**
 * 
 */
package com.ixfi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.dataprovider.DataProviders;
import com.ixfi.pageobjects.ForgotPasswordPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.utility.Log;

/**
 **/
public class LoginPageTest extends BaseClass {

	IndexPage index;
	LoginPage login;
	SignUpPage signUp;
	ForgotPasswordPage forgotPass;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyThatUserIsNavigatedToLoginPage() {
		Log.startTestCase("verifyThatUserIsNavigatedToLoginPage");
		login = index.clickOnLoginButton();
		Log.info("User is clicked on login button");
		String actualURL = login.verifyUserIsNavigatedToLoginPage();
		Log.info("User retrived the Actual URL");
		System.out.println(actualURL);
		String ExpectedURL = prop.getProperty("loginPageURL");
		Log.info("User Retrived the Expected URL");
		Log.info("Verifying the actual and expected URL, if both are same or not");
		Assert.assertEquals(actualURL, ExpectedURL, "User is not navigated to LoginPage");
		Log.info("Acutal and expected URL is same");
		Log.endTestCase("verifyThatUserIsNavigatedToLoginPage");
	}

	// @Test(dataProvider="Credentials", dataProviderClass=DataProviders.class)
	@Test
	public void verifyLoginByEmailFunctionality() throws InterruptedException {
		Log.startTestCase("verifyLoginByEmailFunctionalityTest");
		// index.acceptCookies();
		// Action.waitForLoaderToDisappear(driver);
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		// login.clickOnemailButton();
		String title = login.loginUsingEmailID(prop.getProperty("email"), prop.getProperty("password"));
		Log.info("Entered email and password and clicked on Login button");
		Log.info("Retrieved the login page title: " + title);
		// String title=login.loginUsingEmailID(uname,pswd);//this will take the data
		// from excell sheet
		String expected = prop.getProperty("title");
		Assert.assertEquals(title, expected, "Both the titles are not matched, please check again");
		Log.info("Verified " + title + " With Expected title " + expected);
		Log.endTestCase("verifyLoginByEmailFunctionalityTest");
	}

	@Test
	public void verifyThatUserIsNotAbleToLoginByInvalidEmailAndPassword() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsNotAbleToLoginByInvalidEmailAndPasswordTest");
		login = index.clickOnLoginButton();
		String alertMsg = login.loginUsingInvalidEmailAndPassword("abc@gamil.com", "Test@12345t");
		Log.info("Alert Message: " + alertMsg);

		Log.endTestCase("verifyThatUserIsNotAbleToLoginByInvalidEmailAndPasswordTest");
	}

	@Test
	public void verifyLoginByPhoneNumberFunctionality() throws InterruptedException {
		Log.startTestCase("verifyLoginByPhoneNumberFunctionalityTest");
		// Action.waitForLoaderToDisappear(driver);
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button ");
		login.clickOnPhoneButton();
		Log.info("Clicked on phone button");
		login.loginUsingPhoneNumber(prop.getProperty("phone"), prop.getProperty("password"));
		Log.info("Entered phone number: " + prop.getProperty("phone")
				+ " and Password into the textfield and clicked on login button");
		Log.endTestCase("verifyLoginByPhoneNumberFunctionalityTest");
	}

	@Test
	public void verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLink() {
		Log.startTestCase("verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLinkTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		signUp = login.clickOnSignUpNowLink();
		Log.info("Clicked on signUpNow link ");
		Assert.assertEquals(signUp.verifyUserIsNavigatedToSignUpPage(), prop.getProperty("signUpPageURL"));
		Log.info("Verified that user is navigated to signup page from login page by clicking on singup link");
		Log.endTestCase("verifyThatUserIsNavigatedToSignUpPageByClickingSingUpNowLinkTest");

	}

	@Test
	public void verifyUserIsAbleToClickOnForgotPasswordLinkAndForgotPasswordPageTitle() {
		Log.startTestCase("verifyUserIsAbleToClickOnForgotPasswordLinkAndForgotPasswordPageTitleTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on login Button");
		forgotPass = login.clickOnForgotPasswordLink();
		Log.info("Clicked On Forgot Password Link");
		Assert.assertNotNull(forgotPass, "User is not able to click on forgot password link");
		Log.info("User is navigated to forgot password page");
		String actualTitle = forgotPass.getforgotPasswordPageTitle();
		Log.info("Retrieved actual title: " + actualTitle);
		String expectedTitle = "Reset Password | IXFI";
		Assert.assertEquals(actualTitle, expectedTitle, "Titles are not matched, please verify again");
		Log.endTestCase("verifyUserIsAbleToClickOnForgotPasswordLinkAndForgotPasswordPageTitleTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnEyeIconAndGrabTheTextOfPassword() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnEyeIconAndGrabTheTextOfPasswordTest");
		//
		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");

		login.clickOnEyeIconPasswordField();
		Log.info("Clicked on eye button");
		String imgSrcafterClicking = login.getPropertyofEyeButton();
		Log.info("Retrieved the eye button property after clicking on it: src- " + imgSrcafterClicking);
		Log.info("Verifying that src must containt eye.svg, that is password is visible after clicking on it");
		Assert.assertTrue(imgSrcafterClicking.contains("eye"),
				"The src does not contain 'eye': " + imgSrcafterClicking);
		Log.info("Verified. \n Again clicking on eye button");
		Thread.sleep(3000);
		login.clickOnEyeIconPasswordField();
		Log.info("Getting the property of eye button it must be off");
		String imgSrc = login.getPropertyofEyeButton();
		Log.info("Retrieved the eye button src property before clicking on it: Src - " + imgSrc);
		System.out.println(imgSrc);
		Log.info("Verifying that src must containt eye-off.svg, that is password is invisible");
		Assert.assertTrue(imgSrc.contains("eye-off"), "The src does not contain 'eye-off': " + imgSrc);
		Log.info("Verified");
		Log.endTestCase("verifyThatUserIsAbleToClickOnEyeIconAndGrabTheTextOfPasswordTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnDownloadButtonPresentOnTop() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnDownloadButtonPresentOnTopTest");

		login = index.clickOnLoginButton();
		Log.info("Clicked on login button");
		Thread.sleep(4000);
		login.clickOnAosAndIosAppIcon();
		Log.info("Clicked on Download Icon");
		String getQRText = login.getAppDownloadScannerText();
		Log.info("Retrieved the QR Text" + getQRText);
		Assert.assertTrue(login.verifyAppDownloadQRCodeImgIsDisplayed(), "QR Code is not displayed");
		Log.endTestCase("verifyThatUserIsAbleToClickOnDownloadButtonPresentOnTopTest");
	}

	@Test
	public void verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightMode() throws InterruptedException {
		
		Log.startTestCase("verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightModeTest");
		login=index.clickOnLoginButton();
		Log.info("Clicked on Login button");
		Thread.sleep(4000);
		String actualAlt=login.validateDayLightMode();
		Log.info("Retrieved the dayLigh mode before clicking on it:  "+actualAlt);
		Assert.assertTrue(actualAlt.contains("sun.webp"),"Does not changed to light mode, please check again");
		login.clickOnDayLightButton();
		Log.info("Clicked on DayLight Button");
		Thread.sleep(4000);
		String modeAfterClicking=login.validateDayLightMode();
		Log.info("Retrieved the mode after clicking on daylight mode button: "+modeAfterClicking);
		Assert.assertTrue(modeAfterClicking.contains("dark-icon.svg"),"Does not changed to Dark mode, please check again");
		Log.endTestCase("verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightModeTest");

	}
	
	@Test
	public void verifyThatByClickingOnIXFILogoTheUserIsNavigatedBackToTheIndexPage() throws InterruptedException
	{
		Log.startTestCase("verifyThatByClickingOnIXFILogoTheUserIsNavigatedBackToTheIndexPageTest");
		login=index.clickOnLoginButton();
		Log.info("Clicked on Login Button");
		Action.implicitWait(driver, 10);
		login.clickOnIxfiLogo();
		Log.info("Clicked on IXFI Logo");
		Thread.sleep(4000);
		index=login.clickOnYesButton();
		Log.info("Clicked on Yes button from popup");
		String actTitle=index.getIXFITitle();
		Log.info("Retrieved the title of index page: "+actTitle);
		Assert.assertEquals(actTitle, prop.getProperty("title"),"Titles are not matched, please verify again");
		Log.endTestCase("verifyThatByClickingOnIXFILogoTheUserIsNavigatedBackToTheIndexPageTest");
	}
	
	@Test
	public void verifyThatByClickingOnIXFILogoAndNotAcceptingThePopUPTheUserIsNotNavigatedBackToTheIndexPage() throws InterruptedException
	{
		Log.startTestCase("verifyThatByClickingOnIXFILogoAndNotAcceptingThePopUPTheUserIsNotNavigatedBackToTheIndexPage");
		login=index.clickOnLoginButton();
		Log.info("Clicked on Login Button");
		Action.implicitWait(driver, 10);
		login.clickOnIxfiLogo();
		Log.info("Clicked on IXFI Logo");
		Thread.sleep(4000);
		login.clickOnNoButton();
		Log.info("Clicked on No button from popup");
		String actTitle=login.getLoginPageTitle();
		Log.info("Retrieved the title of Login page: "+actTitle);
		Assert.assertEquals(actTitle, "Login | IXFI","Titles are not matched, please verify again");
		Log.endTestCase("verifyThatByClickingOnIXFILogoAndNotAcceptingThePopUPTheUserIsNotNavigatedBackToTheIndexPage");
	}
}
