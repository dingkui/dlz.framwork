package com.dlz.framework.db.nosql.operator.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.dlz.framework.bean.JSONMap;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private  static int PORT = 27017;// 端口
	private  static int POOLSIZE = 100;// 连接数量
	private  static int BLOCKSIZE = 100; // 等待队列长度
	private  static int MAX_WAIT_TIME = 1000 * 60 * 2; // 成功获取到一个可用数据库连接之前的最长等待时间
	private  static int CONN_TIME_OUT = 1000 * 60 * 1; // 与数据库建立连接的timeout
	private  static String DBNAME = ""; // 数据库名称
	private static MongoClient mongoClient = null;
	
	private MongoManager() {
	}

	public static MongoDatabase getDB() {
		return mongoClient.getDatabase(DBNAME);
	}
//	public static MongoCollection<DBObject> getDBColl(String name) {
//		return getDB().getCollection(name,DBObject.class);
//	}
	public static MongoCollection<Document> getDBColl(String name) {
		return getDB().getCollection(name);
	}
	public static MongoDatabase getDB(String dbName) {
		return mongoClient.getDatabase(dbName);
	}

	/**
	 * 初始化连接池
	 */
	public static void init(JSONMap m_dbset) {
		// 其他参数根据实际情况进行添加
		try {
			if(mongoClient!=null){
				mongoClient.close();
			}
			DBNAME=m_dbset.getStr("mongo.opt.dbname");
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.connectionsPerHost(m_dbset.getInt("mongo.opt.poolsize",POOLSIZE)); // 与目标数据库能够建立的最大connection数量为50
			// build.autoConnectRetry(true); //自动重连数据库启动
			build.threadsAllowedToBlockForConnectionMultiplier(m_dbset.getInt("mongo.opt.blocksize",BLOCKSIZE)); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(m_dbset.getInt("mongo.opt.maxwatitime",MAX_WAIT_TIME) );
			build.connectTimeout(m_dbset.getInt("mongo.opt.connnectionTimeout",CONN_TIME_OUT) ); // 与数据库建立连接的timeout设置为1分钟
			List<ServerAddress> arrayList = new ArrayList<ServerAddress>();
			arrayList.add(new ServerAddress(m_dbset.getStr("mongo.opt.host"), m_dbset.getInt("mongo.opt.port",PORT)));
			if(m_dbset.getStr("mongo.opt.user")!=null&&m_dbset.getStr("mongo.opt.userPwd")!=null){
				MongoCredential credential = MongoCredential.createCredential(m_dbset.getStr("mongo.opt.user"), DBNAME, m_dbset.getStr("mongo.opt.userPwd").toCharArray()); 
				mongoClient = new MongoClient(arrayList,credential, build.build());
			}else{
				mongoClient = new MongoClient(arrayList,build.build());
			}
		} catch (MongoException e) {

		}
	}
	
	
//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		new NosqlDbInfo();
//		List<DBObject> dbo = (List<DBObject>) JSON.parse("[{\"ccc\":6},{\"ccc\":\"222\"},{\"ccc\":3},{\"ccc\":1},{\"ccc\":2},{\"ccc\":5},{\"ccc\":4}]");  
//		MongoCollection<DBObject> collection = getDBColl(getDB(),"xx");
//		collection.drop();
//		collection.insertMany(dbo);
//		
//		Bson v=(Bson)JSON.parse("{ccc:\"222\"}");
//		System.out.println(v);
//
//		Bson v2=(Bson)JSON.parse("{}");
//		BasicDBObject sort=new BasicDBObject();
//		sort.append("ccc", 1);
//		FindIterable<DBObject> find = collection.find(v2);
//		find=find.skip(4).limit(2);
//		find=find.sort(sort);
//		MongoCursor<DBObject> iterator = find.iterator();
//		while(iterator.hasNext()){
//			System.out.println(iterator.next().toString());
//		}
//		System.out.println(collection.count(v));
//	}
}