package com.chi.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.chi.dao.TaobaokeItemDetailDao;
import com.chi.po.TaobaokeItemDetailVo;

/**
 * TaobaokeItemDao的Mongo实现
 * 
 * @author Administrator
 * 
 */
@Repository(value = "taobaokeItemDetailDao")
public class TaobaokeItemDetailMongoDaoImpl extends BasicDAO<TaobaokeItemDetailVo, String> implements TaobaokeItemDetailDao {

    protected TaobaokeItemDetailMongoDaoImpl() {
	super(TaobaokeItemDetailVo.class, MongoConstants.CHI_ITEM);
    }

    @Override
    public boolean batchInsertTaobaokeItemDetailVo(List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo)
    {
	/*
	 * 
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
	
	
	ds.insert(listTaobaokeItemDetailVo);
	return true;
    }

}
