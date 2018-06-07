package com.dlz.framework.db.dao;

import java.util.List;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.mySequence.ISequenceMaker;

/**
 * 数据库操作接口
 * @author dk
 * @param <T>
 */
public interface IDaoOperator  {
	default void setSequenceMaker(ISequenceMaker sequenceMaker){
		
	}

	long getSeq(String seqName);
	
	long getSeqWithTime(String seqName);

	List<ResultMap> getList(BaseParaMap paraMap);

	int getCnt(BaseParaMap paraMap);

	int updateSql(BaseParaMap paraMap);
}
