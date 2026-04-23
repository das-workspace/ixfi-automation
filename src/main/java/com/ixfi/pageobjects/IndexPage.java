package com.ixfi.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.basepage.BaseClass;

import jdk.internal.org.jline.utils.Log;

import com.ixfi.actiondriver.*;

//this page will act as NewHomePage -- as it is the landing page of IXFI, from here we can navigate to different sections

public class IndexPage extends BaseClass {
	
	//WebElements
	
	//-------------------------Menu's----------------------
		@FindBy(xpath="//a[normalize-space()='Markets']")
		WebElement marketsHeaderMenu;
	
	//------------------------IXFI Tokens -- new features-----------------
		
		@FindBy(xpath="//a[@class='ng-tns-c2484022394-0 ng-star-inserted'][normalize-space()='IXFI Token']")
		WebElement ixfiTokenHeaderMenu;
		
		@FindBy(xpath="//div[@class='token-banner']")
		WebElement ixfitokenBanner;
		
		@FindBy(xpath="//div[@class='token-banner']/div/img[@class='token-logo']")
		WebElement ixfiTokenLogo;
		
		@FindBy(xpath="//div[@class='token-banner']/div[2]/h3/strong")
		WebElement ixfiTokenText;
		
		@FindBy(xpath="//div[@class='token-banner']/div[2]/h3")
		WebElement ixfiTokenTextPara;
		
		@FindBy(xpath="//div[@class='token-banner']/div[2]/div/div[@class='token-info']/span")
		WebElement tokenInfo;
		
		@FindBy(xpath="//div[@class='token-info']//following-sibling::a")
		WebElement ixfiTokenLink;  //clicking  on it. it will open in new tab
		
		
		
		
	
	//----------------------------------------------------
	
	@FindBy(xpath="//a[normalize-space()='Buy Crypto']")
	WebElement buyCryptoMenu;

	@FindBy(xpath = "//header/div[3]/div[1]/a[normalize-space(text())='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//header/div[3]/div[1]/a[normalize-space(text())='Register']")
	WebElement SignUpBtn;

	@FindBy(xpath = "//img[@alt='Logo' and @src='https://cdn.ixfi.com/new-header/logo.svg']")
	WebElement IxfiLogo; // can be act as link to when clicked on it, check whether it is clickable or
							// not

	@FindBy(xpath = "//img[@rel='preload' and @class='themeIcon ng-tns-c2484022394-0 ng-star-inserted' ]")
	WebElement dayLightModeBtn;

	@FindBy(xpath = "//div[@class='hamburger-login ng-tns-c2484022394-0']")
	WebElement humbergerMenuBtn;

	@FindBy(xpath = "//b[normalize-space()='Download']")
	WebElement downloadBtn;

	@FindBy(xpath = "//b[normalize-space()='Language']")
	WebElement languageBtn;

	@FindBy(xpath = "//b[normalize-space()='Limbă']")
	WebElement limbaBtn; // in romania language

	@FindBy(xpath = "//span[normalize-space()='English']")
	WebElement englishTxt;

	@FindBy(xpath = "//span[normalize-space()='Română']")
	WebElement romanaTxt;

	@FindBy(xpath = "//div[@class='mobile-sideBar2 ng-tns-c3965838326-0 show']//ul/li")
	WebElement languageDD; // Language Dropdown values -- English and romania

	By languageDDLocator = By.xpath("//div[@class='mobile-sideBar2 ng-tns-c2484022394-0 show']//ul/li");

	@FindBy(xpath = "//b[normalize-space()='Currency']")
	WebElement currencyBtn;

	By currencyLocator = By.xpath("//div[@class='mobile-sideBar2 ng-tns-c2484022394-0 show']/ul/li");

	@FindBy(xpath = "//h1[@class='ltr']")
	WebElement gen3_0_cryptoExchangeText;

	@FindBy(xpath = "//p[@class='px-3 px-md-0']")
	WebElement paraText0fCryptoExchange;

