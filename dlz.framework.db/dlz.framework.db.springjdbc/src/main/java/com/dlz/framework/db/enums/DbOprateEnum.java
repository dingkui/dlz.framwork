package com.dlz.framework.db.enums;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.modal.BaseParaMap;

public enum DbOprateEnum {
	eq("dbn = #{key}"),
	lt("dbn < #{key}"),//小于
	le("dbn <= #{key}"),//小于等于
	gt("dbn < #{key}"),//大于
	ge("dbn >= #{key}"),//大于等于
	ne("dbn <> #{key}"),//不等于
	in("dbn in (${key})"),
	like("dbn like #{key}"),
	lk("dbn like #{key}"),
	notLike("dbn not like #{key}"),
	nl("dbn not like #{key}"),
	likeLeft("dbn like #{key}"),
	ll("dbn like #{key}"),
	likeRight("dbn like #{key}"),
	lr("dbn like #{key}"),
	between("dbn between #{key1} and #{key2}"),//BETWEEN 值1 AND 值2
	bt("dbn between #{key1} and #{key2}"),//BETWEEN 值1 AND 值2
	notbetween("dbn not between #{key1} and #{key2}"),//BETWEEN 值1 AND 值2,
	nb("dbn not between #{key1} and #{key2}");//BETWEEN 值1 AND 值2,
	private String condition;
	
	DbOprateEnum(String condition) {
		this.condition = condition;
	}

	public static String getConditionByKey(String key,Object value, BaseParaMap para){
		String op="eq";
		String dbn=key;
		if(key.startsWith("$")){
			int i = key.indexOf("_");
			SystemException.isTrue(i==-1,"参数名有误："+key);
			op=key.substring(1,i);
			dbn=key.substring(i);
		}
		try{
			DbOprateEnum oprateEnum = DbOprateEnum.valueOf(op);
			return oprateEnum.getCondition(dbn,value,para);
		}catch (Exception e){
			SystemException.isTrue(true,"匹配符有误："+op);
		}
		return null;
	}


	public String getCondition(String dbn,Object value, BaseParaMap para){
		try{
			String result = condition;
			switch (this) {
				case between:
				case bt:
				case notbetween:
				case nb:
					Object[] array = ValUtil.getArray(value);
					para.addPara(this.toString()+"1_"+dbn,array[0]);
					para.addPara(this.toString()+"2_"+dbn,array[1]);
					result = result.replace("key1", this.toString()+"1_"+dbn);
					result = result.replace("key2", this.toString()+"2_"+dbn);
					result = result.replaceAll("^dbn", ConvertUtil.str2Clumn(dbn));
					return result;
				case like:
				case lk:
				case nl:
				case notLike:
					value = "%"+value+"%";
					break;
				case likeLeft:
				case ll:
					value = value+"%";
					break;
				case likeRight:
				case lr:
					value = "%"+value;
					break;
			}
			para.addPara(this.toString()+"_"+dbn,value);
			result = result.replace("key", this.toString()+"_"+dbn);
			result = result.replaceAll("^dbn", ConvertUtil.str2Clumn(dbn));
			return result;
		}catch (Exception e){
			SystemException.isTrue(true,"匹配符有误："+this.toString());
		}
		return null;
	}
}
