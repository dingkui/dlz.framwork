package com.dlz.plugin.weixin.apilogic;

import org.springframework.stereotype.Service;

import com.dlz.common.logic.NoAuthCommLogic;
import com.dlz.common.logic.interfaces.IAppLogic;
import com.dlz.common.logic.interfaces.IWapLogic;
import com.dlz.common.logic.interfaces.IWxLogic;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.util.WxUtil.WxConfig;
@Service()
public class WxApiLogic extends NoAuthCommLogic implements IAppLogic, IWxLogic,IWapLogic {
	public JSONResult config(JSONMap data) {
		JSONResult r=JSONResult.createResult();
		r.putAll(WxConfig.createConfigJson(data.getStr("url")));
		return r;
	}
}
