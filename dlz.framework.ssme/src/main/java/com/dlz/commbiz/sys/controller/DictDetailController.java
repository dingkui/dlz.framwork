package com.dlz.commbiz.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.commbiz.dict.model.DictDetail;
import com.dlz.commbiz.dict.model.DictDetailCriteria;
import com.dlz.commbiz.dict.model.DictDetailKey;
import com.dlz.commbiz.dict.service.DictDetailService;
import com.dlz.common.util.criterias.Criterias;
import com.dlz.common.util.string.JacksonUtil;
import com.dlz.framework.db.modal.Page;

@Controller
@RequestMapping(value = "/rbac/dictDetail")
public class DictDetailController {
	private static Logger logger = LoggerFactory
			.getLogger(DictDetailController.class);

	@Autowired
	private DictDetailService dictDetailService;

	@RequestMapping()
	public String init(String dictId, Model model) {
		model.addAttribute("dictId", dictId);
		return "/rbac/dict_detail";
	}

	/*
	 * 显示-字典明细
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public Page list(HttpServletRequest request) {
		try {
			DictDetailCriteria ddc = Criterias.buildCriteria(
					DictDetailCriteria.class, request);
			return dictDetailService.pageByExample(ddc);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "getDictDetailList") public Page
	 * getDictDetailList(HttpServletRequest request){ DictDetailCriteria ddc =
	 * Criterias.buildCriteria(DictDetailCriteria.class, request); return
	 * dictDetailService.pageByExample(ddc); }
	 */
	/*
	 * 添加
	 */
	@ResponseBody
	@RequestMapping(value = "create")
	public boolean create(@RequestBody DictDetail dictDetail) {
		try {
			dictDetailService.insertSelective(dictDetail);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/*
	 * 修改（之前）-根据字典编号查询出字典明细
	 */
	@ResponseBody
	@RequestMapping(value = "{dictId}/{dictParamValue}/{dictParamName}")
	public DictDetailKey get(@PathVariable("dictId") String dictId,
			@PathVariable("dictParamValue") String dictParamValue,
			@PathVariable("dictParamName") String dictParamName) {
		try {
			DictDetailKey key = new DictDetailKey();
			key.setDictId(dictId);
			key.setDictParamValue(dictParamValue);
			DictDetailKey d = dictDetailService.selectByPrimaryKey(key);
			return d;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public boolean update(@RequestBody DictDetail dictDetail) {
		try {
			dictDetailService.updateByPrimaryKeySelective(dictDetail);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/*
	 * 添加或更新
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(String data) throws Exception {
		DictDetail[] pc = JacksonUtil.readValue(data, DictDetail[].class);
		for (int i = 0; i < pc.length; i++) {
			DictDetailKey key = new DictDetailKey();
			key.setDictId(pc[i].getDictId());
			key.setDictParamValue(pc[i].getDictParamValue());
			DictDetail d = dictDetailService.selectByPrimaryKey(key);
			if (d == null) {
				if(pc[i].getDictParamStatus()==null){
					pc[i].setDictParamStatus("1");
				}
				dictDetailService.insert(pc[i]);
			} else {
				dictDetailService.updateByPrimaryKey(pc[i]);
			}
		}
		return "OK";
	}

	/*
	 * 删除-根据字典编号删除字典明细
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{dictId}/{dictParamValue}")
	public String delete(@PathVariable("dictId") String dictId,
			@PathVariable("dictParamValue") String dictParamValue) {
		try {
			DictDetailKey key = new DictDetailKey();
			key.setDictId(dictId);
			key.setDictParamValue(dictParamValue);

			dictDetailService.deleteByPrimaryKey(key);
			return "ok";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "删除错误";
		}
	}
}
