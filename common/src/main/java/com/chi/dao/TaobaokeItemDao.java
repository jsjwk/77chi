package com.chi.dao;

import java.util.List;

import com.chi.po.TaobaokeItemDetailVo;
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
     * 存入淘宝客商品详细信息
     * @param taobaokeItemDetailVo
     * @return
     */
    boolean batchInsertTaobaokeItemDetailVo(List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo);

}
