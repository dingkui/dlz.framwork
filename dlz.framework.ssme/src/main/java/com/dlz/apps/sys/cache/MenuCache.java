package com.dlz.apps.sys.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.db.model.FunOpt;
import com.dlz.framework.ssme.db.service.FunOptService;

@Component
public class MenuCache extends AbstractCache<Long, FunOpt> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	FunOptService funOptService;

	public MenuCache() {
		super(MenuCache.class.getSimpleName());
		dbOperator = new DbOperator<Long, FunOpt>() {
			protected FunOpt getFromDb(Long id) {
				try {
					return funOptService.selectByPrimaryKey(id);
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
	private static MyLogger logger = MyLogger.getLogger(MenuCache.class);

}
