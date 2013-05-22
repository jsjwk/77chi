package com.chi.dao.mysql;

import java.util.List;

import com.chi.po.MongoTaobaokeItemDetailVo;

/**
 * TaobaokeItem Dao 接口
 * 
 * @author wangkui
 * 
 */

public interface TaobaokeItemDetailDao {

    public boolean batchInsertTaobaokeItemDetailVo(List<MongoTaobaokeItemDetailVo> listTaobaokeItemDetailVo);
    
}
