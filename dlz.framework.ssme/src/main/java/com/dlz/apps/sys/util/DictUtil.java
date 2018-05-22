package com.dlz.apps.sys.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.ssme.constants.Constants;
import com.dlz.framework.ssme.db.model.Dict;
import com.dlz.framework.ssme.db.model.DictCriteria;
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
		List<JSONMap> dictList = SpringHolder.getBean(DictCache.class).getDictList(dictEnum);
		StringBuilder sb = new StringBuilder();
		for(JSONMap dict:dictList){
			String val = dict.getStr("id");
			sb.append("<option value='").append(val).append("'");
			if(StringUtils.NVL(defaultValue).equals(val)){
				sb.append(" selected");
			}
			sb.append(">").append(dict.getStr("text")).append("</option>");
		}
		return sb.toString();
	}

	/**
	 * 取得字典对应的下拉框
	 * @author dingkui
	 * @param dictEnum
	 * @param defaultValue
	 * @return
	 */
	public static Map<String,String> getOptions(String dictEnum){
		List<JSONMap> dictList = SpringHolder.getBean(DictCache.class).getDictList(dictEnum);
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		for(JSONMap dict:dictList){
			map2.put( dict.getStr("id"), dict.getStr("text"));
		}
		return map2;
	}

	public static void makeJson(String jsonSavePath) throws Exception{
		DictCache cache = SpringHolder.getBean(DictCache.class);
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
	
	private static String getDictJson(DictCache cache,String dictEnum){
		StringBuilder sb = new StringBuilder();
//		Map<String, DictItem> itemMap = 
		List<JSONMap> dictList = cache.getDictList(dictEnum);
		if(dictList.isEmpty()){
			return "";
		}
		//重置成miui组件所需格式
		//valueField	值字段	id
		//textField	文本显示字段	text
		Map<String, JSONMap> cbxItemMap = new LinkedHashMap<String, JSONMap>();
		for(JSONMap jm : dictList){
			JSONMap jsonMap = new JSONMap();
			String id = jm.getStr("id");
			jsonMap.put("id", id);
			jsonMap.put("text", jm.getStr("text"));
			cbxItemMap.put(id,jsonMap);
		}
		
		sb.append("_dL.push('"+dictEnum+"');");
		sb.append("var "+dictEnum+"Json=").append(JsonMapper.nonEmptyMapper().toJson(cbxItemMap));
		sb.append(";\n");
		return sb.toString();
	}
	
}
