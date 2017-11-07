package com.dlz.commbiz.dict.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.commbiz.dict.model.Dicts;
import com.dlz.commbiz.dict.model.DictsCriteria;
import com.dlz.commbiz.dict.service.DictsService;
import com.dlz.framework.cache.AbstractCache;

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
	private static Logger logger = LoggerFactory.getLogger(DictCacheSsme.class);
	
}
