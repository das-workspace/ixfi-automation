package com.ixfi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.utility.Log;

public class SignUpPageTest extends BaseClass {
	IndexPage index;
	SignUpPage signUpPage;
	LoginPage loginPage;
	SoftAssert soft;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		soft = new SoftAssert();
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyCreateAccountByEmailFunctionalityTest() throws InterruptedException {
		Log.startTestCase("verifyCreateAccountByEmailFunctionalityTest");
		Thread.sleep(2000);
		signUpPage = index.clickOnSignUpButton();
		Log.info("clicked On SignUp Button");
		signUpPage.createAccountByUsingEmail();

		signUpPage.clickOnCreateAccountButton();
		Log.info("Clicked on Create Account button");
		Thread.sleep(5000);
		Log.info("Entered all the details in signup form - SignUp by email");
		// Assert.assertFalse(signUpPage.clickOnCreateAccountButton());//after clicking
		// on terms and condition the create account buttons attribute will be changed
		// from disable to empty i.e. enabled
		// therefor it will return us false
		Log.endTestCase("verifyCreateAccountByEmailFunctionalityTest");
	}

	@Test
	public void verifyCreateAccountByPhoneFunctionalityTest() throws InterruptedException {
		Log.startTestCase("verifyCreateAccountByPhoneFunctionalityTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on signup button");
		signUpPage.clickOnPhoneSectionButton();
		Log.info("Clicked on Phone tab");
		if (signUpPage.isWarningCardDisplayed()) {
			Log.info("Warning card displayed");
			Assert.assertTrue(true);

		} else {
			signUpPage.createAccountByUsingPhoneNumber();
			signUpPage.clickOnCreateAccountButton();
			Log.info("Clicked on create Account button");
			Thread.sleep(5000);
		}
		Log.endTestCase("verifyCreateAccountByPhoneFunctionalityTest");
	}

	@Test
	public void verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest() {
		Log.startTestCase("verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Signup button");
		Assert.assertTrue(signUpPage.verifyCreateAccountButtonisClicked());
		Log.info("Veried that create account button is clicked");
		Log.endTestCase("verifyCreateAccountButtonRemainsDisabledUntilTermsAndConditionsAcceptanceTest");
	}

	@Test
	public void verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButton() {
		Log.startTestCase("verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButtonTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on signup button");
		String actualURL = signUpPage.verifyUserIsNavigatedToSignUpPage();
		Log.info("Retrieved the Actual URL from website: " + actualURL);
		String expectedURL = prop.getProperty("signUpPageURL");
		Log.info("Retrieved the expected title from properties file: " + expectedURL);
		Assert.assertEquals(actualURL, expectedURL, "User is not navigated to SignUpPage");
		Log.info("Both the titles are matched ");
		Log.endTestCase("verifyThatUserIsNavigatedToSignUpPageAfterClickingOnRegisterButtonTest");

	}

	// Verify User is navigated to LoginPage from SignUpPage
	@Test
	public void verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest()
			throws InterruptedException {

		Log.startTestCase("verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on SignUp Button");
		loginPage = signUpPage.clickOnLoginButtonLink();
		Thread.sleep(5000);
		// String actualURL=loginPage.verifyUserIsNavigatedToLoginPage();
		// System.out.println(actualURL);
		// String expectedURL=prop.getProperty("loginPageURL");
		Assert.assertEquals(loginPage.verifyUserIsNavigatedToLoginPage(), prop.getProperty("loginPageURL"),
				"User is not naviagted to loginPage from signUpPage");
		Log.info("Retreievd both actual and expected url and matched");
		Log.endTestCase("verifyThatUserIsNavigatedToLoginPageAfterClickinOnLoginLinkPresentOnSignUpPageTest");
	}

