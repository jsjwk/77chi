package com.chi.dao.mongo;

import java.util.List;

import com.chi.po.MongoTaobaokeItemVo;

/**
 * TaobaokeItem Dao 接口
 * 
 * @author wangkui
 * 
 */

public interface MongoTaobaokeItemDao {
    
    /**
     * 保存淘宝客商品基本信息
     * @param mongoTaobaokeItemVo
     * @return
     */
    boolean insertTaobaokeItemVo(MongoTaobaokeItemVo mongoTaobaokeItemVo);

    /**
     * 批量保存淘宝客商品基本信息
     * @param mongoTaobaokeItemVo
     * @return
     */
    boolean batchInsertTaobaokeItemVo(List<MongoTaobaokeItemVo> listMongoTaobaokeItemVo);
    
    /**
     * 所有的商品
     * @return
     */
    List<MongoTaobaokeItemVo> findAllItems();
    
    /**
     * 获取某个类别所有的商品
     * @return
     */
    public List<MongoTaobaokeItemVo> findItemsByCid(Long cid);
    
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
    public MongoTaobaokeItemVo getItemByNumIid(Long numIid);
    
    /**
     * 获取某个77chi类别所有的商品
     * @return
     */
    public List<MongoTaobaokeItemVo> findItemsByItemType(int itemType);
    
    /**
     * 根据numIid删除某个商品
     * @param numIid
     * @return
     */
    public boolean deleteItemByNumIid(Long numIid);
    
}
