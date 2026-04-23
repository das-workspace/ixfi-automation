package com.ixfi.testcases;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.AirdropsPage;
import com.ixfi.pageobjects.ConvertPage;
import com.ixfi.pageobjects.IXFITokenPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.actiondriver.*;
import com.ixfi.pageobjects.ReferralProgramPage;
import com.ixfi.pageobjects.RewardsProgramPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.pageobjects.TaskCenterPage;
import com.ixfi.pageobjects.TradePage;
import com.ixfi.utility.Log;

public class IndexPageTest extends BaseClass {

	// IndexPage index=new IndexPage();

	LoginPage login;
	IndexPage index;
	RewardsProgramPage rewardsProgramPage;
	AirdropsPage airdrops;
	ConvertPage convert;
	ReferralProgramPage referral;
	SignUpPage signUp;
	TaskCenterPage taskCenter;
	TradePage trade;
	IXFITokenPage token;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUpTest() {
		// Action.acceptCookies(null);
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert=new SoftAssert();
	}

	@Test
	public void verifyPresenceOfIxfiLogo() {
		Log.startTestCase("verifyPresenceOfIxfiLogoTest");
		Log.info("Checking ixfi logo is displayed or not");
		boolean result = index.validateLogo(); // return true is logo is present else false --> if false test will fail
		Log.info("Logo display status: " + result);
		Assert.assertTrue(result, "Logo is not displayed on the page.");
		Log.info("Logo present on webpage and verified successfully");
		Log.endTestCase("verifyPresenceOfIxfiLogoTest");
	}

	@Test
	public void verifyIXFIWebPageTitle() {
		Log.startTestCase("verifyIXFIWebPageTitleTest");
		Log.info("Verifying the title of the landing page");
		String actTitle = index.getIXFITitle(); // this is actual title present on website
		Log.info("Retrived the actual Title of page: " + actTitle);
		// System.out.println(actTitle);
		String expectedTitle = prop.getProperty("title");
		Log.info("Retrieved the title from properties file: " + expectedTitle);
		// System.out.println(expectedTitle);
		Log.info("Verifying both the titles are similer or not");
		Assert.assertEquals(actTitle, expectedTitle, "Titles are not matched");
		Log.info("Bot the title i.e. Actual Title:  " + actTitle + " and Expected Title: " + expectedTitle
				+ " are matched ");
		Log.endTestCase("verifyIXFIWebPageTitleTest");

	}

	@Test
	public void verifyLoginButtonIsClickable() throws InterruptedException {
		Log.startTestCase("verifyLoginButtonIsClickable");
		Log.info("Navigating to Login button on index page");
		Assert.assertNotNull(login=index.clickOnLoginButton(), "Login button is not found or not clickable.");
		Thread.sleep(2000);
		Log.info("Clicked on Login button");
		String welcomTitle=login.getWelcomeTitleFromLoginPage();
		Log.info("Actual Title: "+welcomTitle);
		Assert.assertEquals(welcomTitle, "Welcome To IXFI","User is not navigated to login page, please check again");
		
		Log.endTestCase("verifyLoginButtonIsClickableTest");

		// if button is not clickable, it will return null object and the condition will
		// become true and test will fail
	}

	@Test
	public void verifySignUpButtonIsClickable() {
		Log.startTestCase("verifySignUpButtonIsClickableTest");
		Log.info("Navigating to the SignUp button on Index Page");
		Assert.assertNotNull(index.clickOnSignUpButton(), "SignUp button is not found or not clickable.");
		Log.info("Clicked on signUp Button");
		Log.endTestCase("verifySignUpButtonIsClickableTest");
	}

	// purposefully // failing this // testcase -- // letter I have // to use frame
	// // handling // concept

	@Test
	public void verifyLoginButtonIsClickableAndUserIsNavigatedToLoginPage() throws InterruptedException {

		Log.startTestCase("verifyLoginButtonIsClickableAndUserIsNavigatedToLoginPageTest");
		login = index.clickOnLoginButton();
		Log.info("Clicked on Login button");
		Thread.sleep(4000);
		String loginPageTitle = login.getLoginPageTitle();
		Log.info("Retrieved the login page title: " + loginPageTitle);
		String loginPageTitleExpected = "Login | IXFI";
		Log.info("Expected title: " + loginPageTitleExpected);
		Log.info("Verifying both are equal or not");
		Assert.assertEquals(loginPageTitle, loginPageTitleExpected, "Titles are not matched, please check again");
		Log.info("Verifed both the titles. Both are matched");
		Log.endTestCase("verifyLoginButtonIsClickableAndUserIsNavigatedToLoginPageTest");
	}

	@Test
	public void verifyThePresenceOfHeaderMenu() throws InterruptedException {
		Log.startTestCase("verifyThePresenceOfHeaderMenuTest");
		Thread.sleep(2000);
		List<WebElement> menuItems = index.getHeaderMenuList();
		Log.info("Retrieved the header menu list from web");
		// Verify that the header has the expected number of items
		// index.printHeaderMenuList();
		int expectedItemCount = 16; // Adjust this to the number of expected menu items
		Assert.assertEquals(menuItems.size(), expectedItemCount, "Number of menu items mismatch");

		// Verify the presence of each menu item
		String[] expectedMenuItems = { "Buy Crypto", "Markets", "Trade", "", "", "Rewards Program", "", "", "", "", "",
				"", "Research", "", "", "IXFI Token" };

		for (int i = 0; i < menuItems.size(); i++) {
			String actualMenuItemText = menuItems.get(i).getText().trim();
			Log.info("Matching " + actualMenuItemText + " with " + expectedMenuItems[i]);
			Assert.assertEquals(actualMenuItemText, expectedMenuItems[i], "Menu item text mismatch at index " + i);
		}
		Log.info("Verified The header menu items ");
		Log.endTestCase("verifyThePresenceOfHeaderMenuTest");
	}