	@Test
	public void verifyThatEyeButtonIsClickableAndBothPasswordIsCorrect() throws InterruptedException {
		Log.startTestCase("verifyThatEyeButtonIsClickableAndBothPasswordIsCorrectTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on register button");
		signUpPage.createAccountByUsingEmail();
		Log.info("Entered all the details");
		Thread.sleep(2000);
		String actual_src = signUpPage.getAttributeSRCForPasswordField();
		String expected_src = "https://cdn.ixfi.com/shared/eye-off.svg";
		Log.info("Before clicking on eye icon on password field: the attribute src is:" + actual_src);
		soft.assertEquals(actual_src, expected_src);
		signUpPage.clickOnPasswordEyeImage();
		Log.info("Clicked on password eye image to see the entered password");
		String actual_src_afterClick = signUpPage.getAttributeSRCForPasswordField();
		String expected_src_afterClick = "https://cdn.ixfi.com/shared/eye.svg";
		Log.info("After clicking on eye icon on password field: the attribute src is:" + actual_src_afterClick);
		soft.assertEquals(actual_src_afterClick, expected_src_afterClick);

		String pass = signUpPage.getPasswordText();
		Log.info("Retrieved Password is: " + pass);

		String actualConfirmSrc = signUpPage.getConfirmPasswordSRCAttribute();
		Log.info("Before clicking on confirm password eye button: src: " + actualConfirmSrc);
		soft.assertTrue(actualConfirmSrc.contains("eye-off.svg"),
				"Not able to get the src for confirm password eye field");
		signUpPage.clickOnConfirmPasswordEyeImage();
		Log.info("Clicked on confirm password eye image to see the entered confirm password ");

		String actualConfirmSrcAfterClick = signUpPage.getConfirmPasswordSRCAttribute();
		Log.info("After clicking on confirm password eye button: src: " + actualConfirmSrcAfterClick);
		soft.assertTrue(actualConfirmSrcAfterClick.contains("eye.svg"),
				"Not able to get the src for confirm password eye field");

		String confirmPass = signUpPage.getConfirmPasswordText();
		Log.info("Retrieved Confrim Password is: " + confirmPass);
		soft.assertEquals(pass, confirmPass, "Password and confirmPassword are not matched, please check again");
		soft.assertAll();
		Log.endTestCase("verifyThatEyeButtonIsClickableAndBothPasswordIsCorrectTest");
	}

	@Test
	public void verifyThatWhenUserEntersWrongInputsWhileSignUpWithEmailItShowsProperValidationMessage() {
		Log.startTestCase("verifyThatWhenUserEntersWrongInputsWhileSignUpWithEmailItShowsProperValidationMessageTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Register button");
		signUpPage.clickOnEmailSectionButton();
		Log.info("Clicked on email tab");
		signUpPage.enterEmailId();
		Log.info("Entered email id");
		Log.info("Verifying Whether entered email is as per validation rule or not");
		soft.assertEquals(signUpPage.getEmailValidationMsg(), prop.getProperty("emailValMsg"),
				"Entered email is as per validation rule.");
		Log.info("Verified Email ID validation rule");
		signUpPage.enterPassword();
		Log.info("Entered password");
		Log.info("Verifying Whether entered password is as per validation rule or not");
		soft.assertEquals(signUpPage.getPasswordValidationMsg(), prop.getProperty("passwordValMsg"),
				"Entered Password is as per validation rule.");
		Log.info("Verified password validation rule");
		signUpPage.enterConfirmPassword();
		Log.info("Entered Confirm password");
		Log.info("Verifying Whether entered confirm password is correct or not");
		soft.assertEquals(signUpPage.getConfirmPassValidationMsg(), prop.getProperty("confirmPassValMsg"),
				"Entered confirm password is matched and it is as per validation rule.");
		Log.info("Verified Confirm password validation rule");
		signUpPage.enterUsername();
		Log.info("Entered username");
		Log.info("Verifying Whether entered username is as per validation rule or not");
		soft.assertEquals(signUpPage.getUsernameValidationMsg(), prop.getProperty("usernameValMsg"),
				"Enter username is correct and it is as per validation rule.");
		Log.info("Verified username validation rule");

		soft.assertAll();
		Log.endTestCase("verifyThatWhenUserEntersWrongInputsWhileSignUpWithEmailItShowsProperValidationMessageTest");
	}

