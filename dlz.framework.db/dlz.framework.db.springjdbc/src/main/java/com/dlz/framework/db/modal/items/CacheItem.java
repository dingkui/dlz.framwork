package com.dlz.framework.db.modal.items;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.encry.Md5Util;
import com.dlz.framework.db.cache.DbOprationCache;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CacheItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private static DbOprationCache dbOprationCache;

    private int cacheTime = 0;//缓存时间

	private String key;

    private String key(BaseParaMap paraMap, String type,boolean mkKey) {
    	if(key!=null && !mkKey){
    		return key;
		}
        SqlItem sqlitem = paraMap.getSqlItem();
        Page page = paraMap.getPage();
        JSONMap para = paraMap.getPara();
        StringBuffer sb = new StringBuffer(sqlitem.getSqlKey());
        if (page != null) {
            sb.append(page.getPageIndex()).append(page.getPageSize()).append(page.getSortField()).append(page.getSortOrder());
        }
        sb.append(para);
        return type+Md5Util.md5(sb.toString());
    }

    public Page getCache(String type, BaseParaMap paraMap) {
        if (dbOprationCache != null && cacheTime != 0) {
			return dbOprationCache.getFromCache(key(paraMap,type,true));
        }
        return null;
    }

    public void saveCache(int cnt){
		if (dbOprationCache != null && cacheTime != 0) {
			SystemException.notNull(key,"此处key不应该为空！");
			dbOprationCache.put(key, new Page().setCount(cnt), cacheTime);
		}
	}
	public void saveCache(List data){
		if (dbOprationCache != null && cacheTime != 0) {
			SystemException.notNull(key,"此处key不应该为空！");
			dbOprationCache.put(key, new Page().setData(data), cacheTime);
		}
	}
	public void saveCache(Page page){
		if (dbOprationCache != null && cacheTime != 0) {
			SystemException.notNull(key,"此处key不应该为空！");
			dbOprationCache.put(key, page, cacheTime);
		}
	}

}