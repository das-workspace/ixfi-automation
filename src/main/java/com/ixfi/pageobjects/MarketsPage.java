package com.ixfi.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class MarketsPage extends BaseClass {

	// WebElements
	@FindBy(xpath="//h1[@class='page-heading']")
	WebElement MarketsHeading;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchBox;  //universal on this page
	
	By coinHeadingMarketCard=By.xpath("//div[@class='market-card']/h6");
	
	By coinVolumeList=By.xpath("//div[@class='market-card']/div[@class='coin-volume']");
	
	By table=By.xpath("//div[contains(@class, 'ixfi-mat-table-container')]//table[contains(@class, 'mat-mdc-table')]");
	
	By tradeBtn=By.xpath("//div[@class='custom-templates-flex ng-star-inserted']/span/a[normalize-space()='Trade']");
	By detailsBtn=By.xpath("//div[@class='custom-templates-flex ng-star-inserted']/span/a[normalize-space()='Details']");
	
	//---------------------------Tabel - Pagination ----------------------------
	
	@FindBy(xpath="//ul[@class='pagination']//a[@aria-label='Next']")
	WebElement next;
	
	@FindBy(xpath="//ul[@class='pagination']//a[@aria-label='Previous']")
	WebElement prev;
	
	
	
	//-------------------------Spot Markets---------------------
	
	@FindBy(xpath="//span[normalize-space()='Spot Markets']")
	WebElement spotMarket;
	
	@FindBy(xpath="//a[starts-with(@id, 'ngb-nav') and normalize-space(text())='All']")
	WebElement all;
	
	@FindBy(xpath="//a[starts-with(@id, 'ngb-nav') and normalize-space(text())='ETH Markets']")
	WebElement ethMarkets;
	
	@FindBy(xpath="//a[starts-with(@id, 'ngb-nav') and normalize-space(text())='BTC Markets']")
	WebElement btcMarkets;
	
	@FindBy(xpath="//a[starts-with(@id, 'ngb-nav') and normalize-space(text())='ALTS Markets']")
	WebElement altMarkets;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='All']")
	WebElement altMarketsAll;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='BNB']")
	WebElement altMarketsBNB;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='TRX']")
	WebElement altMarketsTRX;


	@FindBy(xpath="//a[starts-with(@id, 'ngb-nav') and normalize-space(text())='FIAT Markets']")
	WebElement fiatMarkets;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='All']")
	WebElement fiatMarketsAll;  //xpath is similar like altMarketsAll
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='USDT']")
	WebElement fiatMarketsUSDT;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='FDUSD']")
	WebElement fiatMarketsFDUSD;
	
	@FindBy(xpath="//li[@class='nav-item ng-star-inserted']//a[normalize-space(text())='USDC']")
	WebElement fiatMarketsUSDC;
	
	//-----------------------------Zones------------------------------------
	@FindBy(xpath="//span[normalize-space()='Zones']")
	WebElement zones;
	
	

	// Constructor
	public MarketsPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// Action Method
	public String getMarketsPageTitle() {
		String MarketsPageTitle = Action.getTitle(driver);
		return MarketsPageTitle;
	}
	
	public String getMarketPageHeadingText()
	{
		Action.waitForElementToBeVisible(MarketsHeading, 10);
		return Action.getText(MarketsHeading);
	}
	
	public void enterCoinsPairsToSearchInMarketSections(String coinsPair)
	{
		Action.waitForElementToBeVisible(searchBox, 10);
		Action.click(searchBox);
		Action.enterText(searchBox, coinsPair);
		
	}
	
	public List<WebElement> getDefualtCoinPairsFromMarketHeading()
	{
		List<WebElement> coinPairs=Action.findElements(driver, coinHeadingMarketCard);
		return coinPairs;
	}
	
	public List<WebElement> getDefualtcoinVolumeListFromMarketHeading()
	{
		List<WebElement> coinPairsVolume=Action.findElements(driver, coinVolumeList);
		return coinPairsVolume;
	}
	
	//------------------------------------------Spot Markets-------------------------------------
	public void clickOnSpotMarketsSection()
	{
		Action.waitForElementToBeVisible(spotMarket, 10);
		Action.click(spotMarket);
	}
	
	public void scrollToTheSpotMarketSection()
	{
		Action.scrollByVisibilityOfElement(spotMarket);
	}
	
	public void clickOnAllButtonInSpotMarketsSection()
	{
		Action.waitForElementToBeVisible(all, 10);
		Action.click(all);
	}
	
	public void clickOnETHMarketInSpotMarketsSection()
	{
		Action.waitForElementToBeVisible(ethMarkets, 10);
		Action.click(ethMarkets);
	}
	
	public void clickOnBTCMarketsInSpotMarketsSection()
	{
		Action.waitForElementToBeVisible(btcMarkets, 10);
		Action.click(btcMarkets);
	}
	
	//----------------------Alt markets ----------------
	
	public void clickOnAltsMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(altMarkets, 10);
		Action.click(altMarkets);
	}
	
	public void clickOnAllButtonAltsMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(altMarketsAll, 10);
		Action.click(altMarketsAll);
	}
	
	public void clickOnBNBButtonAltsMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(altMarketsBNB, 10);
		Action.click(altMarketsBNB);
	}
	
	public void clickOnTRXButtonAltsMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(altMarketsTRX, 10);
		Action.click(altMarketsTRX);
	}
	
	
	//-----------------------------------Fiat markets -------------------
	
	public void clickOnFIATMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(fiatMarkets, 10);
		Action.click(fiatMarkets);
	}
	
	public void clickOnAllButtonFiatMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(fiatMarketsAll, 10);
		Action.click(fiatMarketsAll);
	}
	
	public void clickOnUSDTButtonFiatMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(fiatMarketsUSDT, 10);
		Action.click(fiatMarketsUSDT);
	}
	
	public void clickOnFDUSDButtonFiatMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(fiatMarketsFDUSD, 10);
		Action.click(fiatMarketsFDUSD);
	}
	
	public void clickOnUSDCButtonFiatMarketsInSpotMarketsSecion()
	{
		Action.waitForElementToBeVisible(fiatMarketsUSDC, 10);
		Action.click(fiatMarketsUSDC);
	}
	
	//--------------------------------------------All coins section----------------------
	
	public Map<String, List<List<String>>> getAllSectionInSpotMarketsTableData() throws InterruptedException {
		Action.scrollByVisibilityOfElement(all); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		//Action.clickUsingJavaScript(driver, walletsBtn);
		return Action.getTableData(table); // Fetch the entire table data
	}
	
	public void validateTradeButtonIsClickableAndAbleToOpenInNewTab() throws InterruptedException
	{
		List<WebElement> tradeButtons=Action.findElements(driver, tradeBtn);
		for(WebElement trade:tradeButtons)
		{
			
			Action.openInNewTab(trade);
			//Action.waitForElementToBeClickable(trade, 10);
			Thread.sleep(2000);
			//Action.scrollByVisibilityOfElement(trade);
		}
	}
	
	public void validateDetailsButtonIsClickableAndAbleToOpenInNewTab() throws InterruptedException
	{
		List<WebElement> detailsButtons=Action.findElements(driver, detailsBtn);
		for(WebElement detail:detailsButtons)
		{
			
			Action.openInNewTab(detail);
			//Action.waitForElementToBeClickable(trade, 10);
			Thread.sleep(2000);
			//Action.scrollByVisibilityOfElement(trade);
		}
	}
	
	
	//--------------------------Pagination
	
	public void clickOnNextButtonOnPagination()
	{
		Action.scrollByVisibilityOfElement(next);
		Action.click(next);
	}
	
	public void clickOnPrevButtonOnPagination()
	{
		Action.scrollByVisibilityOfElement(prev);
		Action.click(prev);
	}
	
	//-------------------------------------- Zones ---------------------------
	public void clickOnZonesSection()
	{
		Action.waitForElementToBeVisible(zones, 10);
		Action.click(zones);
	}

}
