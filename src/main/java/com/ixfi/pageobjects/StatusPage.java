package com.ixfi.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;

public class StatusPage extends BaseClass {

	// WebElements
	@FindBy(xpath = "//span[normalize-space()='System Status']")
	WebElement systemStatusText;

	// ------------All program ---------------------------------

	@FindBy(xpath = "//a[normalize-space(text())='All Program']")
	WebElement allProgramBtn;

	@FindBy(xpath = "//span[normalize-space()='All Program']")
	WebElement allProgramText;

	By tableData = By.xpath("//table[@class='table nowrap table-bordered']/tbody/tr"); // only text will fetch remove label and you will get text with
															// icon -- removed //label

	By secondColumnData = By.xpath("//table//tr//td[2]//div[contains(@class, 'd-flex justify-content-between')]"); // text and icon- both
	
	//By tableRows = By.xpath("//table/tbody/tr");
																											
	// -----------------------------Wallets ----------------------
	@FindBy(xpath = "//a[normalize-space(text())='Wallets']")
	WebElement walletsBtn;

	@FindBy(xpath="//span[normalize-space()='Wallets']")
	WebElement walletText;
	
	@FindBy(xpath="//div[@id='accountSecurity']//input[@id='form1']")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@id='accountSecurity']//button[@type='button']")
	WebElement searchButton;
	
	By walletTableData=By.xpath("//div[@id='v-pills-tabContent']//div[2]//table[@class='table nowrap table-striped table-hover']");
	
	@FindBy(xpath="//div[@id='accountSecurity']")
	WebElement walletPagination;
	
	@FindBy(xpath="//div[@id='accountSecurity']//ul[@class='pagination']//nav//ul//li[@class='small-screen']")
	WebElement walletPageCounts;
	
	// ---------------------------Trade Info ---------------------
	@FindBy(xpath = "//a[normalize-space(text())='Trade Info']")
	WebElement tradeInfoBtn;
	
	@FindBy(xpath="//span[normalize-space()='Trade Info']")
	WebElement tradeInfoHeading;
	
	
	
	By tradeInfoTable=By.xpath("//div[@id='KYCVerification']//div[@class='table-responsive mt-1']");
	
	//----------------------------footer section ------ used for scrolling 
	@FindBy(xpath="//pagination-controls[@id='walletData']//li[@class='pagination-previous disabled ng-star-inserted']")
	WebElement paginationNumbers;

	public StatusPage(WebDriver driver) {
		BaseClass.driver = driver;

		PageFactory.initElements(driver, this);
	}

	
	
	
	// Action Method
	public String getStatusPageTitle() {
		String statusPageTitle = Action.getTitle(driver);
		return statusPageTitle;
	}
	
	public void scrollToThePaginations()
	{
		Action.scrollByVisibilityOfElement(paginationNumbers);
	}
	
	
	public String getSystemStatusText()
	{
		Action.waitForElementToBeVisible(systemStatusText, 10);
		return Action.getText(systemStatusText);
	}
	
	//------------------------------------------------All Programm Section -------------------------------
	
	public void clickOnAllProgramSection()
	{
		Action.waitForElementToBeVisible(allProgramBtn, 10);
		Action.click(allProgramBtn);
	}
	
	public String getAllProgramSectionHeading()
	{
		Action.waitForElementToBeVisible(allProgramText, 10);
		return Action.getText(allProgramText);
	}
	
	public List<WebElement> getAllProgramtableData()
	{
		List<WebElement>data=Action.findElements(driver, tableData);
		return data;
		
	}
	
	public List<WebElement> getAllProgramSecondColumnData()
	{
		List<WebElement>data=Action.findElements(driver, secondColumnData);
		return data;
		
	}
	
