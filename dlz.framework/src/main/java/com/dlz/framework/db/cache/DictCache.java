package com.dlz.framework.db.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.cache.bean.Dict;
import com.dlz.framework.db.cache.bean.DictItem;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.ParaMap;
import org.slf4j.Logger;

@Component
public class DictCache extends AbstractCache<String, Dict>{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private static String DICTS="dicts";
	private static String SPLIT_STR="_";
	
	public Dict getDicts(Long pid){
		return get(DICTS+SPLIT_STR+pid);
	}
	public void removeDicts(Long pid){
		remove(DICTS+SPLIT_STR+pid);
	}
	
	public DictCache() {
		super(DictCache.class.getSimpleName());
		dbOperator=new DbOperator() {
			protected Dict getFromDb(String dicdCode) {
				String[] codes=dicdCode.split(SPLIT_STR);
				
//				private Integer id; // ID
//				private String code;// 字典code
//				private String name;// 字典名
//				private String memo;// 字典名
//				private String type;// 字典名
//				private Integer enable;// 是否有效 1删除 0正常
//				private Integer source;// 字段来源 1:字典表 2:sql
				
				Dict dict=null;
				if(DICTS.equals(codes[0])){
					//多级字典
					dict=new Dict();
					dict.setSqltext("key.dict.getDictsItem");
				}else{
					ParaMap pm = new ParaMap("key.dict.getDict");
					pm.addPara("code", codes[0]);
					dict= commService.getBean(pm, Dict.class);
					if(dict==null){
						return null;
					}
				}
				
//				private Integer id; // ID
//				private Integer dictid;// 字典ID
//				private String value;// 字典值
//				private String text;// 字典中文
//				private Integer sort;//排序
//				private Integer del=0;//是否删除
				String sql=dict.getSqltext();
				if(sql==null||(!sql.startsWith("key") && !sql.matches("\\s*(?i)select .+ from .+ where .*"))){
					sql="key.dict.getDictItem";
				}
				
				ParaMap pm2 = new ParaMap(sql);
				pm2.addPara("dictid", dict.getId());
				for(int i=0;i<codes.length;i++){
					pm2.addPara("p"+i, codes[i]);
				}
				List<DictItem> dictItems= commService.getBeanList(pm2, DictItem.class);
				for(DictItem item:dictItems){
					dict.getItemMap().put(item.getValue(), item);
				}
				return dict;
			} 
		};
	}
	
	/**
	 * 取得字典明细列表
	 * @param dictCode
	 * @return
	 */
	public List<JSONMap> getDictList(String dictCode){
		Dict dict=get(dictCode);
		if(dict.getEnable()==0){
			return null;
		}
		List<JSONMap> list=new ArrayList<JSONMap>();
		for(DictItem item:dict.getItemMap().values()){
			if(item.getDel()!=1){
				JSONMap jm=new JSONMap();
				jm.put("id", item.getId());
				jm.put("text", item.getText());
				list.add(jm);
			}
		}
		return list;
	}
	
	public String getVal(String dictCode,String value) {
		Dict dict=get(dictCode);
		if(dict==null){
			throw new DbException("字典转换错误，字典【"+dictCode+"】未定义",1004);
		}
		DictItem item=dict.getItemMap().get(String.valueOf(value));
		if(item==null){
			logger.warn("字典转换错误，字典【"+dictCode+"】中的值【"+value+"】未找到！");
			return value;
		}
		return item.getText();
	}
}
