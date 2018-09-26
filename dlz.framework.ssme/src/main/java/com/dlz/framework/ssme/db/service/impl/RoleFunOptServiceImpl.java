package com.dlz.framework.ssme.db.service.impl;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.RoleFunOptMapper;
import com.dlz.framework.ssme.db.model.RoleFunOptKey;
import com.dlz.framework.ssme.db.service.RoleFunOptService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleFunOptServiceImpl extends BaseServiceImpl<RoleFunOptKey, RoleFunOptKey> implements RoleFunOptService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	/**
	 * 系统日志
	 */
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(RoleFunOptServiceImpl.class);
	
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
