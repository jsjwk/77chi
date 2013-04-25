package com.test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;

public class Test {

	public static void main(String[] args) throws ApiException 
	{
//		String url = "http://gw.api.taobao.com/router/rest";
//		String appkey = "21476881";
//		String secret = "0f93084ac2c9f30df87f9472c6b47619";

		String url = "http://gw.api.tbsandbox.com/router/rest";
		String appkey = "test";
		String secret = "test";
		
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		TaobaokeItemsGetRequest req=new TaobaokeItemsGetRequest();
		req.setFields("num_iid");
		req.setNick("jay");
		req.setPid(123456L);
		req.setKeyword("abc");
		req.setCid(123L);
		req.setStartPrice("1");
		req.setEndPrice("999");
		req.setAutoSend("true");
		req.setArea("杭州");
		req.setStartCredit("1heart");
		req.setEndCredit("1heart");
		req.setSort("price_desc");
		req.setGuarantee("true");
		req.setStartCommissionRate("1234");
		req.setEndCommissionRate("2345");
		req.setStartCommissionNum("1000");
		req.setEndCommissionNum("10000");
		req.setStartTotalnum("1");
		req.setEndTotalnum("10");
		req.setCashCoupon("true");
		req.setVipCard("true");
		req.setOverseasItem("true");
		req.setSevendaysReturn("true");
		req.setRealDescribe("true");
		req.setOnemonthRepair("true");
		req.setCashOndelivery("true");
		req.setMallItem("true");
		req.setPageNo(1L);
		req.setPageSize(40L);
		req.setOuterCode("abc");
		req.setIsMobile(true);
		req.setReferType(1L);
		TaobaokeItemsGetResponse response = client.execute(req);
		
		System.out.println(response);
		System.out.println(response.getBody());
		
	}
	
}
