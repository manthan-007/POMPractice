package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData(){
		Object[][] data = ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return data;
				
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImageCountTest(String searchKey,String productName,int imageCount) {
		searchPage = accountsPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actProductImagesCount = productInfoPage.productImagesCount();
		Assert.assertEquals(actProductImagesCount, imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accountsPage.performSearch("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		
		softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchPage = accountsPage.performSearch("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantiity(2);
		String actCartMsg = productInfoPage.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actCartMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMsg.indexOf("MacBook Pro")>=0);
		softAssert.assertEquals(actCartMsg, "Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
		
	}

}
