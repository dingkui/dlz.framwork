package com.dlz.framework.db.conver.impl;

import com.dlz.framework.db.conver.AGroupConverter;
import com.dlz.framework.db.conver.ILogicServer;
import com.dlz.framework.holder.SpringHolder;

import java.util.Map;

public class AddressConverter extends AGroupConverter<Object,Object,String>{

	ILogicServer<Object,Map<String,Object>> logicServer=null;
	public AddressConverter(String name, String para) {
		super(name, para);
		this.logicServer=SpringHolder.getBean("addrConverterLogicServer");
	}

	@Override
	public Object conver2Str(Object o, Map<String, Object> map) {
		if(logicServer!=null){
			return logicServer.conver2Str((String)o, map);
		}
		return null;
	}

	@Override
	public Object conver2Db(Object o, Map<String, Object> map) {
		if(logicServer!=null){
			return logicServer.conver2Db((String)o, map);
		}
		return null;
	}
}
