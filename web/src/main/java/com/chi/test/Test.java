package com.chi.test;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsDetailGetRequest;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsDetailGetResponse;
import com.taobao.api.response.TaobaokeItemsGetResponse;

public class Test {

	static String url = "http://gw.api.taobao.com/router/rest";
	static String appkey = "21476881";
	static String secret = "0f93084ac2c9f30df87f9472c6b47619";
	
//		String url = "http://gw.api.tbsandbox.com/router/rest";
//		String appkey = "test";
//		String secret = "test";
	
	public static void main(String[] args) throws ApiException 
	{
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		//查询淘宝客推广商品
		TaobaokeItemsGetRequest req=new TaobaokeItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
		req.setNick("wk11766988");
		//req.setPid(123456L);
//		req.setKeyword("芒果");
		req.setCid(50013063L);
		req.setStartPrice("1");
		req.setEndPrice("99");
//		req.setAutoSend("true");
//		req.setArea("杭州");
//		req.setStartCredit("1heart");
//		req.setEndCredit("1heart");
//		req.setSort("price_desc");
//		req.setGuarantee("true");
//		req.setStartCommissionRate("1234");
//		req.setEndCommissionRate("2345");
//		req.setStartCommissionNum("1000");
//		req.setEndCommissionNum("10000");
//		req.setStartTotalnum("1");
//		req.setEndTotalnum("10");
//		req.setCashCoupon("true");
//		req.setVipCard("true");
//		req.setOverseasItem("true");
//		req.setSevendaysReturn("true");
//		req.setRealDescribe("true");
//		req.setOnemonthRepair("true");
//		req.setCashOndelivery("true");
//		req.setMallItem("true");
//		req.setPageNo(1L);
//		req.setPageSize(40L);
//		req.setOuterCode("abc");
//		req.setIsMobile(true);
//		req.setReferType(1L);
		TaobaokeItemsGetResponse response = client.execute(req);
		
		System.out.println(response.getBody());
		List<TaobaokeItem> listTaobaokeItem = response.getTaobaokeItems();
		for (TaobaokeItem taobaokeItem : listTaobaokeItem) {
			
			System.out.println(taobaokeItem.toString());
		}
		
	}
	
	public static void main2(String[] args) throws ApiException 
	{
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		TaobaokeItemsDetailGetRequest detailRequest = new TaobaokeItemsDetailGetRequest();
		detailRequest.setFields("num_iid,detail_url");
		detailRequest.setNumIids("16789054211");
		TaobaokeItemsDetailGetResponse detailResponse = client.execute(detailRequest);
		System.out.println(detailResponse.getBody());
	}
	
}
