package com.dlz.framework.db;

import com.dlz.comm.exception.DbException;
import com.dlz.framework.db.config.DlzDbProperties;
import com.dlz.framework.db.enums.DbTypeEnum;
import com.dlz.framework.db.holder.ISqlHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据库配置信息
 *
 * @author dingkui 2011-08-12 v1.0
 * history dingkui 2012-05-07 v1.1
 * 修改sql文件路径取得方式，以便执行init时可以刷新内存
 */
@Slf4j
public class DbInfo {

    final DlzDbProperties dbProperties;
    final ISqlHolder sqlHolder;
    private static DbInfo info;

    public DbInfo(ISqlHolder sqlHolder, DlzDbProperties dbProperties) {
        this.dbProperties=dbProperties;
        this.sqlHolder=sqlHolder;
        info=this;
    }


    public static String getSql(String key) {
        if (key == null) {
            throw new DbException("输入的sql为空！", 1002);
        }
        if(key.matches("[\\s]*(?i)select.*") ){
            return key;
        }
        if (!key.startsWith("key.")) {
            throw new DbException("sqlKey格式无效:" + key, 1002);
        }
        String sql = info.sqlHolder.getSql(key + info.dbProperties.getDbtype().getEnd());
        if (sql == null) {
            sql = info.sqlHolder.getSql(key);
        }
        if (sql == null) {
            throw new DbException("sqlKey未配置：" + key, 1002);
        }
        return sql;
    }

    public static void reload() {
        info.sqlHolder.reLoad();
    }
    public static String getBlob_charsetname() {
        return info.dbProperties.getBlob_charsetname();
    }
    public static DbTypeEnum getDbtype() {
        return info.dbProperties.getDbtype();
    }
}
