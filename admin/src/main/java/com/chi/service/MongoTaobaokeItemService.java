package com.chi.service;

import java.util.List;

import com.chi.po.TaobaokeItemVo;

public interface MongoTaobaokeItemService {
	
	/**
	 * 获取所有的商品
	 * @return
	 */
	public List<TaobaokeItemVo> findAllItems();
	
	/**
	 * 获取某个taobao类别所有的商品
	 * @return
	 */
	public List<TaobaokeItemVo> findItemsByCid(Long cid);

    /**
     * 获取某个77chi类别所有的商品
     * @return
     */
    public List<TaobaokeItemVo> findItemsByItemType(int itemType);
    
    /**
     * 根据numIid获取商品
     * @param numIid
     * @return
     */
    public TaobaokeItemVo getItemByNumIid(Long numIid);
    
}
