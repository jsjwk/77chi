package com.chi.constant;

public class ConstatVar {

//	private static boolean platform = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
	//Test
    	private static boolean platform = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1;
    	
    	//消息常量
	public static String MESSAGE = "message";
	
	public static String PRODUCT_URL = "http://gw.api.taobao.com/router/rest";
	public static String PRODUCT_APPKEY = "21476881";
	public static String PRODUCT_SECRET = "0f93084ac2c9f30df87f9472c6b47619";
	
	public static String TEST_URL = "http://gw.api.tbsandbox.com/router/rest";
	public static String TEST_APPKEY = "test";
	public static String TEST_SECRET = "test";
	
	//淘宝API的URL、APPKEY、SECRET
	public static String TAOBAO_API_URL = platform ? TEST_URL:PRODUCT_URL;
	public static String TAOBAO_API_APPKEY = platform ? TEST_APPKEY:PRODUCT_APPKEY;
	public static String TAOBAO_API_SECRET = platform ? TEST_SECRET:PRODUCT_SECRET;
	
	//新浪微博的APPKEY、SECRET
	public static String WEIBO_APPKEY = "3461084792";
	public static String WEIBO_SECRET = "ad539de0f34104552728dffbebd8fb10";
	
}
