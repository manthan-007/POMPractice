package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By searchProductResults = By.cssSelector("div#content div.product-layout");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}

	public int getSearchProductsCount() {
		int productsCount =  elementUtil.waitForElemetsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product count::" + productsCount);
		return productsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		elementUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver); 
	}

}
