package com.dlz.commbiz.sets.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.commbiz.sets.model.ProvinceAndCity;
import com.dlz.commbiz.sets.model.ProvinceAndCityCriteria;
import com.dlz.commbiz.sets.service.ProvinceAndCityService;
import com.dlz.common.base.controller.BaseController;
import com.dlz.common.util.criterias.Criterias;
import com.dlz.common.util.string.JacksonUtil;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.ICommService;

/**
 * 区域设置
 * 
 */
@Controller
@RequestMapping(value = "/conf/provinceandCity")
public class ProvinceAndCityController extends BaseController {
	@Autowired
	ProvinceAndCityService provinceAndCityService;
	@Autowired
	ICommService commService;

	/*
	 * 页面初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String init(Model m,Long pFid) {
		if(pFid==null){
			pFid=0l;
		}
		m.addAttribute("pFid",pFid);
		return "/conf/provinceandCity";
	}

	/*
	 * 取得列表信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Page list(HttpServletRequest request) throws Exception {
		ProvinceAndCityCriteria mic = Criterias.buildCriteria(ProvinceAndCityCriteria.class, request);
		return provinceAndCityService.pageByExample(mic);
	}

	/*
	 * 取得列表信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del/{pId}")
	public String del(@PathVariable(value = "pId") Long pId) throws Exception {
		ProvinceAndCityCriteria mic = new ProvinceAndCityCriteria();
		mic.getCurrentCriteria().andPFidEqualTo(pId);
		int i = provinceAndCityService.countByExample(mic);
		if (i > 0) {
			return "存在子目录，不能删除";
		}
		provinceAndCityService.deleteByPrimaryKey(pId);
		return "OK";
	}
	
	/*
	 * 添加或更新
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(String data) throws Exception {
		ProvinceAndCity[] pc = JacksonUtil.readValue(data, ProvinceAndCity[].class);
		for (int i = 0; i < pc.length; i++) {
			if(pc[i].getpId()!=null){
				provinceAndCityService.updateByPrimaryKeySelective(pc[i]);
			}else{
				pc[i].setpId(commService.getSeq(ProvinceAndCity.class));
				provinceAndCityService.insert(pc[i]);
			}
		}
		return "OK";
	}
	/*
	 * 添加或更新
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createJson")
	public String createJson() throws Exception {
		ProvinceAndCityCriteria mic = new ProvinceAndCityCriteria();
		mic.createCriteria().andPFlagEqualTo(1l);
		mic.getPage().setOrderBy(" p_code asc");
		mic.getPage().setPageSize(5000);
		//List<ProvinceAndCity> list= (List<ProvinceAndCity>) provinceAndCityService.pageByExample(mic).getData();
		return "OK";
	}
	
	
}