	@FindBy(xpath = "//div/div[1]/div[1]/a[@type='button']")
	WebElement signupwithemailorphonebutton;

	@FindBy(xpath = "//a[@aria-label='play store icon']")
	WebElement playstoreAppDownloadIcon;

	@FindBy(xpath = "//a[@aria-label='apple store icon']/img")
	WebElement iosAppDownloadIcon;

	@FindBy(xpath = "//span/img[@alt='QR']")
	WebElement scanAndDownloadIcon;

	@FindBy(xpath = "//div[@class='social-app-cover d-none d-md-inline-block']")
	WebElement downloadTheIXFIBottomAppSection;

	@FindBy(xpath = "//a//picture//img[@alt='appstore']")
	WebElement bottomAppStoreBtn;

	@FindBy(xpath = "//a//img[@alt='google play']")
	WebElement bottomPlayStoreButton;

	@FindBy(xpath = "//div[@class='app-qr-code']//img[@alt='QR']")
	WebElement bottomScanner;

	@FindBy(xpath = "//div[@class='card-info']")
	WebElement cardInfo_4; // capture all 4 text with this xpath

	@FindBy(xpath = "//div[@class='card-body']//h2[contains(text(),'Rewards Center')]")
	WebElement rewardsCenter;

	@FindBy(xpath = "//div[@class='card-body']//h2[contains(text(),'Airdrops')]")
	WebElement airdrops;

	@FindBy(xpath = "//div[@class='card-body']//h2[contains(text(),'Referral Program')]")
	WebElement referralProgramme;

	@FindBy(xpath = "//div[@class='card-body']//h2[contains(text(),'0% Fee Conversion')]")
	WebElement convertFee;

	@FindBy(xpath = "//div[@class='wrapper']/div[@class='title-area ltr mb-2 mb-sm-3 pb-2 ng-star-inserted']")
	WebElement weeklySummaryTitleSection;

	@FindBy(xpath = "//div[@class='title-area ltr mb-2 mb-sm-3 pb-2 ng-star-inserted']/a")
	WebElement weeklySummaryTitle;

	@FindBy(xpath = "//div[@class='title-area ltr mb-2 mb-sm-3 pb-2 ng-star-inserted']/div/a")
	WebElement weeklySummaryReadMoreBtn;

//	@FindBy(xpath="//div[@class='card-footer']//a")
//	WebElement readMoreBtnOnWeeklySummaryFooterCard; 

	By readMoreBtnOnWeeklySummaryFooterCard = By.xpath("//div[@class='card-footer']//a");

	// Market Section Xpaths
	// -------------------------------------------------------------

	@FindBy(xpath = "//div[@class='wrapper market-wrapper']")
	WebElement marketTrendSection;

	@FindBy(xpath = "//div[@class='market-section']")
	WebElement marketSection;

	@FindBy(id = "myTabContent")
	WebElement markeTable;

	@FindBy(xpath = "//div[contains(@class, 'd-flex')]//a[contains(@class, 'btn-twice-outline') and contains(text(), 'Details')]")
	WebElement detailsBtn;

	By detailsBtnLocator = By.xpath(
			"//div[contains(@class, 'd-flex')]//a[contains(@class, 'btn-twice-outline') and contains(text(), 'Details')]");

	@FindBy(xpath = "//div[contains(@class, 'd-flex')]//a[contains(@class, 'btn-default') and contains(text(), 'Trade')]")
	WebElement tradeBtn;

	By tradeBtnLocator = By.xpath(
			"//div[contains(@class, 'd-flex')]//a[contains(@class, 'btn-default') and contains(text(), 'Trade')]");

	By topTableLocator = By.xpath("//div[@id='hot']//table[@role='table']");
	By gainerTableLocator = By.xpath("//div[@id='gainers']//table[@role='table']");
	By loosersTableLocator = By.xpath("//div[@id='looser']//table[@role='table']");

	@FindBy(xpath = "//div[@class='market-section']/ul/li/button[normalize-space()='Top']")
	WebElement topBtn;

