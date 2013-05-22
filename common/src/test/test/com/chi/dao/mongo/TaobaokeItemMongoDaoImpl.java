package test.com.chi.dao.mongo;

import com.chi.po.MongoTaobaokeItemVo;
import com.chi.util.JsonUtils;
import com.chi.util.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

/**
 * TaobaokeItemDao的Mongo实现
 * @author Administrator
 *
 */
//@Repository(value = "taobaokeItemDao")
public class TaobaokeItemMongoDaoImpl{

	public boolean insertTaobaokeItemVo(MongoTaobaokeItemVo mongoTaobaokeItemVo) 
	{
		Long numIid = mongoTaobaokeItemVo.getTaobaokeItem().getNumIid();
		String jsonStr = null;
		try {
			jsonStr = JsonUtils.getJSONString(mongoTaobaokeItemVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BasicDBObject object = new BasicDBObject();
		object.put("numIid", numIid);
		object.put("taobaokeItemVo", jsonStr);

		DBCollection collection = MongoDBUtil.getCollection("item");
		WriteResult wr = collection.insert(object);
		
		return true;
	}


	/*
	 * 
	 * 
	@Override
	public boolean batchInsertTaobaokeItemDetailVo(List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo) 
	{
		List<DBObject> listBasicDBObject = new ArrayList<DBObject>();
		for (TaobaokeItemDetailVo detailVo : listTaobaokeItemDetailVo) 
		{
			Long numIid = detailVo.getTaobaokeItemDetail().getItem().getNumIid();
			String jsonStr = null;
			try {
				jsonStr = JsonUtils.getJSONString(detailVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			BasicDBObject object = new BasicDBObject();
			object.put("numIid", numIid);
			object.put("taobaokeItemVo", jsonStr);
			
			listBasicDBObject.add(object);
		}

		DBCollection collection = MongoDBUtil.getCollection("item_detail");
		WriteResult wr = collection.insert(listBasicDBObject);
		
		return true;
	}
	 */

}
