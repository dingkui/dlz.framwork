package com.dlz.framework.db.dao;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.List;


/**
 * 数据库操作接口
 *
 * @author kingapex
 * 2010-6-13上午11:05:32
 * <p>
 * 2018-10-17 dk 覆盖query和execute，去掉过多的sql debug日志,添加异常时的sql日志
 */
@Lazy
public interface IDlzDao {
    List<ResultMap> getList(BaseParaMap paraMap);

    int getCnt(BaseParaMap paraMap);

    int updateSql(BaseParaMap paraMap);

    HashMap<String, Integer> getTableColumsInfo(String tableName);
}
