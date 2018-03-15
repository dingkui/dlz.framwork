package com.dlz.framework.db.nosql.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.dao.IDaoOperator;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;

@Service
public class DaoOperatorMongo implements IDaoOperator {
	@Override
	public List<ResultMap> getList(Find paraMap) {
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(paraMap.getName());
		Bson bson=(Bson)JSON.parse(paraMap.getFilterBson());
		Bson projection=(Bson)JSON.parse(paraMap.getClumns());
		FindIterable<DBObject> find = dbColl.find(bson);
		if(projection!=null){
			find=find.projection(projection);
		}
		if(paraMap.getPage()!=null){
			if(paraMap.getPage().getSortField()!=null && paraMap.getPage().getSortOrder()!=null){
				BasicDBObject sort=new BasicDBObject();
				sort.append(paraMap.getPage().getSortField(), "desc".equalsIgnoreCase(paraMap.getPage().getSortOrder())?-1:1);
				find=find.sort(sort);
			}
			if(paraMap.getPage().getBegin()!=null){
				find=find.skip(paraMap.getPage().getBegin());
			}
			if(paraMap.getPage().getPageSize()>0){
				find=find.limit(paraMap.getPage().getPageSize());
			}
		}
		List<ResultMap> r = new ArrayList<ResultMap>();
		MongoCursor<DBObject> iterator = find.iterator();
		while (iterator.hasNext()) {
			r.add(JacksonUtil.readValue(iterator.next().toString(), ResultMap.class));
		}
		return r;
	}

	@Override
	public int getCnt(Find paraMap) {
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(paraMap.getName());
		Bson bson=(Bson)JSON.parse(paraMap.getFilterBson());
		return ValUtil.getInt(dbColl.count(bson));
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insert(Insert paraMap) {
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(paraMap.getName());
		List<DBObject> dbo = (List<DBObject>) JSON.parse(paraMap.getDataBson());  
		dbColl.insertMany(dbo);
		return dbo.size();
	}

	@Override
	public int update(Update paraMap) {
		MongoDatabase db = MongoManager.getDB();
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(db, paraMap.getName());
		Bson dbo=(Bson)JSON.parse(paraMap.getDataBson());  
		Bson filter =(Bson)JSON.parse(paraMap.getFilterBson());  
		UpdateResult updateMany = dbColl.updateMany(filter, dbo);
		return ValUtil.getInt(updateMany.getModifiedCount());
	}

	@Override
	public int del(Delete paraMap) {
		MongoDatabase db = MongoManager.getDB();
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(db, paraMap.getName());
		Bson filter = (Bson) JSON.parse(paraMap.getFilterBson());  
		DeleteResult deleteMany = dbColl.deleteMany(filter);
		return ValUtil.getInt(deleteMany.getDeletedCount());
	}
	/**
	 * db.counters.findAndModify({
		    query: {_id:"productid611"},
		    update: { $inc: { val: 1 } },
		    upsert: true,
		    fields:{"_id":0}
		})
	 * @param seqName
	 * @return
	 */
	@Override
	public long getSeq(String seqName) {
		MongoDatabase db = MongoManager.getDB();
		MongoCollection<DBObject> dbColl = MongoManager.getDBColl(db, "_SEQS");
		BasicDBObject fileter = new BasicDBObject("_id", seqName);
//		fileter.put("_id",  Pattern.compile("^.*"+seqName+".*$", Pattern.CASE_INSENSITIVE) );
//		System.out.println(fileter.toJson());
		BasicDBObject update = new BasicDBObject("$inc", new BasicDBObject("val", 1));
		FindOneAndUpdateOptions option = new FindOneAndUpdateOptions();
		option.projection(new BasicDBObject("_id", 0));
		option.upsert(true);
		option.returnDocument(ReturnDocument.AFTER);
		return ValUtil.getLong(dbColl.findOneAndUpdate(fileter, update, option).get("val"));
	}
}
