package com.chi.dao;

import java.util.List;

import com.chi.po.TaobaokeItemVo;

/**
 * TaobaokeItem Dao 接口
 * 
 * @author wangkui
 * 
 */

public interface TaobaokeItemDao {

    /**
     * 存入淘宝客商品基本信息
     * @param taobaokeItemVo
     * @return
     */
    boolean insertTaobaokeItemVo(TaobaokeItemVo taobaokeItemVo);
    
    /**
     * 所有的商品
     * @return
     */
    List<TaobaokeItemVo> findAllItems();
    
    /**
     * 获取某个类别所有的商品
     * @return
     */
    public List<TaobaokeItemVo> findItemsByCid(Long cid);
    
    /**
     * 所有的商品个数
     * @return
     */
    Long countItems();
    
    /**
     * 根据numIid获取商品
     * @param numIid
     * @return
     */
    public TaobaokeItemVo getItemByNumIid(Long numIid);
    
    /**
     * 获取某个77chi类别所有的商品
     * @return
     */
    public List<TaobaokeItemVo> findItemsByItemType(int itemType);
    
}
