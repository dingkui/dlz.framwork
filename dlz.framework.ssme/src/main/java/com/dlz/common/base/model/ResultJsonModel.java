package com.dlz.common.base.model;

import java.util.HashMap;
import java.util.Map;

import com.dlz.common.util.xml.mapper.JsonMapper;
import com.dlz.framework.db.modal.Page;

/**
 * JSON交互模型
 */
public class ResultJsonModel extends HashMap<String, Object> {
	private static final long serialVersionUID = -5972679257248081155L;
	protected String tableName;

	protected String tableColums;

	protected Map<String, Object> m_i = new HashMap<String, Object>();

	public ResultJsonModel() {
		this.put("flag", true);
	}

	@SuppressWarnings("unchecked")
	public void addErr(String... args) {
		if (args == null) {
			this.put("flag", false);
			return;
		}
		if (args.length == 1) {
			addMsg(args[0]);
		} else {
			Map<String, String> error = (Map<String, String>) this.get("error");
			if (error == null) {
				error = new HashMap<String, String>();
				this.put("error", error);
			}
			error.put(args[0], args[1]);
		}
		this.put("flag", false);
	}

	public void addMsg(String message) {
		this.put("msg", message);
	}

	public void addPageInfo(Page pg) {
		this.put("pageSize", pg.getPageSize());
		this.put("pageNow", pg.getPageNow());
		this.put("pages", pg.getPages());
		this.put("total", pg.getCount());
		this.put("data", pg.getData());
	}

	@Override
	public String toString() {
		return JsonMapper.nonEmptyMapper().toJson(this);
	}

}
