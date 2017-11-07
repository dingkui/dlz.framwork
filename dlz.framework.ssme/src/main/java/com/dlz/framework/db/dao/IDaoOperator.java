package com.dlz.framework.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;

/**
 * 数据库操作接口
 * @author dk
 * @param <T>
 */
public interface IDaoOperator  {

	long getSeq(@Param("seqName") String seqName);
	
	long getSeqWithTime(@Param("seqName") String seqName);

	List<ResultMap> getList(BaseParaMap paraMap);

	int getPageCnt(BaseParaMap paraMap);

	int updateSql(BaseParaMap paraMap);
}
