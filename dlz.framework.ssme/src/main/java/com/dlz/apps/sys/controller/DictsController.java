package com.dlz.apps.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlz.apps.dict.cache.DictsCache;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.base.controller.BaseController;
import com.dlz.framework.ssme.db.model.Dicts;
import com.dlz.framework.ssme.db.model.DictsCriteria;
import com.dlz.framework.ssme.db.service.DictsService;
import com.dlz.framework.ssme.db.service.RbacService;
import com.dlz.framework.ssme.util.criterias.Criterias;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.MapUtil;
import com.dlz.framework.util.StringUtils;
@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/dicts")
public class DictsController extends BaseController {
	@Autowired
	ICommService commService;
	@Autowired
	DictsService dictsService;
	@Autowired
	RbacService rbacService;
	@Autowired
	DictsCache dictsCache;

	/*
	 * 列表首页
	 */
	@RequestMapping("")
	public String init() {
		return "/rbac/dicts";
	}

	/*
	 * 取得列表信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Page list(HttpServletRequest request, Page page) throws Exception {
		DictsCriteria mic = Criterias.buildCriteria(DictsCriteria.class, request);
		return dictsService.pageByExample(mic);
	}
	/*
	 * 取得列表信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listAll")
	public List listAll(HttpServletRequest request, Page page) throws Exception {
		DictsCriteria mic = Criterias.buildCriteria(DictsCriteria.class, request);
		if(!StringUtils.isEmpty(request.getParameter("code"))){
			mic.getCurrentCriteria().addCriterion("code like '"+request.getParameter("code")+"%'");
		}
		mic.setOrderByClause("pid,ord");
		return dictsService.selectByExample(mic);
	}
	
	/*
	 * 取得单条信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfo/id")
	public Dicts getInfo(@PathVariable(value = "id") Long id) throws Exception {
		return dictsService.selectByPrimaryKey(id);
	}
	
	/*
	 * 添加或更新(多个)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdates")
	public String addOrUpdates(String data) throws Exception {
		Dicts[] items = JacksonUtil.readValue(data, Dicts[].class);
		for (int i = 0; i < items.length; i++) {
			addOrUpdate(items[i]);
		}
		return "OK";
	}
	
	/*
	 * 添加或更新（单条）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public String addOrUpdate(Dicts dicts) throws Exception {
		if (dicts.getId() != null) {
			dictsService.updateByPrimaryKeySelective(dicts);
		} else {
			Dicts pDicts=dictsService.selectByPrimaryKey(dicts.getPid());
			dicts.setCode(rbacService.getCode(pDicts==null?"":pDicts.getCode(), "code", "T_B_DICTS"));
			dicts.setId(commService.getSeq(Dicts.class));
			dicts.setIsLeaf(1l);
			dictsService.insert(dicts);
			ParaMap pm = new ParaMap("key.dicts.upisleaf");
			pm.addPara("id", dicts.getPid());
			pm.addPara("isLeaf", 0l);
			commService.excuteSql(pm);
		}
		dictsCache.remove(dicts.getPid());
		return "OK";
	}
	
	
	/*
	 * 批量删除
	 */
	@ResponseBody
	@RequestMapping(value = "/dels")
	public String dels(String data) throws Exception {
		Dicts[] pc = JacksonUtil.readValue(data, Dicts[].class);
		for (int i = 0; i < pc.length; i++) {
			del(pc[i].getId());
		}
		return "OK";
	}
	/*
	 * 删除单条
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable(value = "id") Long id) throws Exception {
		DictsCriteria dcc = new DictsCriteria();
		dcc.createCriteria().andPidEqualTo(id);
		int i =dictsService.countByExample(dcc);
		if(i>0){
			return "存在子节点，请删除子节点后再删除";
		}
		
		Dicts d = dictsService.selectByPrimaryKey(id);
		dictsService.deleteByPrimaryKey(id);
		ParaMap pm = new ParaMap("key.dicts.upisleaf");
		pm.addPara("id", d.getPid());
		pm.addPara("pid", d.getPid());
		pm.addPara("isLeaf", 1l);
		commService.excuteSql(pm);
		dictsCache.remove(d.getPid());
		return "OK";
	}
	
	/**
	 * 获取分类树
	 * @author  dk: 
	 * @param id  父节点ID
	 * @param rootId 根节点ID  与数据库中的模块和商品的根节点ID一致
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({  "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/getDicts/{rootId}")
	public List<Map> getDictsTree(Long id,@PathVariable(value = "rootId")Long rootId,boolean showAll,HttpServletRequest request) throws Exception {
		if(rootId==null){
			return null;
		}
		List<Map> catTree = new ArrayList<Map>();
		add2Map(catTree,id==null?rootId:id,showAll);
		String nullStr=request.getParameter("nullStr");
		if(StringUtils.isNotEmpty(nullStr)){
			Map data = new HashMap();
			String nullVal=request.getParameter("nullVal");
			data.put("id", StringUtils.isEmpty(nullVal)?"000000":nullVal);
			data.put("text", nullStr);
			catTree.add(0, data);
		}
		return catTree;
	}
	private void add2Map(List<Map> catTree,Long id,boolean showAll) throws Exception{
		List<Dicts> treeData = dictsCache.get(id);
		if(!CollectionUtils.isEmpty(treeData)){
			for(Dicts cat : treeData){
				Map data = MapUtil.convertBean(cat);
				data.put("text", cat.getName());
				data.remove("name");
				catTree.add(data);
				if(showAll && 0==cat.getIsLeaf()){
					add2Map(catTree,cat.getId(),showAll);
				}else{
					data.put("isLeaf", cat.getIsLeaf());
				}
			}
		}
	}
	
}