	@FindBy(xpath = "//div[@class='market-section']/ul/li/button[normalize-space()='Gainers']")
	WebElement gainersBtn;

	@FindBy(xpath = "//div[@class='market-section']/ul/li/button[normalize-space()='Losers']")
	WebElement losersBtn;

	@FindBy(xpath = "//div[@id='hot']//span[@class='me-2'][normalize-space()='Start trading now']")
	WebElement startTradingNowBtn;

	// --------------------------------------------------------------------------------------

	// Earn-Platform -- Use our Rewards Program to win up to $10,000

	@FindBy(xpath = "//section[@class='earn-platform']/div/div[@class='earn-ph-wrapper']")
	WebElement earn_platform_section;

	@FindBy(xpath = "//div[@class='earn-content-head mb-2 mb-md-4 d-none d-lg-block']//h2[@class='header-name mb-2']")
	WebElement earnPlatformTitle;

	@FindBy(xpath = "//div[@class='earn-content-head mb-2 mb-md-4 d-none d-lg-block']//p[@class='header-description'][contains(text(),'Rewards just don’t stop coming to IXFI users. Ther')]")
	WebElement earnPlatformTitleParagraph;

	@FindBy(xpath = "//h3[normalize-space()='1. Join IXFI']")
	WebElement joinIXFIBtn; // on clicking this, user will navigated to register page

	@FindBy(xpath = "//h3[normalize-space()='2. Complete Tasks']")
	WebElement completeTask; // on clicking this user will navigated to task center

	@FindBy(xpath = "//h3[normalize-space()='3. Get Free Crypto']")
	WebElement getFreeCrypto; // on clicking this, user will navigated to rewards section

	// ----------------------------------------------------------------------------------------
	@FindBy(xpath = "//h3[text()='Scan & Download the App']")
	WebElement scanAndDownloadScanner;

	@FindBy(xpath = "//ul[@class='ng-tns-c2484022394-4']//a")
	WebElement headerMenuList;
	By headerMenu = By.xpath("//ul[@class='ng-tns-c2484022394-0']//li//a");
	By childLocator = (By) headerMenuList;

	@FindBy(xpath = "//a[@class='ng-tns-c2484022394-0 dropbtn ng-star-inserted'][normalize-space()='Trade']")
	WebElement tradeMenu;

//	@FindBy(xpath="//div[@class='ixfi-navigation d-none d-xl-inline-block ng-tns-c3437203097-0']//li[3]//ul[1]//li/a")
//	WebElement convertMenuList;
	By tradeMenuList = By
			.xpath("//div[@class='ixfi-navigation d-none d-xl-inline-block ng-tns-c2484022394-0']//li[3]//ul[1]//li/a");

	@FindBy(xpath = "//a[normalize-space()='Rewards Program']")
	WebElement rewardsProgramMenu;

	By rewardsProgramMenuList = By
			.xpath("//li[a[text()=' Rewards Program ']]//ul[contains(@class, 'dropdown-content')]/li/a/div[1]/div[2]");

	@FindBy(xpath = "//a[normalize-space()='Research']")
	WebElement researchMenu;

	By researchMenuList = By
			.xpath("//li[a[text()=' Research ']]//ul[contains(@class, 'dropdown-content')]/li/a/div[1]/div[2]");
	
	
	//--------------------FooterSection
	@FindBy(xpath="//footer[@class='w-100 pt-4 flex-shrink-0']")
	WebElement footerSection;
	
	// trade menu -- convert and spot trade -- 
	
	@FindBy(xpath="//a[normalize-space()='Trade']")
	WebElement trade;
	
	@FindBy(xpath="//a[@href='/convert']//div[contains(text(), 'Convert')]")
	WebElement convert;
	
	@FindBy(xpath="//a[@href='/trade/BTC/USDT']//div[contains(text(), 'Spot Trade')]")
	WebElement spotTrade;
	
	
	

