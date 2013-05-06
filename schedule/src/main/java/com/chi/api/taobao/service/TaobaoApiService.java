package com.chi.api.taobao.service;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;

/**
 * 获取淘宝API的Service
 * @author Administrator
 *
 */
public interface TaobaoApiService {

	/**
	 * 查询淘宝客推广商品
	 * @return
	 * @throws ApiException 
	 */
	public List<TaobaokeItem> getTaobaokeItems(Long cid,Long pageNo,Long pageSize) throws ApiException;
	
	/**
	 * 获取商品详细信息
	 * @return
	 * @throws ApiException 
	 */
	public List<TaobaokeItemDetail> getTaobaokeItemDetail(String NumIids) throws ApiException;
	
	/**
	 * 获取商品类目
	 * @return
	 * @throws ApiException 
	 */
	public List<ItemCat> getItemCat(Long parentCid) throws ApiException;
	
}
