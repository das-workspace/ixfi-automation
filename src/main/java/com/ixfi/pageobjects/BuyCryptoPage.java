package com.ixfi.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class BuyCryptoPage extends BaseClass {

	// WebElements
	@FindBy(xpath = "//h1[@class='ng-star-inserted']")
	WebElement bannerTitleofBuyCrypto;
	
	@FindBy(xpath="//h4[@class='my-lg-3 my-2 crypto-sub-title crypto-title']")
	WebElement availableOfferTitle;

	@FindBy(xpath = "//div[@class='country-img']//img[@alt='India']")
	WebElement defualtIndiaImg;

	@FindBy(xpath = "//strong[normalize-space()='USDT']")
	WebElement defaultUSDTCrypto;
	
	@FindBy(xpath="//div[@id='buy']/div[@id='pills-tabContent']/div[@id='buy-limit']/div[1]/div[2]/div/span/strong")
	WebElement defualtCrypto;

	@FindBy(xpath = "//strong[normalize-space()='INR']")
	WebElement defaultCurrencyINR;
	
	@FindBy(xpath="//div[@id='buy']/div[@id='pills-tabContent']/div[@id='buy-limit']/div[2]/div[2]/div/span/strong")
	WebElement defaultCurrency;

	@FindBy(xpath = "//input[@placeholder='Fiat Value']")
	WebElement fiatValue;

	@FindBy(xpath = "//div[@class='country-sheet']//em[@class='flaticon flaticon-down']")
	WebElement countryDD;

	@FindBy(xpath="//div[@class='search-item mb-3']//input[@placeholder='Search...']")
	WebElement countrySearchField;
	
	@FindBy(xpath = "//div[@class='bottom-sheet show-sheet']//div[@class='modal-header']//em[@class='flaticon-close']")
	WebElement supportedRegionPopupCloseBtn;
	
	
	//-----------------------More Supported Currencies -- WebElement ------------------------------------------------

	@FindBy(xpath="//h5[@class='crypto-sub-title ng-star-inserted']")
	WebElement cryptoSubTitle; //more supported countries section title for selected countries 
	
	@FindBy(xpath="//button[@data-bs-dismiss='modal']//em[@class='flaticon-close']")
	WebElement closePopup;
	
	By moreSupportedCurrencies=By.xpath("//div[@class='currency-item cursor-pointer ng-star-inserted']//div");
	
	By moreSupportedCurrenciesFromPopup=By.xpath("//div[@class='task-center-bg d-flex align-items-center justify-content-start flex-wrap supported-regions-scroll mt-3']//div//div");
	
	@FindBy(xpath="//div[@class='search-item ng-star-inserted']//input[@type='search']")
	WebElement searchCurrency;
	
	@FindBy(xpath="//div[@class='modal-header']//h5[contains(text(), 'More supported currencies for')]")
	WebElement cryptoSubTitleFromPopup;
	
	@FindBy(xpath="//div[normalize-space()='More']")
	WebElement moreButton;
	
	//---------------------------------------Buy Crypto accross different region with IXFI-------------------------------------------------------------------------
	
	
	@FindBy(xpath="//div[@class='d-flex align-items-center']//h4[@class='mb-0']")
	WebElement buyCryptoAccrossDifferentRegion;
	
	@FindBy(xpath="//div[@class='modal-content']")
	WebElement supportedRegionPopup;
	
	@FindBy(xpath="//div[@class='modal-content']//div[@class='modal-header']")
	WebElement supoortedRegionHeader;
	
	@FindBy(xpath="//div[@class='search-item ng-star-inserted']//input[@placeholder='Search...']")
	WebElement searchSupportedRegion;
	
	@FindBy(xpath="//button[@data-bs-dismiss='modal']//em[@class='flaticon-close']")
	WebElement cancelPopupSupportedRegion;
	
	By supportedRegionsList = By.xpath("//div[@class='modal-content']//div[@class='currency-item cursor-pointer ng-star-inserted']//div");
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------
	
//	@FindBy(xpath="//div[@class='country-name']/span[@class='coin_full_name']")
//	WebElement countryShortName;

	By countryShortNameList = By.xpath(".//span[@class='coin_full_name']");
	By countryFullNameList = By.xpath(".//span[@class='coin_short_name']");
	By countryRow = By.xpath("//div[@class='country-name']/span");

	By buyCryptoSectionCountryDDScroller = By
			.xpath("//div[@class='country-sheet']//em[@class='flaticon flaticon-down']");
	
	By availableOffersProvidersName=By.xpath("//div[@class='vendor-list']//img[@class='vendor_logo light-show']");
	
	By vendorPrice=By.xpath("//div[@class='vendor-price']//h3[@class='ng-star-inserted']");
	
	By buyNowButton=By.xpath("//div[@class='vendor-list']//div[2]//button//span[normalize-space()='Buy now']");//it has button present only on odd number
	
	
	
	@FindBy(xpath = "//div[@class='bottom-sheet show-sheet']")
	WebElement countryContainer;
	
	//---------------------------IXFI Token Banner ------------------------------------
	
	@FindBy(xpath="//div[@class='token-banner']")
	WebElement token_banner;
	
	@FindBy(xpath="//div[@class='token-banner']//h3")
	WebElement token_banner_text;
	
	@FindBy(xpath="//div[@class='token-banner']//a")
	WebElement token_banner_link;

	// Constructor
	public BuyCryptoPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public String getBuyCryptoPageTitle() {
		return Action.getTitle(driver);
	}

	public void waitForVisibilityOfBannerTitle() {
		Action.waitForElementToBeVisible(bannerTitleofBuyCrypto, 10);
	}
	
	public String getBannerTitleForSelectedCountry()
	{
		Action.scrollByVisibilityOfElement(bannerTitleofBuyCrypto);
		waitForVisibilityOfBannerTitle();
		return Action.getText(bannerTitleofBuyCrypto);
	}

	public void waitForVisibilityOfAvailableOffers()
	{
		Action.waitForElementToBeVisible(availableOfferTitle, 20);
	}
	
	public void waitForVisibilityOfCryptoSubTitle()
	{
		Action.scrollByVisibilityOfElement(cryptoSubTitle);
		Action.waitForElementToBeVisible(cryptoSubTitle, 20);
	}
	
	public String getCryptoSubTitle()
	{
		waitForVisibilityOfCryptoSubTitle();
		return Action.getText(cryptoSubTitle);
	}
	
	public boolean validateIndianImageInCountryDD() {
		return Action.isDisplayed(driver, defualtIndiaImg);
	}

	public String getDefualtUSDTCrypto() {
		return Action.getText(defaultUSDTCrypto);
	}

	public String getDefaultCurrencyINR() {
		return Action.getText(defaultCurrencyINR);
	}

	public String getDefaultCrypto()
	{
		Action.waitForElementToBeVisible(defualtCrypto, 10);
		return Action.getText(defualtCrypto);
	}
	
	public String getDefaultCurrency()
	{
		Action.waitForElementToBeVisible(defaultCurrency, 10);
		return Action.getText(defaultCurrency);
	}
	
	public String getDefualtFiateValue() {
		Action.waitForElementToBeVisible(fiatValue, 10);
		return Action.getText(fiatValue);
	}

	public void clickOnCountryDD() {
		Action.waitForElementToBeVisible(countryDD, 10);
		Action.click(countryDD);
	}

	public void enterTheCountryInSearchField(String country)
	{
		Action.waitForElementToBeVisible(countrySearchField, 10);
		Action.enterText(countrySearchField, country);
	}
	
//	public void clickOnCountryNameAfterSearch(String countryName)
//	{
//		By ele=By.xpath("//span[normalize-space(text())='" + countryName + "' and @class='coin_short_name']");
//		WebElement element=Action.findElement(driver, ele);
//		Action.click(element);
//
//	}
	public void clickOnCountryNameAfterSearch(String countryName) {
	    // Build the XPath using double quotes around the entire expression
	    By ele = By.xpath("//span[normalize-space(text())=\"" + countryName + "\" and @class='coin_short_name']");
	    WebElement element = Action.findElement(driver, ele);
	    Action.click(element);
	}


	
	public void scrollOnlyBuyCryptoSection() {
		Action.click(countryDD);
		WebElement scroller = Action.miniDriver(driver, buyCryptoSectionCountryDDScroller);
		// return scroller;
		// pass this scroller to scrollByVisibilityOfElement method in action class
		Action.scrollByVisibilityOfElementUsingMiniDriver(scroller, buyCryptoSectionCountryDDScroller);
	}
	
	public void clickOnSupportedRegionCrossButton()
	{
		Action.scrollByVisibilityOfElement(bannerTitleofBuyCrypto);
		Action.waitForElementToBeVisible(supportedRegionPopupCloseBtn, 10);
		Action.click(supportedRegionPopupCloseBtn);
	}
	
//	public Set<String> getAvailableOffersOfProviderForPerticularCountry()
//	{
//		Set<String> providersName=new HashSet<>();
//		List<WebElement> providers=Action.findElements(driver, availableOffersProvidersName);
//		//int prevProviderCount=0;
//		for(WebElement provider:providers)
//		{
//			String name=provider.getAttribute("alt");
//			providersName.add(name);
//		}
//		return providersName;
//	}
	
	public List<WebElement> clickOnBuyNowButtonAndOpenInNewTab() throws InterruptedException
	{
		List<WebElement> buyNowButtons=Action.findElements(driver, buyNowButton);
		
//		for(WebElement ele:buyNowButtons)
//		{
//			Action.scrollByVisibilityOfElement(ele);
//			Action.waitForElementToBeVisible(ele, 20);
//			Action.click(ele);
//		}
		
		return buyNowButtons;
	}
	
	
	public List<String> getAvailableOffersOfProviderForPerticularCountry() {
	    List<String> providersName = new ArrayList<>();  // Use ArrayList to preserve order
	    List<WebElement> providers = Action.findElements(driver, availableOffersProvidersName);
	    
	    for (WebElement provider : providers) {
	    	
	    	
	        String name = provider.getAttribute("alt");  // Capture the 'alt' attribute value
	        providersName.add(name);  // Add to the list (preserves insertion order)
	    	
	    	
	    }
	    
	    return providersName;
	}

	
	
//	
//	public Set<String> getVendorPrice()
//	{
//
//		Set<String> VendorsPrice=new HashSet<>();
//		List<WebElement> prices=Action.findElements(driver, vendorPrice);
//		//int prevProviderCount=0;
//		for(WebElement price:prices)
//		{
//			if(price.getText()==null || price.getText().trim().isEmpty())
//			{
//				continue;
//			}
//			String name=price.getText();
//			VendorsPrice.add(name);
//		}
//		return VendorsPrice;
//	}
	
	public List<String> getVendorPrice() {
	    List<String> vendorsPrice = new ArrayList<>();  // Use ArrayList to preserve the order
	    List<WebElement> prices = Action.findElements(driver, vendorPrice);
	    
	    for (WebElement price : prices) {
	        // Check for null or empty text and skip if the price is empty
	        if (price.getText() == null || price.getText().trim().isEmpty()) {
	            continue;
	        }
	        
	        String priceText = price.getText();  // Capture the price text
	        vendorsPrice.add(priceText);  // Add to the list, preserving the order
	    }
	    
	    return vendorsPrice;
	}


	public Map<String, String> mapVendorWithPrice()
	{
		 List<String> prices=getVendorPrice();
		 List<String> providers=getAvailableOffersOfProviderForPerticularCountry();
		 Map<String, String> providerPriceMap = new HashMap<>();

	        Iterator<String> providerIterator = providers.iterator();
	        Iterator<String> priceIterator = prices.iterator();

	        // Assign prices to providers sequentially
	        while (providerIterator.hasNext()) {
	            String provider = providerIterator.next();
	            if (priceIterator.hasNext()) {
	                // Assign the next available price to the provider
	                providerPriceMap.put(provider, priceIterator.next());
	            } else {
	                // If there are no more prices, assign null
	                providerPriceMap.put(provider, null);
	            }
	        }

//	        // Print the result
//	        for (Map.Entry<String, String> entry : providerPriceMap.entrySet()) {
//	            System.out.println(entry.getKey() + " : " + entry.getValue());
//	        }
		 return providerPriceMap;
	}
	
	
	public Set<String> getListOfFullCountryNameFromDD() throws InterruptedException {
		Set<String> countryNames = new HashSet<>();
		int prevCountryCount = 0;
		while (true) {
			List<WebElement> visibleCountries = Action.findElementsUsingWebElement(countryContainer,
					countryFullNameList);

			for (WebElement country : visibleCountries) {
				countryNames.add(country.getText());
			}
			// check if we reached at the bottom(no element found)
			if (countryNames.size() == prevCountryCount) {
				break;
			}
			prevCountryCount = countryNames.size();

			// scroll to the container using keyboard action
			Action.scrollToElement(driver, visibleCountries);

		}

		return countryNames;
	}

	public Set<String> getListOfShortCountryNameFromDD() throws InterruptedException {
		Set<String> countryNames = new HashSet<>();
		int prevCountryCount = 0;

		while (true) {
			List<WebElement> visibleCountries = Action.findElementsUsingWebElement(countryContainer,
					countryShortNameList);

			for (WebElement country : visibleCountries) {
				countryNames.add(country.getText());
			}
			// check if we reached at the bottom(no element found)
			if (countryNames.size() == prevCountryCount) {
				break;
			}
			prevCountryCount = countryNames.size();

			// scroll to the container using keyboard action
			Action.scrollToElement(driver, visibleCountries);

		}

		return countryNames;
	}

	public Set<String> getCountriesWebElementFromDD() throws InterruptedException {
		Set<String> countryNames = new HashSet<>();
		int prevCountryCount = 0;
		List<WebElement> visibleCountries = new ArrayList<>();

		// Collect all visible countries by scrolling
		while (true) {
			List<WebElement> currentCountries = Action.findElementsUsingWebElement(countryContainer,
					countryFullNameList);

			// Add the countries from this scroll to the set
			for (WebElement country : currentCountries) {
				countryNames.add(country.getText());
			}

			// Check if the size of the countryNames set has not changed (meaning no new
			// countries were found)
			if (countryNames.size() == prevCountryCount) {
				break; // If no new countries, exit the loop
			}

			// Update the previous country count for the next iteration
			prevCountryCount = countryNames.size();

			// Scroll to the next set of countries
			Action.scrollToElement(driver, currentCountries);

			// Store the current visible countries for further actions (selecting later)
			visibleCountries.addAll(currentCountries);
		}
		return countryNames;

	}
	
	public void validateTheDefualtValuesOfCryptoAndCurrencyForEveryCountry() throws InterruptedException
	{
		Set<String> countryNames = new HashSet<>();
		int prevCountryCount = 0;
		List<WebElement> visibleCountries = new ArrayList<>();

		// Collect all visible countries by scrolling
		while (true) {
			List<WebElement> currentCountries = Action.findElementsUsingWebElement(countryContainer,
					countryShortNameList);
			
			// Add the countries from this scroll to the set
			for (WebElement country : currentCountries) {
				countryNames.add(country.getText());
				
			}

			// Check if the size of the countryNames set has not changed (meaning no new
			// countries were found)
			if (countryNames.size() == prevCountryCount) {
				break; // If no new countries, exit the loop
			}

			// Update the previous country count for the next iteration
			prevCountryCount = countryNames.size();

			// Scroll to the next set of countries
			Action.scrollToElement(driver, currentCountries);

			// Store the current visible countries for further actions (selecting later)
			visibleCountries.addAll(currentCountries);
		}
	}
	
	public void scrollToTheMoreSupportedCurrenciesSubtitle()
	{
		Action.scrollByVisibilityOfElement(cryptoSubTitle);
	}
	
	public List<WebElement> getMoreSupportedCurrenciesForParticularSelectedCountry()
	{
		
		List<WebElement> currencies=Action.findElements(driver, moreSupportedCurrencies);
		return currencies;
	}
	
	public void clickOnMoreButton()
	{
		Action.waitForElementToBeVisible(moreButton, 20);
		Action.click(moreButton);
	}
	
	public void scrollToMoreButton()
	{
		Action.scrollByVisibilityOfElement(moreButton);
	}
	
	public boolean isMoreButtonPresent()
	{
		return Action.isDisplayed(driver, moreButton);
	}
	
	public List<WebElement> getMoreSupportedCurrenciesFromPopupForParticularSelectedCountry()
	{
		List<WebElement> currencies=Action.findElements(driver, moreSupportedCurrenciesFromPopup);
		return currencies;
	}
	
	public void enterTheCurrencyInSearchFieldInMoreSupportedCurrencyPopup(String currency)
	{
		Action.waitForElementToBeVisible(searchCurrency, 10);
		Action.click(searchCurrency);
		Action.enterText(searchCurrency, currency);
	}

	public void clickOnCurrencyNameAfterSearch(String currencyName) {
	    // Build the XPath using double quotes around the entire expression
	    By ele = By.xpath("//div[@class='task-center-bg d-flex align-items-center justify-content-start flex-wrap supported-regions-scroll mt-3']//div[contains(text(),'" + currencyName + "')]");
	    WebElement element = Action.findElement(driver, ele);
	    Action.waitForElementToBeVisible(element, 10);
	    Action.click(element);
	}
	
	public String getCryptoSubTitleFromPopup()
	{
		Action.waitForElementToBeVisible(cryptoSubTitleFromPopup, 10);
		return Action.getText(cryptoSubTitleFromPopup);
	}
	
	public void clickOnBuyCryptoAccrossDifferentRegion()
	{
		Action.waitForElementToBeVisible(buyCryptoAccrossDifferentRegion, 20);
		Action.click(buyCryptoAccrossDifferentRegion);
	}
	
	public String getSupportedRegionPopupHeader()
	{
		Action.waitForElementToBeVisible(supportedRegionPopup, 10);
		return Action.getText(supoortedRegionHeader);
	}
	
	public void enterRegionInTheSearchBox(String region)
	{
		Action.waitForElementToBeVisible(searchSupportedRegion, 20);
		Action.enterText(searchSupportedRegion, region);
	}
	
	public List<WebElement> getSupportedRegionListFromPopup()
	{
		
		List<WebElement> supportedRegions=Action.findElementsUsingWebElement(supportedRegionPopup, supportedRegionsList);
		return supportedRegions;
	}
	
	public void clickOnRegionsNameAfterSearch(String region) {
	    // Correct the XPath expression using proper string concatenation
	    By ele = By.xpath("//div[contains(text(), '" + region + "')]");  // Use single quotes around the parameter in XPath
	    WebElement element = Action.findElement(driver, ele);
	    Action.waitForElementToBeVisible(element, 10);
	    Action.click(element);
	}

	
	//-------------------------------------IXFI Token Banner Action methods
	
	public boolean isIXFIBannerDisplayed()
	{
		Action.waitForElementToBeVisible(token_banner, 30);
		return Action.isElementDisplayed(token_banner);
	}
	
	public String getTheTextOfIXFIBannerSection()
	{
		return Action.getText(token_banner_text);
	}
	
	public void clickOnTheIXFIBannerLink()
	{
		Action.click(token_banner_link);
	}
	
	
}