	public IndexPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public LoginPage clickOnLoginButton() {

		int attempts = 0;
		while (attempts < 3) {
			try {
				Action.click(loginBtn);
				return new LoginPage(driver);
			} catch (StaleElementReferenceException e) {
				// Reinitialize the web element
				PageFactory.initElements(driver, this);
				attempts++;
			}
		}
		throw new StaleElementReferenceException("Unable to click on login button after multiple attempts");
	}
	
	public BuyCryptoPage clickOnBuyCryptoMenu()
	{
		Action.click(buyCryptoMenu);
		return new BuyCryptoPage(driver);
	}

	public MarketsPage clickOnMarketsHeaderMenu()
	{
		Action.click(marketsHeaderMenu);
		return new MarketsPage(driver);
	}
	
	public List<WebElement> getHeaderMenuList() {
		return Action.findElements(driver, headerMenu);

	}

	public void printHeaderMenuList() {
		Action.printElementsText(driver, headerMenu);
	}

	public List<WebElement> getTradeMenuList() {
		Action.mouseOverElement(tradeMenu);
		return Action.findElements(driver, tradeMenuList);

	}
	//trade page menu list--------------------
	public ConvertPage clickOnConvertMenu()
	{
		Action.mouseOverElement(trade);
		Action.click(convert);
		return new ConvertPage(driver);
	}
	
	public TradePage clickOnTradeMenu()
	{
		Action.click(trade);
		return new TradePage(driver);
	}
	
	public TradePage clickOnSpotTradeMenuUnderTradeMenu()
	{
		Action.click(spotTrade);
		return new TradePage(driver);
	}
    //--------------------------------------
	
	public List<WebElement> getRewardsProgramMenuList() {
		Action.mouseOverElement(rewardsProgramMenu);
		return Action.findElements(driver, rewardsProgramMenuList);
	}

	public List<WebElement> getResearchMenuList() {
		Action.mouseOverElement(researchMenu);
		return Action.findElements(driver, researchMenuList);
	}

	public String getIXFITitle() {
		String ixfi_title = driver.getTitle();
		return ixfi_title;
	}

	public SignUpPage clickOnSignUpButton() {
//		Action.click(SignUpBtn);
//		return new SignUpPage(driver);

		int attempts = 0;
		while (attempts < 3) {
			try {
				Action.click(SignUpBtn);
				return new SignUpPage(driver);
			} catch (StaleElementReferenceException e) {
				// Reinitialize the web element
				PageFactory.initElements(driver, this);
				attempts++;
			}
		}
		throw new StaleElementReferenceException("Unable to click on SignUp button after multiple attempts");
	}

	public boolean validateLogo() {
		// Action.explicitWait(driver,IxfiLogo,Duration.ofSeconds(10));
		return Action.isDisplayed(driver, IxfiLogo);
	}

	public IndexPage clickOnIxfiLogo() {
		Action.click(IxfiLogo);
		return new IndexPage(driver);
	}

	public String validateGen3_0CryptoExchangeText() {
		// Log.info("Getting the crypto 3.0 gen exchnage text");
		String gen3CryptoText = gen3_0_cryptoExchangeText.getText();
		return gen3CryptoText;
	}

	public String validateParaText0fCryptoExchange() {
		String paraTextOfGen3 = paraText0fCryptoExchange.getText();
		return paraTextOfGen3;
	}

	// to verify daylight mode using alt attribute of img tag
	public String validateDayLightMode() {
		String actualAlt = dayLightModeBtn.getAttribute("alt");
		return actualAlt;
	}

	public void clickOnDayLightButton() {
		Action.click(dayLightModeBtn);
	}

	public SignUpPage clickOnSignUpWithEmailorPhoneButton() {
		Action.click(signupwithemailorphonebutton);
		return new SignUpPage(driver);
	}

	public void clickOnPlayStoreAppDownloadIcon() {
		Action.click(playstoreAppDownloadIcon);
	}

	public void clickOniosAppDownloadIcon() {
		Action.click(iosAppDownloadIcon);
	}

	public void validateScanAndDownloadIcon() {
		Action.isDisplayed(driver, scanAndDownloadIcon);
	}

