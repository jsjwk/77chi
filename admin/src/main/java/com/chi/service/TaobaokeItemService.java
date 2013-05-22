package com.chi.service;

import java.util.List;

import com.chi.po.TaobaokeItemVo;


public interface TaobaokeItemService {

    /**
     * 保存一个TaobaokeItemVo
     * @param mongoTaobaokeItemVo
     * @return
     */
    public boolean save(TaobaokeItemVo taobaokeItemVo);
    
    /**
     * 根据类型获取商品
     * @param itemType
     * @return
     */
    public List<TaobaokeItemVo> listTaobaokeItemVoByType(int itemType);
    
    /**
     * 根据numIid删除某个商品
     * @param numIid
     * @return
     */
    public boolean deleteItemByNumIid(Long numIid);
    
    /**
     * 根据numIid获取商品
     * @param numIid
     * @return
     */
    public TaobaokeItemVo getItemByNumIid(Long numIid);
    
}
