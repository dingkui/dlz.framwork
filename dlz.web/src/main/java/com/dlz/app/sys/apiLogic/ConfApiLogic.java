package com.dlz.app.sys.apiLogic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlz.app.uim.annotation.AnnoAuth;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.util.config.ConfUtil;
import com.dlz.web.logic.AuthedCommLogic;
/**
 * 部门管理
 * @author lxm 
 *
 * 2018年6月7日
 */
@Service
@AnnoAuth("admin")
@SuppressWarnings("rawtypes")
public class ConfApiLogic extends AuthedCommLogic{
	/**
	 * 获取某个配置
	 * @param data
	 * @return
	 */
	public String done(JSONMap data){
		return ConfUtil.getConfig(data.getStr("name"));
	}
	/**
	 * 获取配置集合
	 * @param data
	 * @return
	 */
	public Map map(JSONMap data){
		return ConfUtil.getMap(data.getStr("name"));
	}
	/**
	 * 获取配置列表
	 * @param data
	 * @return
	 */
	public List list(JSONMap data){
		return ConfUtil.getList(data.getStr("name"));
	}
}
