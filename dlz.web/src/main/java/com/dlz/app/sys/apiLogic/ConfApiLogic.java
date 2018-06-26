package com.dlz.app.sys.apiLogic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.exception.ValidateException;
import com.dlz.framework.util.config.ConfUtil;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 配置信息取得
 * @author dk 2018-06-26
 */
@Service
@AnnoAuth("admin")
@SuppressWarnings("rawtypes")
public class ConfApiLogic extends AuthedCommLogic{
	/**
	 * 取得站点配置信息
	 * @param data
	 * @return
	 */
	@AnnoAuth("N")
	public Map done(JSONMap data){
		return ConfUtil.getMap("conf.sitefront");
	}
	/**
	 * 获取某个配置
	 * @param data
	 * @return
	 */
	public String str(JSONMap data){
		final String name = data.getStr("name");
		if(name==null){
			throw new ValidateException("name 未输入");
		}
		return ConfUtil.getConfig(name);
	}
	/**
	 * 获取配置集合
	 * @param data
	 * @return
	 */
	public Map map(JSONMap data){
		final String name = data.getStr("name");
		if(name==null){
			throw new ValidateException("name 未输入");
		}
		return ConfUtil.getMap(name);
	}
	/**
	 * 获取配置列表
	 * @param data
	 * @return
	 */
	public List list(JSONMap data){
		final String name = data.getStr("name");
		if(name==null){
			throw new ValidateException("name 未输入");
		}
		return ConfUtil.getList(name);
	}
}
