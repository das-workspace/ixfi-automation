package com.ixfi.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.ixfi.actioninterface.ActionInterface;
import com.ixfi.basepage.BaseClass;

public class Action extends BaseClass {

	// this method is used when we want to scroll the elements from particular
	// sections only
	public static void scrollToElement(WebDriver driver, List<WebElement> elements) throws InterruptedException {

		Actions actions = new Actions(driver);
		actions.moveToElement(elements.get(elements.size() - 1)).perform();
		actions.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
		Thread.sleep(500); // Wait for the next batch to load

	}

	// Method to wait for an element to be visible
	public static void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.visibilityOf(element));
	}

	// Method to wait for an element to be clickable
	public static void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
				.until(ExpectedConditions.elementToBeClickable(element));
//		Wait<WebDriver> wait = new FluentWait<>(driver)
//	            .withTimeout(Duration.ofSeconds(timeoutInSeconds))
//	            .pollingEvery(Duration.ofMillis(500))
//	            .ignoring(NoSuchElementException.class);
//
//	        wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Method to click an element with safety checks (scrolling and waiting)
	public static void safeClick(WebElement element, int timeoutInSeconds) {
		Action.scrollByVisibilityOfElement(element); // Scroll to ensure visibility
		waitForElementToBeClickable(element, timeoutInSeconds); // Wait for clickability
		element.click(); // Perform click
	}

	// Method to click an element
	public static void click(WebElement element) {

		waitForElementToBeClickable(element, 15);
		element.click();
	}

	

	public static void clickCheckbox(WebElement checkbox) {
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public static void waitForLoaderToDisappear(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> loaders = driver.findElements(By.className("loader-container"));
		if (!loaders.isEmpty()) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(loaders));
		}
		// Adjust locator as needed
	}

	public static boolean verifyButtonClicked(WebElement button) {
		// Assuming the button's 'disabled' attribute changes or some other property
		// changes when clicked
		waitForElementToBeVisible(button, 6);
		boolean isDisabled = Boolean.parseBoolean(button.getAttribute("disabled"));
		return isDisabled;
		// Assert that the button is disabled, indicating it has been clicked
		// Assert.assertTrue(isDisabled, "Button was not clicked successfully.");
	}

	public static void click(WebDriver driver, WebElement element) {
		new Actions(driver).moveToElement(element).click().perform();

	}

	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementSelected(WebElement element) {
		return isElementDisplayed(element) && element.isSelected();
	}

	public boolean isElementEnabled(WebElement element) {
		return isElementDisplayed(element) && element.isEnabled();
	}

	public static boolean enterText(WebElement element, String text) {
		if (isElementDisplayed(element)) {
			element.clear();
			element.sendKeys(text);
			return true;
		}
		return false;
	}

	public boolean selectBySendKeys(WebElement element, String value) {
		if (isElementDisplayed(element)) {
			element.sendKeys(value);
			return true;
		}
		return false;
	}

	public static boolean selectByIndex(WebElement element, int index) {
		try {
			new Select(element).selectByIndex(index);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean selectByValue(WebElement element, String value) {
		try {
			new Select(element).selectByValue(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean selectByVisibleText(WebElement element, String visibleText) {
		try {
			new Select(element).selectByVisibleText(visibleText);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean mouseHoverUsingJavaScript(WebDriver driver, WebElement element) {
		try {
			String script = "var event = document.createEvent('MouseEvents');"
					+ "event.initMouseEvent('mouseover', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(event);";
			((JavascriptExecutor) driver).executeScript(script, element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean clickUsingJavaScript(WebDriver driver, WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchToFrameByIndex(WebDriver driver, int index) {
		try {
			driver.switchTo().frame(index);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchToFrameById(WebDriver driver, String id) {
		try {
			driver.switchTo().frame(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchToFrameByName(WebDriver driver, String name) {
		try {
			driver.switchTo().frame(name);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void hoverOverElement(WebDriver driver, WebElement element) {
		new Actions(driver).moveToElement(element).perform();
	}

	public boolean dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean dragAndDropByOffset(WebDriver driver, WebElement source, int xOffset, int yOffset) {
		try {
			new Actions(driver).dragAndDropBy(source, xOffset, yOffset).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean rightClick(WebDriver driver, WebElement element) {
		try {
			new Actions(driver).contextClick(element).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean switchToWindowByTitle(WebDriver driver, String title) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(title)) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean switchToNewWindow(WebDriver driver) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			String newWindowHandle = windowHandles.toArray(new String[0])[windowHandles.size() - 1];
			driver.switchTo().window(newWindowHandle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchToOldWindow(WebDriver driver) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			String oldWindowHandle = windowHandles.toArray(new String[0])[0];
			driver.switchTo().window(oldWindowHandle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Switch to the newly opened tab
	public static void switchToNewTab() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
		}
		// Log.info("Switched to new tab.");
	}

	// Close the current tab and switch back to the original one
	public static void closeCurrentTabAndSwitchBack() {
		String originalTab = driver.getWindowHandles().iterator().next();
		driver.close(); // Close the current tab
		driver.switchTo().window(originalTab); // Switch back to the original tab
		// Log.info("Closed the new tab and switched back to the original tab.");
	}

	public int getColumnCount(WebElement row) {
		return row.findElements(By.tagName("td")).size();
	}

	public int getRowCount(WebElement table) {
		return table.findElements(By.tagName("tr")).size() - 1;
	}

	// Print the entire table content
	public static Map<String, List<List<String>>> getTableData(By tableLocator) {
		Map<String, List<List<String>>> tableData = new HashMap<>();

		WebElement table = driver.findElement(tableLocator); // Locate the table

		// Fetch headers from <thead>
		List<List<String>> headers = new ArrayList<>();
		List<WebElement> headerRows = table.findElements(By.xpath(".//thead/tr"));
		for (WebElement headerRow : headerRows) {
			List<String> headerData = new ArrayList<>();
			List<WebElement> headerCells = headerRow.findElements(By.xpath(".//th"));
			for (WebElement cell : headerCells) {
				headerData.add(cell.getText().trim());
			}
			headers.add(headerData);
		}
		tableData.put("headers", headers);

		// Fetch rows from <tbody>
		List<List<String>> bodyRows = new ArrayList<>();
		List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
		for (WebElement row : rows) {
			List<String> rowData = new ArrayList<>();
			List<WebElement> cells = row.findElements(By.xpath(".//td"));
			for (WebElement cell : cells) {
				rowData.add(cell.getText().trim());
			}
			bodyRows.add(rowData);
		}
		tableData.put("bodyRows", bodyRows);

		return tableData;
}

	public static void navigateBack()
	{
		driver.navigate().back();
	}
	
	
	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static boolean acceptAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
			// alert.accept();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			return e.getMessage();
		}
	}

	public boolean launchUrl(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static String getText(WebElement ele) {
		return ele.getText();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static boolean clickElement(WebElement element) {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static WebElement miniDriver(WebDriver driver, By ele) {
		WebElement miniDriver = driver.findElement(ele);
		return miniDriver;
	}

	// this action method is just to scroll into the particular section and not the
	// whole webpage
	public static void scrollByVisibilityOfElementUsingMiniDriver(WebElement miniDriver, By ele) {
		try {
			// Scroll the element into view
			((JavascriptExecutor) miniDriver).executeScript("arguments[0].scrollIntoView(true);", ele);

			// Wait for the element to be clickable and then click
			// element.click();
		} catch (Exception e) {
			System.out.println("Failed to scroll on element due to: " + e.getMessage());
		}
	}

	// this method is to scroll the entire webpage
	public static void scrollByVisibilityOfElement(WebElement ele) {
		
		try {
			// Scroll the element into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

			// Wait for the element to be clickable and then click
			// element.click();
		} catch (Exception e) {
			System.out.println("Failed to scroll on element due to: " + e.getMessage());
		}

	}

	//this method scrolls element using Action class
	public static void scrollToElement(WebDriver driver, WebElement element) {
	    Actions actions = new Actions(driver);
	    actions.moveToElement(element).perform();
	}
	
	public static void scrollToElementHorizontally(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolls horizontally until the element is in view
		js.executeScript("arguments[0].scrollIntoView({block: 'nearest', inline: 'center'});", element);
	}

	public static boolean isDisplayed(WebDriver ldriver, WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void openLinkInNewTab(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('" + url + "','_blank');");
	}

	public static void openInNewTab(WebElement element) throws InterruptedException {
		
		Actions actions = new Actions(driver);
		// Perform Ctrl + Click (or Cmd + Click on Mac) to open in a new tab
		//element.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));//uncomment this line if the below code is not worked
		 actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();

		// Optionally, switch to the new tab if needed
		// switchToNewTab();
	}

	public static List<WebElement> findElements(WebDriver driver, By locator) {
		return driver.findElements(locator);
	}
	
	public static WebElement findElement(WebDriver driver, By locator) {
        try {
            // Try to find and return the element immediately
            WebElement element = driver.findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("No such element found: " + locator);
            return null;  // Return null if the element is not found
        } catch (Exception e) {
            System.out.println("An error occurred while finding the element: " + locator);
            e.printStackTrace();
            return null;  // Return null for any other exception
        }
    }
	

	public static List<WebElement> findElementsUsingWebElement(WebElement ele, By locator) {
		return ele.findElements(locator);
	}

	public static void printElementsText(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
	}

	public boolean isSelected(WebDriver ldriver, WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled(WebDriver ldriver, WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean JSClick(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		// return false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
			return true;
		} catch (Exception e) {
			return false;
			// Assert.fail("Failed to click on element due to: " + e.getMessage());
		}
	}

	public static void waitForTabsToOpen(int expectedTabs, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(d -> d.getWindowHandles().size() >= expectedTabs);
	}

	public static List<String> getAllOpenTabs() {
		return new ArrayList<>(driver.getWindowHandles());
	}

//	public static void switchToTab(int index) {
//		List<String> tabs = getAllOpenTabs();
//		driver.switchTo().window(tabs.get(index));
//	}

	public static void switchToTab(int tabIndex) {
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	public static String waitForTitle(WebDriver driver, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(d -> !d.getTitle().isEmpty() ? d.getTitle() : null);
	}

	public boolean switchToDefaultFrame(WebDriver driver) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void mouseOverElement(WebElement element) {

		try {
			Actions actions = new Actions(driver); // Initialize Actions class with WebDriver
			actions.moveToElement(element).build().perform(); // Move to the specified WebElement
			// System.out.println("Mouse hovered over the element successfully.");
		} catch (Exception e) {
			System.out.println("Failed to hover over the element: " + e.getMessage());
		}

	}

	public static String standardizeText(String text) {
		// Remove extra spaces, normalize line breaks, and trim the text
		return text.replaceAll("\\r\\n|\\r|\\n", "\n").trim();
	}

	public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean Alert(WebDriver driver) {
		// TODO Auto-generated method stub
		return false;
	}

	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	public static void implicitWait(WebDriver driver, int timeOut) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);

	}

	public static void explicitWait(WebDriver driver, WebElement element, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);

	}

//	public static void waitForPageToLoad(WebDriver driver, Duration timeoutInSeconds) {
//        new WebDriverWait(driver, timeoutInSeconds).until((Function<? super WebDriver, V>) wd ->
//            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
//        );
//    }

	public static void acceptCookies(WebElement ele) {
		WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[text()=' Okay, thanks ']"));
		acceptCookiesButton.click();
	}

	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShot\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + dateName + ".png"; // i will return this newImageString whenever I WILL implement jenkins
		return destination;
	}

	public String getCurrentTime() {
		// TODO Auto-generated method stub
		return null;
	}
//
//	public static void scrollByVisibilityOfElement(WebElement ele) {
//		// TODO Auto-generated method stub
//		try {
//	        // Scroll the element into view
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
//	        
//	        // Wait for the element to be clickable and then click
//	       // element.click();
//	    } catch (Exception e) {
//	        System.out.println("Failed to scroll on element due to: " + e.getMessage());
//	    }
//	
//	}

}
