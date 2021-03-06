package com.chi.service;

import java.util.List;

import com.chi.po.MongoTaobaokeItemVo;

public interface MongoTaobaokeItemService {
	
	/**
	 * 获取所有的商品
	 * @return
	 */
	public List<MongoTaobaokeItemVo> findAllItems();
	
	/**
	 * 获取某个taobao类别所有的商品
	 * @return
	 */
	public List<MongoTaobaokeItemVo> findItemsByCid(Long cid);

    /**
     * 获取某个77chi类别所有的商品
     * @return
     */
    public List<MongoTaobaokeItemVo> findItemsByItemType(int itemType);
    
    /**
     * 根据numIid获取商品
     * @param numIid
     * @return
     */
    public MongoTaobaokeItemVo getItemByNumIid(Long numIid);
    
    /**
     * 根据numIid删除某个商品
     * @param numIid
     * @return
     */
    public boolean deleteItemByNumIid(Long numIid);
    
}
