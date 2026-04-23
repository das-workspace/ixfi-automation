package com.ixfi.testcases;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ixfi.actiondriver.Action;
import com.ixfi.basepage.BaseClass;
import com.ixfi.pageobjects.FooterPage;
import com.ixfi.pageobjects.IndexPage;
import com.ixfi.pageobjects.StatusPage;
import com.ixfi.utility.Log;

public class StatusPageTest extends BaseClass {

	IndexPage index;
	SoftAssert softAssert;
	FooterPage footer;
	StatusPage status;

	@BeforeMethod
	public void setUpTest() {
		index = new IndexPage(driver); // Initialize IndexPage with driver after setup
		softAssert = new SoftAssert();
		// login=new LoginPage(driver);
	}

	@Test
	public void verifyThatUserIsAbleToClickOnSystemStatusLinkAndNavigatesToTheStatusPage() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnSystemStatusLinkAndNavigatesToTheStatusPageTest");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		String text = status.getSystemStatusText();
		Log.info("System status text: " + text);
		Assert.assertEquals(text, "System Status",
				"Text are not matched, user is not navigated to the status page, please check again");
		String expectedTitle = prop.getProperty("statusPageTitle");
		Log.info("Expected Title: " + expectedTitle);
		String actualTitle = status.getStatusPageTitle();
		Log.info("ActualTitle: " + actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Titles are not matched, please check again");
		Log.endTestCase("verifyThatUserIsAbleToClickOnSystemStatusLinkAndNavigatesToTheStatusPageTest");
	}

	@Test
	public void verifyThatUserIsAbleToClickOnAllProgramSection() throws InterruptedException {
		Log.startTestCase("verifyThatUserIsAbleToClickOnAllProgramSectionTest");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		Thread.sleep(1000);
		status.clickOnAllProgramSection();
		Log.info("Clicked on All Programm section");
		String heading = status.getAllProgramSectionHeading();
		Log.info("Heading: " + heading);
		Assert.assertEquals(heading, "All Program", "Heading text are not matched, please check again..");

		Log.endTestCase("verifyThatUserIsAbleToClickOnAllProgramSectionTest");
	}

	@Test
	public void verifyTheAllProgrammSectionTableData_CurrentStateByServices() throws InterruptedException {
		Log.startTestCase("verifyTheAllProgrammSectionTableData_CurrentStateByServicesTest");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		Thread.sleep(1000);
		status.clickOnAllProgramSection();
		Log.info("Clicked on All Programm section");
		String[][] expectedData = { { "Trade", "No issues", "Convert", "No issues" },
				{ "Wallet", "No issues", "Buy Crypto", "No issues" },
				{ "Reward", "No issues", "Research", "No issues" } };

		// Fetch all rows
		List<WebElement> rows = status.getAllRows();
		Log.info("All Rows:"+rows.size());
		Assert.assertEquals(rows.size(), expectedData.length, "Number of rows does not match!");
		// Iterate through rows and validate
		for (int i = 0; i < rows.size(); i++) {
			Thread.sleep(1000);
			WebElement row = rows.get(i);
			// Get data from the row
			String serviceName = status.getServiceName(row);
			Log.info("Service Name: "+serviceName);
			String serviceStatus = status.getServiceStatus(row);
			Log.info("Service status: "+serviceStatus);
			String subServiceName = status.getSubServiceName(row);
			Log.info("SubService Name"+subServiceName);
			String subServiceStatus = status.getSubServiceStatus(row);
			Log.info("SubService status: "+subServiceStatus);
			// Assert the values
			Assert.assertEquals(serviceName, expectedData[i][0], "Service name mismatch at row " + (i + 1));
			Assert.assertEquals(serviceStatus, expectedData[i][1], "Service status mismatch at row " + (i + 1));
			Assert.assertEquals(subServiceName, expectedData[i][2], "Sub-service name mismatch at row " + (i + 1));
			Assert.assertEquals(subServiceStatus, expectedData[i][3], "Sub-service status mismatch at row " + (i + 1));
		}

		Log.endTestCase("verifyTheAllProgrammSectionTableData_CurrentStateByServicesTest");
	}
	
	@Test
	public void validateWalletSectionTableData_CurrentStateByServices() throws InterruptedException
	{
		Log.startTestCase("validateWalletSectionTableData_CurrentStateByServicesTest");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		status.clickOnWalletsSection();
		Log.info("Clicked on wallet Section");
		Thread.sleep(1000);
		Map<String, List<List<String>>> tableData=status.validateWalletSectionTableData_servicesByState();
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
			Log.info("Row " + (i + 3) + ": " + row);
			softAssert.assertFalse(row.isEmpty(), "Row " + (i + 3) + " should not be empty");

			// Validate the 24h change value (assuming it's at index 2)
			String changeValue = row.get(4).trim(); // Get the 24h CHG value
			Log.info("Checking if the attribute value is success" + changeValue);
			//add validation here
			//softAssert.assertTrue(changeValue.startsWith("-"),"Row " + (i + 1) + ": 24h CHG value should be negative. Found: " + changeValue);
		}
		
		
		  // Collect all the soft assertion failures (if any)
        softAssert.assertAll();  // This will report all failures if any

