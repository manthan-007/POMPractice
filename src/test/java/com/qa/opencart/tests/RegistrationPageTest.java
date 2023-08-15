package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;



public class RegistrationPageTest extends BaseTest{
	
	
	@BeforeClass
	public void setUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmail() {
		return  "automation" + System.currentTimeMillis() + "@gmail.com";
	}
	
	
	@DataProvider(name = "regExcelData")
	public Object[][] getRegisterTestData() {
		Object[][] regData = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	@Test(dataProvider = "regExcelData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password,String subscribe) {
		Assert.assertTrue(registerPage.registerUser(firstName,lastName, getRandomEmail(), telephone,password,subscribe));
	}
	
}
