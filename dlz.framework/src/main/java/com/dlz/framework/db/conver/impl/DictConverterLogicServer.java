package com.dlz.framework.db.conver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.db.cache.bean.Dict;
import com.dlz.framework.db.cache.bean.DictItem;
import com.dlz.framework.db.conver.ILogicServer;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.logger.MyLogger;

@Component
class DictConverterLogicServer implements ILogicServer<Object,String> {
	private static MyLogger logger =MyLogger.getLogger(DictConverterLogicServer.class);
	@Autowired
	DictCache dictCache;

	@Override
	public Object conver2Str(Object value, String dictCode) {
		Dict dict=dictCache.get(dictCode);
		if(dict==null){
			throw new DbException("字典转换错误，字典【"+dictCode+"】未定义");
		}
		DictItem item=dict.getItemMap().get(String.valueOf(value));
		if(item==null){
			logger.warn("字典转换错误，字典【"+dictCode+"】中的值【"+value+"】未找到！");
			return value;
		}
		return item.getText();
	}

	@Override
	public Object conver2Db(Object o, String para) {
		return null;
	}

}
