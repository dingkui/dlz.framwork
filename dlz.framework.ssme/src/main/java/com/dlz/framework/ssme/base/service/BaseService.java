package com.dlz.framework.ssme.base.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;

public interface BaseService<T, PK extends Serializable> {
	  /**
	   * 构造查询条件查询数据条数
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */
	  int countByExample(Object example) throws Exception;
	  /**
	   * 构造查询条件删除数据
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */
    int deleteByExample(Object example)throws Exception;
	  /**
	   * 根据主键删除数据
	   * @param pk 主键，可能是单个字段也可能是组合字段，组合字段采用key对象
	   * @return
	   * @throws Exception
	   */
    int deleteByPrimaryKey(PK pk)throws Exception;
	  /**
	   * 插入数据
	   * @param record 数据对象（key要在之前设定）
	   * @return
	   * @throws Exception
	   */
    int insert(T record)throws Exception;
	  /**
	   * 字段选择插入数据（空字段在sql中不出现）
	   * @param record 数据对象（key要在之前设定）
	   * @return
	   * @throws Exception
	   */
    int insertSelective(T record)throws Exception;
	  /**
	   * 构造查询条件查询数据列表
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */
    List<T> selectByExample(Object example)throws Exception;
    /**
     * 构造查询条件查询数据对象<br>
     * @param example Criteria对象
     * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
     * xc.createCriteria().andXxxEqualTo(xxx).and....
     * @return 查询结果为0条则返回null，查询条数大于1则抛出异常
     * @throws Exception
     */
    T selectBeanByExample(Object example)throws Exception;
	  /**
	   * 根据主键查询单条数据
	   * @param pk 主键，可能是单个字段也可能是组合字段，组合字段采用key对象
	   * @return
	   * @throws Exception
	   */
    T selectByPrimaryKey(PK pk)throws Exception;
	  /**
	   * 根据查询条件选择性更新数据（数据对象中设置了的字段做更新）
	   * @param record 数据对象
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return 更新条数
	   * @throws Exception
	   */
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example)throws Exception;
	  /**
	   * 根据查询条件更新数据（数据对象中所有字段都做更新）
	   * @param record 数据对象
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return 更新条数
	   * @throws Exception
	   */
    int updateByExample(@Param("record") T record, @Param("example") Object example)throws Exception;
	  /**
	   * 根据主键选择性更新数据（数据对象中设置了的字段做更新）
	   * @param record 数据对象
	   * @return 更新条数
	   * @throws Exception
	   */
    int updateByPrimaryKeySelective(T record)throws Exception;
	  /**
	   * 根据主键更新数据（数据对象中所有字段都做更新）
	   * @param record 数据对象
	   * @return 更新条数
	   * @throws Exception
	   */
    int updateByPrimaryKey(T record)throws Exception;
	  /**
	   * 构造查询条件查询数据列表（带分页信息）
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */
		Page<T>  pageByExample(Object example)throws Exception;
	  /**
	   * 构造查询条件查询数据列表（带分页信息）（查询的内容包含大字段）
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */		
		Page<T>  pageByExampleWithBlobs(Object example)throws Exception;
	  /**
	   * 构造查询条件查询数据列表（带分页信息）
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return
	   * @throws Exception
	   */			
		@SuppressWarnings("rawtypes")
		Page<T>  getPageByExample(Object example,Page page) throws Exception;
	  /**
	   * 根据主键更新数据（数据对象中所有字段都做更新）（包含大字段）
	   * @param record 数据对象
	   * @return 更新条数
	   * @throws Exception
	   */
		int updateByPrimaryKeyWithBLOBs(T record) throws Exception;
	  /**
	   * 根据查询条件更新数据（数据对象中所有字段都做更新）（包含大字段）
	   * @param record 数据对象
	   * @param example Criteria对象
	   * Criteria构造方式   XxxxCriteria xc=new XxxxCriteria();<br>
	   * xc.createCriteria().andXxxEqualTo(xxx).and....
	   * @return 更新条数
	   * @throws Exception
	   */
	   int updateByExampleWithBLOBs(T record, Object example) throws Exception;
	   List<T> selectByExampleWithBLOBs(Object example) throws Exception;

	   List<T> getBeanList(BaseParaMap pm) throws Exception;
	   T getBean(BaseParaMap pm) throws Exception;
	   Page<T> getPage(BaseParaMap pm) throws Exception;
	   int excute(BaseParaMap pm) throws Exception;
}
