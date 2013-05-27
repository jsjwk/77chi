package com.chi.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.chi.po.MongoTaobaokeItemVo;
import com.google.code.morphia.query.Query;

/**
 * TaobaokeItemDao的Mongo实现
 * 
 * @author Administrator
 * 
 */
@Repository(value = "mongoTaobaokeItemDao")
public class MongoTaobaokeItemDaoImpl extends BasicDAO<MongoTaobaokeItemVo, Long> implements MongoTaobaokeItemDao {

    protected MongoTaobaokeItemDaoImpl() {
	super(MongoTaobaokeItemVo.class, MongoConstants.CHI_ITEM);
    }

    @Override
    public boolean insertTaobaokeItemVo(MongoTaobaokeItemVo mongoTaobaokeItemVo)
    {
	this.save(mongoTaobaokeItemVo);
	return true;
    }

    @Override
    public List<MongoTaobaokeItemVo> findAllItems()
    {
	return findList();
    }

    @Override
    public Long countItems()
    {
	Query<MongoTaobaokeItemVo> query = this.createQuery();
	return this.count(query);
    }

    @Override
    public MongoTaobaokeItemVo getItemByNumIid(Long numIid)
    {
	return this.get(numIid);
    }

    @Override
    public List<MongoTaobaokeItemVo> findItemsByCid(Long cid)
    {
	Query<MongoTaobaokeItemVo> query = this.createQuery();
	query.filter("cid", cid);
	return this.findList(query);
    }

    @Override
    public List<MongoTaobaokeItemVo> findItemsByItemType(int itemType)
    {
	Query<MongoTaobaokeItemVo> query = this.createQuery();
	query.filter("itemType", itemType);
	return this.findList(query);
    }

    @Override
    public boolean deleteItemByNumIid(Long numIid)
    {
	this.deleteById(numIid);
	return true;
    }

    @Override
    public boolean batchInsertTaobaokeItemVo(List<MongoTaobaokeItemVo> listMongoTaobaokeItemVo)
    {
	if(listMongoTaobaokeItemVo==null || listMongoTaobaokeItemVo.size()<=0) return false;
	for (MongoTaobaokeItemVo mongoTaobaokeItemVo : listMongoTaobaokeItemVo)
	{
	    this.save(mongoTaobaokeItemVo);
	}
	return true;
    }

}
