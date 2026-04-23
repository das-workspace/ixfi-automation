package com.ixfi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class TradePage extends BaseClass {
	// WebElements

	@FindBy(xpath="//span[@class='pt-1 coinName ng-star-inserted']")
	WebElement coinName;
	
	 By coinPriceScrollHeader =By.xpath("//div[@class='coin-price-scroll']//span[1]"); //this is the list of top values such as Last price, 24h CHG,24h high, 24h low etc
	 
	 @FindBy(xpath="//div[@class='d-flex trade-coin-name']//span[@class='pt-1 coinName ng-star-inserted']")
	 WebElement coinPair;
	 
	 @FindBy(xpath="//div[@class='d-flex trade-coin-name']//em[@class='flaticon-sort-down coinName ms-1 ng-star-inserted']")
	 WebElement coinPairDD;
	 
	 @FindBy(xpath="//input[@placeholder='e.g. “ETH” or “Ethereum”']")
	 WebElement searchField; //search field from the coin pair DD - to search the coin 
	 
	 @FindBy(xpath="//div[@class='card border-trade trade-user-info mt-3 d-none d-lg-block ng-star-inserted']//a[normalize-space()='Log In']")
	 WebElement loginLink;
	 
	 @FindBy(xpath="//div[@class='card border-trade trade-user-info mt-3 d-none d-lg-block ng-star-inserted']//a[normalize-space()='Register Now']")
	 WebElement registerNow;
	 
	 @FindBy(xpath="//li[@ngbnavitem='buy']//a[normalize-space()='Buy']")
	 WebElement buy;
	 
	 @FindBy(xpath="//li[@ngbnavitem='sell']//a[normalize-space()='Sell']")
	 WebElement sell;
	 
	 @FindBy(xpath="//button[normalize-space(text())='Login']")
	 WebElement loginButton;
	 
	 @FindBy(xpath="//li[@ngbnavitem='limit']//a[normalize-space()='Limit']")
	 WebElement limit;
	 
	 @FindBy(xpath="//li[@ngbnavitem='market']//a[normalize-space()='Market']")
	 WebElement market;
	 
	 @FindBy(xpath="//li[@ngbnavitem='stop-limit']//a[normalize-space()='Stop-Limit']")
	 WebElement stopLimit;
	 
	 @FindBy(xpath="//span[normalize-space()='Market Trades']")
	 WebElement marketTradesTitle;
	 
	 @FindBy(xpath="//div[@class='card border-trade marketTrand p-3 ng-star-inserted']")
	 WebElement marketTradesSection;
	 
	 @FindBy(xpath="//div[@class='trade-card border-trade card orderbookCover card-spacing ng-star-inserted']")
	 WebElement orderBookSection;
	 
	 @FindBy(xpath="//div[@class='card border-trade cart-cover ng-star-inserted']")
	 WebElement tradeChartSection;
	 //now i need to capture the webElements for price and coin pair verification
	 
	 @FindBy(xpath="//input[@formcontrolname='quoteCoinPrice']/following-sibling::span")
	 WebElement priceFieldCrypto;
	 
	 @FindBy(xpath="//input[@formcontrolname='baseCoinQuantity']/following-sibling::span")
	 WebElement quantityFieldCrypto;
	
	// Constructor
	public TradePage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// Action Method
	public String getTradePageTitle() {
		String convertPageTitle = Action.getTitle(driver);
		return convertPageTitle;
	}
	
	public String getPairCoinName()
	{
		Action.waitForElementToBeVisible(coinName, 15);
		return Action.getText(coinName);
	}
	
	public List<WebElement> getListOfHeaderParameter()
	{
		return Action.findElements(driver, coinPriceScrollHeader);
	}
	
	public String getSelectedCoinPairNameFromTop()
	{
		Action.waitForElementToBeVisible(coinPair, 20);
		return Action.getText(coinPair);
	}
	
	public void clickOnCoinPairDD()
	{
		Action.click(coinPairDD);	
	}
	
	public void enterCoinInSearchField(String coinName)
	{
		Action.waitForElementToBeVisible(searchField, 20);
		Action.enterText(searchField, coinName);
	}
	
	public LoginPage clickOnLoginLink()
	{
		Action.scrollByVisibilityOfElement(loginLink);
		Action.click(loginLink);
		return new LoginPage(driver);
	}
	
	public SignUpPage clickOnRegisterNowLink()
	{
		Action.scrollByVisibilityOfElement(registerNow);
		Action.click(registerNow);
		return new SignUpPage(driver);
	}
	
	public void clickOnBuySection()
	{
		Action.scrollByVisibilityOfElement(buy);
		Action.click(buy);
	}
	
	public void clickOnSellSection()
	{
		Action.scrollByVisibilityOfElement(sell);
		Action.waitForElementToBeVisible(sell, 20);
		Action.click(sell);
	}
	
	public void clickOnLimit()
	{
		Action.click(limit);
	}
	
	public void clickOnMarket()
	{
		Action.click(market);
	}
	
	public void clickOnStopLimit()
	{
		Action.click(stopLimit);
	}
	
	public LoginPage clickOnLoginButton()
	{
		Action.scrollByVisibilityOfElement(loginButton);
		Action.waitForElementToBeVisible(loginButton, 20);
		Action.click(loginButton);
		return new LoginPage(driver);
	}
	
	public String getTheMarketTradeText()
	{
		return Action.getText(marketTradesTitle);
	}
	
	public boolean checkThePresenceOfMarketTradesSection()
	{
		Action.scrollByVisibilityOfElement(marketTradesTitle);
		return Action.isElementDisplayed(marketTradesSection);
	}
	
	public boolean checkThePresenceOfOrderBookSection()
	{
		return Action.isElementDisplayed(orderBookSection);
	}
	
	public boolean checkThePresenceOfTradeChartSection()
	{
		return Action.isElementDisplayed(tradeChartSection);
	}
	
	public String getPriceFieldCryptoName()
	{
		Action.waitForElementToBeVisible(priceFieldCrypto, 20);
		return Action.getText(priceFieldCrypto);
	}
	
	public String getQuantityFieldCryptoName()
	{
		Action.waitForElementToBeVisible(quantityFieldCrypto, 20);
		return Action.getText(quantityFieldCrypto);
	}
}
