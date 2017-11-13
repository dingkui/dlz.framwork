package com.dlz.apps.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.ssme.db.model.BaseSet;
import com.dlz.framework.ssme.db.model.BaseSetCriteria;
import com.dlz.framework.ssme.db.service.BaseSetService;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.dlz.framework.util.StringUtils;

/**
 * 系统管理-基础信息设置业务处理类
 * 描述：
 * 2014-5-9 
 */
@Controller
@RequestMapping(value="/rbac/baseSet")
public class BaseSetController {
	private static Logger logger = LoggerFactory.getLogger(BaseSetController.class);

	@Autowired
	BaseSetService baseSetService;
	
	/*
	 * 初始页面
	 * @return
	 * @2014-5-9 
	 */
	@RequestMapping()
	public String init(){
		return "sys/rbac/baseMsgSet";
	}
	
	/*
	 * 查询列表
	 * @return
	 * @2014-5-9 
	 */
	@ResponseBody
	@RequestMapping(value="list")
	public Page list(HttpServletRequest request){
		try {
			BaseSetCriteria bsc = Criterias.buildCriteria(BaseSetCriteria.class, request);
			String k = request.getParameter("key");
			if(StringUtils.isNotEmpty(k)){
				k="%"+k+"%";
				bsc.or().andBaseValueLike(k);
				bsc.or().andBaseCodeLike(k);
				bsc.or().andRemarksLike(k);
			}
			return baseSetService.pageByExample(bsc);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/*
	 * 添加-保存
	 * @param baseSet
	 * @return
	 * @2014-5-9 
	 */
	@ResponseBody
	@RequestMapping(value="/addNew")
	public boolean addNew(@ModelAttribute BaseSet baseSet){
		try {
			ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			baseSet.setCreateId(loginUser.getUserId());
			baseSet.setCreateName(loginUser.getUserName());
			baseSet.setCreateTime(new Date());
			baseSetService.insertSelective(baseSet);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
	/*
	 * 根据编号获取编辑对象
	 * @param baseCode
	 * @return
	 * @2014-5-9 
	 */
	@ResponseBody
	@RequestMapping(value="/getEdit")
	public BaseSet getEdit(HttpServletRequest request){
		try {
			String baseCode = request.getParameter("baseCode");
			BaseSet baseSet = baseSetService.selectByPrimaryKey(baseCode);
			return baseSet;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/*
	 * 添加编码之前验证输入的编码是否存在
	 * @param baseCode
	 * @return
	 * @2014-5-10 
	 */
	@ResponseBody
	@RequestMapping(value="/checkExits")
	public boolean checkExits(HttpServletRequest request){
	   try {
	  	 String baseCode = request.getParameter("baseCode");
			BaseSetCriteria bsc = new BaseSetCriteria();
			bsc.createCriteria().andBaseCodeEqualTo(baseCode);
			List<BaseSet> list = baseSetService.selectByExample(bsc);
			return list.isEmpty();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
	/*
	 * 编辑-保存
	 * @param baseSet
	 * @return
	 * @2014-5-9 
	 */
	@ResponseBody
	@RequestMapping(value="/edit")
	public boolean edit(@ModelAttribute BaseSet baseSet){
		try {
			ShiroUser loginUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			baseSet.setUpdateId(loginUser.getUserId());
			baseSet.setUpdateName(loginUser.getUserName());
			baseSet.setUpdateTime(new Date());
			baseSetService.updateByPrimaryKeySelective(baseSet);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
}
