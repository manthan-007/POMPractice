package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By logout = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button"); 

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = elementUtil.waitForTitleIsContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page title is : " + title);
		return title;

	}

	public String getAccountsPageUrl() {
		String url = elementUtil.waitForUrlContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Accounts Page Url is : " + url);
		return url;

	}

	public boolean isLogoutLinkExist() {
		return elementUtil.waitForElementVisible(logout, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}

	public boolean isSearchExist() {
		return elementUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}

	public List<String> getAccountsPageHeadersList() {
		List<WebElement> accHeadersList = elementUtil.waitForElemetsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<String> valueList = new ArrayList<String>();
		for (WebElement element : accHeadersList) { 
			String headernName = element.getText();
			valueList.add(headernName);
		}
		return valueList;
	}
	
	public SearchPage performSearch(String searchProduct) {
		if(isSearchExist()) {
			elementUtil.doSendKeys(search, searchProduct);
			elementUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search box does not exist: .......");
			return null;
		}
	}

}
