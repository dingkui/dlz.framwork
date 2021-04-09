package com.dlz.framework.config;


import com.dlz.comm.json.IUniversalVals;
import com.dlz.comm.json.JSONList;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.config.ConfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.function.Function;

/**
 * 系统配置取值器
 * 取值优先级：
 * springboot配置 > config.txt > 客户端自定义配置
 */
public class BootConfig implements IUniversalVals {
    /**
     * spring boot配置环境变量
     */
    @Autowired
    Environment environment;

    /**
     * spring boot 和 config 配置本地缓存
     */
    private JSONMap map = new JSONMap();

    /**
     * 客户端自定义取得配置的服务
     */
    @Autowired
    ICustomConfig customConfig;

    @Override
    public Object getInfoObject() {
        return map;
    }

    private Function<String, Object> getStrFn = (name) -> {
        Object val = map.getKeyVal(name);
        if (val != null) {
            return val;
        }

        String valStr = environment.getProperty(name);
        if (valStr == null) {
            valStr = ConfUtil.getConfig(name);
        }
        if (valStr != null) {
            if (JacksonUtil.isJsonObj(valStr)) {
                val = new JSONMap(valStr);
            } else if (JacksonUtil.isJsonArray(valStr)) {
                val = new JSONList(valStr);
            } else {
                val = valStr;
            }
            map.put(name, val);
            return val;
        }

        if (val == null) {
            return customConfig.get(name);
        }

        return val;
    };

    @Override
    public Object getKeyVal(String key) {
        return StringUtils.getReplaceStr(key, getStrFn, 0);
    }
}
