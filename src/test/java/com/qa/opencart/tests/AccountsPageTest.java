package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	//@Test
	public void accountsPageTitleTest() {
		String accTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}

	//@Test
	public void accountsPageUrlTest() {
		String url = accountsPage.getAccountsPageUrl();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}

	//@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	//@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accountsPage.isSearchExist());
	}

	//@Test
	public void accountsPageHeadersValueTest() {  
		List<String> actualAccPageHeadersList = accountsPage.getAccountsPageHeadersList();
		Assert.assertEquals(actualAccPageHeadersList,AppConstants.ACCOUNT_PAGE_HEADERS_LIST );
	}
	//@Test
	public void accountsPageHeadersCountTest() {  
		List<String> actualAccPageHeadersList = accountsPage.getAccountsPageHeadersList();
		Assert.assertEquals(actualAccPageHeadersList.size(),AppConstants.ACCOUNT_PAGE_HEADERS_LIST.size());
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][]{
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
			{"Manthan"}
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage= accountsPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);	
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][]{
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void selectProductTest(String searchKey,String productName) {
		searchPage= accountsPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}	
	}
}
