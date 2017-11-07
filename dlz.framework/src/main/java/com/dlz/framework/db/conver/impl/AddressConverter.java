package com.dlz.framework.db.conver.impl;

import java.util.Map;

import com.dlz.framework.db.conver.AGroupConverter;
import com.dlz.framework.db.conver.ILogicServer;
import com.dlz.framework.holder.SpringHolder;

public class AddressConverter extends AGroupConverter<Object,Object,String> implements ILogicConverter<Object,Map<String,Object>>{
	ILogicServer<Object,Map<String,Object>> logicServer=null;
	public AddressConverter(String name, String para) {
		super(name, para);
		this.logicServer=getLogicServ();
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

	@Override
	public ILogicServer<Object,Map<String,Object>> getLogicServ() {
		ILogicServer<Object,Map<String,Object>> LogicServer=SpringHolder.getBean("addrConverterLogicServer");
		if(LogicServer==null){
			return null;
		}
		return LogicServer;
	}
}
