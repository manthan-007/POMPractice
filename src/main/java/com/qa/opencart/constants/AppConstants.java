package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppConstants {

	public static final int DEFAULT_MEDIUM_TIMEOUT = 10;
	public static final int DEFAULT_SHORT_TIMEOUT = 5;
	public static final int DEFAULT_LONG_TIMEOUT = 20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";
	
	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION_VALUE = "route=account/account";
	
	public static final List<String> ACCOUNT_PAGE_HEADERS_LIST= Arrays.asList("My Account","My Orders", "My Affiliate Account", "Newsletter");
	public static final CharSequence USER_REG_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	
	//*********************************Sheet Names************************************************//
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_NAME =  "product";
}
