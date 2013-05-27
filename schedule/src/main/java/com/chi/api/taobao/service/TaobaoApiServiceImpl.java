package com.chi.api.taobao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chi.constant.ConstatVar;
import com.chi.po.TaobaokeItemPo;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.request.TaobaokeItemsDetailGetRequest;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.TaobaokeItemsDetailGetResponse;
import com.taobao.api.response.TaobaokeItemsGetResponse;

@Service(value = "taobaoApiService")
public class TaobaoApiServiceImpl implements TaobaoApiService {

    private static final Logger LOG = LoggerFactory.getLogger(TaobaoApiServiceImpl.class);

    @Override
    public TaobaokeItemPo getTaobaokeItems(Long cid, Long pageNo, Long pageSize) throws ApiException
    {
	LOG.info("ConstatVar.TAOBAO_API_URL:" + ConstatVar.TAOBAO_API_URL);
	int apiExceptionTryNum = 0;

	TaobaoClient client = new DefaultTaobaoClient(ConstatVar.TAOBAO_API_URL, ConstatVar.TAOBAO_API_APPKEY, ConstatVar.TAOBAO_API_SECRET);
	// 查询淘宝客推广商品
	TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
	req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
	req.setNick("wk11766988");
	// req.setPid(123456L);
	// req.setKeyword("芒果");
	// req.setCid(50013063L);
	req.setCid(cid);
	// req.setStartPrice("1");
	// req.setEndPrice("99");
	// req.setAutoSend("true");
	// req.setArea("杭州");
	req.setStartCredit("1heart");
	// req.setEndCredit("1heart");
	req.setSort("commissionNum_desc");
	// req.setGuarantee("true");
	// req.setStartCommissionRate("1234");
	// req.setEndCommissionRate("2345");
	// req.setStartCommissionNum("1000");
	// req.setEndCommissionNum("10000");
	// req.setStartTotalnum("1");
	// req.setEndTotalnum("10");
	// req.setCashCoupon("true");
	// req.setVipCard("true");
	// req.setOverseasItem("true");
	// req.setSevendaysReturn("true");
	// req.setRealDescribe("true");
	// req.setOnemonthRepair("true");
	// req.setCashOndelivery("true");
	// req.setMallItem("true");
	req.setPageNo(pageNo);
	req.setPageSize(pageSize);
	// req.setOuterCode("abc");
	// req.setIsMobile(true);
	// req.setReferType(1L);
	TaobaokeItemsGetResponse response = null;
	try
	{
	    response = client.execute(req);
	} catch (ApiException e)
	{
	    e.printStackTrace();
	    if(apiExceptionTryNum<5)
	    {//重试次数不超过5次
		LOG.info("ApiException[重试一次]");
		try
		{
		    Thread.sleep(1000);
		} catch (InterruptedException e1)
		{
		    e1.printStackTrace();
		}
		// 重试一次
		apiExceptionTryNum++;
		getTaobaokeItems(cid, pageNo, pageSize);
	    }
	}

	Long totalResults = response.getTotalResults();
	List<TaobaokeItem> listTaobaokeItem = response.getTaobaokeItems();
	TaobaokeItemPo po = new TaobaokeItemPo(totalResults, listTaobaokeItem);
	return po;
    }

    @Override
    public List<TaobaokeItemDetail> getTaobaokeItemDetail(String NumIids) throws ApiException
    {
	LOG.info("ConstatVar.TAOBAO_API_URL:" + ConstatVar.TAOBAO_API_URL);
	int apiExceptionTryNum = 0;
	
	TaobaoClient client = new DefaultTaobaoClient(ConstatVar.TAOBAO_API_URL, ConstatVar.TAOBAO_API_APPKEY, ConstatVar.TAOBAO_API_SECRET);
	TaobaokeItemsDetailGetRequest detailRequest = new TaobaokeItemsDetailGetRequest();
	detailRequest.setFields("num_iid,cid,detail_url,title,type,desc,props_name,created,auction_point,volume,is_xinpin,food_security,locality_life,item_weight,item_size,num,valid_thru,location,list_time,delist_time,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,modified,approve_status,item_imgs,score,is_taobao,violation");
	// detailRequest.setFields("num_iid,detail_url,skus");
	// 16789054211
	detailRequest.setNumIids(NumIids);
	TaobaokeItemsDetailGetResponse detailResponse = null;
	try
	{
	    detailResponse = client.execute(detailRequest);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    if(apiExceptionTryNum<5)
	    {//重试次数不超过5次
		LOG.info("ApiException[重试一次]");
		try
		{
		    Thread.sleep(1000);
		} catch (InterruptedException e1)
		{
		    e1.printStackTrace();
		}
		// 重试一次
		apiExceptionTryNum++;
		getTaobaokeItemDetail(NumIids);
	    }
	}
	System.out.println(detailResponse.getBody());
	List<TaobaokeItemDetail> listTaobaokeItemDetail = detailResponse.getTaobaokeItemDetails();

	return listTaobaokeItemDetail;
    }

    @Override
    public List<ItemCat> getItemCat(Long parentCid) throws ApiException
    {
	TaobaoClient client = new DefaultTaobaoClient(ConstatVar.TAOBAO_API_URL, ConstatVar.TAOBAO_API_APPKEY, ConstatVar.TAOBAO_API_SECRET);
	ItemcatsGetRequest req = new ItemcatsGetRequest();
	req.setFields("cid,parent_cid,name,is_parent");
	// 设置父类目为0，则获取的是跟目录下面的类目
	req.setParentCid(parentCid);
	ItemcatsGetResponse response = null;
	try
	{
	    response = client.execute(req);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    LOG.info("ApiException[重试一次]");
	    // 重试一次
	    response = client.execute(req);
	}
	System.out.println(response.getBody());
	List<ItemCat> listItemCat = response.getItemCats();

	return listItemCat;
    }

}
