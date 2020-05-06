package com.dlz.framework.db.nosql.operator;

import java.util.List;

import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;

/**
 * 数据库操作接口
 * @author dk
 * @param <T>
 */
public interface INosqlDaoOperator  {
	List<ResultMap> getList(Find paraMap);
	int getCnt(Find paraMap);
	int insert(Insert paraMap);
	int update(Update paraMap);
	int del(Delete paraMap);
	long getSeq(String seqName);
}
