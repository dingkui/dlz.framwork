package com.dlz.framework.ssme.interfaces.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dlz.apps.dict.util.DictUtil;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.ssme.db.model.BaseSet;
import com.dlz.framework.ssme.db.model.BaseSetCriteria;
import com.dlz.framework.ssme.db.service.BaseSetService;
import com.dlz.framework.ssme.interfaces.IConfigInterface;
import com.dlz.framework.util.StringUtils;

@Component
public class ConfigInterface implements IConfigInterface {
	private static Logger logger = LoggerFactory.getLogger(ConfigInterface.class);

	public String getNameByKey(String valueOf, String para) {
		return DictUtil.getNameByKey(valueOf,para);
	}

	public Map<String, String> getConfigMap() {
		BaseSetService baseSetService = (BaseSetService)SpringHolder.getBean(BaseSetService.class);
		BaseSetCriteria bsc = new BaseSetCriteria();
		bsc.createCriteria().andStatusEqualTo("1");
		Map<String,String> map=new HashMap<String,String>();
		List<BaseSet> subjectList;
		try {
			subjectList = baseSetService.selectByExample(bsc);
			for (BaseSet subject : subjectList) {
				map.put(subject.getBaseCode(), StringUtils.NVL(subject.getBaseValue()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return map;
	}

}
