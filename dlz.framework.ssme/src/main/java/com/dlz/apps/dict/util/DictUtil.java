package com.dlz.apps.dict.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dlz.apps.dict.cache.DictCacheSsme;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.ssme.constants.Constants;
import com.dlz.framework.ssme.db.model.Dict;
import com.dlz.framework.ssme.db.model.DictCriteria;
import com.dlz.framework.ssme.db.model.DictModel;
import com.dlz.framework.ssme.db.service.DictService;
import com.dlz.framework.ssme.util.xml.mapper.JsonMapper;
import com.dlz.framework.util.StringUtils;

public class DictUtil {

	/**
	 * 取得字典对应的下拉框
	 * @author dingkui
	 * @param dictEnum
	 * @param defaultValue
	 * @return
	 */
	public static String getOptions(String dictEnum,String defaultValue){
		Map<String,DictModel> map = SpringHolder.getBean(DictCacheSsme.class).get(dictEnum);
		StringBuilder sb = new StringBuilder();
		for(String dictkey:map.keySet()){
			DictModel dicm=map.get(dictkey);
			sb.append("<option value='").append(dicm.getId()).append("'");
			if(StringUtils.NVL(defaultValue).equals(dicm.getId())){
				sb.append(" selected");
			}
			sb.append(">").append(dicm.getId()).append("</option>");
		}
		return sb.toString();
	}

	/**
	 * 取得字典对应的下拉框
	 * @author dingkui
	 * @param dictEnum
	 * @return
	 */
	public static Map<String,DictModel> getDicts(String dictEnum){
		return SpringHolder.getBean(DictCacheSsme.class).get(dictEnum);
	}

	/**
	 * 取得字典对应的下拉框
	 * @author dingkui
	 * @param dictEnum
	 * @param defaultValue
	 * @return
	 */
	public static Map<String,String> getOptions(String dictEnum){
		Map<String,DictModel> map = SpringHolder.getBean(DictCacheSsme.class).get(dictEnum);
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		for(String dictkey:map.keySet()){
			map2.put(dictkey, map.get(dictkey).getText());
		}
		return map2;
	}
	/**
	 * 取得字典对应的下拉框
	 * @author dingkui
	 * @param dictEnum
	 * @param defaultValue
	 * @return
	 */
	public static Map<String,String> getSubOptions(String parentId){
		Map<String,DictModel> map = SpringHolder.getBean(DictCacheSsme.class).get(parentId);
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		for(String dictkey:map.keySet()){
			if(map.get(dictkey).getpId().equals(parentId)){
				map2.put(dictkey, map.get(dictkey).getText());
			}
		}
		return map2;
	}

	public static String getNameByKey(String o, String de) {
		if(o==null || o.equals("null")){
			return "";
		}
		return SpringHolder.getBean(DictCacheSsme.class).getNameByKey(de, o);
	}
	
	public static void makeJson(String jsonSavePath) throws Exception{
		DictCacheSsme cache = SpringHolder.getBean(DictCacheSsme.class);
		File folder = new File(jsonSavePath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		File emF = new File(jsonSavePath+"/common/dicJson.txt");
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(emF),"UTF-8");
		out.write("var _dL=[];\n");
		
	  // 查询所有字典
		DictCriteria dictCriteria = new DictCriteria();
		dictCriteria.createCriteria().andDictStatusEqualTo(Constants.EFFECTIVE_STATUS_ACTIVE);
		List<Dict> subjectList = SpringHolder.getBean(DictService.class).selectByExample(dictCriteria);
		for (Dict subject : subjectList) {
			out.write(getDictJson(cache,subject.getDictId()));
		}
		out.close();
	}
	
	private static String getDictJson(DictCacheSsme cache,String dictEnum){
		StringBuilder sb = new StringBuilder();
		Map<String, DictModel> mapList =cache.get(dictEnum);
		if(mapList.isEmpty()){
			return "";
		}
		sb.append("_dL.push('"+dictEnum.toString()+"');");
		sb.append("var "+dictEnum.toString()+"Json=").append(JsonMapper.nonEmptyMapper().toJson(mapList));
		sb.append(";\n");
		return sb.toString();
	}
	
}
