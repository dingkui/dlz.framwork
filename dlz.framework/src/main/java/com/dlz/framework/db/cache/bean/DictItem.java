package com.dlz.framework.db.cache.bean;

/**
 * 字典明细
 * 
 * @author dk 2017-06-15
 *
 */
public class DictItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private String id; // ID
	private String dictid;// 字典ID
	private String value;// 字典值
	private String text;// 字典中文
	private Integer sort;//排序
	private Integer del=0;//是否删除
	private String memo;//注释

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}