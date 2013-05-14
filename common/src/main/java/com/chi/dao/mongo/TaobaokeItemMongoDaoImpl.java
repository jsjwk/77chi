package com.chi.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;
import com.google.code.morphia.query.Query;

/**
 * TaobaokeItemDao的Mongo实现
 * 
 * @author Administrator
 * 
 */
@Repository(value = "taobaokeItemDao")
public class TaobaokeItemMongoDaoImpl extends BasicDAO<TaobaokeItemVo, String> implements TaobaokeItemDao {

    protected TaobaokeItemMongoDaoImpl() {
	super(TaobaokeItemVo.class, MongoConstants.CHI_ITEM);
    }

    @Override
    public boolean insertTaobaokeItemVo(TaobaokeItemVo taobaokeItemVo)
    {
	// taobaokeItemVo.setNumIid(UUID.randomUUID().getLeastSignificantBits());
	this.save(taobaokeItemVo);
	return true;
    }

    @Override
    public List<TaobaokeItemVo> findAllItems()
    {
	System.out.println(this.find());
	System.out.println(this.findIds());
	System.out.println(this.findList());
	Query<TaobaokeItemVo> query = this.createQuery();
	System.out.println(this.count(query));
	return findList();
    }

}
