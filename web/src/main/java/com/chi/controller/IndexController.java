package com.chi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;

/**
 * 账户信息
 * 
 * @author wangkui
 * 
 */
@Controller(value = "indexController")
public class IndexController extends UserBaseController {

	public static String url = "http://gw.api.taobao.com/router/rest";
	public static String appkey = "21476881";
	public static String secret = "0f93084ac2c9f30df87f9472c6b47619";

	/**
	 * 进入用户信息页面
	 * @throws ApiException 
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException 
	{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		// 查询淘宝客推广商品
		TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
		req.setNick("wk11766988");
		// req.setPid(123456L);
		// req.setKeyword("芒果");
		req.setCid(50013063L);
		req.setStartPrice("1");
		req.setEndPrice("99");
		TaobaokeItemsGetResponse itemsGetResponse = client.execute(req);

		System.out.println(itemsGetResponse.getBody());
		List<TaobaokeItem> listTaobaokeItem = itemsGetResponse.getTaobaokeItems();
		// for (TaobaokeItem taobaokeItem : listTaobaokeItem) {
		//
		// System.out.println(taobaokeItem.getClickUrl());
		// }

		request.setAttribute("listTaobaokeItem", listTaobaokeItem);

		return "index";
	}

}
