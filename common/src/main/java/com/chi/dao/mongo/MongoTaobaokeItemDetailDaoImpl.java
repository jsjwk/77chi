package com.chi.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.chi.po.MongoTaobaokeItemDetailVo;

/**
 * TaobaokeItemDao的Mongo实现
 * 
 * @author Administrator
 * 
 */
@Repository(value = "mongoTaobaokeItemDetailDao")
public class MongoTaobaokeItemDetailDaoImpl extends BasicDAO<MongoTaobaokeItemDetailVo, String> implements MongoTaobaokeItemDetailDao {

    protected MongoTaobaokeItemDetailDaoImpl() {
	super(MongoTaobaokeItemDetailVo.class, MongoConstants.CHI_ITEM);
    }

    @Override
    public boolean batchInsertTaobaokeItemDetailVo(List<MongoTaobaokeItemDetailVo> listTaobaokeItemDetailVo)
    {
	/*
	 * 
	List<DBObject> listBasicDBObject = new ArrayList<DBObject>();
	for (TaobaokeItemDetailVo detailVo : listTaobaokeItemDetailVo)
	{
	    Long numIid = detailVo.getTaobaokeItemDetail().getItem().getNumIid();
	    String jsonStr = null;
	    try
	    {
		jsonStr = JsonUtils.getJSONString(detailVo);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }
	    BasicDBObject object = new BasicDBObject();
	    object.put("numIid", numIid);
	    object.put("taobaokeItemVo", jsonStr);

	    listBasicDBObject.add(object);
	}

	DBCollection collection = MongoDBUtil.getCollection("item_detail");
	WriteResult wr = collection.insert(listBasicDBObject);
	 */
	
	for (MongoTaobaokeItemDetailVo detailVo : listTaobaokeItemDetailVo)
	{
	    //save支持主键冲突自动更新
	    ds.save(detailVo);
	}
	
	//insert是可以传入批量的，但是不支持主键冲突更新
	//ds.insert(listTaobaokeItemDetailVo);
	return true;
    }

}
