//package com.dlz.commbiz.sys.rbac.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.dlz.common.base.service.impl.BaseServiceImpl;
//import com.dlz.commbiz.sys.rbac.dao.MenueMapper;
//import com.dlz.commbiz.sys.rbac.dao.MenueTreeMapper;
//import com.dlz.commbiz.sys.rbac.dao.RbacMapper;
//import com.dlz.commbiz.sys.rbac.model.Menue;
//import com.dlz.commbiz.sys.rbac.model.MenueTreeKey;
//import com.dlz.commbiz.sys.rbac.service.MenueService;
//
///**
// * 说明：菜单管理模块service实现类
// * 
// * 2013-8-25
// */
//@Service
//@Transactional(rollbackFor=Exception.class)
//@SuppressWarnings("rawtypes")
//public class MenueServiceImpl extends BaseServiceImpl<Menue, Integer> implements
//		MenueService {
//	
//	@Autowired
//	public void setMapper(MenueMapper mapper) {
//		super.mapper = mapper;
//	}
//
//	@Autowired
//	private RbacMapper rbacMapper = null;
//	
//	@Autowired
//	private MenueTreeMapper menueTreeMapper = null;
//	
//	@Override
//	public List<Map<String, Object>> getMenueMapByUserId(Map map) throws Exception{
//		return rbacMapper.getMenueMapByUserId(map);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Map<String, Object>> getSubMenusByUserId(Map map) throws Exception{
//		List<Map<String, Object>> menuMap = rbacMapper.getSubMenusByUserId(map); 
//		for(int i=0;i<menuMap.size();i++){
//			map.put("menueId", menuMap.get(i).get("MENUE_ID"));
//			List<Map<String, Object>> subMenuMap=rbacMapper.getSubMenusByUserId(map);
//			if(!subMenuMap.isEmpty() && subMenuMap.size()>0){
//				menuMap.addAll(i+1, subMenuMap);
//			}
//		}
//		return menuMap;
//	}
//
//	@Override
//	public boolean saveMenue(Menue menue) {
//			MenueTreeKey menuTree = new MenueTreeKey();
//			menuTree.setMenueId(menue.getMenueId());
//			menuTree.setcMenueId(menue.getMenueId());
//			menueTreeMapper.insert(menuTree);
//			menueTreeMapper.insertParMenueTree(menue);
//			mapper.insert(menue);
//			return true;
//	}
//
//	@Override
//	public boolean deleteMenue(int menueId) {
//			menueTreeMapper.deleteTreeByChild(menueId);
//			mapper.deleteByPrimaryKey(menueId);
//			
//			return true;
//	}
//
//	@Override
//	public List<Map<String, Object>> getRootMenueByUserId(Long userId) {
//		return rbacMapper.getRootMenueByUserId(userId);
//	}
//
//	@Override
//	public Integer getToCheckApplyNum(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//
//}
