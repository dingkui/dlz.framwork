package com.dlz.apps.sys.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.conver.impl.ClobConverter;
import com.dlz.framework.db.enums.CharsetNameEnum;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import org.slf4j.Logger;
import com.dlz.framework.ssme.db.service.FunOptService;

/**
 * @ClassName: MenuRolesCache
 * @Description: 菜单对应的角色cache
 */
@Component
public class MenuRolesCache extends AbstractCache<Long, String> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	FunOptService funOptService;

	public MenuRolesCache() {
		super(MenuRolesCache.class.getSimpleName());
		dbOperator = new DbOperator<Long, String>() {
			protected String getFromDb(Long id) {
				try {
					ParaMap pm = new ParaMap("select XMLAGG(XMLELEMENT(E, rf.role_id || ',')).EXTRACT('//text()').getclobval() as roles from T_P_ROLE_FUN_OPT rf where rf.fun_opt_id =#{memuId} ");
					pm.getConvert().addClassConvert(new ClobConverter(CharsetNameEnum.UTF8));
					pm.addPara("memuId", id);
					ResultMap map = commService.getMap(pm);
					String roles = map.getStr("roles");
					return roles.substring(0,roles.length()-1);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return null;
				}
			}
		};
	}

	/**
	 * 日志logger
	 */
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MenuRolesCache.class);

	
}