	@Test
	public void verifyThePresenceOfTradeMenuDD() {
		Log.startTestCase("verifyThePresenceOfTradeMenuDDTest");
		List<WebElement> convertMenuItems = index.getTradeMenuList();
		Log.info("Retrieved The actual convert DD menu List After mouse hovering on Convert header menu and the number of submenu's are: "+convertMenuItems.size());
		// System.out.println("Menu items found: " + convertMenuItems.size());

		String[] expectedConvertMenuItems = { "Convert\nConvert any coin with 0% fees",
				"Spot Trade\nBuy & Sell 350+ cryptos" };

		for (int i = 0; i < convertMenuItems.size(); i++) {
			String actualConvertMenuItemText = Action.standardizeText(convertMenuItems.get(i).getText());
			Log.info("Actual Menu: "+actualConvertMenuItemText);
			String expectedConvertMenuItemText = Action.standardizeText(expectedConvertMenuItems[i]);
			Log.info("Expected Menu: "+expectedConvertMenuItemText);
			// System.out.println("Actual: " + actualConvertMenuItemText);
			// System.out.println("Expected: " + expectedConvertMenuItemText);

			Assert.assertEquals(actualConvertMenuItemText, expectedConvertMenuItemText,
					"Menu item text mismatch at index " + i);
		}
		Log.info("Verified the actual convert menu items with the expected menu items");
		Log.endTestCase("verifyThePresenceOfTradeMenuDDTest");
	}

	@Test
	public void verifyThePresenceOfRewardsProgramMenuDD() {
		Log.startTestCase("verifyThePresenceOfRewardsProgramMenuDDTest");
		List<WebElement> rewardsProgramMenuItems = index.getRewardsProgramMenuList();
		Log.info("Retrieved the Rewards program DD menu List after hovering on RewardsProgram menu button; Found - "+rewardsProgramMenuItems.size());
		// System.out.println("Menu items found: " + rewardsProgramMenuItems.size());

		String[] expectedRewardsProgramMenuItems = { "Rewards\nRedeem & Earn free crypto",
				"NFT Rewards\nWin NFTs & unique rewards", "Task Center\nComplete tasks. Earn IXFI Points",
				"Airdrops\nDigital rewards for everyone", "Steps\nWalk your way into crypto!",
				"Leaderboard\nCheck your ranking among users" };

		for (int i = 0; i < rewardsProgramMenuItems.size(); i++) {
			String actualRewardsProgramMenuItemText = Action.standardizeText(rewardsProgramMenuItems.get(i).getText());
			// System.out.println(actualRewardsProgramMenuItemText);
			String expectedRewardsProgramMenuItemText = Action.standardizeText(expectedRewardsProgramMenuItems[i]);
			Log.info("Matching " + actualRewardsProgramMenuItemText + " With " + expectedRewardsProgramMenuItemText);
			// System.out.println("Actual: " + actualRewardsProgramMenuItemText);
			// System.out.println("Expected: " + expectedRewardsProgramMenuItemText);

			softAssert.assertEquals(actualRewardsProgramMenuItemText, expectedRewardsProgramMenuItemText,
					"Menu item text mismatch at index " + i);
		}
		softAssert.assertAll();
		Log.info("Verified the actual RewardsProgram menu items with the expected menu items");

		Log.endTestCase("verifyThePresenceOfRewardsProgramMenuDDTest");
	}

	@Test
	public void verifyThePresenceOfResearchMenuDD() {
		Log.startTestCase("verifyThePresenceOfResearchMenuDDTest");
		List<WebElement> researchMenuItems = index.getResearchMenuList();
		Log.info("Retrieved The actual Research DD menu List After mouse hovering on Research header menu: Found - "+researchMenuItems.size());
		// System.out.println("Menu items found: " + convertMenuItems.size());

		String[] expectedResearchMenuItems = { "Coin Info\nAll the infos & data you need",
				"News\nRead the latest crypto news" };

		for (int i = 0; i < researchMenuItems.size(); i++) {
			String actualResearchMenuItemText = Action.standardizeText(researchMenuItems.get(i).getText());
			String expectedResearchMenuItemText = Action.standardizeText(expectedResearchMenuItems[i]);
			Log.info(
					"Matching Actual " + actualResearchMenuItemText + " with Expected " + expectedResearchMenuItemText);
			// System.out.println(actualResearchMenuItemText);
			// System.out.println("Expected: " + expectedResearchMenuItemText);

			softAssert.assertEquals(actualResearchMenuItemText, expectedResearchMenuItemText,
					"Menu item text mismatch at index " + i);
		}
		softAssert.assertAll();
		Log.info("Verified the actual research menu items with the expected menu items");
		Log.endTestCase("verifyThePresenceOfResearchMenuDDTest");
	}

