package com.dlz.framework.db.modal.items;

import com.dlz.comm.json.JSONMap;
import lombok.Data;

import java.io.Serializable;

@Data
public class SqlItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 输入的key
     */
    private String sqlKey;
    /**
     * 解析过后的sql(只存在填充符)
     */
    private String sqlDeal;
    /**
     * 解析过后的sql(只存在填充符)
     */
    private String sqlRun;
    /**
     * 翻页
     */
    private String sqlPage;
    /**
     * 条数
     */
    private String sqlCnt;
    /**
     * 条数
     */
    private JSONMap systemPara = new JSONMap();


    private String sqlJdbc;
    private Object[] sqlJdbcPara;


}