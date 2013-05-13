package com.chi.dao;

import java.util.List;

import com.chi.po.TaobaokeItemDetailVo;

/**
 * TaobaokeItem Dao 接口
 * 
 * @author wangkui
 * 
 */

public interface TaobaokeItemDetailDao {

    public boolean batchInsertTaobaokeItemDetailVo(List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo);
    
}
