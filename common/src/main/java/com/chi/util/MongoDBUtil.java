package com.chi.util;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

/**
 * MongoDB工具类
 * @author wangkui
 *
 */
public class MongoDBUtil {

	private static String MONGO_HOST1 = null;
	private static String MONGO_HOST2 = null;
	private static String MONGO_HOST3 = null;
	
	private static int MONGO_PORT1 = 27017;
	private static int MONGO_PORT2 = 27017;
	private static int MONGO_PORT3 = 27017;
	
	private static String DB_NAME = "chi_item";
	
	private static Mongo mongo = null;
	private static DB db = null;
	
	// 初始化
	static {
		try {
			Properties props = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			if(in!=null){
				props.load(in);
				in.close();
			}
			
			MONGO_HOST1 = props.getProperty("chi.mongo.host1");
			MONGO_HOST2 = props.getProperty("chi.mongo.host2");
			MONGO_HOST3 = props.getProperty("chi.mongo.host3");
			
			MONGO_PORT1 = Integer.valueOf(props.getProperty("chi.mongo.port1"));
			MONGO_PORT2 = Integer.valueOf(props.getProperty("chi.mongo.port2"));
			MONGO_PORT3 = Integer.valueOf(props.getProperty("chi.mongo.port3"));
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException("Config error, msg=" + e.getMessage(), e);
		}
		
	}
	
	public static void init()
	{
		try {
			mongo = new Mongo(MONGO_HOST1, MONGO_PORT1);
			/**
			 * 
			mongo = new Mongo(Arrays.asList(new ServerAddress(MONGO_HOST1, MONGO_PORT1)
			,new ServerAddress(MONGO_HOST2, MONGO_PORT2)
			,new ServerAddress(MONGO_HOST3, MONGO_PORT3)
			));
			 */
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		db = mongo.getDB(DB_NAME);
	}
		 
	public static void destory()
	{
		if(mongo != null) mongo.close();
		mongo = null;
		db = null;
	}
	
	public static DBCollection getCollection(String collectionName)
	{
		if(db == null) init();
		DBCollection collection = db.getCollection(collectionName);
		return collection;
	}
	
	public static void main(String[] args) throws UnknownHostException 
	{
		DBCollection collection = getCollection("item");
		
		BasicDBObject object = new BasicDBObject();
		object.put("mx_uuid", UUID.randomUUID().toString());
		object.put("text", "text:===============================");
		object.put("html", "html:=======================================");
		
		WriteResult wr = collection.insert(object);
		System.out.println(wr.getN());
	}
	
}
