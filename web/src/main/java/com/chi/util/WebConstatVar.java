package com.chi.util;

public class WebConstatVar {

    	private static boolean platform = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    	
	public static String MESSAGE = "message";
	
	public static String PRODUCT_URL = "http://gw.api.taobao.com/router/rest";
	public static String PRODUCT_APPKEY = "21476881";
	public static String PRODUCT_SECRET = "0f93084ac2c9f30df87f9472c6b47619";
	
	public static String TEST_URL = "http://gw.api.tbsandbox.com/router/rest";
	public static String TEST_APPKEY = "test";
	public static String TEST_SECRET = "test";
	
	public static String URL = platform ? TEST_URL:PRODUCT_URL;
	public static String APPKEY = platform ? TEST_APPKEY:PRODUCT_APPKEY;
	public static String SECRET = platform ? TEST_SECRET:PRODUCT_SECRET;
	
}
