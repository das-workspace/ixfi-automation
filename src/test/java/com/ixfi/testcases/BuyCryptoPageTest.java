package com.ixfi.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.BuyCryptoPage;
import com.ixfi.pageobjects.GmailPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.utility.Log;

public class BuyCryptoPageTest extends BaseClass {

	IndexPage index;
	GmailPage gmail;
	LoginPage login;
	BuyCryptoPage buy;
	SoftAssert softAssert;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		gmail = new GmailPage(driver);
		login=new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}

	@Test
	public void verifyUserIsAbleToNavigateToTheBuyCryptoPage() throws InterruptedException {
		Log.startTestCase("verifyUserIsAbleToNavigateToTheBuyCryptoPageTest");
		Log.info("Opened the IXFI Web Index Page");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on Buy Crypto Menu from header menu and Navigated to the buy crypto page");
		Thread.sleep(4000);
		// buy.waitForVisibilityOfBannerTitle();
		String actualTitle = buy.getBuyCryptoPageTitle();
		Log.info("Retrieved the actual buy crypto page title: " + actualTitle);
		String expectedTitle = prop.getProperty("buyCryptoPageTitle");
		Log.info("Expected title is: " + expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Titles are not matched please check again");
		Log.endTestCase("verifyUserIsAbleToNavigateToTheBuyCryptoPageTest");
	}

	@Test
	public void verifyDefualtValuesForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTime()
			throws InterruptedException {
		Log.startTestCase("verifyDefualtValuesForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTimeTest");
		Log.info("User is Landed On Index page");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked On Buy Cyrpto from header menu and navigated to the Buy Crypto page");
		Thread.sleep(4000);
		Log.info("Validating the defualt country is India or not by verifying presence of indian country flag image");
		softAssert.assertTrue(buy.validateIndianImageInCountryDD(), "India Country Image is not visible in country DD");
		Log.info("Verified presence of defualt country as India");

		String defaultCryptoForIndia = buy.getDefualtUSDTCrypto();
		Log.info("Retrieved the defualt crypto for india: " + defaultCryptoForIndia);
		softAssert.assertEquals(defaultCryptoForIndia, "USDT",
				"Defualt crypto are not matched for Country: India , please check again");
		String defaultCurrency = buy.getDefaultCurrencyINR();
		Log.info("Retrieved the default currency for india: " + defaultCurrency);
		softAssert.assertEquals(defaultCurrency, "INR",
				"Default currencies are not matched for Country: India, Please check again");
		
		String bannerTitle=buy.getBannerTitleForSelectedCountry();
		Log.info("Banner title for India: "+bannerTitle);
		softAssert.assertEquals(bannerTitle, "Buy TetherUS (USDT) with INR in India from ixfi, The World’s Largest Buy Crypto Platform","Banner title not matched for india, please check");
		// String actualYouPay=buy.getDefualtFiateValue();
		// Log.info("Default Fiat Value for India: "+actualYouPay);
		// Assert.assertEquals(actualYouPay, "5000","Default fiat values are not matched
		// for Country: India, Please check again");
		softAssert.assertAll();
		Log.endTestCase("verifyDefualtValuesForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTimeTest");
	}

	@Test
	public void verifyAvailableOffersForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTime()
			throws InterruptedException {
		Log.startTestCase("verifyAvailableOffersForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTimeTest");
		Log.info("User is Landed On Index page");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked On Buy Cyrpto from header menu and navigated to the Buy Crypto page");
		Thread.sleep(4000);
		Log.info("Validating the defualt country is India or not by verifying presence of indian country flag image");
		Assert.assertTrue(buy.validateIndianImageInCountryDD(), "India Country Image is not visible in country DD");
		Log.info("Verified presence of defualt country as India");

		String defaultCryptoForIndia = buy.getDefualtUSDTCrypto();
		Log.info("Retrieved the defualt crypto for india: " + defaultCryptoForIndia);
		Assert.assertEquals(defaultCryptoForIndia, "USDT",
				"Defualt crypto are not matched for Country: India , please check again");
		String defaultCurrency = buy.getDefaultCurrencyINR();
		Log.info("Retrieved the default currency for india: " + defaultCurrency);
		Assert.assertEquals(defaultCurrency, "INR",
				"Default currencies are not matched for Country: India, Please check again");
		// String actualYouPay=buy.getDefualtFiateValue();
		// Log.info("Default Fiat Value for India: "+actualYouPay);
		// Assert.assertEquals(actualYouPay, "5000","Default fiat values are not matched
		// for Country: India, Please check again");
//		Thread.sleep(3000);
//		List<String> providers = buy.getAvailableOffersOfProviderForPerticularCountry();
//		for (String provider : providers) {
//			Log.info("Provider: "+provider);
//		}
//		Thread.sleep(2000);
//		List<String> prices = buy.getVendorPrice();
//		for (String price : prices) {
//			Log.info("Vendor price: "+price);
//		}

		Thread.sleep(3000);
		buy.waitForVisibilityOfAvailableOffers();
		Map<String, String> providerPriceMap = buy.mapVendorWithPrice();
		Log.info("Retrieved the Available offers from multiple vendors along with its prices");
		for (Map.Entry<String, String> entry : providerPriceMap.entrySet()) {
			Log.info("Vendor price list for India is " + entry.getKey() + " : " + entry.getValue());
		}

		Log.endTestCase("verifyAvailableOffersForIndiaWhenUserLandsOnTheBuyCryptoPageForTheFirstTimeTest");
	}

	@Test
	public void verifyThatUserIsAbleToGetVendorAndItsPriceListForSelectedCountries() throws InterruptedException {
		Log.startTestCase("getVendorAndItsPriceListTest");
		Log.info("User is Landed On Index page");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked On Buy Cyrpto from header menu and navigated to the Buy Crypto page");
		Thread.sleep(4000);
		Map<String, String> providerPriceMap = buy.mapVendorWithPrice();
		Log.info("Size of vendors:" + providerPriceMap.size());
		for (Map.Entry<String, String> entry : providerPriceMap.entrySet()) {
			Log.info(entry.getKey() + " : " + entry.getValue());
		}

		Log.endTestCase("getVendorAndItsPriceListTest");
	}

	@Test
	public void verifyTheListOfAvailableCountriesAndRetrieveTheFullName() throws InterruptedException {
		Log.startTestCase("verifyTheListOfAvailableCountriesAndRetrieveTheFullNameTest");
		buy = index.clickOnBuyCryptoMenu();
		buy.clickOnCountryDD();
		Set<String> countryDD = buy.getListOfFullCountryNameFromDD();
		Log.info("Size: " + countryDD.size());
		// Log.info(" "+countryDD);
		for (String country : countryDD) {
			// Action.scrollByVisibilityOfElement(country);
			// buy.scrollOnlyBuyCryptoSection();
			Thread.sleep(1000);
			// String name=country.getText();
			Log.info("Country: " + country);
		}
		Log.endTestCase("verifyTheListOfAvailableCountriesAndRetrieveTheFullNameTest");
	}

	@Test
	public void verifyTheListOfAvailableCountriesAndRetrieveTheShortName() throws InterruptedException {
		Log.startTestCase("verifyTheListOfAvailableCountriesAndRetrieveTheShortNameTest");
		buy = index.clickOnBuyCryptoMenu();
		buy.clickOnCountryDD();
		Set<String> countryDD = buy.getListOfShortCountryNameFromDD();
		Log.info("Size: " + countryDD.size());
		// Log.info(" "+countryDD);
		for (String country : countryDD) {
			// Action.scrollByVisibilityOfElement(country);
			// buy.scrollOnlyBuyCryptoSection();
			Thread.sleep(1000);
			// String name=country.getText();
			Log.info("Country: " + country);
		}
		Log.endTestCase("verifyTheListOfAvailableCountriesAndRetrieveTheShortNameTest");
	}

	@Test
	public void verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForRandomlySelectedCountry()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForRandomlySelectedCountryTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(1000);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(1000);
		Log.info("Clicked on Cancle button on supported region popup");

		// Convert the Set to a List for easy random selection
		List<String> countryList = new ArrayList<>(countries);

		// Shuffle the list to randomize the order
		Collections.shuffle(countryList);

		// Select the first 25 countries (if available)
		int countriesToProcess = Math.min(25, countryList.size()); // Ensure we don't go beyond the size of the list
		for (int i = 0; i < countriesToProcess; i++) {
			String country = countryList.get(i);

			if (country == null || country.trim().isEmpty()) {
				Log.warn("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			Thread.sleep(1000);
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(2000);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country + " ");
			Log.info("Selected country: " + country);

			String defaultCrypto = buy.getDefaultCrypto();
			Log.info("Default Crypto for " + country + " is " + defaultCrypto);
			Thread.sleep(2000);
			String defaultCurrency = buy.getDefaultCurrency();
			Log.info("Default Currency for " + country + " is " + defaultCurrency);
			Thread.sleep(1000);
		}

		Log.endTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForRandomlySelectedCountryTest");
	}

	// @Test
	// above method is similer to this method. I have just included random 25
	// countries in above test method to reduce the execution time
	public void verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForSelectedCountry()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForSelectedCountryTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(1000);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(1000);
		Log.info("Clicked on Cancel button on supported region popup");

		// Convert the Set to a List
		List<String> countryList = new ArrayList<>(countries);

		// Shuffle the list to get a random order
		Collections.shuffle(countryList);

		// Loop over the first 25 countries (or the total if less than 25)
		for (int i = 0; i < Math.min(25, countryList.size()); i++) {
		    String country = countryList.get(i);
		    Log.info("Count: "+i);
		    if (country == null || country.trim().isEmpty()) {
		        Log.warn("Country is null or empty, skipping.");
		        continue; // Skip to the next country in the loop
		    }

		    Log.info("Processing country: " + country);
		    buy.clickOnCountryDD();
		    Thread.sleep(1000);
		    Log.info("Clicked on Country Dropdown ");
		    buy.enterTheCountryInSearchField(country);
		    Log.info("Entered " + country + " in the search field");
		    Thread.sleep(2000);
		    buy.clickOnCountryNameAfterSearch(country);
		    Log.info("Clicked on the " + country + " ");
		    // Action.selectByVisibleText(country, country.getText());
		    Log.info("Selected country: " + country);
		    String defaultCrypto = buy.getDefaultCrypto();
		    Log.info("Default Crypto for " + country + " is " + defaultCrypto);
		    Thread.sleep(2000);
		    String defaultCurrency = buy.getDefaultCurrency();
		    Log.info("Default Currency for " + country + " is " + defaultCurrency);
		    Thread.sleep(1000);
		}
		Log.endTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyDefualtCryptoAndCurrencyForSelectedCountryTest");
	}

	@Test
	public void verifyThatUserIsAbleToFetchTheAvailableOffers() throws InterruptedException {

		Log.startTestCase("verifyThatUserIsAbleToFetchTheAvailableOffersTest");

		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(1000);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(1000);
		Log.info("Clicked on Cancle button on supported region popup");
		
		List<String> countryList = new ArrayList<>(countries);

		// Shuffle the list to get a random order
		Collections.shuffle(countryList);

		// Loop over the first 25 countries (or the total if less than 25)
		for (int i = 0; i < Math.min(25, countryList.size()); i++) {
		    String country = countryList.get(i);
		    Log.info("Count: "+i);
		

			if (country == null || country.trim().isEmpty()) {
				Log.warn("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			Thread.sleep(500);
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(500);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country + " ");
			// Action.selectByVisibleText(country, country.getText());
			Log.info("Selected country: " + country);
			// Thread.sleep(5000);
			buy.waitForVisibilityOfAvailableOffers();
			List<String> availOffer = buy.getAvailableOffersOfProviderForPerticularCountry();
			Log.info("Available Offers for: " + country);
			for (String avail : availOffer) {
				Log.info(avail);
				softAssert.assertNotNull(avail);
			}
			List<String> prices = buy.getVendorPrice();
			Log.info("Price for: " + country);
			for (String price : prices) {
				Log.info(price);
				softAssert.assertNotNull(price);
			}

		}
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToFetchTheAvailableOffersTest");
	}

	@Test
	public void verifyThatUserIsAbleToFetchTheAvailableOffersAndThePricesOfVendorsForParticularCountry()
			throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToFetchTheAvailableOffersAndThePricesOfVendorsForParticularCountryTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(2000);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(2000);
		Log.info("Clicked on Cancle button on supported region popup");

		// Convert the Set to a List for easy random selection
		List<String> countryList = new ArrayList<>(countries);

		// Shuffle the list to randomize the order
		Collections.shuffle(countryList);

		// Select the first 25 countries (if available)
		int countriesToProcess = Math.min(25, countryList.size()); // Ensure we don't go beyond the size of the list
		for (int i = 0; i < countriesToProcess; i++) {
			String country = countryList.get(i);
			Log.info("Selected " + country + " :" + i);
			if (country == null || country.trim().isEmpty()) {
				Log.info("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			Thread.sleep(3000);
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(3000);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country + " ");
			Log.info("Selected country: " + country);

			// Optionally, you can fetch the default crypto and currency here if needed
//	        String defaultCrypto = buy.getDefaultCrypto();
//	        Log.info("Default Crypto for " + country + " is " + defaultCrypto);
//	        Thread.sleep(2000);
//	        String defaultCurrency = buy.getDefaultCurrency();
//	        Log.info("Default Currency for " + country + " is " + defaultCurrency);

			Thread.sleep(3000);
			buy.waitForVisibilityOfAvailableOffers();
			Map<String, String> providerPriceMap = buy.mapVendorWithPrice();
			Log.info("Retrieved the Available offers from multiple vendors along with its prices");

			for (Map.Entry<String, String> entry : providerPriceMap.entrySet()) {
				Log.info("Vendor price list for: " + country + " is " + entry.getKey() + " : " + entry.getValue());
			}
		}
		Log.endTestCase("verifyThatUserIsAbleToFetchTheAvailableOffersAndThePricesOfVendorsForParticularCountryTest");
	}

	@Test
	public void verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyTheBannerTitleForSelectedCountry()
			throws InterruptedException {
		Log.startTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyTheBannerTitleForSelectedCountryTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(500);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(500);
		Log.info("Clicked on Cancel button on supported region popup");

		// Convert the Set to a List for easy random selection
		List<String> countryList = new ArrayList<>(countries);

		// Shuffle the list to randomize the order
		Collections.shuffle(countryList);

		// Select the first 25 countries (if available)
		int countriesToProcess = Math.min(25, countryList.size()); // Ensure we don't go beyond the size of the list
		for (int i = 0; i < countriesToProcess; i++) {
			String country = countryList.get(i);
			Log.info("Selected " + country + " :" + i);
			if (country == null || country.trim().isEmpty()) {
				Log.info("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			// Thread.sleep(500); // You can adjust/remove this if you want to reduce the
			// wait time
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(500);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country + " ");
			// Action.selectByVisibleText(country, country.getText());
			// Log.info("Selected country: " + country);

			String defaultCrypto = buy.getDefaultCrypto();
			// Log.info("Default Crypto for " + country + " is " + defaultCrypto);
			Thread.sleep(500);
			String defaultCurrency = buy.getDefaultCurrency();
			// Log.info("Default Currency for " + country + " is " + defaultCurrency);
			Thread.sleep(500);

			String actualBannerTitle = buy.getBannerTitleForSelectedCountry();
			Log.info("Actual Banner Title for " + country + " " + actualBannerTitle);

			String expectedBannerTitle = "Buy TetherUS" + " (" + defaultCrypto + ") with " + defaultCurrency + " in "
					+ country + " from ixfi, The World’s Largest Buy Crypto Platform";
			Log.info("Expected Banner Title for " + country + " " + expectedBannerTitle);

			// Validate that the actual banner title matches the expected one
			softAssert.assertEquals(actualBannerTitle, expectedBannerTitle,
					"Banner titles are not matched, please check again");

			Thread.sleep(500); // Adjust as needed for appropriate delay between actions
		}

		// Report all failures if any
		softAssert.assertAll();

		Log.endTestCase(
				"verifyThatUserIsAbleToSelectTheCountriesFromDDAndAbleToVerifyTheBannerTitleForSelectedCountryTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnBuyNowButtonWithoutLoginUserWillBeNavigatedToLoginPage()
			throws InterruptedException {
		// Open buy now button in same page -- do this change -- after complete remove
		// this comment
		Log.startTestCase("verifyThatUserIsAbleToClickOnBuyNowButtonWithoutLoginUserWillBeNavigatedToLoginPageTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(500);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(500);
		Log.info("Clicked on Cancel button on supported region popup");

		// Convert Set to List to use random selection
		List<String> countryList = new ArrayList<>(countries);
		Log.info("CountryList Count: " + countryList.size());
		// Shuffle the list to randomize the order
		Collections.shuffle(countryList);

		// Limit the list to 25 countries (or fewer if there are less than 25)
		int countriesToTest = Math.min(15, countryList.size());
		Log.info("Countries randomaly to test: " + countriesToTest);
		int j = 0; // Limit to 25 countries
		for (int i = 0; i < countriesToTest; i++) {
			String country = countryList.get(i);

			if (country == null || country.trim().isEmpty()) {
				Log.info("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(2000);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country);
	//		Action.implicitWait(driver, 10);
			Thread.sleep(4000);
			
			List<WebElement> tabs = buy.clickOnBuyNowButtonAndOpenInNewTab();//this is not a method to open in new tab, but only finding the webelement and returing it
			// need to change here.. just click on buy now button and check it will be
			// navigated to the login page and come back to same page
			Log.info("Count of Buy now button: "+tabs.size());
			Thread.sleep(3000);
			for(WebElement ele:tabs)
			{
				
				Action.waitForElementToBeVisible(ele, 20);
				Thread.sleep(2000);
				Action.click(ele);
				Log.info("clicked on: "+ele.getText());
				Thread.sleep(2000);
				String actualTitle = driver.getTitle();
				Log.info("Title name of Tab is: " + actualTitle);
				Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
				Action.navigateBack();
				Log.info("Navigated back to buy crypto page");
				//verify page title here
				Thread.sleep(1000);
				buy=login.clickOnExitConfirmYesButtonAndNavigateBackToBuyCryptoPage();
				Thread.sleep(2000);
				Action.scrollByVisibilityOfElement(ele);
				break;
			}
			
			
//			Log.info("Total Opened Tab: " + tabs.size());
//			for (int k = 0; k < tabs.size(); k++) {
//				Action.switchToTab(k); // Switch to the tab by index
//				Log.info("switched to tab " + k);
//				Thread.sleep(2000);
//				String actualTitle = driver.getTitle();
//				Log.info("Title name of Tab: " + k + " is: " + actualTitle);
//				Assert.assertFalse(actualTitle.isEmpty(), "Title should not be empty");
//			}

			if (j == countriesToTest - 1) {
				break;
			}

			j++;
		}

		Log.endTestCase("verifyThatUserIsAbleToClickOnBuyNowButtonWithoutLoginUserWillBeNavigatedToLoginPageTest");
	}

	@Test
	public void verifyTheMoreSupportedCurrenciesForEveryCountryAndClickOnTheCountryFromPopupAndVerifyInDefaultCurrencyDD()
			throws InterruptedException {
		Log.startTestCase(
				"verifyTheMoreSupportedCurrenciesForEveryCountryAndClickOnTheCountryFromPopupAndVerifyInDefaultCurrencyDDTest");

		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");

		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());

		Thread.sleep(1000);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(1000);
		Log.info("Clicked on Cancel button on supported region popup");

		for (String country : countries) {
			if (country == null || country.trim().isEmpty()) {
				Log.info("Country is null or empty, skipping.");
				continue; // Skip to the next country in the loop
			}

			Log.info("Processing country: " + country);
			buy.clickOnCountryDD();
			Log.info("Clicked on Country Dropdown ");
			buy.enterTheCountryInSearchField(country);
			Log.info("Entered " + country + " in the search field");
			Thread.sleep(2000);
			buy.clickOnCountryNameAfterSearch(country);
			Log.info("Clicked on the " + country);

			Action.implicitWait(driver, 10);
			Thread.sleep(2000);

			String cryptoSubTitle = buy.getCryptoSubTitle();
			Log.info("Crypto subtitle for " + country + " is " + cryptoSubTitle);
			Thread.sleep(2000);

			// Re-fetch the currencies to avoid StaleElementReferenceException
			List<WebElement> currencies = buy.getMoreSupportedCurrenciesForParticularSelectedCountry();
			Log.info("Size of currencies: " + currencies.size());

			// If the currencies list is not empty
			if (currencies.size() != 0) {
				for (int i = 0; i < currencies.size(); i++) {
					WebElement curr = currencies.get(i); // Re-locate the element
					boolean moreTextPresent = false;

					// Check if "More" button exists in the currency list
					List<WebElement> updatedCurrencies = buy.getMoreSupportedCurrenciesForParticularSelectedCountry(); // Re-locate
					for (WebElement currency : updatedCurrencies) {
						if (currency.getText().contains("More")) {
							moreTextPresent = true;
							Log.info("Status: More Text Present - " + moreTextPresent);
							break; // No need to check further if "More" is found
						}
					}

					if (moreTextPresent) {
						Log.info("Currency: " + curr.getText());
						buy.clickOnMoreButton();
						Log.info("Clicked on more button");

						String cryptoSubTitleFromPopup = buy.getCryptoSubTitleFromPopup();
						Log.info("cryptoSubTitleFromPopup: " + cryptoSubTitleFromPopup);

						// Assert if the subtitle matches
						softAssert.assertEquals(cryptoSubTitle, cryptoSubTitleFromPopup,
								"Both the sub crypto titles are not matched, please check again");

						List<WebElement> currenciesFromPopup = buy
								.getMoreSupportedCurrenciesFromPopupForParticularSelectedCountry();

						for (WebElement currencyFromPopup : currenciesFromPopup) {
							String currencyName = currencyFromPopup.getText();
							buy.enterTheCurrencyInSearchFieldInMoreSupportedCurrencyPopup(currencyName);
							buy.clickOnCurrencyNameAfterSearch(currencyName);
							Thread.sleep(3000);
							String coin = buy.getDefaultCurrency();
							Log.info("Selected coin from select crypto DD: " + coin);
							Thread.sleep(3000);

							// Scroll to the More Supported Currencies section again to check for "More"
							// button
							buy.scrollToTheMoreSupportedCurrenciesSubtitle();
							Log.info("Scrolling to the more supported currencies section");

							// Re-fetch the currencies from the "More Supported Currencies" section
							updatedCurrencies = buy.getMoreSupportedCurrenciesForParticularSelectedCountry(); // Re-locate
																												// again
							Log.info("Currency fetched again and the size is: " + updatedCurrencies.size());

							// If "More" button is found, we continue the loop and click it again
							if (buy.isMoreButtonPresent()) {
								buy.scrollToTheMoreSupportedCurrenciesSubtitle();
								Log.info("Scrolled to the More Button");
								Thread.sleep(2000);
								buy.clickOnMoreButton(); // Check again the More button
								Log.info("Clicked On More button");
								List<WebElement> updatedCurrenciesAfterClick = buy
										.getMoreSupportedCurrenciesFromPopupForParticularSelectedCountry();
								Log.info("Updated currencies count: " + updatedCurrenciesAfterClick.size());
								currenciesFromPopup = updatedCurrenciesAfterClick; // Update the currencies list
								Thread.sleep(2000);
								continue;
							} else {
								Log.info("More button is not present");
								break; // No more "More" buttons, break out of the loop
							}
						}

					} else {
						// If "More" button is not present, just log the currency
						Log.info("Currency: " + curr.getText()); // No change here
					}
				}
			} else {
				Log.info("No more supported currencies for country " + country);
				continue;
			}
		}

		// Collect all the soft assertion failures (if any)
		softAssert.assertAll(); // This will report all failures if any

		Log.endTestCase(
				"verifyTheMoreSupportedCurrenciesForEveryCountryAndClickOnTheCountryFromPopupAndVerifyInDefaultCurrencyDDTest");
	}

	@Test
	public void verifyTheSupportedRegionsSectionAndValidateTheCountriesPresentInInThePopup()
			throws InterruptedException {
		Log.startTestCase("verifyTheSupportedRegionsSectionAndValidateTheCountriesPresentInInThePopup");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		buy.clickOnCountryDD();
		Log.info("Clicked on Country Dropdown ");
		Set<String> countries = buy.getCountriesWebElementFromDD();
		Log.info("Fetched all the supported countries");
		Log.info("Size of countries present: " + countries.size());
		Thread.sleep(500);
		buy.clickOnSupportedRegionCrossButton();
		Thread.sleep(500);
		Log.info("Clicked on Cancle button on supported region popup");
		buy.clickOnBuyCryptoAccrossDifferentRegion();
		Log.info("Clicked on Buy Crypto Accross different regions link ");
		String SupportedRegionTitle = buy.getSupportedRegionPopupHeader();
		Log.info("Supported Region popup title: " + SupportedRegionTitle);
		List<WebElement> supportedRegions = buy.getSupportedRegionListFromPopup();
		Log.info("Retrieved the supported regions from popup and the Count is: " + supportedRegions.size());
		int count = 0;
		for (WebElement region : supportedRegions) {
			Log.info("Count: " + count);
			if (count == 20) {
				break;
			}
			// Generate a random index to pick a random region from supportedRegions
			Random rand = new Random();
			int randomIndex = rand.nextInt(supportedRegions.size()); // Random index within the list size

			// Get the random region's name
			String name = supportedRegions.get(randomIndex).getText();

			Log.info("Region Name: " + name);
			buy.enterRegionInTheSearchBox(name);
			Log.info("Entered " + name + " in the search box");

			Thread.sleep(2000);
			buy.clickOnRegionsNameAfterSearch(name);
			Log.info("Clicked on the " + name);
			
			//validate the title here -- if the selected region is selected or not  --- 
			String title=buy.getBannerTitleForSelectedCountry();
			Log.info("Selected country title Banner: "+title);
			Thread.sleep(2000);
			buy.clickOnBuyCryptoAccrossDifferentRegion();
			Log.info("Clicked on Buy Crypto Across different regions link ");
			supportedRegions = buy.getSupportedRegionListFromPopup();
			Thread.sleep(2000);
			count++;

		}

		Log.endTestCase("verifyTheSupportedRegionsSectionAndValidateTheCountriesPresentInInThePopup");
	}

	//----------------------------------IXFI Banner Testcases ------------------------------------
	
	@Test
	public void verifyThePresenceOfIXFIBannerSection() throws InterruptedException
	{
		Log.startTestCase("verifyThePresenceOfIXFIBannerSectionTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		Thread.sleep(3000);
		Log.info("Check if IXFI banner is displayed: ");
		boolean status=buy.isIXFIBannerDisplayed();
		Log.info("Banner status: "+status);
		Assert.assertTrue(status,"IXFI banner is not displayed");
		Log.endTestCase("verifyThePresenceOfIXFIBannerSectionTest");
	}
	
	@Test(dependsOnMethods="verifyThePresenceOfIXFIBannerSection")
	public void verifyThatUserIsAbleToClickOnIXFITokenBanner() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnIXFITokenBannerTest");
		buy = index.clickOnBuyCryptoMenu();
		Log.info("Clicked on buy crypto menu");
		Thread.sleep(3000);
		String title=buy.getTheTextOfIXFIBannerSection();
		Log.info("IXFI Token banner text: "+title);
		String expectedTitletext = "IXFI Token: Driving the next phase of IXFI’s evolution";
        title = title.trim().replaceAll("\\s+", " ");
		softAssert.assertEquals(title, expectedTitletext,"Title text from IXFI token banner section is not matched, please verify again");
		buy.clickOnTheIXFIBannerLink();
		Log.info("Clicked on IXFI banner link");
		Action.switchToNewTab();
		Log.info("Switched to new opened tab, i.e. IXFI banner tab");
		String actualTitle=Action.getTitle(driver);
		Log.info("Actual Title: "+actualTitle);
		String expectedTitle=prop.getProperty("ixfiTokenPageTitle");
		Log.info("Expected IXFI Token Page Title: "+expectedTitle);
		softAssert.assertEquals(actualTitle, expectedTitle,"Titles are not matched, please verify again");
		softAssert.assertAll();
		Log.endTestCase("verifyThatUserIsAbleToClickOnIXFITokenBannerTest");
	}
	
	
}
