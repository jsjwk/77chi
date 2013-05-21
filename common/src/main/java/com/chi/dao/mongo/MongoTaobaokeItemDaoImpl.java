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
@Repository(value = "mongoTaobaokeItemDao")
public class MongoTaobaokeItemDaoImpl extends BasicDAO<TaobaokeItemVo, Long> implements TaobaokeItemDao {

    protected MongoTaobaokeItemDaoImpl() {
	super(TaobaokeItemVo.class, MongoConstants.CHI_ITEM);
    }

    @Override
    public boolean insertTaobaokeItemVo(TaobaokeItemVo taobaokeItemVo)
    {
	this.save(taobaokeItemVo);
	return true;
    }

    @Override
    public List<TaobaokeItemVo> findAllItems()
    {
	return findList();
    }

    @Override
    public Long countItems()
    {
	Query<TaobaokeItemVo> query = this.createQuery();
	return this.count(query);
    }

	@Override
	public TaobaokeItemVo getItemByNumIid(Long numIid) 
	{
		return this.get(numIid);
	}

	@Override
	public List<TaobaokeItemVo> findItemsByCid(Long cid) 
	{
		Query<TaobaokeItemVo> query = this.createQuery();
		query.filter("cid", cid);
		return this.findList(query);
	}

	@Override
	public List<TaobaokeItemVo> findItemsByItemType(int itemType) 
	{
		Query<TaobaokeItemVo> query = this.createQuery();
		query.filter("itemType", itemType);
		return this.findList(query);
	}

}