	public void clickOnscanAndDownloadIcon() {
		Action.click(driver, scanAndDownloadIcon);
	}

	public void validateDownloadButtonUnderHamburgerMenuIsClickable() throws InterruptedException {
		Action.click(humbergerMenuBtn);
		// Log.info("Humberger menu is clicked.");
		Thread.sleep(2000);
		Action.click(downloadBtn);
		// Action.switchToWindowByTitle(driver,"xy");
		Thread.sleep(2000);
		// Log.info("Download Button is clicked.");
	}

	public void clickOnHamburgerMenu() {
		Action.waitForElementToBeClickable(humbergerMenuBtn, 10);
		Action.click(humbergerMenuBtn);
	}

	public void clickOnLanguageButtonUnderHamburgerMenu() {
		// Action.click(humbergerMenuBtn);
		Action.waitForElementToBeVisible(languageBtn, 5);
		Action.clickUsingJavaScript(driver, languageBtn);
	}

	public void clickOnLimbaButton() {
		Action.waitForElementToBeVisible(limbaBtn, 5);
		Action.clickUsingJavaScript(driver, limbaBtn);
	}

	public String getTheTextOfLimbaButton() {
		return Action.getText(limbaBtn);
	}

	public String getTextOfEnglishLanguage() {
		return Action.getText(englishTxt);
	}

	public String getTextOfRomanaLanguage() {
		return Action.getText(romanaTxt);
	}

	public List<WebElement> validateLanguageButtonUnderHambugerMenuIsClickable() {

		Action.click(humbergerMenuBtn);
		Action.waitForElementToBeVisible(languageBtn, 5);
		Action.clickUsingJavaScript(driver, languageBtn);
		List<WebElement> langauges = Action.findElements(driver, languageDDLocator);
		return langauges;

	}

	public void clickOnCurrencyButton() {
		Action.waitForElementToBeClickable(currencyBtn, 5);
		Action.clickUsingJavaScript(driver, currencyBtn);
	}

	public List<WebElement> getListOfCurrency() {
		return Action.findElements(driver, currencyLocator);

	}

	public void validateCurrencyButtonUnderHambugerMenuIsClickable() {

	}

	public void verifyPresenceOfCardsSections() {
		// use list to capture all 4 text and verify one by one -- use findElements
		// method here
	}

	public RewardsProgramPage validateUserIsNavigateToRewardsPage() {
		Action.click(rewardsCenter);
		return new RewardsProgramPage(driver);
	}

	public AirdropsPage validateUserIsNavigateToAirdropsPage() {
		// return object of AirdropsPage
		Action.click(airdrops);
		return new AirdropsPage(driver);
	}

	public ReferralProgramPage validateUserIsNavigateToRefferalProgramPage() throws InterruptedException {
		// return object of Refferal Program page
		Action.click(referralProgramme);
		Thread.sleep(5000);
		return new ReferralProgramPage(driver);
	}

	public ConvertPage validateUserIsNavigateToConvertPage() throws InterruptedException {
		// return object of convert page
		Action.click(convertFee);
		Thread.sleep(5000);
		return new ConvertPage(driver);
	}

	// this method will validate the download app functionality
	public List<String> openTopDownloadLinkInNewTab() throws InterruptedException {
		// Click icons and buttons using safeClick method
		Action.click(iosAppDownloadIcon);
		Action.click(playstoreAppDownloadIcon);

		// Scroll and click bottom buttons safely
//        Thread.sleep(4000);
//        Action.scrollByVisibilityOfElement(downloadTheIXFIBottomAppSection);
//        Action.click(bottomAppStoreBtn);
//        Thread.sleep(4000);
//        Action.click(bottomPlayStoreButton);

		return Action.getAllOpenTabs();

	}

