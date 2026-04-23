package com.ixfi.testcases;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.MarketsPage;
import com.ixfi.pageobjects.TradePage;
import com.ixfi.utility.Log;

public class MarketsPageTest extends BaseClass {

	IndexPage index;
	SoftAssert softAssert;
	MarketsPage markets;
	TradePage trade;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert = new SoftAssert();
		trade = new TradePage(driver);
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyThatUserIsAbleToNavigateToTheMarketsSection() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToNavigateToTheMarketsSectionTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		String actualMarketPageTitle = markets.getMarketsPageTitle();
		Log.info("Actual Markets Page Title: " + actualMarketPageTitle);
		String expectedTitle = prop.getProperty("marketsPageTitle");
		Log.info("Expected Markets Page Title: " + expectedTitle);
		Assert.assertEquals(actualMarketPageTitle, expectedTitle, "Titles are not matched, please Verify again");
		Log.endTestCase("verifyThatUserIsAbleToNavigateToTheMarketsSectionTest");
	}

	@Test
	public void verifyTheDefualtCoinPairsIsDisplayedOnTheCardPresentOnMarketPage() throws InterruptedException {
		Log.startTestCase("verifyTheDefualtCoinPairsIsDisplayedOnTheCardPresentOnMarketPageTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		List<WebElement> defualtCoinPairs = markets.getDefualtCoinPairsFromMarketHeading();
		Log.info("Captured the defualt coins pairs and the size is: " + defualtCoinPairs.size());
		String[] expectedDefualtCoin = { "BTC/USDT", "ETH/USDT", "ADA/USDT", "BNB/USDT" };
		softAssert.assertEquals(defualtCoinPairs.size(), expectedDefualtCoin.length,
				"Size of default coin does not match with the expected coin");
		for (int i = 0; i < defualtCoinPairs.size() - 1; i++) {
			String str = defualtCoinPairs.get(i).getText();
			Log.info("Actual: " + str + "\n Expected: " + expectedDefualtCoin[i]);
			// softAssert.assertEquals(str, expectedDefualtCoin[i],"Default coin at
			// index"+i+"is Not matched, Please check again");
			softAssert.assertTrue(str.contains(expectedDefualtCoin[i]),
					"Default coin at index " + i + " is Not matched, Please check again");
			softAssert.assertTrue(str.contains("$"), "Does not contain $ sign as prefix to the price");

		}
		softAssert.assertAll();
		Log.endTestCase("verifyTheDefualtCoinPairsIsDisplayedOnTheCardPresentOnMarketPageTest");
	}

	@Test
	public void verify24HVolumeDataIsPresentForDefaultCoinPairs() throws InterruptedException {
		Log.startTestCase("verify24HVolumeDataIsPresentForDefaultCoinPairsTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		List<WebElement> defualtCoinPairs24HVolume = markets.getDefualtcoinVolumeListFromMarketHeading();
		Log.info("Captured 24h volume data for default coin and the size is " + defualtCoinPairs24HVolume.size());
		for (WebElement coin : defualtCoinPairs24HVolume) {
			String str = coin.getText();
			Log.info(str);
			softAssert.assertTrue(str.contains("24h Volume"), "Does not contain 24h Volume, please verify again");
			softAssert.assertTrue(str.contains("USDT"), "Does not contain USDT, please verify again");
		}
		softAssert.assertAll();
		Log.endTestCase("verify24HVolumeDataIsPresentForDefaultCoinPairsTest");
	}

	@Test
	public void verifyThatOnClickingOnDefaultCoinPairUserIsNavigatedToTheTradePage() throws InterruptedException {
		Log.startTestCase("verifyThatOnClickingOnDefaultCoinPairUserIsNavigatedToTheTradePageTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);

		// Create a set to track already visited coin pairs to avoid duplicates
		Set<String> visitedCoinPairs = new HashSet<>();

		// Initial capture of default coin pairs
		List<WebElement> defaultCoinPairs = markets.getDefualtCoinPairsFromMarketHeading();
		Log.info("Captured the default coin pairs and the size is: " + defaultCoinPairs.size());

		// Iterate over the coin pairs
		for (int i = 0; i < defaultCoinPairs.size(); i++) {
			// Re-capture the list of coin pairs every time (to avoid stale element)
			defaultCoinPairs = markets.getDefualtCoinPairsFromMarketHeading();
			WebElement coin = defaultCoinPairs.get(i);
			String marketCoinStr = coin.getText();

			// Skip the coin if it's already visited
			if (visitedCoinPairs.contains(marketCoinStr)) {
				continue; // Skip this coin and move to the next one
			}

			Log.info("Processing default pair: " + marketCoinStr);
			Log.info("Coin Pair Name: " + marketCoinStr);

			// Wait for the coin pair to be clickable before clicking
			Action.waitForElementToBeVisible(coin, 15);

			// Click on the coin pair
			Action.click(coin);
			Log.info("Clicked on: " + marketCoinStr + " And navigated to trade page");
			Thread.sleep(2000);

			// Capture the coin pair on the trade page
			String tradeCoinStr = trade.getPairCoinName();
			Log.info("Captured coin pair from trade page: " + tradeCoinStr);

			// Verify that the coin pairs match
			softAssert.assertTrue(marketCoinStr.contains(tradeCoinStr),
					"Coin pairs do not match between market page and trade page, please check again");

			// Mark the coin pair as visited
			visitedCoinPairs.add(marketCoinStr);

			// Navigate back to the market page
			Action.navigateBack();
			Log.info("Navigated back on Market Page");
			Thread.sleep(3000);
			
		}

		softAssert.assertAll();
		Log.endTestCase("verifyThatOnClickingOnDefaultCoinPairUserIsNavigatedToTheTradePageTest");
	}

	//Validate Spot markets All sections table data --
	@Test
	public void validateAllSectionsTableDataInSpotMarkets() throws InterruptedException
	{
		Log.startTestCase("validateAllSectionsTableDataInSpotMarketsTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		
//		markets.scrollToTheSpotMarketSection();
//		Log.info("Scrolled to the spot markets section");
		Thread.sleep(4000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
		markets.clickOnAllButtonInSpotMarketsSection();
		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("Wallets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");
        
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		Log.endTestCase("validateAllSectionsTableDataInSpotMarketsTest");
	}
	
	@Test
	public void verifyTheTradeButtonsFromActionColumnIsClickableAndAbleToOpenInNewTab() throws InterruptedException
	{
		Log.startTestCase("verifyTheTradeButtonsFromActionColumnIsClickableAndAbleToOpenInNewTabTest");
		
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		markets.scrollToTheSpotMarketSection();
		Log.info("Scrolled to the spot markets section");
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
		markets.clickOnAllButtonInSpotMarketsSection();
		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.validateTradeButtonIsClickableAndAbleToOpenInNewTab();
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

		
		Log.endTestCase("verifyTheTradeButtonsFromActionColumnIsClickableAndAbleToOpenInNewTabTest");
	}
	
	@Test
	public void verifyTheDetailsButtonsFromActionColumnIsClickableAndAbleToOpenInNewTab() throws InterruptedException
	{
		Log.startTestCase("verifyTheDetailsButtonsFromActionColumnIsClickableAndAbleToOpenInNewTabTest");
		//i want to click on 3 pages -- i.e. 3 times next button, thats why this for loop
		
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(2000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
		markets.clickOnAllButtonInSpotMarketsSection();
		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(4000);
		
		markets.validateDetailsButtonIsClickableAndAbleToOpenInNewTab();
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
		
		
		//softAssert.assertAll();
		Log.endTestCase("verifyTheDetailsButtonsFromActionColumnIsClickableAndAbleToOpenInNewTabTest");
	}
	  
	@Test
	public void verifyThatUserIsAbleToClickOnETHMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnETHMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
		markets.clickOnAllButtonInSpotMarketsSection();
		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnETHMarketInSpotMarketsSection();
		Log.info("Clicked on ETH Market section");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("ETH Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "ETH" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("ETH"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnETHMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnBTCMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnBTCMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnBTCMarketsInSpotMarketsSection();
		Log.info("Clicked on BTC Market section");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("BTC Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "BTC" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("BTC"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnBTCMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnAllSectionInALTSMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnAllSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on ALTS Market section");
		Thread.sleep(2000);
		markets.clickOnAllButtonAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on All section in ALT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("ALTS Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnAllSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnBNBSectionInALTSMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnBNBSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on ALTS Market section");
		Thread.sleep(2000);
		markets.clickOnBNBButtonAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on BNB section in ALT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("BNB - ALTS Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "BNB" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("BNB"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnBNBSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnTRXSectionInALTSMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnTRXSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on ALTS Market section");
		Thread.sleep(2000);
		markets.clickOnTRXButtonAltsMarketsInSpotMarketsSecion();
		Log.info("Clicked on TRX section in ALT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("TRX - ALTS Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "TRX" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("TRX"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnTRXSectionInALTSMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnAllSectionInFIATMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnALLSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnFIATMarketsInSpotMarketsSecion();
		Log.info("Clicked on FIAT Market section");
		Thread.sleep(2000);
		markets.clickOnAllButtonFiatMarketsInSpotMarketsSecion();
		Log.info("Clicked on All section in FIAT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("All - FIAT Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnALLSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnUSDTSectionInFIATMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnUSDTSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnFIATMarketsInSpotMarketsSecion();
		Log.info("Clicked on FIAT Market section");
		Thread.sleep(2000);
		markets.clickOnUSDTButtonFiatMarketsInSpotMarketsSecion();
		Log.info("Clicked on USDT section in FIAT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("USDT - FIAT Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "USDT" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("USDT"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnUSDTSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnFDUSDSectionInFIATMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnFDUSDSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnFIATMarketsInSpotMarketsSecion();
		Log.info("Clicked on FIAT Market section");
		Thread.sleep(2000);
		markets.clickOnFDUSDButtonFiatMarketsInSpotMarketsSecion();
		Log.info("Clicked on FDUSD section in FIAT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("FDUSD - FIAT Markets Table Data: " + tableData);
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "FDUSD" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("FDUSD"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnFDUSDSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnUSDCSectionInFIATMarketSectionAndAbleToValidateTheTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnUSDCSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		//markets.scrollToTheSpotMarketSection();
		//Log.info("Scrolled to the spot markets section");
		Thread.sleep(1000);
		markets.clickOnSpotMarketsSection();
		Log.info("Clicked on spot marktes section");
//		markets.clickOnAllButtonInSpotMarketsSection();
//		Log.info("Clicked on All tab in spot markets: ");
		Thread.sleep(2000);
		markets.clickOnFIATMarketsInSpotMarketsSecion();
		Log.info("Clicked on FIAT Market section");
		Thread.sleep(2000);
		markets.clickOnUSDCButtonFiatMarketsInSpotMarketsSecion();
		Log.info("Clicked on USDC section in FIAT Market");
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("USDC - FIAT Markets Table Data: " + tableData); //no need to print this data in logs 
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
	
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        // Check if "USDC" is present in the first column (assuming the first column contains the market pair)
	        String tradePair = row.get(0); // Getting the trade pair value (e.g., BTC/USDC)
	        Log.info("Trade pair is: "+tradePair);
	        softAssert.assertTrue(tradePair.contains("USDC"), "Row " + (i + 1) + ": 'USDC' should be present in the trade pair. Found: " + tradePair);
	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnUSDCSectionInFIATMarketSectionAndAbleToValidateTheTableDataTest");
	}
	
	//-----------------------------Zones Test cases

	@Test
	public void verifyThatUserIsAbleToClickOnZonesAndVerifyItsTableData() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnZonesAndVerifyItsTableDataTest");
		markets = index.clickOnMarketsHeaderMenu();
		Log.info("Clicked on Markets Header Menu Button");
		Thread.sleep(2000);
		markets.clickOnZonesSection();
		Log.info("Clicked on Zones section");		
		Thread.sleep(2000);
		Map<String, List<List<String>>> tableData=markets.getAllSectionInSpotMarketsTableData();
		Log.info("Zones Table Data: " + tableData); //no need to print this data in logs 
		Thread.sleep(2000);
		// Validate Headers
		List<List<String>> headers = tableData.get("headers");
		softAssert.assertFalse(headers.isEmpty(), "Headers should not be empty");
		Log.info("Table Headers:");
		for (int i = 0; i < headers.size(); i++) {
			List<String> headerRow = headers.get(i);
			Log.info("Header Row " + (i + 1) + ": " + headerRow);
			softAssert.assertFalse(headerRow.isEmpty(), "Header Row " + (i + 1) + " should not be empty");
		}
		Thread.sleep(2000);
		// Validate Body Rows
		List<List<String>> bodyRows = tableData.get("bodyRows");
		softAssert.assertFalse(bodyRows.isEmpty(), "Table body rows should not be empty");
		Log.info("Table Body Rows:");
	
		
		for (int i = 0; i < bodyRows.size(); i++) {
	        List<String> row = bodyRows.get(i);
	        Log.info("Row " + (i + 1) + ": " + row);
	        softAssert.assertFalse(row.isEmpty(), "Row " + (i + 1) + " should not be empty");

	        
	        // Validate the 24h change value (assuming it's at index 3)
	        String changeValue = row.get(3).trim(); // Get the 24h CHG value
	        Log.info("24H CHG Value: " + changeValue);
	        softAssert.assertFalse(changeValue.isEmpty(), "Row " + (i + 1) + " should not have an empty 24h CHG value.");
	    }
		
		
		softAssert.assertAll();
		
		
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnZonesAndVerifyItsTableDataTest");
	}
	
}