	@Test
	public void verifyThePresenceOfGen3_0CryptoExchangeText() {
		Log.startTestCase("verifyThePresenceOfGen3_0CryptoExchangeTextTest");
		String actualText = index.validateGen3_0CryptoExchangeText();
		Log.info("Got the gen3.0 text from index page");
		String expectedText = prop.getProperty("cryptoExchangeText");
		Log.info("Got the actual text of cryto exchange from property file");
		Assert.assertEquals(actualText, expectedText, "Text Not matched, please check the correct text on index page");
		Log.info("Verified the actual and expected text and Both the text matched.");
		Log.endTestCase("verifyThePresenceOfGen3_0CryptoExchangeTextTest");
	}

	@Test
	public void verifyThePresenceOfCryptoParaText() {
		Log.startTestCase("verifyThePresenceOfCryptoParaTextTest");
		String actualText = index.validateParaText0fCryptoExchange();
		Log.info("Got the gen3.0 para text from index page: Actual Para: " + actualText);
		String expectedText = prop.getProperty("cryptoExchangeParaText");
		Log.info("Got the Expected para text of cryto exchange from property file: Expected Para: " + expectedText);
		Assert.assertEquals(actualText, expectedText, "Text Not matched, please check the correct text on index page");
		Log.info("Verified the actual and expected text and Both the text matched.");
		Log.endTestCase("verifyThePresenceOfCryptoParaTextTest");

	}

	@Test
	public void verifyTheDayLightModeIsChangingWhenUserClicksOnItFromDayToNightMode() {
		Log.startTestCase("verifyTheDayLightModeIsChangingWhenUserClicksOnItFromDayToNightModeTest");
		// bydefualt alt attribute is light and when clicked on it, it will change to
		// dark mode
		String actualAlt = index.validateDayLightMode();
		Log.info("Retrived Light mode, Status: " + actualAlt);
		// System.out.println(actualAlt);

		if (actualAlt.contains("light")) {
			index.clickOnDayLightButton();
			Log.info("Clicked On DayLightMode button");
			String alt = index.validateDayLightMode();
			Log.info("Changed to Dark Mode: Status: " + alt);
			// System.out.println(alt);
			Assert.assertEquals(alt, prop.getProperty("darkMode"), "DayLightMode in not working");
			Log.info("Verified Day to night Mode");
			Log.endTestCase("verifyTheDayLightModeIsChangingWhenUserClicksOnItFromDayToNightModeTest");
		}

	}

	@Test
	public void verifyTheDayLightModeIsChangingWhenUserClicksOnItFromNightToDayMode() {
		Log.startTestCase("verifyTheDayLightModeIsChangingWhenUserClicksOnItFromNightToDayModeTest");
		// bydefualt alt attribute is light and when clicked on it, it will change to
		// dark mode
		String actualAlt = index.validateDayLightMode();
		Log.info("Retrived Light mode, status: " + actualAlt);
		index.clickOnDayLightButton();// here it changes to dark mode
		Log.info("Changed to night mode - clicked on dayLight Button");
		String nightMode = index.validateDayLightMode();
		// System.out.println(actualAlt);
		Log.info("Status change to: " + nightMode);
		if (nightMode.contains("dark")) {
			index.clickOnDayLightButton();
			Log.info("Clicked On DayLightMode button");
			String alt = index.validateDayLightMode();
			Log.info("Changed to Day Mode i.e. Light Mode");
			// System.out.println(alt);
			Assert.assertEquals(alt, prop.getProperty("lightMode"), "DayLightMode in not working");
			Log.info("Verified Night to day Mode");
			Log.endTestCase("verifyTheDayLightModeIsChangingWhenUserClicksOnItFromNightToDayModeTest");
		}

	}

	@Test
	public void verifyDownloadButtonIsClickableUnderHamburgerMenu() throws InterruptedException {
		Log.startTestCase("verifyDownloadButtonIsClickableUnderHamburgerMenuTest");
		index.validateDownloadButtonUnderHamburgerMenuIsClickable();
		Log.info("Clicked on download button");
		Log.info("New window tab is opened");
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
		Log.endTestCase("verifyDownloadButtonIsClickableUnderHamburgerMenuTest");
		// Assert.assertTrue(index.validateDownloadButtonUnderHamburgerMenuIsClickable(),
		// "Download Button is not clicked");

	}