//	public List<String> getServices(List<WebElement> rows)
//	{
//		List<String> services = new ArrayList<>();
//		for (int i = 0; i < rows.size(); i++) {
//            WebElement row = rows.get(i);
//            // Get service name (e.g., "Trade")
//            String service = row.findElement(By.xpath(".//td[1]/div/label")).getText();
//            services.add(service);
//		}
//		return services;
//		
//	}
//	
//	public List<WebElement> getServicesStatus(List<WebElement> rows)
//	{
//		List<WebElement> servicesStatus = new ArrayList<>();
//		for (int i = 0; i < rows.size(); i++) {
//            WebElement row = rows.get(i);
//            // Get service name (e.g., "Trade")
//            WebElement status = row.findElement(By.xpath(".//td[1]//i"));
//            servicesStatus.add(status);
//		}
//		return servicesStatus;
//		
//	}
	
	// Generic locator methods for service and sub-service elements
    private By getServiceNameLocator(WebElement row) {
        return By.xpath(".//td[1]/div/label");
    }
    private By getServiceStatusIconLocator(WebElement row) {
        return By.xpath(".//td[1]//i");
    }
    private By getSubServiceNameLocator(WebElement row) {
        return By.xpath(".//td[2]/div");
    }
    private By getSubServiceStatusIconLocator(WebElement row) {
        return By.xpath(".//td[2]//i");
    }
   
    public List<WebElement> getAllRows() {
        return driver.findElements(tableData);
    }
    
    public String getServiceName(WebElement row) {
        return row.findElement(getServiceNameLocator(row)).getText();
    }
    
    public String getServiceStatus(WebElement row) {
        return getStatusFromIcon(row.findElement(getServiceStatusIconLocator(row)));
    }
    
    public String getSubServiceName(WebElement row) {
        return row.findElement(getSubServiceNameLocator(row)).getText();
    }
    
    public String getSubServiceStatus(WebElement row) {
        return getStatusFromIcon(row.findElement(getSubServiceStatusIconLocator(row)));
    }
    
    private String getStatusFromIcon(WebElement iconElement) {
        String iconClass = iconElement.getAttribute("class");
        if (iconClass.contains("success-icon")) {
            return "No issues";
        } else if (iconClass.contains("warning-icon")) {
            return "Degraded Performance";
        } else if (iconClass.contains("error-icon")) {
            return "Outages";
        } else if (iconClass.contains("maintenance-icon")) {
            return "Maintenance";
        } else {
            return "Unknown";
        }
    }
	
	//----------------------------------------------Wallets -------------------------------------
    
    public void clickOnWalletsSection()
    {
    	Action.waitForElementToBeVisible(walletsBtn, 10);
    	Action.click(walletsBtn);
    }
    
    public String getWalletSectionHeadingText()
    {
    	Action.waitForElementToBeVisible(walletText, 10);
    	return Action.getText(walletText);
    
    }
    
    public void searchByWalletCoinOrTokenNameOrFullname(String name)
    {
    	Action.waitForElementToBeVisible(searchBox, 10);
    	Action.enterText(searchBox, name);
        //Add action.click(searchButton) here if needed.. as of now when you enter the text in the search box it will directly provide search result
    }
    
    public void clickOnSearchButton()
    {
    	Action.waitForElementToBeVisible(searchButton, 10);
    	Action.click(searchButton);
    }

    public Map<String, List<List<String>>> validateWalletSectionTableData_servicesByState() throws InterruptedException {
		Action.scrollByVisibilityOfElement(walletsBtn); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		//Action.clickUsingJavaScript(driver, walletsBtn);
		return Action.getTableData(walletTableData); // Fetch the entire table data
	}

    public String getWalletPageCount() throws InterruptedException
    {
    	//Action.scrollByVisibilityOfElement(walletPagination);
    	//Thread.sleep(3000);
    	String counttext=Action.getText(walletPageCounts);
    	//int totalPages = Integer.parseInt(counttext);
    	return counttext;
    	
    }
    
    
    //-----------------------------------------------Trade Info --------------------------------------
    
    public void clickOnTradeInfoSection()
    {
    	Action.waitForElementToBeVisible(tradeInfoBtn, 10);
    	Action.click(tradeInfoBtn);
    }
    
    public String getTradeInfoHeading()
    {
    	Action.waitForElementToBeVisible(tradeInfoHeading, 10);
    	return Action.getText(tradeInfoHeading);
    }
    

    public Map<String, List<List<String>>> validateTradeInfoSectionTableData_servicesByState() throws InterruptedException {
		Action.scrollByVisibilityOfElement(tradeInfoBtn); // Ensure visibility
		Thread.sleep(3000); // Allow time for the table to load
		//Action.clickUsingJavaScript(driver, walletsBtn);
		return Action.getTableData(tradeInfoTable); // Fetch the entire table data
	}
}
