package com.dlz.framework.db.nosql.operator.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;
import com.dlz.framework.db.nosql.operator.INosqlDaoOperator;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
@Service
public class NosqlDaoOperatorMongo implements INosqlDaoOperator {

	@SuppressWarnings("rawtypes")
	@Override
	public List<ResultMap> getList(Find paraMap) {
		MongoCollection<Document> dbColl = MongoManager.getDBColl(paraMap.getName());
		
		FindIterable<Document> find = dbColl.find(BasicDBObject.parse(paraMap.getFilterBson()));
		if(paraMap.getClumns()!=null){
			find=find.projection(new Document(paraMap.getClumns()));
		}
		Page page = paraMap.getPage();
		if(page!=null){
			if(StringUtils.isNotEmpty(page.getSortField()) && page.getSortOrder()!=null){
				BasicDBObject sort=new BasicDBObject();
				sort.append(page.getSortField(), "desc".equalsIgnoreCase(page.getSortOrder())?-1:1);
				find.sort(sort);
			}
			if(page.isNeedFy()&&page.getBegin()!=null){
				find.skip(page.getBegin());
			}
			if(page.getPageSize()>0){
				find.limit(page.getPageSize());
			}
		}
		
		List<ResultMap> r = new ArrayList<ResultMap>();
		MongoCursor<Document> iterator = find.iterator();
		while (iterator.hasNext()) {
			Set<Entry<String, Object>> entrySet = iterator.next().entrySet();
			ResultMap rm=new ResultMap();
			for (Map.Entry<String, Object> e : entrySet) {
                rm.put(e.getKey(), e.getValue());
            }
			r.add(rm);
		}
		return r;
	}

	@Override
	public int getCnt(Find paraMap) {
		MongoCollection<Document> dbColl = MongoManager.getDBColl(paraMap.getName());
		Bson bson=(Bson)BasicDBObject.parse(paraMap.getFilterBson());
		return ValUtil.getInt(dbColl.count(bson));
	}

	@Override
	public int insert(Insert paraMap) {
		MongoCollection<Document> dbColl = MongoManager.getDB().getCollection(paraMap.getName());
		List<JSONMap> datas = paraMap.getDatas();
		List<Document> dbo = new ArrayList<Document>();
		for(JSONMap obj:datas){
			if(!obj.containsKey("_id")){
				obj.put("_id", getSeq(paraMap.getName()));
			}
			Document e = new Document(obj);
			dbo.add(e);
		}
		dbColl.insertMany(dbo);
		return dbo.size();
	}
	
	@Override
	public int update(Update paraMap) {
		MongoCollection<Document> dbColl = MongoManager.getDB().getCollection(paraMap.getName());
		Document update = new Document("$set", new Document(paraMap.getData()));  
		Bson filter =(Bson)BasicDBObject.parse(paraMap.getFilterBson());  
		UpdateResult updateMany = dbColl.updateMany(filter, update);
		return ValUtil.getInt(updateMany.getModifiedCount());
	}

	@Override
	public int del(Delete paraMap) {
		MongoCollection<Document> dbColl = MongoManager.getDBColl(paraMap.getName());
		Bson filter = (Bson) BasicDBObject.parse(paraMap.getFilterBson());  
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
		MongoCollection<Document> dbColl = MongoManager.getDBColl( "_SEQS");
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
