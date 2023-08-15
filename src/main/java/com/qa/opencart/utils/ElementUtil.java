package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight))
			jsUtil.flash(element);
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public WebElement waitForElementVisible(By locator, int timOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public String waitForUrlContainsAndFetch(int timOut, String fractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut));
		wait.until(ExpectedConditions.urlContains(fractionValue));
		return driver.getCurrentUrl();
	}

	public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();

	}

	public String waitForTitleIsContainsAndFetch(int timeOut, String titleValue) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();

	}

	public List<WebElement> waitForElemetsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
		
	}
	
	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}
	
}