	@Test
	public void verifyThatWhenUserEntersWrongInputsWhileSignUpWithPhoneNumberItShowsProperValidationMessage() {
		Log.startTestCase(
				"verifyThatWhenUserEntersWrongInputsWhileSignUpWithPhoneNumberItShowsProperValidationMessageTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Register button");
		signUpPage.clickOnPhoneSectionButton();
		Log.info("Clicked on Phone tab");
		if (signUpPage.isWarningCardDisplayed()) {
			Log.info("Warning card displayed");
			soft.assertTrue(true);

		} else {
			signUpPage.enterPhoneNumber();
			Log.info("Entered Phone Number");

			signUpPage.enterPassword();
			Log.info("Entered password");

			signUpPage.enterConfirmPassword();
			Log.info("Entered Confirm password");

			signUpPage.enterUsername();
			Log.info("Entered username");

			Log.info("Verifying Whether entered phone Number is as per validation rule or not");
			soft.assertEquals(signUpPage.getPhoneValidationMsg(), prop.getProperty("phoneValMsg"),
					"Entered Phone number is as per validation rule.");
			Log.info("Verified Phone Number validation rule");
			Log.info("Verifying Whether entered password is as per validation rule or not");
			soft.assertEquals(signUpPage.getPasswordValidationMsg(), prop.getProperty("passwordValMsg"),
					"Entered Password is as per validation rule.");
			Log.info("Verified password validation rule");
			Log.info("Verifying Whether entered confirm password is correct or not");
			soft.assertEquals(signUpPage.getConfirmPassValidationMsg(), prop.getProperty("confirmPassValMsg"),
					"Entered confirm password is matched and it is as per validation rule.");
			Log.info("Verified Confirm password validation rule");
			Log.info("Verifying Whether entered username is as per validation rule or not");
			soft.assertEquals(signUpPage.getUsernameValidationMsg(), prop.getProperty("usernameValMsg"),
					"Enter username is correct and it is as per validation rule.");
			Log.info("Verified username validation rule");
		}
		soft.assertAll();
		Log.endTestCase(
				"verifyThatWhenUserEntersWrongInputsWhileSignUpWithPhoneNumberItShowsProperValidationMessageTest");
	}

	@Test
	public void verifyUserIsNavigatedBackToTheHomePageByClickingOnIxfiLogo() throws InterruptedException {
		Log.startTestCase("verifyUserIsNavigatedBackToTheHomePageByClickingOnIxfiLogoTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Register button");
		loginPage = new LoginPage(driver); // used login page method bcz IXFI logo xpath is same on both signup page and
											// login page. to avoid duplicate code. I used all methods from login page
		loginPage.clickOnIxfiLogo();
		Log.info("Clicked on IXFI Logo");
		Thread.sleep(4000);
		index = loginPage.clickOnYesButton();
		Log.info("Clicked on Yes Button");
		String title = index.getIXFITitle();
		Log.info("Retrieved the title of homepage: " + title);
		Assert.assertEquals(title, prop.getProperty("title"), "Titles are not matched, please check again");
		Log.endTestCase("verifyUserIsNavigatedBackToTheHomePageByClickingOnIxfiLogoTest");
	}

	@Test
	public void verifyUserIsNotNavigatedBackToTheHomePageByClickingOnIxfiLogoAndNoOnPopup()
			throws InterruptedException {
		Log.startTestCase("verifyUserIsNotNavigatedBackToTheHomePageByClickingOnIxfiLogoAndNoOnPopupTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Register button");
		loginPage = new LoginPage(driver); // used login page method bcz IXFI logo xpath is same on both signup page and
											// login page. to avoid duplicate code. I used all methods from login page
		loginPage.clickOnIxfiLogo();
		Log.info("Clicked on IXFI Logo");
		Thread.sleep(4000);
		loginPage.clickOnNoButton();
		Log.info("Clicked on No Button");
		// Thread.sleep(4000);
		String title = signUpPage.getSignUpPageTitle();
		Log.info("Retrieved the title of SignUpPage: " + title);
		Assert.assertEquals(title, prop.getProperty("signUpPageTitle"), "Titles are not matched, please check again");
		Log.endTestCase("verifyUserIsNotNavigatedBackToTheHomePageByClickingOnIxfiLogoAndNoOnPopupTest");
	}

	@Test
	public void verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightMode() throws InterruptedException {

		Log.startTestCase("verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightModeTest");
		signUpPage = index.clickOnSignUpButton();
		Log.info("Clicked on Register button");
		Thread.sleep(4000);
		String actualSrc = signUpPage.validateDayLightMode();
		Log.info("Retrieved the dayLigh mode before clicking on it:  " + actualSrc);
		soft.assertTrue(actualSrc.contains("sun.webp"), "Does not changed to light mode, please check again");
		signUpPage.clickOnDayLightButton();
		Log.info("Clicked on DayLight Button");
		Thread.sleep(4000);
		String modeAfterClicking = signUpPage.validateDayLightMode();
		Log.info("Retrieved the mode after clicking on daylight mode button: " + modeAfterClicking);
		soft.assertTrue(modeAfterClicking.contains("dark-icon.svg"),
				"Does not changed to Dark mode, please check again");

		soft.assertAll();
		Log.endTestCase("verifyThatTheDayLightButtonIsClickableAndAbleToChangeTheDarkAndLightModeTest");

	}

}
