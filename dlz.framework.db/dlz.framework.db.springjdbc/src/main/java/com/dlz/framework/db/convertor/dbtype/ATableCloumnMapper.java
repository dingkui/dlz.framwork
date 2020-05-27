package com.dlz.framework.db.convertor.dbtype;

import com.dlz.framework.db.convertor.ConvertUtil;

public abstract class ATableCloumnMapper {
    public ATableCloumnMapper() {
        ConvertUtil.setTableCloumnCache(this);
    }
    /**
     * 取得字段对应的类型
     *
     * @param @param  tableName
     * @param @param  clumnName
     * @param @param  value
     * @param @return 设定文件
     * @return Object    返回类型
     * @throws
     * @Title: converObj4Db
     */
    public abstract Object converObj4Db(String tableName, String clumnName, Object value);

    /**
     * 判断字符串是否在表中存在
     *
     * @param @param  tableName
     * @param @param  clumnName
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isClumnExists
     */
    public abstract boolean isClumnExists(String tableName, String clumnName);
}
