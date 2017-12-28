package com.dlz.apps.sys.cache;

import java.util.List;

import com.dlz.framework.logger.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.ssme.db.model.Dicts;
import com.dlz.framework.ssme.db.model.DictsCriteria;
import com.dlz.framework.ssme.db.service.DictsService;

@Component
public class DictsCache extends AbstractCache<Long, List<Dicts>> {
	@Autowired
	DictsService dictsService;
		public DictsCache() {
			super(DictsCache.class.getSimpleName());
			dbOperator=new DbOperator<Long, List<Dicts>>() {
				protected List<Dicts> getFromDb(Long id) {
					DictsCriteria dcc = new DictsCriteria();
					dcc.getCurrentCriteria().andPidEqualTo(id);
					dcc.setOrderByClause("ord");
					try {
						return dictsService.selectByExample(dcc);
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
						return null;
					}
				} 
			};
		}

	/**
	 * 日志logger
	 */
	private static MyLogger logger = MyLogger.getLogger(DictCacheSsme.class);
	
}
