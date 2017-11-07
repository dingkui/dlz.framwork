package com.dlz.commbiz.sys.rbac.service.impl;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.RoleFunOptMapper;
import com.dlz.commbiz.sys.rbac.model.RoleFunOptKey;
import com.dlz.commbiz.sys.rbac.service.RoleFunOptService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleFunOptServiceImpl extends BaseServiceImpl<RoleFunOptKey, RoleFunOptKey> implements RoleFunOptService {
	/**
	 * 系统日志
	 */
	private static Logger logger = LoggerFactory.getLogger(RoleFunOptServiceImpl.class);
	
	@Autowired
	public void setMapper(RoleFunOptMapper mapper) {
		super.mapper = mapper;
	} 
	
	 
	public int insertRoleFunOpt(Integer roleId,Integer[] detailId){
		int m =0;
		   try {
				for (int i = 0; i < detailId.length; i++) {
					RoleFunOptKey rfok = new RoleFunOptKey();
					rfok.setRoleId(roleId);
					rfok.setFunOptId(Long.valueOf(detailId[i]));
					this.insertSelective(rfok);
					m=m+1;
				}
				return m;
		} catch (Exception e) {
			logger.error("保存绑定资源信息失败.");
			logger.error(e.getMessage(),e);
			return m;
		}
	}
	
 }
