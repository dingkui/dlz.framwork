package com.dlz.common.cache;

import org.springframework.stereotype.Component;

import com.dlz.common.bean.Role;
import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.logger.MyLogger;

@Component
public class RoleCache extends AbstractCache<Integer, Role>{
	protected final MyLogger logger = MyLogger.getLogger(getClass());
	public RoleCache() {
		super(RoleCache.class.getSimpleName());
		dbOperator=new DbOperator<Integer, Role>() {
			protected Role getFromDb(Integer role_id) {
				ParaMap pm = new ParaMap("select * from DS_ROLE where id=#{id}");
				pm.addPara("id", role_id);
				return commService.getBean(pm, Role.class);
			} 
		};
	}
}
