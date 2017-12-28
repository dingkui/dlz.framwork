package com.dlz.apps.sys.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.logger.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.model.Dict;
import com.dlz.framework.ssme.db.model.DictModel;
import com.dlz.framework.ssme.db.service.DictService;
import com.dlz.framework.util.StringUtils;

@Component
public class DictCacheSsme extends AbstractCache<String, Map<String,DictModel>> {
		public DictCacheSsme() {
			super(DictCacheSsme.class.getSimpleName());
			dbOperator=new DbOperator<String, Map<String,DictModel>>() {
				protected Map<String,DictModel> getFromDb(String dicdCode) {
					Dict d;
					try {
						d = dictService.selectByPrimaryKey(dicdCode);
						if(d==null){
							return null;
						}
						Map<String,DictModel> dict= new LinkedHashMap<String,DictModel>();
						String dictSource=d.getDictSource();
						if(dictSource==null || "".equals(dictSource)){
							dictSource="select dict_param_value id,dict_param_name text from T_B_DICT_DETAIL where dict_Id=#{dictId} and dict_Param_Status=#{status} order by DICT_ORDER";
						}
						ParaMap pm = new ParaMap(dictSource);
						pm.addPara("dictId", dicdCode);
						pm.addPara("status", "1");
						List<DictModel> list = commService.getBeanList(pm, DictModel.class);
						convert2Result(list, dict);
						
						if(!dict.isEmpty()){
							StringBuffer desc = new StringBuffer();
							for(DictModel dictt: dict.values()){
								desc.append(dictt.getId()).append(":").append(dictt.getText()).append(",");
							}
							if(desc.length()>300){
								d.setDictDesc(desc.substring(0, 299));
							}else{
								d.setDictDesc(desc.substring(0, desc.length()-1));
							}
							dictService.updateByPrimaryKey(d);
						}
						return dict;
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
						return null;
					}
				} 
			};
		}

	/**
	 * 日志logger
	 */
	private static MyLogger logger = MyLogger.getLogger(DictCacheSsme.class);
	/**
	 * 数据字典
	 */
	@Autowired
	private DictService dictService = null;
	/**
	 * 数据字典
	 */
	@Autowired
	private ICommService commService = null;

	
	
	private Map<String,DictModel> convert2Result(List<DictModel> list,Map<String,DictModel> m){
		for(DictModel dict:list){
			m.put(dict.getId(), dict);
		}
		return m;
	}
	
	public String getNameByKey(String dictId,String value){
		Map<String,DictModel> map = get(dictId);
		if(map==null || map.get(value)==null){
			if(StringUtils.isNotEmpty(value)){
				logger.error("字典未设置："+dictId+"."+value);
			}
			return value;
		}
		return map.get(value).getText();
	}
	
	public DictModel getModelByKey(String dictId,String value){
		Map<String,DictModel> map = get(dictId);
		return map.get(value);
	}
}
