package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productImageCount = By.cssSelector("div#content  a.thumbnail"); // By locator of Naveen : "ul.thumbnails img"
	private By productHeader = By.tagName("h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		String productHeaderValue = elementUtil.doElementGetText(productHeader);
		System.out.println("product header value : " + productHeaderValue);
		return productHeaderValue;
	}

	public int productImagesCount() {

		int totalProductImagesCount = elementUtil
				.waitForElemetsVisible(productImageCount, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		return totalProductImagesCount;
	}

	public void enterQuantiity(int qty) {
		System.out.println("Product Quantity : " + qty);
		elementUtil.doSendKeys(quantity, String.valueOf(qty));
		
	}
	
	public String addProductToCart() {
		elementUtil.doClick(addToCartBtn);
		String successMsg = elementUtil.waitForElementVisible(cartSuccessMessage, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
		
		StringBuilder sb = new StringBuilder(successMsg);
		String msg = sb.substring(0,successMsg.length()-1).replace("\n", "").toString();
		System.out.println("Cart success Message is : " + sb );
		return msg;
	}
	
	//Map<String, String>  productInfoMap= new HashMap<String, String>();
	Map<String, String>  productInfoMap= new LinkedHashMap<String, String>();
	//Map<String, String>  productInfoMap= new TreeMap<String, String>();

	public Map<String,String> getProductInfo() {
		// header
		productInfoMap.put("productName", getProductHeaderValue());
		//data calling from method
		getProductMataData();
		getProductPricingData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}

	//metadata:
	private void getProductMataData() {
				
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock	
	List<WebElement> metalist = elementUtil.getElements(productMetaData);
	for(WebElement e:metalist) {	
		String meta = e.getText();
		String[] metaInfo = meta.split(":");
		String key = metaInfo[0].trim();
		String value = metaInfo[1].trim();
		productInfoMap.put(key,value);
		
	}
	}
	//pricing data:
	private void getProductPricingData() {
		// $2,000.00
		// Ex Tax : $2000.00
		List<WebElement> priceList = elementUtil.getElements(productPricingData);
		String price = priceList.get(0).getText().trim();
		String exTax = priceList.get(1).getText();
		String exTaxValue = exTax.split(":")[1].trim();

		productInfoMap.put("productPrice", price);
		productInfoMap.put("exTax", price);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
