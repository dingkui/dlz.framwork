package com.dlz.framework.db.dao;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.convertor.JdbcUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.items.SqlItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DaoOperator implements IDlzDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ResultSetExtractor<List<ResultMap>> extractor = JdbcUtil::buildResultMapList;

    public List<ResultMap> getList(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        return jdbcTemplate.query(sqlItem.getSqlJdbc(), sqlItem.getSqlJdbcPara(), extractor);
    }

    public int getCnt(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        return jdbcTemplate.queryForObject(sqlItem.getSqlJdbc(), Integer.class, sqlItem.getSqlJdbcPara());
    }

    public int updateSql(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        return jdbcTemplate.update(sqlItem.getSqlJdbc(), sqlItem.getSqlJdbcPara());
    }

    @Override
    public HashMap<String, Integer> getTableColumsInfo(String tableName) {
        // 查询表结构定义；返回表定义Map
        String sql = "select * from " + tableName + " limit 0";
        ResultSetExtractor<HashMap<String, Integer>> extractor = rs -> {
            HashMap<String, Integer> infos = new HashMap<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++) {
                String columnLabel = rsmd.getColumnLabel(i).toUpperCase();
                infos.put(columnLabel, rsmd.getColumnType(i));
            }
            return infos;
        };
        return jdbcTemplate.query(sql, extractor);
    }
}
