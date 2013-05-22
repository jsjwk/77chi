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
}
