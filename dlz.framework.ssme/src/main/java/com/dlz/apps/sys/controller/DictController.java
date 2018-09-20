package com.dlz.apps.sys.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.cache.DictCache;
import com.dlz.framework.db.cache.bean.DictItem;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.db.model.ComboBoxModel;
import com.dlz.framework.ssme.db.model.Dict;
import com.dlz.framework.ssme.db.model.DictCriteria;
import com.dlz.framework.ssme.db.model.DictDetail;
import com.dlz.framework.ssme.db.model.DictDetailCriteria;
import com.dlz.framework.ssme.db.service.DictDetailService;
import com.dlz.framework.ssme.db.service.DictService;
import com.dlz.framework.ssme.util.criterias.Criterias;

@Controller
@RequestMapping(value = ControllerConst.ADMIN+"/rbac/dict")
public class DictController {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(DictController.class);

	@Autowired
	private DictService dictService;
	@Autowired
	private DictDetailService dictDetailService;
	@Autowired
	private DictCache dictCache = null;

	@RequestMapping()
	public String init() {
		return "sys/rbac/dict";
	}

	/*
	 * 显示-字典列表
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public Page list(HttpServletRequest request) {
		try {
			DictCriteria dc = Criterias.buildCriteria(DictCriteria.class, request);
			dc.setOrderByClause(" DICT_ID ASC ");
			return dictService.pageByExample(dc);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/dictDetail/{dictCode}")
	public List<JSONMap> dictDetail(
			@PathVariable(value = "dictCode") String dictCode) {
		List<JSONMap> list = dictCache.getDictList(dictCode);
		return list;
	}

	/*
	 * 返回除掉部分字典值的字典信息
	 * 
	 * @param dictCode
	 * 
	 * @return
	 * 
	 * @2014-3-10 
	 */
	@ResponseBody
	@RequestMapping(value = "/removePart/{dictCode}/{dictValue}")
	public List<ComboBoxModel> removePart(
			@PathVariable(value = "dictCode") String dictCode,
			@PathVariable(value = "dictValue" ) String dictValue) {
		try {
			DictDetailCriteria ddc = new DictDetailCriteria();
			ddc.createCriteria().andDictIdEqualTo(dictCode);
			List<DictDetail> listDictDetail = dictDetailService
					.selectByExample(ddc);
			String[] str = dictValue.split("\\,");
			List<ComboBoxModel> list = new ArrayList<ComboBoxModel>();
			for (DictDetail dictDetail : listDictDetail) {
				for (int i = 0; i < str.length; i++) {
					if (!str[i].equals(dictDetail.getDictParamValue())) {
						ComboBoxModel combobox = new ComboBoxModel();
						combobox.setId(dictDetail.getDictParamValue());
						combobox.setText(dictDetail.getDictParamName());
						list.add(combobox);
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/*
	 * 返回除掉部分字典值的字典信息
	 * 
	 * @param dictCode
	 * 
	 * @return
	 * 
	 * @2014-3-10 
	 */
	@ResponseBody
	@RequestMapping(value = "/only/{dictCode}/{dictValue}")
	public List<ComboBoxModel> onlyPart(
			@PathVariable(value = "dictCode") String dictCode,
			@PathVariable(value = "dictValue" ) String dictValue) {
		try {
			DictDetailCriteria ddc = new DictDetailCriteria();
			ddc.createCriteria().andDictIdEqualTo(dictCode);
			List<DictDetail> listDictDetail = dictDetailService
					.selectByExample(ddc);
			String[] str = dictValue.split("\\,");
			List<ComboBoxModel> list = new ArrayList<ComboBoxModel>();
			for (DictDetail dictDetail : listDictDetail) {
				for (int i = 0; i < str.length; i++) {
					if (str[i].equals(dictDetail.getDictParamValue())) {
						ComboBoxModel combobox = new ComboBoxModel();
						combobox.setId(dictDetail.getDictParamValue());
						combobox.setText(dictDetail.getDictParamName());
						list.add(combobox);
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/*
	 * 检查-字典信息是否合法
	 */
	@ResponseBody
	@RequestMapping(value = "check")
	public boolean check(String dictId) {
		try {
			DictCriteria dc = new DictCriteria();
			dc.createCriteria().andDictIdEqualTo(dictId);
			return dictService.selectByExample(dc).isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 添加
	 */
	@ResponseBody
	@RequestMapping(value = "create")
	public boolean create(@RequestBody Dict dict) {
		try {
			dictService.insertSelective(dict);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 修改（之前）-根据字典编号查询出字典
	 */
	@ResponseBody
	@RequestMapping(value = "{dictId}")
	public Dict get(@PathVariable("dictId") String dictId) {
		try {
			return dictService.selectByPrimaryKey(dictId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	/*
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public boolean update(@RequestBody Dict dict) {
		try {
			dictService.updateByPrimaryKeySelective(dict);
			dictCache.reload(dict.getDictName());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 删除-根据字典编号删除字典明细
	 */
	@ResponseBody
	@RequestMapping(value = "delete/{dictId}/{dictName}")
	public boolean delete(@PathVariable("dictId") String dictId) {
		try {
			dictService.deleteByPrimaryKey(dictId);
			dictCache.remove(dictId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	/*
	 * 根据字典编号查询字典的明细
	 */
	@ResponseBody
	@RequestMapping(value = "detail/{dictId}")
	public List<ComboBoxModel> getDetail(@PathVariable("dictId") String dictId) {
		com.dlz.framework.db.cache.bean.Dict dict = dictCache.get(dictId);
		List<ComboBoxModel> comboboxList = new ArrayList<ComboBoxModel>();
		Collection<DictItem>  list=dict.getItemMap().values();
		for (DictItem dictDetail : list) {
			ComboBoxModel model = new ComboBoxModel();
			model.setId(dictDetail.getValue());
			model.setText(dictDetail.getText());
			comboboxList.add(model);
		}
		return comboboxList;
	}
}
