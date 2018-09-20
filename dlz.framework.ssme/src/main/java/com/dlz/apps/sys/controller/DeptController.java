package com.dlz.apps.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.ControllerConst;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.conver.impl.ClobConverter;
import com.dlz.framework.db.enums.CharsetNameEnum;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.db.model.Dept;
import com.dlz.framework.ssme.db.model.DeptCriteria;
import com.dlz.framework.ssme.db.model.DeptUser;
import com.dlz.framework.ssme.db.model.DeptUserCriteria;
import com.dlz.framework.ssme.db.service.DeptService;
import com.dlz.framework.ssme.db.service.DeptUserService;
import com.dlz.framework.ssme.db.service.RbacService;
import com.dlz.framework.ssme.db.service.UserService;
import com.dlz.framework.ssme.shiro.ShiroUser;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;

/**
 * MenueController
 * 菜单管理模块相关功能操作实现
 * 说明：
 * 
 * 2013-8-25
 */
@Controller
@RequestMapping(ControllerConst.ADMIN+"/rbac/dept")
public class DeptController {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(DeptController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	ICommService commService;
	@Autowired
	DeptService deptService;
	@Autowired
	RbacService rbacService;
	@Autowired
	DeptUserService deptUserService;
	
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping()
	public String init(Model model, HttpServletRequest request) {
		try {
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			String dCode=request.getParameter("dcode");
			if(!StringUtils.isEmpty(dCode)){
				if(!SecurityUtils.getSubject().hasRole("1")){
					SearchParaMap pm = new SearchParaMap("t_p_dept","d_code");
					pm.addCondition("d_manager_id", "=", user.getUserId());
					pm.addCondition("d_code", "=", dCode);
					dCode=(String)commService.getColum(pm);
				}
			}else{
				if(SecurityUtils.getSubject().hasRole("1")){
					dCode="";
				}else{
					SearchParaMap pm = new SearchParaMap("t_p_dept","d_code");
					pm.addCondition("d_manager_id", "=", user.getUserId());
					pm.createPage().setOrderBy("d_code asc");
					dCode=(String)commService.getColum(pm);
				}
			}
			if(dCode==null){
				dCode="-1";
			}
			model.addAttribute("dcode", dCode);
			return "sys/rbac/dept";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping("/edit")
	public String edit(Model m,HttpServletRequest request) {
		try {
			ParaMap pMap=new ParaMap("SELECT * FROM T_P_DEPT WHERE D_ID=#{did}");
			pMap.addPara("did", request.getParameter("did"));
			m.addAttribute("dept",commService.getBean(pMap, JSONMap.class));
			return "sys/rbac/deptEdit";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/*
	 * 菜单初始页面-首页
	 */
	@RequestMapping("/editAccount")
	public String editAccount(Model m,HttpServletRequest request) {
		try {
			ParaMap pMap=new ParaMap("select * from t_p_dept_account where deptId=#{did}");
			pMap.addPara("did", request.getParameter("did"));
			pMap.getConvert().addClassConvert(new ClobConverter(CharsetNameEnum.UTF8));
			JSONMap deptAccount=commService.getBean(pMap, JSONMap.class);
			if(deptAccount==null){
				deptAccount=new JSONMap();
				deptAccount.put("deptid", request.getParameter("did"));
			}
			m.addAttribute("deptAccount",deptAccount);
			return "sys/rbac/editAccount";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	/*
	 * 添加或更新（单条）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(Dept dept) throws Exception {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (dept.getdId() != null) {
			Dept oldD=deptService.selectByPrimaryKey(dept.getdId());
			deptService.updateByPrimaryKeySelective(dept);
			if(!String.valueOf(oldD.getdManagerId()).equals(String.valueOf(dept.getdManagerId()))){
				DeptUserCriteria dc = new DeptUserCriteria();
				//删除老部门负责人(修改)-start lfeng.li 2018/5/30
				//dc.createCriteria().andDuUIdEqualTo(dept.getdManagerId()).andDuDIdEqualTo(dept.getdId());
				dc.createCriteria().andDuUIdEqualTo(oldD.getdManagerId()).andDuDIdEqualTo(oldD.getdId());
				//删除老部门负责人(修改)-end
				deptUserService.deleteByExample(dc);
				DeptUser  du = new DeptUser();
				du.setDuUId(dept.getdManagerId());
				du.setDuDId(dept.getdId());
				du.setDuId(commService.getSeq(DeptUser.class));
				du.setDuAddUserId(user.getUserId());
				du.setDuAddTime(new Date());
				du.setDuDuty("1");
				deptUserService.insert(du);
			}
		} else {
			dept.setdCode(rbacService.getCode(dept.getdCode(), "d_code", "t_p_dept"));
			dept.setdId(commService.getSeq(Dept.class));
			dept.setdFid(dept.getdFid()==null?0l:dept.getdFid());
			dept.setdScFlg("0");
			deptService.insert(dept);
			if(dept.getdManagerId()!=null){
				DeptUser  du = new DeptUser();
				du.setDuUId(dept.getdManagerId());
				du.setDuDId(dept.getdId());
				du.setDuId(commService.getSeq(DeptUser.class));
				du.setDuAddUserId(user.getUserId());
				du.setDuAddTime(new Date());
				du.setDuDuty("1");//部门主管
				deptUserService.insert(du);
			}
		}
		return "OK";
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public List<Dept> getDepts(HttpServletRequest request) throws Exception{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		DeptCriteria dc = new DeptCriteria();
		String dCode=request.getParameter("dcode");
		if(!StringUtils.isEmpty(dCode)){
			dc.createCriteria().andDCodeLike(dCode+"%");
		}
		if(!SecurityUtils.getSubject().hasRole("1")){
			SearchParaMap pm = new SearchParaMap("t_p_dept","d_code");
			pm.addCondition("d_manager_id", "=", user.getUserId());
			if(!StringUtils.isEmpty(dCode)){
				pm.addCondition("d_code", "=", dCode);
				dCode=(String)commService.getColum(pm);
				if(dCode==null){
					return new ArrayList<Dept>();
				}
			}else{
				pm.createPage().setOrderBy("d_code asc");
				dCode=(String)commService.getColum(pm);
				if(dCode==null){
					return new ArrayList<Dept>();
				}
				dc.createCriteria().andDCodeLike(dCode+"%");
			}
		}
		dc.setOrderByClause("d_code asc");
		List<Dept> list = deptService.selectByExample(dc);
		list.forEach(item->item.setdIdName(item.getdName()+"("+item.getdId()+")"));
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="getDeptsMember")
	public List<ResultMap> getDeptsMember(HttpServletRequest request) throws Exception{
		String dCode=request.getParameter("dCode");
		ParaMap pm = new ParaMap("key.dept.getDeptAndUsers",new Page(1,5000,"id","asc"));
		pm.addPara("dCode", dCode);
		return commService.getMapList(pm);
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public Map<String, Object>  add() throws Exception{
		Map<String, Object> m = new  HashMap<String, Object>();
		return m;
	}
	@ResponseBody
	@RequestMapping(value="/del/{id}")
	public String del(@PathVariable(value="id")Long id) throws Exception {
		ParaMap pm = new ParaMap("key.dept.searchUser");
		Dept dept = deptService.selectByPrimaryKey(id);
		pm.addPara("dCode", dept.getdCode());
		int cnt = commService.getCnt(pm);
		if(cnt>0){
			return "请删除部门成员后再删除部门！";
		}
		deptService.deleteByPrimaryKey(id);
		return "ok";
	}
	
	/**
	 * 取得部门已经绑定的用户
	 * @param request
	 * @param page
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getBindUsers")
	public Page getBindUsers(HttpServletRequest request, Page page,Long dId) throws Exception {
		ParaMap pm = new ParaMap("key.dept.searchUser", page);
		pm.getConvert().addDateConvert(DateFormatEnum.DateTimeStr);
		pm.addPara("userName", request.getParameter("userName"));
		pm.addPara("dCode", request.getParameter("dCode"));
		pm.addPara("dId", request.getParameter("dId"));
		return commService.getPage(pm);
	}
	/**
	 * 添加用户跳转页面
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUsers")
	public String addUsers(HttpServletRequest request,Model m,Long dId) throws Exception {
		m.addAttribute("dId", dId);
		m.addAttribute("addZg",request.getParameter("addZg"));
		return "sys/rbac/userListCheck";
	}
	/**
	 * 保存用户信息
	 * @param m
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveUsers")
	public String saveUsers(String data) throws Exception {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		DeptUser[] items = JacksonUtil.readValue(data, DeptUser[].class);
		List msg=new ArrayList();
		for (int i = 0; i < items.length; i++) {
		  ParaMap pMap=new ParaMap("select count(1) count from t_p_dept_user du where exists (select d_id from t_p_dept where d_id = du.du_d_id and  d_code like '03%')   and du.du_u_id = #{dUId}");
		  pMap.addPara("dUId", items[i].getDuUId());
		  JSONMap isExist=commService.getMap(pMap);
		  if(isExist.getInt("count")>0){
			    msg.add(items[i].getDuUId());
			    continue;
		  }
		  if (items[i].getDuId() != null) {
				deptUserService.updateByPrimaryKeySelective(items[i]);
			} else {
				items[i].setDuId(commService.getSeq(DeptUser.class));
				items[i].setDuAddUserId(user.getUserId());
				items[i].setDuAddTime(new Date());
				deptUserService.insert(items[i]);
			}
		}
		
		if(msg.size()!=0){
			return msg.toString();
		}else{
			return "OK";
		}
	}
	
	/**
	 * 编辑用户
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/editUsers")
	public String editUsers(String data) throws Exception {
		JSONMap[] items = JacksonUtil.readValue(data, JSONMap[].class);
		if(items == null || items.length < 1){
			return "error";
		}
		for (int i = 0; i < items.length; i++) {
			for (JSONMap user : items) {
				UpdateParaMap uParaMap = new UpdateParaMap("t_p_dept_user");
				uParaMap.addSetValue("du_duty", user.getInt("duDuty"));
				uParaMap.addEqCondition("du_id", user.getInt("duId"));
				commService.excuteSql(uParaMap);
				
				uParaMap = new UpdateParaMap("ptn_user_info");
				uParaMap.addSetValue("price_level", user.getInt("priceLevel"));
				uParaMap.addEqCondition("id", user.getInt("duUId"));
				commService.excuteSql(uParaMap);
			}
		}
		return "OK";
	}
	/**
	 * 添加用户
	 * @param request
	 * @param dIds
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delUsers")
	public  String delUsers(HttpServletRequest request,String dIds) throws Exception {
		String dId[]=dIds.split(",");
		for(String id:dId){
			deptUserService.deleteByPrimaryKey(Long.parseLong(id));
		}
		return "OK";
	}
	
	/**
	 * 添加或更新部门账户信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateAccount")
	public String addOrUpdateAccount(Integer deptid,String bank,String accounts,String company,String alipay,String weixin) throws Exception {
		//1、查询该部门是否有银行账户信息
		ParaMap pMap=new ParaMap("select count(1) count from t_p_dept_account where deptId=#{deptId}");
		pMap.addPara("deptId", deptid);
		JSONMap isExist=commService.getMap(pMap);
		if(isExist.getInt("count")>0){
			//存在则更新数据
			UpdateParaMap uMap=new UpdateParaMap("t_p_dept_account");
			uMap.addSetValue("bank", bank);
			uMap.addSetValue("accounts", accounts);
			uMap.addSetValue("company", company);
			uMap.addSetValue("alipay", alipay);
			uMap.addSetValue("weixin", weixin);
			uMap.addEqCondition("deptid", deptid);
			commService.excuteSql(uMap);
		}else{
			//不存在则插入数据
			InsertParaMap iMap=new InsertParaMap("t_p_dept_account");
			int id=(int)commService.getSeq("seq_t_p_dept_account");
			iMap.addValue("id", id);
			iMap.addValue("deptid", deptid);
			iMap.addValue("bank", bank);
			iMap.addValue("accounts", accounts);
			iMap.addValue("company", company);
			iMap.addValue("alipay", alipay);
			iMap.addValue("weixin", weixin);
			commService.excuteSql(iMap);
		}
		return "ok";
	}
	   
}