		Log.endTestCase("validateWalletSectionTableData_CurrentStateByServicesTest");
	}
	
	@Test
	public void checkTotalCountOfWalletPages() throws InterruptedException
	{
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		status.clickOnWalletsSection();
		Log.info("Clicked on wallet Section");
		status.scrollToThePaginations();
		Thread.sleep(3000);
		String pages=status.getWalletPageCount();
		Log.info("Count: "+pages);
		Thread.sleep(3000);
		
	}
	
	
	//--------------------------------------Trade Info ----------------------------------------------
	
	@Test
	public void verifyThatUserIsAbleToNavigateToTheTradeInfoSection() throws InterruptedException
	{
		Log.startTestCase("verifyThatUserIsAbleToNavigateToTheTradeInfoSection");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		Thread.sleep(1000);
		status.clickOnTradeInfoSection();
		Log.info("Clicked on Trade Info Section");
		String heading=status.getTradeInfoHeading();
		Log.info("Trade section Heading: "+heading);
		Assert.assertEquals(heading, "Trade Info","Trade Info Headings are not matched, please check again");
		
		Log.endTestCase("verifyThatUserIsAbleToNavigateToTheTradeInfoSection");
	}
	
	//@Test
	public void validateTradeInfoSectionTableData_CurrentStateByServices() throws InterruptedException
	{
		Log.startTestCase("validateTradeInfoSectionTableData_CurrentStateByServicesTest");
		footer = index.navigateToFooterSection();
		Log.info("Scrolled to the footer section");
		footer.scrollToTheFooterSection();
		Action.implicitWait(driver, 10);
		Thread.sleep(3000);
		status = footer.clickOnSystemStatusButton();
		Log.info("Clicked on system status link present under support section");
		status.clickOnTradeInfoSection();
		Log.info("Clicked on Trade Info Section");
		Thread.sleep(1000);
		Map<String, List<List<String>>> tableData=status.validateTradeInfoSectionTableData_servicesByState();
		Log.info("Trade info Table Data: " + tableData);
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
			Log.info("Row " + (i + 3) + ": " + row);
			softAssert.assertFalse(row.isEmpty(), "Row " + (i + 3) + " should not be empty");

			// Validate the 24h change value (assuming it's at index 2)
			String changeValue = row.get(4).trim(); // Get the 24h CHG value
			Log.info("Checking if the attribute value is success" + changeValue);
			//add validation here
			//softAssert.assertTrue(changeValue.startsWith("-"),"Row " + (i + 1) + ": 24h CHG value should be negative. Found: " + changeValue);
		}
		
		
		  // Collect all the soft assertion failures (if any)
        softAssert.assertAll();  // This will report all failures if any

		Log.endTestCase("validateTradeInfoSectionTableData_CurrentStateByServicesTest");
	}
	
	@Test
	public void validateTradeInfoSectionTableData_CurrentStateByServicesUpdated() throws InterruptedException {
	    Log.startTestCase("validateTradeInfoSectionTableData_CurrentStateByServicesTest");
	    footer = index.navigateToFooterSection();
	    Log.info("Scrolled to the footer section");
	    footer.scrollToTheFooterSection();
	    Action.implicitWait(driver, 10);
	    Thread.sleep(3000);
	    
	    status = footer.clickOnSystemStatusButton();
	    Log.info("Clicked on system status link present under support section");
	    
	    status.clickOnTradeInfoSection();
	    Log.info("Clicked on Trade Info Section");
	    
	    Thread.sleep(1000);
	    
	    Map<String, List<List<String>>> tableData = status.validateTradeInfoSectionTableData_servicesByState();
	    Log.info("Trade info Table Data: " + tableData);
	    
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

	        // Check if the row has enough columns
	        if (row.size() > 1) {
	            // In this case, we're assuming you're checking the second column (index 1) for the "Status" value
	            String statusValue = row.get(1).trim();
	            Log.info("Checking Status value for Row " + (i + 1) + ": " + statusValue);
	            // Add your validation logic here for the Status value
	        } else {
	            Log.warn("Row " + (i + 1) + " does not have enough columns. Skipping 24h change validation.");
	        }
	    }
	    
	    // Collect all the soft assertion failures (if any)
	    softAssert.assertAll();  // This will report all failures if any

	    Log.endTestCase("validateTradeInfoSectionTableData_CurrentStateByServicesTest");
	}

	
	

}
