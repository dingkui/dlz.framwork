package com.dlz.framework.db.service.impl;

import com.dlz.comm.exception.DbException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.config.DlzDbConfig;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.dao.IDlzDao;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.items.SqlItem;
import com.dlz.framework.db.service.ICommService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CommServiceImpl implements ICommService {
    @Autowired
    private IDlzDao dao;
    @Value("${dlz.db.log.showResult:false}")
    private boolean showResult;
    @Value("${dlz.db.log.showKeySql:true}")
    private boolean showKeySql;
    @Value("${dlz.db.log.showRunSql:false}")
    private boolean showRunSql;
    @Value("${dlz.db.jdbcSql:false}")
    private boolean jdbcSql;

    private void dealJdbc(BaseParaMap paraMap, int dealType) {
        SqlItem sqlItem = paraMap.getSqlItem();

        if (sqlItem.getSqlKey() != null) {
            sqlItem = SqlUtil.dealParm(paraMap);
            switch (dealType) {
                case 1:
                    sqlItem.setSqlRun(sqlItem.getSqlDeal());
                    break;
                case 2:
                    sqlItem.setSqlRun(SqlUtil.getCntSql(sqlItem));
                    break;
                case 3:
                    sqlItem.setSqlRun(SqlUtil.getPageSql(paraMap));
                    break;
            }

            if (showKeySql && log.isInfoEnabled()) {
                log.info("SqlKey:" + sqlItem.getSqlKey() + "[" + sqlItem.getSqlRun() + "]para:[" + paraMap.getPara() + "]");
            }
        }

        if (jdbcSql) {
            SqlUtil.dealParmToJdbc(paraMap);
        }

        if (showKeySql && log.isInfoEnabled()) {
            if (jdbcSql) {
                if (showRunSql) {
                    log.info("sql:" + SqlUtil.getRunSqlByJdbc(sqlItem.getSqlJdbc(), sqlItem.getSqlJdbcPara()));
                } else {
                    log.info("JdbcSql:" + sqlItem.getSqlJdbc() + " paras:" + JacksonUtil.getJson(sqlItem.getSqlJdbcPara()));
                }
            } else {
                log.info("sql:" + SqlUtil.getRunSqlByParaMap(paraMap));
            }
        }
    }

    @Override
    public int excuteSql(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        try {
            dealJdbc(paraMap, 1);
            int r = dao.updateSql(paraMap);
            if (showResult && log.isInfoEnabled()) {
                log.info("result:" + r);
            }
            return r;
        } catch (Exception e) {
            if (e instanceof DbException) {
                throw e;
            }
            throw new DbException(sqlItem.getSqlKey() + ":" + sqlItem.getSqlRun() + " para:" + paraMap.getPara(), 1003, e);
        }
    }


    @Override
    public int getCnt(BaseParaMap paraMap) {
        Page cache = paraMap.getCacheItem().getCache("cnt", paraMap);
        if (cache != null) {
            return cache.getCount();
        }

        SqlItem sqlItem = paraMap.getSqlItem();
        try {
            dealJdbc(paraMap, 2);
            int cnt = ValUtil.getInt(ConvertUtil.getFistClumn(dao.getList(paraMap).get(0)));
            paraMap.getCacheItem().saveCache(cnt);
            return cnt;
        } catch (Exception e) {
            if (e instanceof DbException) {
                throw e;
            }
            throw new DbException(sqlItem.getSqlKey() + ":" + sqlItem.getSqlCnt() + " para:" + paraMap.getPara(), 1003, e);
        }
    }

    /**
     * 从数据库中取得map类型列表如：[{AD_ENDDATE=2015-04-08 13:47:12.0}]
     * sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
     *
     * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
     * @return
     * @throws Exception
     */
    @Override
    public List<ResultMap> getMapList(BaseParaMap paraMap) {
        Page cache = paraMap.getCacheItem().getCache("list", paraMap);
        if (cache != null) {
            return cache.getData();
        }
        SqlItem sqlItem = paraMap.getSqlItem();
        try {
            dealJdbc(paraMap, 3);

            List<ResultMap> list = dao.getList(paraMap);
            List<ResultMap> list2 = list.stream().map(r -> ConvertUtil.converResultMap(r, paraMap.getConvert())).collect(Collectors.toList());
            paraMap.getCacheItem().saveCache(list2);
            return list2;
        } catch (Exception e) {
            if (e instanceof DbException) {
                throw e;
            }
            throw new DbException(e.getMessage() + " " + sqlItem.getSqlKey() + ":" + sqlItem.getSqlPage() + " para:" + paraMap.getPara(), 1003, e);
        }
    }
}