	@Test
	public void verifyLangaugeButtonIsClickableUnderHamburgerMenuAndUserIsAbleToExtractTheLanguagesAndClickOnThem()
			throws InterruptedException {
		Log.startTestCase(
				"verifyLangaugeButtonIsClickableUnderHamburgerMenuAndUserIsAbleToExtractTheLanguagesAndClickOnThemTest");
		List<WebElement> langauegs = index.validateLanguageButtonUnderHambugerMenuIsClickable();
		Log.info("User is clicked on Hambergur Menu and Language Button Under it");
		Log.info("Extracted the Languages and Language Count is: " + langauegs.size());
		Assert.assertEquals(langauegs.size(), 2,"Size not matched, expected size must be 2");
		for (WebElement lang : langauegs) {
			Log.info("Langauge: " + lang.getText());
			if (lang.getText().contains("Română")) {
				lang.click();
				// index.clickOnLimbaButton();
				Log.info("Click on Romania Langauge button, i.e " + lang.getText());
				Thread.sleep(4000);
				String limbaText = index.getTheTextOfLimbaButton();
				Log.info("Extractedt Actual Text of Selected Laguage: " + limbaText);
				Log.info("Verifying both are Equal or not");
				Assert.assertEquals(limbaText, "Limbă", "Both Text are not equal, please verify it again");
				Log.info("Verified both the Text are Matched ");

			}
			if (lang.getText().contains("English")) {

				Action.clickUsingJavaScript(driver, lang);
				Log.info("Clicked on Laguage button and selected English Langauge");
				String actualEnglishText = index.getTextOfEnglishLanguage();
				Log.info("Get the Text of selected Langauge: " + actualEnglishText);
				Log.info("Verifying both actual and Expected Text of Selected: " + lang.getText() + " Langauge");
				Assert.assertEquals(actualEnglishText, "English", "English Text are not mathched, please check again");
				Log.info("Verified the Text, Both Are Equal");
				index.clickOnLanguageButtonUnderHamburgerMenu();
				Log.info("Clicked On Langauge button to change the Langauge");
				Thread.sleep(5000);
			}

		}
		Log.endTestCase(
				"verifyLangaugeButtonIsClickableUnderHamburgerMenuAndUserIsAbleToExtractTheLanguagesAndClickOnThemTest");
	}

	@Test
	public void verifyThatCurrencyButtonIsClickableUnderHamburgerMenuAndUserIsAbleToSelectTheCurrency()
			throws InterruptedException {
		Log.startTestCase("verifyThatCurrencyButtonIsClickableUnderHamburgerMenuAndUserIsAbleToSelectTheCurrencyTest");
		index.clickOnHamburgerMenu();
		Log.info("Clicked On Hamburger Menu");
		index.clickOnCurrencyButton();
		Log.info("Clicked On Currency Button");
		List<WebElement> currencies = index.getListOfCurrency();
		Log.info("Extracted the Currencies Available" + currencies.get(0).getText() + " And "
				+ currencies.get(1).getText());
		for (WebElement curr : currencies) {

			Action.selectByVisibleText(curr, curr.getText());
			Log.info("Selected Currency: " + curr.getText());
			Action.clickUsingJavaScript(driver, curr);
			Log.info("Clicked On Currency: " + curr.getText());
			Thread.sleep(5000);
			index.clickOnCurrencyButton();
			Log.info("Clicked on currency button to select another currency");

		}

		Log.endTestCase("verifyThatCurrencyButtonIsClickableUnderHamburgerMenuAndUserIsAbleToSelectTheCurrencyTest");
	}

