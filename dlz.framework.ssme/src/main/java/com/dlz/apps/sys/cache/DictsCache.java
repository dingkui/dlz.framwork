package com.dlz.apps.sys.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.cache.DictCache;
import org.slf4j.Logger;
import com.dlz.framework.ssme.db.model.Dicts;
import com.dlz.framework.ssme.db.model.DictsCriteria;
import com.dlz.framework.ssme.db.service.DictsService;

@Component
public class DictsCache extends AbstractCache<Long, List<Dicts>> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	DictsService dictsService;
	@Autowired
	DictCache dictCache;

	public DictsCache() {
		super(DictsCache.class.getSimpleName());
		dbOperator = new DbOperator() {
			protected List<Dicts> getFromDb(Long id) {
				DictsCriteria dcc = new DictsCriteria();
				dcc.getCurrentCriteria().andPidEqualTo(id);
				dcc.setOrderByClause("ord");
				try {
					return dictsService.selectByExample(dcc);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return null;
				}
			}
		};
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void remove(Long key) {
		super.remove(key);
		dictCache.removeDicts(key);
	}

	/**
	 * 日志logger
	 */
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DictsCache.class);

}
