package com.dlz.framework.db.holder;

public interface ISqlHolder {
    default String clearSql(String sqlStr){
        return sqlStr.replaceAll("--.*", "").replaceAll("[\\s]+", " ");
    }
    void addSqlSetting(String sqlId,String sqlStr);
    String getSql(String key);
    void load();
    void reLoad();
}
