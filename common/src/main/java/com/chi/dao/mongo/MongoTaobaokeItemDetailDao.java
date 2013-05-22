package com.chi.dao.mongo;

import java.util.List;

import com.chi.po.MongoTaobaokeItemDetailVo;

/**
 * TaobaokeItem Dao 接口
 * 
 * @author wangkui
 * 
 */

public interface MongoTaobaokeItemDetailDao {

    public boolean batchInsertTaobaokeItemDetailVo(List<MongoTaobaokeItemDetailVo> listTaobaokeItemDetailVo);
    
}