	public List<String> validateWeeklySummarySection() throws InterruptedException {

		Action.scrollByVisibilityOfElement(convertFee);
		// System.out.println("Scrolled to the weekly summary title ");
		Action.waitForElementToBeClickable(weeklySummaryTitle, 4);
		// Action.implicitWait(driver, 4);
		Thread.sleep(4000);
		Action.click(weeklySummaryTitle);
		// System.out.println("clicked on weekly summary title");
		Action.click(weeklySummaryReadMoreBtn);
		// System.out.println("clicked on ReadMore Button present on weekly summary
		// header ");
		Thread.sleep(3000);
		Action.scrollByVisibilityOfElement(weeklySummaryTitle);
		// System.out.println("Scrolled to the ReadMore Button on Weekly summary
		// cards");
		List<WebElement> readMoreButton = Action.findElements(driver, readMoreBtnOnWeeklySummaryFooterCard);
		// System.out.println("Fetched All Cards ReadMore Button");
		// System.out.println("ReadMoreButtonCount: "+readMoreButton.size());
		for (WebElement readmore : readMoreButton) {
			Action.clickUsingJavaScript(driver, readmore);
			Thread.sleep(2000);
			Action.scrollToElementHorizontally(readmore);
		}
		return Action.getAllOpenTabs();
	}

	// Verify presence of Market treding section