	@Test
	public void verifyThatTopSectionsDownloadAppWillOpenInNewTab() throws InterruptedException {
		// this test includes all 4 links of app which are present on website -- 2 at
		// the bottom page
		Log.startTestCase("verifyThatTopSectionsDownloadAppWillOpenInNewTabTest");
		List<String> tabs = index.openTopDownloadLinkInNewTab();
		Log.info("Clicked on both the links android and ios. opened the link in new tab");
		// System.out.println(tabs);
		// Thread.sleep(4000);
		List<String> expectedTitles = List.of("IXFI - Buy, Sell, Trade Bitcoin, Ethereum & Altcoins | Crypto Exchange",
				"IXFI - Apps on Google Play", "IXFI on the App Store");
		Log.info("Fetched expected Titles from the list");
		Log.info("Verifying the each page title with the expected title one by one");
		// Switch to each tab and verify its title
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("switched to tab " + i);
			Thread.sleep(2000);
			String actualTitle = driver.getTitle(); // Get the tab's title
			// System.out.println(actualTitle);
			// Assert the title matches the expected value
			Log.info("Verifying page title: " + actualTitle);
			Assert.assertEquals(actualTitle, expectedTitles.get(i), "Page title mismatch at tab index " + i + "!");
			Log.info("Verified " + i + " opened link");
		}
		Log.info("Verified the top android and IOS  app download page title ");
		Log.endTestCase("verifyThatTopSectionsDownloadAppWillOpenInNewTabTest");
	}

	@Test
	public void verifyThatBottomSectionsDownloadAppWillOpenInNewTab() throws InterruptedException {
		// this test includes all 2 links of app (Android and IOS)which are present on
		// website -- 2 at the bottom of the page
		Log.startTestCase("verifyThatBottomSectionsDownloadAppWillOpenInNewTabTest");
		List<String> tabs = index.openBottomDownloadLinkInNewTab();
		Log.info("Clicked on both the links android and ios. opened the link in new tab");
		// System.out.println(tabs);
		// Thread.sleep(4000);
		List<String> expectedTitles = List.of("IXFI - Buy, Sell, Trade Bitcoin, Ethereum & Altcoins | Crypto Exchange",
				"IXFI - Apps on Google Play", "IXFI on the App Store");
		Log.info("Fetched expected Titles from the list");
		// Switch to each tab and verify its title
		Log.info("Verifying the each page title with the expected title one by one");

		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("switched to tab" + i);
			Thread.sleep(2000);
			String actualTitle = driver.getTitle(); // Get the tab's title
			Log.info("Verifying page title: " + actualTitle);
			// System.out.println(actualTitle);
			// Assert the title matches the expected value
			Assert.assertEquals(actualTitle, expectedTitles.get(i), "Page title mismatch at tab index " + i + "!");
			Log.info("Verified " + i + " opened link");
		}
		Log.info("Verified the Bottom android and IOS App download link page title ");
		Log.endTestCase("verifyThatBottomSectionsDownloadAppWillOpenInNewTabTest");
	}

	@Test
	public void verifyUserIsAbleToClickOnRewardsCenterCardAndAbleToNavigateToRewardsPage() {
		Log.startTestCase("verifyUserIsAbleToClickOnRewardsCenterCardAndAbleToNavigateToRewardsPageTest");
		rewardsProgramPage = index.validateUserIsNavigateToRewardsPage();
		Log.info("Clicked on rewads center cards");
		String actualPageTitle = rewardsProgramPage.getRewardsPageTitle();
		// System.out.println(actualPageTitle);
		Log.info("Navigated to rewards program page and captured page title: "+actualPageTitle);
		String expectedPageTitle = prop.getProperty("rewardsPageTitle");
		Log.info("retrived the expected titles from properties file: "+expectedPageTitle);
		Assert.assertEquals(actualPageTitle, expectedPageTitle,
				"Titles are not matched please check the titles properly");
		Log.info("verified the page titles, both the titles are matched!");
		Log.endTestCase("verifyUserIsAbleToClickOnRewardsCenterCardAndAbleToNavigateToRewardsPageTest");
	}

	@Test
	public void verifyUserIsAbleToClickOnAirdropsCardAndAbleToNavigateToAirdropsPage() {
		Log.startTestCase("verifyUserIsAbleToClickOnAirdropsCardAndAbleToNavigateToAirdropsPageTest");
		airdrops = index.validateUserIsNavigateToAirdropsPage();
		Log.info("Clicked on Airdrops cards");
		String actualPageTitle = airdrops.getAirdropsPageTitle();
		// System.out.println(actualPageTitle);
		Log.info("Navigated to Airdrops page and captured page title: "+actualPageTitle);
		String expectedPageTitle = prop.getProperty("airdropsPageTitle");
		Log.info("retrived the expected titles from properties file: "+expectedPageTitle);
		Assert.assertEquals(actualPageTitle, expectedPageTitle,
				"Titles are not matched please check the titles properly");
		Log.info("verified the page titles, both the titles are matched!");
		Log.endTestCase("verifyUserIsAbleToClickOnAirdropsCardAndAbleToNavigateToAirdropsPageTest");
	}

	@Test
	public void verifyUserIsAbleToClickOnReferralProgramCardAndAbleToNavigateToReferralProgamPage()
			throws InterruptedException {
		Log.startTestCase("verifyUserIsAbleToClickOnReferralProgramCardAndAbleToNavigateToReferralProgramPageTest");
		referral = index.validateUserIsNavigateToRefferalProgramPage();
		Log.info("Clicked on ReferralProgram cards");
		String actualPageTitle = referral.getReferralProgramPageTitle();
		// System.out.println(actualPageTitle);
		Log.info("Navigated to ReferralProgram page and captured page title: "+actualPageTitle);
		String expectedPageTitle = prop.getProperty("referralProgramPageTitle");
		Log.info("retrived the expected titles from properties file: "+expectedPageTitle);
		Assert.assertEquals(actualPageTitle, expectedPageTitle,
				"Titles are not matched please check the titles properly");
		Log.info("verified the page titles, both the titles are matched!");
		Log.endTestCase("verifyUserIsAbleToClickOnReferralProgramCardAndAbleToNavigateToReferralProgramPageTest");
	}

	@Test
	public void verifyUserIsAbleToClickOnConvertFeeCardAndAbleToNavigateToConvertPage() throws InterruptedException {
		Log.startTestCase("verifyUserIsAbleToClickOnConvertFeeCardAndAbleToNavigateToConvertPageTest");
		convert = index.validateUserIsNavigateToConvertPage();
		Log.info("Clicked on 0 %conversion fee  cards");
		String actualPageTitle = convert.getConvertPageTitle();
		// System.out.println(actualPageTitle);
		Log.info("Navigated to Convert page and captured page title: "+actualPageTitle);
		String expectedPageTitle = prop.getProperty("convertPageTitle");
		Log.info("retrived the expected titles from properties file: "+expectedPageTitle);
		Assert.assertEquals(actualPageTitle, expectedPageTitle,
				"Titles are not matched please check the titles properly");
		Log.info("verified the page titles, both the titles are matched!");
		Log.endTestCase("verifyUserIsAbleToClickOnConvertFeeCardAndAbleToNavigateToConvertPageTest");
	}

	@Test
	public void verifyThatWeeklySummaryReportSectionIsPresentAndUserIsAbleToClickOnReadMoreButtonAndOpenTheSummaryReportOnNewTab()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatWeeklySummaryReportSectionIsPresentAndUserIsAbleToClickOnReadMoreButtonAndOpenTheSummaryReportOnNewTabTest");
		List<String> tabs = index.validateWeeklySummarySection();
		Log.info("Total Opened Tab: " + tabs.size());
		for (int i = 0; i < tabs.size(); i++) {
			Action.switchToTab(i); // Switch to the tab by index
			Log.info("switched to tab " + i);
			Thread.sleep(2000);
			String actualTitle = driver.getTitle();
			Log.info("Title name of Tab: " + i + " is " + actualTitle);
			Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");

		}
		Log.info("Opened all the Weekly summary announcement in new tab and verified their title is not empty");
		Log.endTestCase(
				"verifyThatWeeklySummaryReportSectionIsPresentAndUserIsAbleToClickOnReadMoreButtonAndOpenTheSummaryReportOnNewTabTest");
	}

	// Testcases for verifying market trend data
	@Test
	public void validateMarketTrendTopTableDataTest() throws InterruptedException {
		Log.startTestCase("validateMarketTrendTopTableDataTest");
		Log.info("Scrolled to the MarketTrend Section");

		// Retrieve the full table data (both headers and rows)
		Map<String, List<List<String>>> tableData = index.validateMarketTrendTopTableData();
		System.out.println(tableData);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		Assert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			Assert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}

		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		Assert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		for (int i = 0; i < bodyRows.size(); i++) {
			List<String> row = bodyRows.get(i);
			Log.info("Row " + (i + 1) + ": " + row);
			Assert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");
		}

		Log.info("Successfully validated both headers and body rows.");
		Log.endTestCase("validateMarketTrendTopTableDataTest");
	}

	@Test
	public void validateMarketTrendGainersTableDataTest() throws InterruptedException {
		Log.startTestCase("validateMarketTrendGainersTableDataTest");
		Log.info("Scrolled to the MarketTrend Section");

		// Retrieve the full table data (both headers and rows)
		Map<String, List<List<String>>> tableData = index.validateMarketTrendGainerTableData();
		System.out.println(tableData);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		Assert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			Assert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}

		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		Assert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		for (int i = 0; i < bodyRows.size(); i++) {
			List<String> row = bodyRows.get(i);
			Log.info("Row " + (i + 1) + ": " + row);
			Assert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

			// Validate the 24h change value (assuming it's at index 2)
			String changeValue = row.get(2).trim(); // Get the 24h CHG value
			Log.info("Checking if the value is positive" + changeValue);
			Assert.assertTrue(changeValue.startsWith("+"),
					"Row " + (i + 1) + ": 24h CHG value should be positive. Found: " + changeValue);

		}

		Log.info("Successfully validated both headers and body rows. including Positive values in 24h CHG.");
		Log.endTestCase("validateMarketTrendGainerTableDataTest");
	}

	@Test
	public void validateMarketTrendLosersTableDataTest() throws InterruptedException {
		Log.startTestCase("validateMarketTrendLosersTableDataTest");
		Log.info("Scrolled to the MarketTrend Section");

		// Retrieve the full table data (both headers and rows)
		Map<String, List<List<String>>> tableData = index.validateMarketTrendLosersTableData();
		Log.info("Loosers Table Data: " + tableData);

		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		Assert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			Assert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}

		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		Assert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");

		for (int i = 0; i < bodyRows.size(); i++) {
			List<String> row = bodyRows.get(i);
			Log.info("Row " + (i + 1) + ": " + row);
			Assert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

			// Validate the 24h change value (assuming it's at index 2)
			String changeValue = row.get(2).trim(); // Get the 24h CHG value
			Log.info("Checking if the value is Negative" + changeValue);
			Assert.assertTrue(changeValue.startsWith("-"),
					"Row " + (i + 1) + ": 24h CHG value should be negative. Found: " + changeValue);
		}

		Log.info("Successfully validated both headers and body rows, including negative values in 24h CHG.");
		Log.endTestCase("validateMarketTrendLosersTableDataTest");
	}

	@Test
	public void verifyDetailsButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTab() {
		Log.startTestCase("verifyDetailsButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTabTest");

		try {
			index.validateDetailsButtonIsClickable();
			Log.info("Scrolled to the MarketTrend Section and opened all the details button in new tabs");
			Thread.sleep(4000);
			List<String> tabs = Action.getAllOpenTabs();
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

			Log.info("Verified all the details of coins are opened in new tabs.");
		} catch (Exception e) {
			Log.info("An error occurred: " + e);
			Assert.fail("Test failed due to an exception.");
		} finally {
			Log.endTestCase("verifyDetailsButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTabTest");
		}
	}

	@Test
	public void verifyTradeButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTab() {

		Log.startTestCase("verifyTradeButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTabTest");

		try {
			// Validate and retrieve all open tabs after clicking trade buttons
			index.validateTradeButtonIsClickable();
			Log.info("Scrolled to the MarketTrend Section and opened all the trade buttons in the new tabs");
			Thread.sleep(4000);
			List<String> tabs = Action.getAllOpenTabs();
			Log.info("Total Opened Tabs: " + tabs.size());

			// Iterate through all open tabs and verify titles
			for (int i = 0; i < tabs.size(); i++) {
				Action.switchToTab(i); // Switch to the tab by index
				Log.info("Switched to tab " + i);

				// Wait until the title is available
				String actualTitle = Action.waitForTitle(driver, 30); // Custom wait with timeout
				Log.info("Title of Tab " + i + ": " + actualTitle);
				Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
			}

			Log.info("Verified all the trade buttons are opened in new tabs.");
		} catch (Exception e) {
			Log.error("An error occurred: " + e);
			Assert.fail("Test failed due to an exception.");
			//Log.info(e.printStackTrace());
		} finally {
			Log.endTestCase("verifyTradeButtonFromTheMarketTablesActionColumnIsClickableAndOpenItInNewTabTest");
		}
	}

	@Test
	public void verifyThatStartTradingNowButtonIsClickableAndUserIsNavigatedToTradePage() throws InterruptedException {
		Log.startTestCase("verifyThatStartTradingNowButtonIsClickableAndUserIsNavigatedToTradePageTest");
		trade = index.validateStartTradingNowButtonIsClickable();
		// Thread.sleep(5000);
		String tradePageActualTitle = trade.getTradePageTitle();
		String[] parts = tradePageActualTitle.split("\\|", 2); // Split at the first occurrence of "|"
		String actualTradePageText = parts[1].trim();
		Log.info("Trade Page Actual Title: " + actualTradePageText);
		String tradePageExpectedTitle = "BTCUSDT Trading and Charts | IXFI Spot Trading";
		Log.info("TradePage Expected Title: " + tradePageExpectedTitle);
		Assert.assertEquals(actualTradePageText, tradePageExpectedTitle,
				"TradePage Actual and expected titles are not matched,Please verify Again");
		Log.info("Both the titles are verified. Both are equals");
		Log.endTestCase("verifyThatStartTradingNowButtonIsClickableAndUserIsNavigatedToTradePageTest");
	}

	// Use our Rewards Program to win up to $10,000 -- testcases
	@Test
	public void verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheTitleOfTheSection() {
		Log.startTestCase(
				"verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheTitleOfTheSectionTest");
		Log.info("Scrolled upto the earnPlatform  - Use our Rewards Program to win up to $10,000 section ");
		String earnPlatformSectionActualtitle = index.validateThePresenceOfRewardsProgramSection();
		Log.info("Extracted the actual Title of section: Title is " + earnPlatformSectionActualtitle);
		String earnPlatformSectionExpectedTitle = prop.getProperty("earnPlatformSectionTitle");
		Log.info("The Expected Title is: " + earnPlatformSectionExpectedTitle);
		Log.info("Verifying both the title is similar or not");
		Assert.assertEquals(earnPlatformSectionActualtitle, earnPlatformSectionExpectedTitle,
				"The titles are not matched, please check again");
		Log.info("Verified the title, Both are matched ");
		Log.endTestCase(
				"verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheTitleOfTheSectionTest");
	}

	@Test
	public void verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheParagraphOfTheSection() {
		Log.startTestCase(
				"verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheParagraphOfTheSectionTest");
		Log.info("Scrolled  upto the earnPlatform  - Use our Rewards Program to win up to $10,000 section ");
		String earnPlatformSectionActualParagraph = index.validateThePresenceOfRewardsProgramParagraphSections();
		Log.info("Extracted the actual Paragraph of section: Title is " + earnPlatformSectionActualParagraph);
		String earnPlatformSectionExpectedParagraph = prop.getProperty("earnPlatformSectionParagraph");
		Log.info("The Expected Paragraph is: " + earnPlatformSectionExpectedParagraph);
		Log.info("Verifying both the Paragraph is similar or not");
		Assert.assertEquals(Action.standardizeText(earnPlatformSectionExpectedParagraph),
				Action.standardizeText(earnPlatformSectionExpectedParagraph),
				"The Paragraph are not matched, please check again");
		Log.info("Verified the Paragraph, Both are matched ");
		Log.endTestCase(
				"verifyThatUserIsAbleToNavigateToTheEarnPlatformRewardsProgramSectionAndValidateTheParagraphOfTheSectionTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnJoinIXFISectionAndAbleToNavigateToTheSignUpPage()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnJoinIXFISectionAndAbleToNavigateToTheSignUpPageTest");
		Log.info("Scrolled upto the JoinIXFI Section");
		signUp = index.validateThatJoinIXFISectionWillNavigateUserToSignUpPage();
		Log.info("Clicked On JoinIXFI Section And Navigated to the SignUpPage");
		String signUpPageActualTitle = signUp.getSignUpPageTitle();
		Log.info("Retrieved the Actual signUpPage: " + signUpPageActualTitle);
		String signUpPageExpectedTitle = "Register | IXFI";
		Log.info("Expected SignUpPage Title: " + signUpPageExpectedTitle);
		Assert.assertEquals(Action.standardizeText(signUpPageActualTitle),
				Action.standardizeText(signUpPageExpectedTitle), "Titles are not matched, please check again");
		Log.info("Both the titles are matched");
		Log.endTestCase("verifyThatUserIsAbleToClickOnJoinIXFISectionAndAbleToNavigateToTheSignUpPageTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnCompleteTaskSectionAndAbleToNavigateToTheRewardsProgramTaskCenterPage()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToClickOnCompleteTaskSectionAndAbleToNavigateToTheRewardsProgramTaskCenterPageTest");
		Log.info("Scrolled upto the Complete Task Section");
		taskCenter = index.validateThatCompleteTaskSectionWillNavigateUserToRewardsPragramsTaskCenterPage();
		Log.info("Clicked On Complete Task Section And Navigated to the TaskCenter Page");
		Thread.sleep(2000);
		String taskCenterPageActualTitle = taskCenter.getTaskCenterPageTitle();
		Log.info("Retrieved the Actual TaskCenter Page Title: " + taskCenterPageActualTitle);
		String taskCenterPageExpectedTitle = "Complete Tasks & Earn Free Crypto | Rewards | ixfi";
		Log.info("Expected taskCenterPage Title: " + taskCenterPageExpectedTitle);
		Assert.assertEquals(Action.standardizeText(taskCenterPageActualTitle),
				Action.standardizeText(taskCenterPageExpectedTitle), "Titles are not matched, please check again");
		Log.info("Both the titles are matched");
		Log.endTestCase(
				"verifyThatUserIsAbleToClickOnCompleteTaskSectionAndAbleToNavigateToTheRewardsProgramTaskCenterPageTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnGetFreeCryptoSectionAndAbleToNavigateToTheRewardsProgramPage()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToClickOnGetFreeCryptoSectionAndAbleToNavigateToTheRewardsProgramPageTest");
		Log.info("Scrolled upto the GetFreeCrypto Section");
		rewardsProgramPage = index.validateThatGetFreeCryptoSectionWillNavigateUserToRewardsProgramPage();
		Log.info("Clicked On GetFreeCrypto Section And Navigated to the Rewards Program Page");
		String rewardsProgramPageActualTitle = rewardsProgramPage.getRewardsPageTitle();
		Log.info("Retrieved the Actual RewardsProgram Page Title: " + rewardsProgramPageActualTitle);
		String rewardsProgramPageExpectedTitle = "Earn Free Crypto | IXFI Rewards";
		Log.info("Expected RewardsProgramPage Title: " + rewardsProgramPageExpectedTitle);
		Assert.assertEquals(Action.standardizeText(rewardsProgramPageActualTitle),
				Action.standardizeText(rewardsProgramPageExpectedTitle), "Titles are not matched, please check again");
		Log.info("Both the titles are matched");
		Log.endTestCase(
				"verifyThatUserIsAbleToClickOnGetFreeCryptoSectionAndAbleToNavigateToTheRewardsProgramPageTest");
	}
	
	//----------------------------------IXFI Token Testcases ----------------------------------------------
	
	@Test
	public void verifyThatTheIXFITokenCardSectionIsVisible() throws InterruptedException
	{
		Log.startTestCase("verifyThatTheIXFITokenCardSectionIsVisibleTest");
		index.navigateToTheIXFITokenCard();
		Thread.sleep(2000);
		Log.info("Scrolled to the IXFI Token Card ");
		Log.info("Checking if the ixfi token card is displayed or not");
		try
		{
			boolean status=index.checkIfIXFITokenBannerIsDisplayed();
			Log.info("Is banner Displayed: "+status);
			softAssert.assertTrue(status,"IXFI token Banner card is not displyed, please check ");
			
		}
		catch(Exception e)
		{
			Log.info("Exception is: "+e);
		}
		Log.info("Checking if the IXFI logo displayed in IXFI Token banner");
		boolean logoStatus=index.checkIfIXFITokenLogoIsDisplayed();
		softAssert.assertTrue(logoStatus,"IXFI Logo is not displayed in the IXFI Token banner section");
		
		Log.info("Checking if the text present in IXFI banner section is correct or not");
		String ixfiTokenTitle=index.getTheIXFITokenTitleText();
		Log.info("IXFI Token title text: "+ixfiTokenTitle);
		softAssert.assertEquals(ixfiTokenTitle, "IXFI Token:","Please check again, the text IXFI Token: is not matched in banner");
		String ixfiTokenTitleParaText=index.getTheIXFITokenTitleTextPara();
		Log.info("Title Paragraph: "+ixfiTokenTitleParaText);
		softAssert.assertEquals(ixfiTokenTitleParaText, "IXFI Token: Driving the next phase of IXFI’s evolution","Please check the IXFI Token title text");
		String tokenInfo=index.getTheTextOfTokenInfo();
		Log.info("IXFI Token Info: "+tokenInfo);
		softAssert.assertEquals(tokenInfo, "Unlock cashback, staking, and trading perks with IXFI","Token information does not matched, please check again");
		softAssert.assertAll();
		Log.endTestCase("verifyThatTheIXFITokenCardSectionIsVisibleTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickIXFIBannerLinkAndOpenItInNewTab() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickIXFIBannerLinkAndOpenItInNewTabTest");
		index.navigateToTheIXFITokenCard();
		Thread.sleep(2000);
		Log.info("Scrolled to the IXFI Token Card ");
		token=index.clickOnIXFITokenLink();
		Log.info("Clicked on IXFI Token link and the link is opened in new tab");
		Action.switchToNewWindow(driver);
		Log.info("Driver switched to IXFI Token page");
		Thread.sleep(2000);
		String actualTitle=driver.getTitle();
		Log.info("Actual IXFI Token Page Title: "+actualTitle);
		String expectedTitle=prop.getProperty("ixfiTokenPageTitle");
		Log.info("Expected IXFI Token Page Title: "+expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle,"Titles are not matched, please verify again");
		Log.info("Titles are verified");
//		List<String> tabs=Action.getAllOpenTabs();
//		for(String tab:tabs)
//		{
//			Log.info("Window tab: "+tab);
//			Log.info(driver.getTitle());
//			Thread.sleep(2000);
//			Action.switchToWindowByTitle(driver, tab);
//			
//		}
		
		Log.endTestCase("verifyThatUserIsAbleToClickIXFIBannerLinkAndOpenItInNewTabTest");
	}
	

}
