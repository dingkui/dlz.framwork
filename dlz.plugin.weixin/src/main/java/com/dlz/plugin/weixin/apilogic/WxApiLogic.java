package com.dlz.plugin.weixin.apilogic;

import org.springframework.stereotype.Service;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.web.logic.NoAuthCommLogic;
import com.dlz.web.util.WxUtil.WxConfig;
@Service()
public class WxApiLogic extends NoAuthCommLogic{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public JSONResult config(JSONMap data) {
		JSONResult r=JSONResult.createResult();
		r.putAll(WxConfig.createConfigJson(data.getStr("url"),data.getStr("appid")));
		return r;
	}
}
