package com.chi.dao.mongo;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;

/**
 * TaobaokeItemDao的Mongo实现
 * @author Administrator
 *
 */
@Repository(value = "taobaokeItemDao")
public class TaobaokeItemMongoDaoImpl extends BasicDAO<TaobaokeItemVo,String> implements TaobaokeItemDao {

	protected TaobaokeItemMongoDaoImpl() {
		super(TaobaokeItemVo.class, MongoConstants.CHI_ITEM);
	}

	@Override
	public boolean insertTaobaokeItemVo(TaobaokeItemVo taobaokeItemVo) 
	{
		Long numIid = taobaokeItemVo.getTaobaokeItem().getNumIid();
		//taobaokeItemVo.setNumIid(UUID.randomUUID().getLeastSignificantBits());
		taobaokeItemVo.setNumIid(100000L);
		save(taobaokeItemVo);
		return true;
	}

}
