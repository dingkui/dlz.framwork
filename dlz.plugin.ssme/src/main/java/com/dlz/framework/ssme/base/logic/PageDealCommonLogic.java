package com.dlz.framework.ssme.base.logic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.ssme.base.model.ResultJsonModel;
import com.dlz.framework.ssme.util.web.Servlets;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.ICommService;

@SuppressWarnings("unchecked")
public class PageDealCommonLogic {
	@Autowired
	ICommService commService;

	private static boolean isEmputy(String str) {
		return str == null || "".equals(str);
	}

	public Page getPage(HttpServletRequest request) {
		Page pi = new Page();
		if (!isEmputy(request.getParameter("pageSize"))) {
			pi.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		}
		if (!isEmputy(request.getParameter("pageIndex"))) {
			pi.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
		}
		if (!isEmputy(request.getParameter("sortField"))) {
			pi.setSortField(request.getParameter("sortField"));
		}
		if (!isEmputy(request.getParameter("sortOrder"))) {
			pi.setSortOrder(request.getParameter("sortOrder"));
		}
		return pi;
	}

	public Map<String, Object> getParaMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(Servlets.getParametersStartingWith(request, "search_"));
		map.putAll(Servlets.getAttrbuteStartingWith(request, "search_"));
		//map.put("page", getPage(request));
		return map;
	}

	public void addErr(Map<String, Object> m, String... args) {
		if (args == null) {
			m.put("flag", false);
			return;
		}
		if (args.length == 1) {
			addMsg(m, args[0]);
		} else {
			Map<String, String> error = (Map<String, String>) m.get("error");
			if (error == null) {
				error = new HashMap<String, String>();
				m.put("error", error);
			}
			error.put(args[0], args[1]);
		}
		m.put("flag", false);
	}

	public void addMsg(Map<String, Object> m, String message) {
		m.put("msg", message);
	}

	public void addMsg(Map<String, Object> m, String name, String message) {
		m.put(name, message);
	}

	public ResultJsonModel createRsutlJson() {
		return new ResultJsonModel();
	}
}
