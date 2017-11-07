package com.dlz.common.base.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T, PK extends Serializable> {
  int countByExample(Object example);

  int deleteByExample(Object example);
  int deleteByPrimaryKey(PK pk);

  int insert(T record);
  int insertSelective(T record);
  
  List<T> selectByExample(Object example);
  List<T> selectByExampleWithBLOBs(Object example);  
  T selectByPrimaryKey(PK pk);
  
  int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);
  int updateByExample(@Param("record") T record, @Param("example") Object example);
  int updateByExampleWithBLOBs(@Param("record") T record, @Param("example") Object example);

  int updateByPrimaryKeySelective(T record);
  int updateByPrimaryKey(T record);
  int updateByPrimaryKeyWithBLOBs(T record);    
}