	public void clickOnTopButton() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, topBtn);
	}

	public void clickOnGainerButton() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, gainersBtn);
	}

	public void clickOnLoserButton() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, losersBtn);
	}

	public void validateDetailsButtonIsClickable() throws InterruptedException {
		Thread.sleep(2000);
		Action.scrollToElement(driver, gainersBtn);
		List<WebElement> detailsButton = Action.findElements(driver, detailsBtnLocator);
		// System.out.println("Total details buttons found: " + detailsButton.size());

		for (int i = 0; i < detailsButton.size(); i++) {
			WebElement detail = detailsButton.get(i);
			
			if (i <= 4) {
				Action.openInNewTab(detail);
			} else if (i <= 9) {
				Action.clickUsingJavaScript(driver, gainersBtn);
				Action.openInNewTab(detail);
			} else if (i <= 14) {
				Action.clickUsingJavaScript(driver, losersBtn);
				Action.openInNewTab(detail);
			}

			// System.out.println("Clicked on Detail button: " + (i + 1));

			// Optional: Ensure the tab is opened properly before proceeding
			Action.waitForTabsToOpen(i + 2, 10); // +2 to account for the main tab
		}
	}

	public void validateTradeButtonIsClickable() throws InterruptedException {
		// int count = 0;
		List<WebElement> tradeButton = Action.findElements(driver, tradeBtnLocator);
		// System.out.println(tradeButton.size());
		for (int i = 0; i < tradeButton.size(); i++) {
			WebElement trade = tradeButton.get(i);

			if (i <= 4) {
				Action.openInNewTab(trade);
			} else if (i <= 9) {
				Action.clickUsingJavaScript(driver, gainersBtn);
				Action.openInNewTab(trade);
			} else if (i <= 14) {
				Action.clickUsingJavaScript(driver, losersBtn);
				Action.openInNewTab(trade);
			}

			// System.out.println("Clicked on Trade button: " + (i + 1));

			// Optional: Ensure the tab is opened properly before proceeding
			Action.waitForTabsToOpen(i + 2, 10); // +2 to account for the main tab
		}
	}

	// verify TOP table
	public Map<String, List<List<String>>> validateMarketTrendTopTableData() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(4000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, topBtn);
		return Action.getTableData(topTableLocator); // Fetch the entire table data
	}

	// VerifyGainers Table
	public Map<String, List<List<String>>> validateMarketTrendGainerTableData() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(4000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, gainersBtn);
		return Action.getTableData(gainerTableLocator); // Fetch the entire table data
	}

	// verify Losers Table
	public Map<String, List<List<String>>> validateMarketTrendLosersTableData() throws InterruptedException {
		Action.scrollByVisibilityOfElement(marketTrendSection); // Ensure visibility
		Thread.sleep(4000); // Allow time for the table to load
		Action.clickUsingJavaScript(driver, losersBtn);
		return Action.getTableData(loosersTableLocator); // Fetch the entire table data
	}

	public TradePage validateStartTradingNowButtonIsClickable() throws InterruptedException {
		Action.scrollByVisibilityOfElement(losersBtn);
		// Allow time for the table to load
		Action.clickUsingJavaScript(driver, startTradingNowBtn);
		Thread.sleep(4000);
		return new TradePage(driver);
	}

	// verify the presence of rewards program section --
	// user IXFIs rewards program to earn more
	// Earn-Platform -- Use our Rewards Program to win up to $10,000
	public String validateThePresenceOfRewardsProgramSection() {
		Action.scrollByVisibilityOfElement(earn_platform_section);
		return Action.getText(earnPlatformTitle);
	}

	public String validateThePresenceOfRewardsProgramParagraphSections() {
		Action.scrollByVisibilityOfElement(earnPlatformTitle);
		return Action.getText(earnPlatformTitleParagraph);
	}

	public SignUpPage validateThatJoinIXFISectionWillNavigateUserToSignUpPage() throws InterruptedException {
		Action.scrollByVisibilityOfElement(earnPlatformTitleParagraph);
		Action.clickUsingJavaScript(driver, joinIXFIBtn);
		Thread.sleep(4000);
		return new SignUpPage(driver);

	}

	public TaskCenterPage validateThatCompleteTaskSectionWillNavigateUserToRewardsPragramsTaskCenterPage() {
		Action.scrollByVisibilityOfElement(joinIXFIBtn);
		Action.clickUsingJavaScript(driver, completeTask);
		return new TaskCenterPage(driver);
	}

	public RewardsProgramPage validateThatGetFreeCryptoSectionWillNavigateUserToRewardsProgramPage()
			throws InterruptedException {
		Action.scrollByVisibilityOfElement(completeTask);
		Action.clickUsingJavaScript(driver, getFreeCrypto);
		// Action.click(getFreeCrypto);
		Thread.sleep(4000);
		return new RewardsProgramPage(driver);
	}

	// this method will validate the download app functionality present at bottom
	// section of the index page
	public List<String> openBottomDownloadLinkInNewTab() throws InterruptedException {

		// Scroll and click bottom buttons safely
		Thread.sleep(3000);
		Action.scrollByVisibilityOfElement(downloadTheIXFIBottomAppSection);
		Thread.sleep(4000);

		Action.click(bottomAppStoreBtn);
		Action.click(bottomPlayStoreButton);

		return Action.getAllOpenTabs();

	}
	
	//----------------------------------------------IXFI Token Helper methods -----------------------------------------------
	public void navigateToTheIXFITokenCard()
	{
		Action.scrollByVisibilityOfElement(ixfitokenBanner);	
	}
	
	public boolean checkIfIXFITokenBannerIsDisplayed()
	{
		Action.waitForElementToBeVisible(ixfitokenBanner, 10);
		return Action.isDisplayed(driver, ixfitokenBanner);
	}
	
	public boolean checkIfIXFITokenLogoIsDisplayed()
	{
		return Action.isDisplayed(driver, ixfiTokenLogo);
	}
	
	public String getTheIXFITokenTitleText()
	{
		return Action.getText(ixfiTokenText);
	}
	
	public String getTheIXFITokenTitleTextPara()
	{
		return Action.getText(ixfiTokenTextPara);
	}
	
	public String getTheTextOfTokenInfo()
	{
		return Action.getText(tokenInfo);
	}
	
	public IXFITokenPage clickOnIXFITokenLink()
	{
		Action.click(ixfiTokenLink);
		return new IXFITokenPage(driver);
	}
	
	public IXFITokenPage clickOnIXFITokenHeaderMenu()
	{
		Action.click(ixfiTokenHeaderMenu);
		return new IXFITokenPage(driver);
	}
	
	//---------------------------  Footer sections ------------------
	
	public FooterPage navigateToFooterSection()
	{
		Action.scrollByVisibilityOfElement(footerSection);
		return new FooterPage(driver);
	}

}
