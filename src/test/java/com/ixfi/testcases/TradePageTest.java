package com.ixfi.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.LoginPage;
import com.ixfi.pageobjects.SignUpPage;
import com.ixfi.pageobjects.TradePage;
import com.ixfi.utility.Log;

public class TradePageTest extends BaseClass{

	IndexPage index;
	LoginPage login;
	SignUpPage signUp;
	SoftAssert softAssert;
	TradePage trade;
	
	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert = new SoftAssert();
		// login=new LoginPage(driver);
	}
	
	@Test
	public void verifyThePresenceOfHeaderTitle()
	{
		Log.startTestCase("verifyThePresenceOfHeaderTitleTest");
		trade=index.clickOnTradeMenu();
		Log.info("Clicked on TradeMenu");
		List<WebElement> actualheaderTitle=trade.getListOfHeaderParameter();
		String [] expectedHeaderTitle= {"Last Price","24h CHG","24h High","24h Low","24h Volume","24h Volume"};
		for(int i=0;i<actualheaderTitle.size();i++)
		{
			String actualheaderTitleText = Action.standardizeText(actualheaderTitle.get(i).getText());
			// System.out.println(actualRewardsProgramMenuItemText);
			String expectedHeaderTitleText = Action.standardizeText(expectedHeaderTitle[i]);
			Log.info("Matching " + actualheaderTitleText + " With " + expectedHeaderTitleText);
			// System.out.println("Actual: " + actualRewardsProgramMenuItemText);
			// System.out.println("Expected: " + expectedRewardsProgramMenuItemText);

			softAssert.assertEquals(actualheaderTitleText, expectedHeaderTitleText,
					"Menu item text mismatch at index " + i);
		}
		
		Log.endTestCase("verifyThePresenceOfHeaderTitleTest");
	}
	
	@Test
	public void verifyThatTradeChartAndOrderBookAndMarketSectionIsDisplayed()
	{
		Log.startTestCase("verifyThatTradeChartIsDisplayedTest");
		trade=index.clickOnTradeMenu();
		Log.info("Clicked on TradeMenu");
		boolean tradestatus=trade.checkThePresenceOfTradeChartSection();
		Log.info("Presence of Trade chart: "+tradestatus);
		softAssert.assertTrue(tradestatus,"Trade chart is not displayed please check again");
		
		boolean orderbookstatus=trade.checkThePresenceOfOrderBookSection();
		Log.info("Presence of order book: "+orderbookstatus);
		softAssert.assertTrue(orderbookstatus,"Order book is not displayed please check again");
		
		boolean marketSectionstatus=trade.checkThePresenceOfMarketTradesSection();
		Log.info("Presence of market section: "+marketSectionstatus);
		softAssert.assertTrue(marketSectionstatus,"Market section is not displayed please check again");
		
	
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatTradeChartIsDisplayedTest");
	}
	
	@Test
	public void verifyThatWhenUserClicksOnLoginLinkUserIsNavigatedToLoginPage() throws InterruptedException
	{
		Log.startTestCase("verifyThatWhenUserClicksOnLoginLinkUserIsNavigatedToLoginPageTest");
		trade=index.clickOnTradeMenu();
		Log.info("Clicked on TradeMenu");
		login=trade.clickOnLoginLink();
		Log.info("Clicked On Login Link");
		Thread.sleep(2000);
		String loginPageWelcomeTitle=login.getWelcomeTitleFromLoginPage();
		Log.info("User is navigated to the login page and the welcome text is: "+loginPageWelcomeTitle);
		softAssert.assertEquals(loginPageWelcomeTitle, "Welcome To IXFI","Welcome titles are not matched, please check if the user is navigated to login page or not");
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatWhenUserClicksOnLoginLinkUserIsNavigatedToLoginPageTest");
	}
	
	@Test
	public void verifyThatWhenUserClicksOnRegisterNowLinkUserIsNavigatedToRegisterPage() throws InterruptedException
	{
		Log.startTestCase("verifyThatWhenUserClicksOnRegisterNowLinkUserIsNavigatedToRegisterPageTest");
		trade=index.clickOnTradeMenu();
		Log.info("Clicked on TradeMenu");
		signUp=trade.clickOnRegisterNowLink();
		Log.info("clicked on register now button");
		Thread.sleep(2000);
		String title=signUp.getSignUpPageTitle();
		Log.info("User is navigated to signUpPage and the page title is: "+title);
		softAssert.assertEquals(title, prop.getProperty("signUpPageTitle"),"Titles are not matched, please check if the user is navigated to sign up page or not");
		
		softAssert.assertAll();
		
		Log.endTestCase("verifyThatWhenUserClicksOnRegisterNowLinkUserIsNavigatedToRegisterPageTest");
	}
	
	@Test
	public void verifyThatUserIsAbleToClickOnLoginButtonUnderBuySection() throws InterruptedException {
	    Log.startTestCase("verifyThatUserIsAbleToClickOnLoginButtonUnderBuySectionTest");
	    
	    trade = index.clickOnTradeMenu();
	    Log.info("Clicked on TradeMenu");
	    
	    String selectedCoinPair = trade.getSelectedCoinPairNameFromTop();
	    Log.info("Selected coin pair name: " + selectedCoinPair);
	    
	    // Splitting the coins by "/"
	    String[] coins = selectedCoinPair.split("/");
	    
	    // Assuming that the pair is valid, you can now access BTC and USDT
	    String firstCoin = coins[0];  // BTC
	    Log.info("First Coin: " + firstCoin);
	    String secondCoin = coins[1]; // USDT
	    Log.info("Second Coin: " + secondCoin);
	    
	    Thread.sleep(2000);
	    trade.clickOnBuySection();
	    Log.info("Clicked on Buy section");
	    
	    String priceCoinName = trade.getPriceFieldCryptoName();
	    Log.info("Price Field Coin Name: " + priceCoinName);
	    String quantityCoinName = trade.getQuantityFieldCryptoName();
	    Log.info("Quantity Field Coin Name: " + quantityCoinName);
	    
	    // Assert that the first coin matches the quantity field, and the second coin matches the price field
	    softAssert.assertEquals(firstCoin, quantityCoinName, "The Crypto from the base coin pair and the Buy section quantity field does not match, please check again");
	    softAssert.assertEquals(secondCoin, priceCoinName, "The crypto from the base coin pair and the buy section price field does not match, please check again");

	    Thread.sleep(2000);
	    login = trade.clickOnLoginButton();
	    Log.info("Clicked On Login Button under buy section");
	    
	    Thread.sleep(2000);
	    String loginPageWelcomeTitle = login.getWelcomeTitleFromLoginPage();
	    Log.info("User is navigated to the login page and the welcome text is: " + loginPageWelcomeTitle);
	    
	    // Assert that the welcome title matches the expected text
	    softAssert.assertEquals(loginPageWelcomeTitle, "Welcome To IXFI", "Welcome titles are not matched, please check if the user is navigated to the login page or not");
	    
	    softAssert.assertAll();  
	    
	    Log.endTestCase("verifyThatUserIsAbleToClickOnLoginButtonUnderBuySectionTest");
	}

	
	@Test
	public void verifyThatUserIsAbleToClickOnLoginButtonUnderSellSection() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToClickOnLoginButtonUnderSellSectionTest");
		trade=index.clickOnTradeMenu();
		Log.info("Clicked on TradeMenu");
		
		String selectedCoinPair=trade.getSelectedCoinPairNameFromTop();
		Log.info("Selected coin pair name: "+selectedCoinPair);
		//splitting the coins by /
		// Split the selected coin pair by the "/" delimiter
        String[] coins = selectedCoinPair.split("/");

        // Assuming that the pair is valid, you can now access BTC and USDT
        String firstCoin = coins[0];  // BTC
        Log.info("First Coin: "+firstCoin);
        String secondCoin = coins[1]; // USDT
        Log.info("Second Coin: "+secondCoin);
        Thread.sleep(4000);
		trade.clickOnSellSection();
		Log.info("Clicked on Sell section");
		Thread.sleep(2000);
		String price_coinName=trade.getPriceFieldCryptoName();
		Log.info("Price Field Coin Name: "+price_coinName);
		String quantity_coinName=trade.getQuantityFieldCryptoName();
		Log.info("Quantity Field Coin Name: "+quantity_coinName);
		
		softAssert.assertEquals(firstCoin, quantity_coinName,"The Crypto from the base coin pair and the Buy section quantity field does not match, please check again");
		softAssert.assertEquals(secondCoin, price_coinName,"The crypto from the base coin pair and the buy section price field does not match, please check again");	
		Thread.sleep(2000);
		login=trade.clickOnLoginButton();
		Log.info("Clicked On Login Button under buy section");
		Thread.sleep(2000);
		String loginPageWelcomeTitle=login.getWelcomeTitleFromLoginPage();
		Log.info("User is navigated to the login page and the welcome text is: "+loginPageWelcomeTitle);
		softAssert.assertEquals(loginPageWelcomeTitle, "Welcome To IXFI","Welcome titles are not matched, please check if the user is navigated to login page or not");
		
		softAssert.assertAll();	
		
		Log.endTestCase("verifyThatUserIsAbleToClickOnLoginButtonUnderSellSectionTest");
	}
	
	
	
}
